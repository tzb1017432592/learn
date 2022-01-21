package com.example.test;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @author: zhangyupeng
 * @date: 2019-11-08 10:14
 * @description: the template of all response
 **/
@Data
public class ResponseTemplate<T> {

  private static final Logger logger = LoggerFactory.getLogger(ResponseTemplate.class);

  /**
   * @description 错误码
   */
  private Integer code;
  /**
   * @description 错误信息
   */
  private String message;
  /**
   * @description 成功 or 失败
   */
  private EnumZoo.Status status;
  /**
   * @description 时间戳
   */
  private Long timestamp;
  /**
   * @description 返回内容
   */
  private T content;

  public ResponseTemplate(Integer code, String message,
                          EnumZoo.Status status, T content) {
    this.code = code;
    this.message = message;
    this.status = status;
    this.timestamp = DateTimeUtil.getTimeInMillis();
    this.content = content;
  }


  /**
   * @description 成功且有返回内容
   * @param content
   * @return
   */
  public static ResponseTemplate<Object> success(Object content){
    ResponseTemplate<Object> response = new ResponseTemplate<>(EnumZoo.Status.SUCCESS.getStatusValue(), EnumZoo.Status.SUCCESS.getMessage(), EnumZoo.Status.SUCCESS, content);
    return response;
  }

  /**
   * @description 成功且有返回内容
   * @param content
   * @param message
   * @return
   */
  public static ResponseTemplate<Object> success(Object content, String message){
    ResponseTemplate<Object> response = new ResponseTemplate<>(EnumZoo.Status.SUCCESS.getStatusValue(),message, EnumZoo.Status.SUCCESS, content);
    return response;
  }

  /**
   * @description 成功且有返回内容
   * @param message
   * @return
   */
  public static ResponseTemplate<Object> success(String message){
    ResponseTemplate<Object> response = new ResponseTemplate<>(EnumZoo.Status.SUCCESS.getStatusValue(),message, EnumZoo.Status.SUCCESS, new ArrayList<>());
    return response;
  }

  /**
   * @description 成功且无返回内容
   * @return
   */
  public static ResponseTemplate<Object> success(){
    ResponseTemplate<Object> response = new ResponseTemplate<>(EnumZoo.Status.SUCCESS.getStatusValue(), EnumZoo.Status.SUCCESS.getMessage(), EnumZoo.Status.SUCCESS, new ArrayList<>());
    return response;
  }

  /**
   * @description 失败且知道失败类型
   * @param code
   * @param msg
   * @return
   */
  public static ResponseTemplate<Object> error(Integer code, String msg, EnumZoo.Status status){
    ResponseTemplate<Object> response = new ResponseTemplate<>(code, msg, status, new ArrayList<>());
    return response;
  }








}
