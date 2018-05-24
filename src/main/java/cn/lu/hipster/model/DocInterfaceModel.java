package cn.lu.hipster.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 接口文档接口信息
 *
 * @author lutiehua
 * @date 2018/5/24
 */
@Setter
@Getter
public class DocInterfaceModel {
    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口说明
     */
    private String desc;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求参数
     */
    private DocRequestModel request;

    /**
     * 返回数据
     */
    private DocResponseModel response;
}
