package com.lemon.commonbase.exception;

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
