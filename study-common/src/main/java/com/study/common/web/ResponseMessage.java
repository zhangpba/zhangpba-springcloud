package com.study.common.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * HttpRequest请求返回的最外层对象,用一种统一的格式返回给前端
 */
@ApiModel(description = "响应消息")
@Data
@AllArgsConstructor
public class ResponseMessage<T> {

    // 错误码
    @ApiModelProperty(value = "错误码")
    private Integer errCode;

    // 信息描述
    @ApiModelProperty(value = "信息描述")
    private String errMsg;

    // 详细错误信息
    @ApiModelProperty(value = "详细错误信息")
    private String errDetail;

    // 生成消息的服务名
    @ApiModelProperty(value = "生成消息的服务名")
    private String service;

    // 具体的信息内容
    @ApiModelProperty(value = "具体的信息内容")
    private T data;

    private Long requestId = 0L;

    public ResponseMessage() {

    }

    public ResponseMessage(Integer errCode, String errMsg, String service, T data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.service = service;
        this.data = data;
    }

    public ResponseMessage(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public static ResponseMessage success(Object object, String message) {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        responseMessage.setErrCode(0);// 成功
        responseMessage.setErrMsg(message);
        responseMessage.setData(object);
        responseMessage.setService("default");
        return responseMessage;
    }

    /**
     * 操作成功不返回消息
     *
     * @return
     */
    public static ResponseMessage success() {
        return success(null, null);
    }

    public static ResponseMessage success(Object obj) {
        return success(obj, null);
    }

    public static ResponseMessage success(String message) {
        return success(null, message);
    }


    public static ResponseMessage error(Integer errCode, String message) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setErrCode(errCode);
        responseMessage.setErrMsg(message);
        responseMessage.setService("study-city");
        return responseMessage;
    }

    public static ResponseMessage error(GeneralErrorCode generalErrorCode) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setErrCode(generalErrorCode.getCode());
        responseMessage.setErrMsg(generalErrorCode.getMessage());
        responseMessage.setService(generalErrorCode.getService());
        return responseMessage;
    }

    public static ResponseMessage error(String message) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setErrCode(-1);
        responseMessage.setErrMsg(message);
        return responseMessage;
    }

    public static ResponseMessage error(GeneralErrorCode generalErrorCode, Object data) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setErrCode(generalErrorCode.getCode());
        responseMessage.setErrMsg(generalErrorCode.getMessage());
        responseMessage.setService(generalErrorCode.getService());
        responseMessage.setData(data);
        return responseMessage;
    }

    public static ResponseMessage error(GeneralErrorCode generalErrorCode, String message) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setErrCode(generalErrorCode.getCode());
        responseMessage.setErrMsg(message);
        responseMessage.setService(generalErrorCode.getService());
        return responseMessage;
    }

    public static ResponseMessage error(GeneralErrorCode generalErrorCode, String message, Object data) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setErrCode(generalErrorCode.getCode());
        responseMessage.setErrMsg(message);
        responseMessage.setService(generalErrorCode.getService());
        responseMessage.setData(data);
        return responseMessage;
    }
}
