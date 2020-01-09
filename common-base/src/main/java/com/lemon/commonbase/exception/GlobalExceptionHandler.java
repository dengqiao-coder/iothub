package com.lemon.commonbase.exception;

import com.lemon.commonbase.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Object unauthorizedExceptionHandler(UnauthorizedException e) {
        log.error(e.getMessage(), e);
        return R.UNAUTHORIZED;
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    public Object forbiddenExceptionHandler(ForbiddenException e) {
        log.error(e.getMessage(), e);
        return R.FORBIDDEN;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public R fileSizeLimitExceptionHandler(MaxUploadSizeExceededException e) {
        log.error(e.getMessage(), e);
        return R.error("超过文件最大限制");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R defaultErrorHandler(Exception e) {
        log.error(e.getMessage(), e);
        return R.error("系统异常");
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (factory -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
            factory.addErrorPages(error404Page, error500Page, error401Page, error403Page);
        });
    }

}
