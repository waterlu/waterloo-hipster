package cn.lu.hipster.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 接口文档请求数据信息
 *
 * @author lutiehua
 * @date 2018/5/24
 */
@Setter
@Getter
public class DocRequestModel {

    private List<DocParamModel> params;

    private String sample;
}
