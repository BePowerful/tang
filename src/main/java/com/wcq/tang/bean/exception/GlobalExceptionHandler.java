package com.wcq.tang.bean.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/10 14:43
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 判断网络请求是否为ajax
     *
     * @param req
     * @return
     */
    private boolean isAjax(HttpServletRequest req) {
        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        return (contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                || (acceptHeader != null && acceptHeader.contains("application/json"))
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith);
    }
    /**
     * 拦截业务异常，返回业务异常信息
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleBusinessError(BusinessErrorException ex, HttpServletRequest request) {
        logger.error("业务异常{}", ex);
        boolean isAjax = isAjax(request);
        if(isAjax){
            return ex.getMessage();
        }else{
            return publicFunc(ex.getCode().toString(),ex.getMessage());
        }
    }
    public ModelAndView publicFunc(String code,String msg){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorCode",code);
        modelAndView.addObject("errorMsg",msg);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
    /**
     * 空指针异常
     * @param ex NullPointerException
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleTypeMismatchException(NullPointerException ex,HttpServletRequest request) {
        logger.error("空指针异常，{}", ex);
        boolean isAjax = isAjax(request);
        if(isAjax){
            return "空指针异常了";
        }else {
            return publicFunc("500", "空指针异常了");
        }
    }

    /**
     * 缺少请求参数异常
     * @param ex HttpMessageNotReadableException
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handleHttpMessageNotReadableException(
            MissingServletRequestParameterException ex,HttpServletRequest request) {
        logger.error("缺少请求参数，{}", ex);
        boolean isAjax = isAjax(request);
        if(isAjax){
            return ex.getMessage();
        }else {
            return publicFunc("400", "缺少必要的请求参数");
        }
    }

    /**
     * 系统异常 预期以外异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleUnexpectedServer(Exception ex,HttpServletRequest request) {
        logger.error("系统异常：{}",ex);
        boolean isAjax = isAjax(request);
        if(isAjax){
            return "系统发生异常，请联系管理员";
        }else {
            return publicFunc("500", "系统发生异常，请联系管理员");
        }
    }

}
