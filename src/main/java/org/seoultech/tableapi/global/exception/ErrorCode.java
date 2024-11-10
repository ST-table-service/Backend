package org.seoultech.tableapi.global.exception;

public enum ErrorCode {
    // Common Errors (C001 ~ C999)
    INVALID_INPUT_VALUE(400, "C001", "Invalid input value"),
    INVALID_TYPE_VALUE(400, "C002", "Invalid type value"),
    RESOURCE_NOT_FOUND(404, "C003", "Resource not found"),
    METHOD_NOT_ALLOWED(405, "C004", "Method not allowed"),
    INTERNAL_SERVER_ERROR(500, "C005", "Server error"),
    ACCESS_DENIED(403, "C006", "Access is denied"),

    // Authentication & Authorization (A001 ~ A999)
    UNAUTHORIZED(401, "A001", "Unauthorized access"),
    INVALID_ROLE(403, "A002", "Invalid role for this operation"),
    TOKEN_EXPIRED(401, "A003", "Token has expired"),
    INVALID_TOKEN(401, "A004", "Invalid token"),

    // Menu Related (M001 ~ M999)
    MENU_NOT_FOUND(404, "M001", "Menu not found"),
    MENU_OPTION_NOT_FOUND(404, "M002", "Menu option not found"),
    MENU_NOT_AVAILABLE(400, "M003", "Menu is currently unavailable"),
    MENU_OPTION_NOT_AVAILABLE(400, "M004", "Menu option is currently unavailable"),
    INVALID_MENU_PRICE(400, "M005", "Invalid menu price"),
    DUPLICATE_MENU_NAME(400, "M006", "Menu name already exists"),

    // Order Related (O001 ~ O999)
    ORDER_NOT_FOUND(404, "O001", "Order not found"),
    INVALID_ORDER_STATUS(400, "O002", "Invalid order status"),
    ORDER_ALREADY_COMPLETED(400, "O003", "Order is already completed"),
    ORDER_CANCELLATION_DENIED(400, "O004", "Order cancellation is denied"),
    MINIMUM_ORDER_AMOUNT_NOT_MET(400, "O005", "Minimum order amount not met"),

    // Payment Related (P001 ~ P999)
    PAYMENT_FAILED(400, "P001", "Payment processing failed"),
    INSUFFICIENT_BALANCE(400, "P002", "Insufficient balance"),
    PAYMENT_ALREADY_PROCESSED(400, "P003", "Payment already processed"),
    INVALID_PAYMENT_METHOD(400, "P004", "Invalid payment method"),
    PAYMENT_AMOUNT_MISMATCHED(400, "P005", "Payment amount does not match order amount"),

    // Stamp & Coupon Related (S001 ~ S999)
    INSUFFICIENT_STAMPS(400, "S001", "Insufficient stamps for coupon"),
    STAMP_ALREADY_USED(400, "S002", "Stamp already used"),
    INVALID_STAMP_COUNT(400, "S003", "Invalid stamp count"),
    COUPON_NOT_FOUND(404, "S004", "Coupon not found"),
    COUPON_ALREADY_USED(400, "S005", "Coupon already used"),
    COUPON_EXPIRED(400, "S006", "Coupon has expired"),

    // Notice Related (N001 ~ N999)
    NOTICE_NOT_FOUND(404, "N001", "Notice not found"),
    UNAUTHORIZED_NOTICE_ACCESS(403, "N002", "Unauthorized access to notice"),
    INVALID_NOTICE_CONTENT(400, "N003", "Invalid notice content"),

    // Store Related (R001 ~ R999)
    STORE_NOT_FOUND(404, "R001", "Store not found"),
    STORE_CLOSED(400, "R002", "Store is currently closed"),
    UNAUTHORIZED_STORE_ACCESS(403, "R003", "Unauthorized access to store"),
    INVALID_STORE_OPERATION(400, "R004", "Invalid store operation");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
