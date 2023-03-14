package com.ding.common.exception;

import com.ding.common.utils.json.ApiResult;
import com.ding.common.utils.json.StatusCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: mySpringBoot
 * @description: 全局异常处理
 * @author: tinTin
 * @create: 2019-04-24 17:49
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResult<String> handle(Exception e){
        log.error("全局异常信息:{}",e);
        return ApiResult.failed(StatusCodeEnum.FAILED.getCode(),StatusCodeEnum.FAILED.getMessage(),e.getMessage());
    }

    /**
     * 自定义服务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    public ApiResult handleValidException(ServiceException e) {
        log.error("异常信息:{}",e);
        return e.getApiResult();
    }


    /**
     * 参数为实体类
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResult handleValidException(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return ApiResult.failed(StatusCodeEnum.FAILED.getCode(),objectError.getDefaultMessage());
    }

    /**
     * Http请求消息序列化异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ApiResult messageExceptionHandler(HttpMessageNotReadableException e) {
        log.warn("http请求参数转换异常: "+ e.getMessage());
        return ApiResult.failed(StatusCodeEnum.PARAM.getCode(),StatusCodeEnum.PARAM.getMessage());
    }

}
