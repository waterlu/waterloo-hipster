package cn.lu.hipster.core;

import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.PackageInfo;
import cn.lu.hipster.model.TableInfo;
import com.google.common.base.CaseFormat;

/**
 * 根据模板生成Java类的基类
 *
 * @author lutiehua
 * @date 2017/11/16
 */
public abstract class AbstractGeneratedJavaTemplateClass extends AbstractGeneratedJavaClass {

    public AbstractGeneratedJavaTemplateClass(GeneratorParam generatorParam, TableInfo tableInfo) {
        super(generatorParam, tableInfo);

        // 基础表名
        String tableName = tableInfo.getName();

        // 基础类名
        String modelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

        // Package信息
        PackageInfo packageInfo = generatorParam.getPackageInfo();

        // 基础包名
        String basePackage = packageInfo.getBasePackage();

        // Entity类和对象名称
        String modelClassName = String.format("%s.%s.%s", basePackage, packageInfo.getEntityPackage(), modelName);
        modelClassName = super.parseJavaImportType(modelClassName);
        model.setModelClassName(modelClassName);
        String modelObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelClassName);
        model.setModelObjectName(modelObjectName);

        // Service类和对象名称
        String serviceClassName = String.format("%s.%s.%sService", basePackage, packageInfo.getServicePackage(), modelName);
        serviceClassName = super.parseJavaImportType(serviceClassName);
        model.setServiceClassName(serviceClassName);
        String serviceObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, serviceClassName);
        model.setServiceObjectName(serviceObjectName);

        // ServiceImpl类名称
        String serviceImplClassName = String.format("%s.%s.impl.%sServiceImpl", basePackage, packageInfo.getServicePackage(), modelName);
        serviceImplClassName = super.parseJavaImportType(serviceImplClassName);
        model.setServiceImplClassName(serviceImplClassName);

        // 持久化类
        String mapperClassName = String.format("%s.%s.%sMapper", basePackage, packageInfo.getDaoPackage(), modelName);
        mapperClassName = super.parseJavaImportType(mapperClassName);
        model.setMapperClassName(mapperClassName);
        String mapperObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, mapperClassName);
        model.setMapperObjectName(mapperObjectName);

        // VO类和对象名称
        String voClassName = String.format("%s.%s.%sVO", basePackage, packageInfo.getVoPackage(), modelName);
        voClassName = super.parseJavaImportType(voClassName);
        model.setVoClassName(voClassName);
        String voObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, voClassName);
        model.setVoObjectName(voObjectName);

        // DTO类和对象名称
        String dtoClassName = String.format("%s.%s.%sDTO", basePackage, packageInfo.getDtoPackage(), modelName);
        dtoClassName = super.parseJavaImportType(dtoClassName);
        model.setDtoClassName(dtoClassName);
        String dtoObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, dtoClassName);
        model.setDtoObjectName(dtoObjectName);

        // 查询参数类
        String paramClassName = String.format("%s.%s.%sQueryDTO", basePackage, packageInfo.getDtoPackage(), modelName);
        paramClassName = super.parseJavaImportType(paramClassName);
        model.setParamClassName(paramClassName);
        String paramObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, paramClassName);
        model.setParamObjectName(paramObjectName);

        // "/mapping"
        String classMapping = modelObjectName;
        model.setClassMapping(classMapping);

//        // 对象名
//        String objectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, this.getJavaClassName());
//        model.setObjectName(objectName);
    }
}