package cn.lu.hipster.core;

import cn.lu.hipster.model.*;
import com.google.common.base.CaseFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成DTO类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaDTOClass extends AbstractGeneratedJavaDatabaseClass {

    private Map<String, String> ignoreFieldMap = new HashMap<>();

    /**
     * 构造函数
     *
     * @param generatorParam
     * @param tableInfo
     */
    public GeneratedJavaDTOClass(GeneratorParam generatorParam, TableInfo tableInfo) {
        super(generatorParam, tableInfo);

        // 忽略这些字段，不用生成到DTO对象中
        ignoreFieldMap.put("delete_flag", "delete_flag");
        ignoreFieldMap.put("create_time", "create_time");
        ignoreFieldMap.put("update_time", "update_time");

        try {
            List<DBField> fieldList = getTableColumns();
            for (DBField field : fieldList) {
                // 有默认值的基础字段，不用添加到DTO中
                if (ignoreFieldMap.containsKey(field.getColumnName())) {
                    continue;
                }

                // 主键字段自动赋值，不用添加到DTO中
                if (field.isPrimaryKey()) {
                    continue;
                }

                // 自增字段不用赋值，不用添加到DTO中
                if (field.isAutoIncrement()) {
                    continue;
                }

                JavaField javaField = new JavaField();
                // 将数据库字段名转化为Java属性名，product_type => productType
                String fieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getColumnName());
                javaField.setName(fieldName);
                String fieldType = toJavaType(field.getTypeName());
                fieldType = super.parseJavaImportType(fieldType);
                javaField.setType(fieldType);
                javaField.setRemark(field.getRemarks());

                // 不能为空，且没有默认值，增加非空校验
                String defaultValue = getDefaultValue(field);
                if(!field.isNullable() && null == defaultValue) {
                    // 非空校验的注解
                    javaField.getAnnotations().add(getNullValidationAnnotation(field, true));
                }

                // 字符串长度校验的注解
                JavaAnnotation annotation = getLenValidationAnnotation(field);
                if (null != annotation) {
                    javaField.getAnnotations().add(annotation);
                }

                // API文档的注解
                javaField.getAnnotations().add(getApiDocumentAnnotation(field));

                javaFieldList.add(javaField);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTemplateName() {
        return "dto.ftl";
    }

    /**
     * 包名
     *
     * @return
     */
    @Override
    public String getJavaPackageName() {
        return generatorParam.getPackageInfo().getDtoPackage();
    }

    /**
     * 类名后缀
     *
     * @return
     */
    @Override
    public String getJavaClassNamePostfix() {
        return "DTO";
    }

    /**
     * 类说明后缀
     *
     * @return
     */
    @Override
    public String getJavaClassRemarkPostfix() {
        return "参数对象";
    }
}