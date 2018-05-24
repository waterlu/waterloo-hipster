package cn.lu.hipster.core;

import cn.lu.hipster.model.*;
import com.google.common.base.CaseFormat;
import com.google.common.base.Function;
import com.google.common.collect.Maps;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * 生成Query类
 *
 * @author lutiehua
 * @date 2017/09/26
 */
public class GeneratedJavaQueryClass extends AbstractGeneratedJavaDatabaseClass {

    /**
     * 构造函数
     *
     * @param generatorParam
     * @param tableInfo
     */
    public GeneratedJavaQueryClass(GeneratorParam generatorParam, TableInfo tableInfo) {
        super(generatorParam, tableInfo);

        List<String> queryFieldList = tableInfo.getQuery();

        // 使用Guava将List转为Map
        Map<String, String> queryFieldMap = Maps.uniqueIndex(queryFieldList.iterator(), new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String s) {
                return s;
            }
        });

        try {
            List<DBField> fieldList = getTableColumns();
            for (DBField field : fieldList) {

                // 只将设置为查询参数的字段添加到参数DTO类中
                if (!queryFieldMap.containsKey(field.getColumnName())) {
                    continue;
                }

                // 将数据库字段名转化为Java属性名，product_type => productType
                String fieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getColumnName());

                JavaField javaField = new JavaField();
                javaField.setName(fieldName);
                String fieldType = toJavaType(field.getTypeName());
                fieldType = super.parseJavaImportType(fieldType);
                javaField.setType(fieldType);
                javaField.setRemark(field.getRemarks());

                // 默认值（写入和更新有默认值，查询没有默认值）

                // Query参数不强制做非空校验
//                if(!field.isNullable()) {
//                    // 非空校验的注解
//                    javaField.getAnnotations().add(getNullValidationAnnotation(field));
//                }

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
        return "query-param.ftl";
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
        return "QueryDTO";
    }

    /**
     * 类说明后缀
     *
     * @return
     */
    @Override
    public String getJavaClassRemarkPostfix() {
        return "查询参数对象";
    }
}