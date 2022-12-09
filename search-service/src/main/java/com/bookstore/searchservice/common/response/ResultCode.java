package com.bookstore.searchservice.common.response;

public enum ResultCode implements IErrorCode {
    SUCCESS(200, "Thành công"),
    FAILED(500, "Lỗi hệ thống"),

    BAD_REQUEST(400, "Có lỗi sảy ra, vui lòng thử lại"),
    VALIDATE_FAILED(404, "Xử lý không được thực hiện"),
    UNAUTHORIZED(401, "Phiên đăng nhập của bạn đã hết hạn"),
    FORBIDDEN(403, "Không có quyền");
    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}