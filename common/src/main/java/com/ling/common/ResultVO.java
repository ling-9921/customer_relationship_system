package com.ling.common;

/**
 * 通用结果类
 *
 * @param <T>
 * @author ling
 * @version 1.0
 * @date 2022/6/27
 */
public class ResultVO<T> {

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 附加数据
     */
    private T data;


    /**
     * 失败没有附加数据
     *
     * @param message
     * @return
     */
    public static ResultVO error(String message) {
        return new ResultVO<>(500, message);
    }

    /**
     * 失败有附加数据
     *
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO error(String message, T data) {
        return new ResultVO<>(500, message, data);
    }


    /**
     * 成功没有附加数据
     *
     * @param message
     * @return
     */
    public static ResultVO ok(String message) {
        return new ResultVO<>(200, message);
    }

    /**
     * 成功有附加数据
     *
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO ok(String message, T data) {
        return new ResultVO<>(200, message, data);
    }


    private ResultVO() {
    }

    private ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
