package com.kh.myproduct.dao;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
  /**
   * 등록
   * @param product 상품
   * @return 상품아이디
   */
  Long save(Product product);

  /**
   * 조회
   * @param productId 상품아이디
   * @return 상품
   */
  Optional<Product> findById(Long productId);

  /**
   * 수정
   * @param productId 상품아이디
   * @param product 상품
   * @return 수정된 레코드 수
   */
  int update(Long productId,Product product);

  /**
   * 삭제
   * @param productId 상품아이디
   * @return 삭제된 레코드 수
   */
  int delete(Long productId);

  /**
   * 목록
   * @return 상품목록
   */
  List<Product> findAll();

  /**
   * 상품존재유무
   * @param productId 상품아이디
   * @return 
   */
  boolean isExist(Long productId);
}
