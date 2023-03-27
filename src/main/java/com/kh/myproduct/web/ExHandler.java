package com.kh.myproduct.web;

import com.kh.myproduct.web.exception.BizException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class ExHandler {
  @ExceptionHandler(BizException.class)
  public String BizExHandle(BizException e, Model model) {
    model.addAttribute("type", e.getClass().getSimpleName());
    model.addAttribute("msg", e.getMessage());
    return "err";
  }
  @ExceptionHandler(Exception.class)
  public String ExHandle(Exception e, Model model) {
    model.addAttribute("type", e.getClass().getSimpleName());
    model.addAttribute("msg", e.getMessage());
    return "err";
  }
}
