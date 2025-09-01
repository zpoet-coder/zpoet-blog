package cn.zpoet.blog.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    SUCCESS(200, "success"),
    BAD_REQUEST(400, "参数错误"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "无权限"),
    NOT_FOUND(404, "接口不存在"),
    INTERNAL_ERROR(500, "系统错误");

    private final Integer code;
    private final String message;
}
