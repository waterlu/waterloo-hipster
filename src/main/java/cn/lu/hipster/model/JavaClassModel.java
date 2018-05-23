package cn.lu.hipster.model;

import cn.lu.hipster.consts.ProjectType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Java类数据模型
 *
 * @author lutiehua
 * @date 2017/09/26
 */
@Getter
@Setter
@NoArgsConstructor
public class JavaClassModel extends DataModel {

    /**
     * 包名
     */
    protected String packageName;

    /**
     * 类名
     */
    protected String className;

    /**
     * Fallback类名(Feign)
     */
    protected String fallbackClassName;

    /**
     * 类注释
     */
    protected String classRemark;

    /**
     * 注册到注册中心的服务名
     */
    protected String serviceName;

    /**
     * 需要引入的包
     */
    protected List<JavaImport> imports = new ArrayList<>();

    /**
     * 类成员变量
     */
    protected List<JavaField> fields = new ArrayList<>();

    /**
     * 类成员方法
     */
    protected List<JavaMethod> methods = new ArrayList<>();

    /**
     * 基础包名(用来查找)
     */
    protected String basePackage;

    /**
     * 持久化包名
     */
    protected String mapperPackage;

    /**
     * Controller中的映射名称
     */
    protected String classMapping;


    /**
     * 接口类名
     */
    protected String facadeClassName;

    /**
     * 实体类名
     */
    protected String modelClassName;

    /**
     * 实体类对象名
     */
    protected String modelObjectName;

    /**
     * 服务类名
     */
    protected String serviceClassName;

    /**
     * 服务实现类名
     */
    protected String serviceImplClassName;

    /**
     * 服务类对象名
     */
    protected String serviceObjectName;

    /**
     * 持久化类名
     */
    protected String mapperClassName;

    /**
     * 持久化类对象名
     */
    protected String mapperObjectName;

    /**
     * 更新参数类名
     */
    protected String dtoClassName;

    /**
     * 更新参数对象名
     */
    protected String dtoObjectName;

    /**
     * 查询参数类名
     */
    protected String paramClassName;

    /**
     * 查询参数对象名
     */
    protected String paramObjectName;

    /**
     * 返回结果类名
     */
    protected String voClassName;

    /**
     * 返回结果对象名
     */
    protected String voObjectName;

    /**
     * 主键字段名称(Controller用)
     */
    protected String keyFieldName;

    /**
     * 项目类型
     */
    protected String projectType = ProjectType.PROJECT_TYPE_BOOT;
}
