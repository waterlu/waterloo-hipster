package cn.lu.hipster.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 接口文档参数信息
 *
 * @author lutiehua
 * @date 2018/5/24
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocParamModel {

    /**
     * 参数名称
     */
    private String name;

    /**
     * 是否必须
     */
    private boolean required = false;

    /**
     * 参数类型
     */
    private String type;

    /**
     * 参数说明
     */
    private String desc;
}
