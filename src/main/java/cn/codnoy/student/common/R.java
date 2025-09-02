package cn.codnoy.student.common;

import lombok.Data;

/**
 * 通用返回结果封装类
 * 用于统一API接口返回格式，包含状态码、消息和数据
 *
 * @param <T> 数据类型泛型
 */
@Data
public class R<T> {
    /**
     * 状态码，200表示成功，其他表示失败
     */
    private int code;

    /**
     * 返回消息，描述操作结果
     */
    private String msg;

    /**
     * 返回数据，具体业务数据
     */
    private T data;

    /**
     * 成功返回结果
     *
     * @param data 业务数据
     * @param <T>  数据类型泛型
     * @return 封装的成功结果
     */
    public static <T> R<T> success(T data) {
        R<T> result = new R<>();
        result.setCode(200);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    /**
     * 错误返回结果
     *
     * @param code 错误状态码
     * @param msg  错误消息
     * @param <T>  数据类型泛型
     * @return 封装的错误结果
     */
    public static <T> R<T> error(int code, String msg) {
        R<T> result = new R<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
