package com.data.chain.base;

import lombok.Getter;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/3/7
 */
@Getter
public enum ResponseEnum {

    //返回值枚举
    SUCCESS(200, "操作成功！"),
    FAIL(-1, "失败！"),
    ERROR_400(400, "错误的请求！"),
    ERROR_404(404, "访问资源不存在！"),
    ERROR_500(500, "服务器异常！"),
    ERROR_403(403, "无访问权限"),
    ERROR_401(401, "账号密码错误！"),
    ERROR_402(402, "参数错误！"),
    ERROR_405(405, "未登录！token失效"),
    ERROR_406(406, "用户已停用"),
    ERROR_501(501, "数据异常！"),
    ERROR_601(601, "调用千帆{0}接口异常：{1}"),
    ERROR_602(602, "{}"); // 采用千帆异常处理结果
    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}