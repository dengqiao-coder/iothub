package com.lemon.exception;

/**
 * 访问被拒绝异常
 */
public class ForbiddenException extends Exception{
    public ForbiddenException(String message)
    {
        super(message);
    }


    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

}
