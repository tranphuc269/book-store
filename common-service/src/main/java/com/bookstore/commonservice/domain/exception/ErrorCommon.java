package com.bookstore.commonservice.domain.exception;

import org.springframework.http.HttpStatus;

public class ErrorCommon {
    /**
     * 400
     */
    public static final BusinessError INVALID_PARAMETERS = new BusinessError(400, "Invalid parameters", HttpStatus.BAD_REQUEST);
    /**
     * 401
     */

    /**
     * 403
     */
    public static final BusinessError FORBIDDEN_ERROR = new BusinessError(403, "You don not have any permissions to access this resource", HttpStatus.FORBIDDEN);
    /**
     * 404
     */
    public static final BusinessError EXAMPLE_NOT_FOUND_ERROR = new BusinessError(404, "Resource is not found", HttpStatus.NOT_FOUND);

    /**
     * 500
     */
    public static final BusinessError INTERNAL_SERVER_ERROR = new BusinessError(500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    /**
     * 405
     */

    /**
     * 415
     */


    private ErrorCommon() {
    }
}
