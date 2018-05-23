package cn.lu.hipster.model;

import lombok.Data;

/**
 * POM.XNL依赖信息
 *
 * @author lutiehua
 * @date 2017/09/22
 */
@Data
public class DependencyInfo {

    /**
     * 依赖项名称
     */
    private String name;

    /**
     * 依赖项组ID
     */
    private String groupId;

    /**
     * 依赖项ID
     */
    private String artifactId;

    /**
     * 依赖项版本
     */
    private String version;

}
