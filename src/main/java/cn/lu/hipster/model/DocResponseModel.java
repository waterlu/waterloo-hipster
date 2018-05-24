package cn.lu.hipster.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 接口文档返回数据信息
 *
 * @author lutiehua
 * @date 2018/5/24
 */
@Setter
@Getter
public class DocResponseModel {

    private List<DocParamModel> result;

    private String sample;
}
