package com.kh.myproduct.web;

import com.kh.myproduct.dao.Product;
import com.kh.myproduct.svc.ProductSVC;
import com.kh.myproduct.web.form.DetailForm;
import com.kh.myproduct.web.form.SaveForm;
import com.kh.myproduct.web.form.UpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor  // final멤버 필드를 매개값으로하는 생성자를 자동 생성
public class ProductController {

  private final ProductSVC productSVC;

//  public ProductController(ProductSVC productSVC) {
//    this.productSVC = productSVC;
//  }

  //등록양식
  @GetMapping("/add")
  public String saveForm(){

    return "product/saveForm";
  }

  //등록처리
  @PostMapping("/add")
  public String save(
//      @Param("pname") String pname,
//      @Param("quantity") Long quantity,
//      @Param("price") Long price
      @ModelAttribute SaveForm saveForm,
      RedirectAttributes redirectAttributes
      ){
//    log.info("pname={}, quantity={}, price={}",pname,quantity,price);
    log.info("saveForm={}",saveForm);
    //데이터 검증
    //등록
    Product product = new Product();
    product.setPname(saveForm.getPname());
    product.setQuantity(saveForm.getQuantity());
    product.setPrice(saveForm.getPrice());

    Long savedProductId = productSVC.save(product);
    redirectAttributes.addAttribute("id",savedProductId);
    return "redirect:/products/{id}/detail";
  }

  //조회
  @GetMapping("/{id}/detail")
  public String findById(
      @PathVariable("id") Long id,
      Model model
  ){
    Optional<Product> findedProduct = productSVC.findById(id);
    Product product = findedProduct.orElseThrow();

    DetailForm detailForm = new DetailForm();
    detailForm.setProductId(product.getProductId());
    detailForm.setPname(product.getPname());
    detailForm.setQuantity(product.getQuantity());
    detailForm.setPrice(product.getPrice());

    model.addAttribute("form",detailForm);
    return "product/detailForm";
  }

  //수정양식
  @GetMapping("/{id}/edit")
  public String updateForm(
      @PathVariable("id") Long id,
      Model model
  ){
    Optional<Product> findedProduct = productSVC.findById(id);
    Product product = findedProduct.orElseThrow();

    UpdateForm updateForm = new UpdateForm();
    updateForm.setProductId(product.getProductId());
    updateForm.setPname(product.getPname());
    updateForm.setQuantity(product.getQuantity());
    updateForm.setPrice(product.getPrice());

    model.addAttribute("form",updateForm);
    return "product/updateForm";
  }

  //수정
  @PostMapping("/{id}/edit")
  public String update(){
    return "redirect:/products/{id}/detail";
  }

  //삭제
  @GetMapping("/{id}/del")
  public String deleteById(){
    return "redirect:/products";
  }

  //목록
  @GetMapping
  public String findAll(){
    return "products/all";
  }

}
