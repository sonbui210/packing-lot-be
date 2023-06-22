package com.sobu.parkinglot.payload.response;

import com.sobu.parkinglot.data.enums.ECodeError;
import com.sobu.parkinglot.data.enums.ECodeError;

public class MessageResponse<T> {

  private Integer msgCode;
  private String msgContent;
  private T result;

  public MessageResponse(Integer msgCode, String msgContent, T result) {
    this.msgCode = msgCode;
    this.msgContent = msgContent;
    this.result = result;
  }

  public MessageResponse(Integer msgCode, String msgContent) {
    this.msgCode = msgCode;
    this.msgContent = msgContent;
    this.result = null;
  }

  public MessageResponse(T result) {
    this.msgCode = ECodeError.SUCCESS.getCode();
    this.msgContent = "Thành công.";
    this.result = result;
  }

  public Integer getMsgCode() {
    return msgCode;
  }

  public void setMsgCode(Integer msgCode) {
    this.msgCode = msgCode;
  }

  public String getMsgContent() {
    return msgContent;
  }

  public void setMsgContent(String msgContent) {
    this.msgContent = msgContent;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
