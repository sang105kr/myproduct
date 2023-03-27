package com.kh.myproduct.web;

import com.kh.myproduct.web.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class RestExHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BizException.class)
  public RestResponse<Object> BizExHandle(BizException e) {
    return RestResponse.createRestResponse(e.getCode(),e.getMessage(),null);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Exception.class)
  public RestResponse<Object> BizExHandle(Exception e) {
    return RestResponse.createRestResponse("99",e.getMessage(),null);
  }
}
