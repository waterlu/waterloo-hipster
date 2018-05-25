package cn.lu.hipster.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 接口返回数据结构
 *
 * @author lu
 * @date 2018/5/11
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DocResponseData<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String message = "";

    /**
     * 数据
     */
    private T data;
}
