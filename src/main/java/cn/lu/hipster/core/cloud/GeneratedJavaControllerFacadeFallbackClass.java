package cn.lu.hipster.core.cloud;

import cn.lu.hipster.consts.KeyType;
import cn.lu.hipster.core.AbstractGeneratedJavaClass;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.PackageInfo;
import cn.lu.hipster.model.TableInfo;
import com.google.common.base.CaseFormat;

/**
 *
 *
 * @author lutiehua
 * @date 2018-12-03
 */
public class GeneratedJavaControllerFacadeFallbackClass extends AbstractGeneratedJavaClass {

    public GeneratedJavaControllerFacadeFallbackClass(GeneratorParam generatorParam, TableInfo tableInfo) {
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
        int pos = modelClassName.lastIndexOf(".");
        if (pos > 0) {
            modelClassName = modelClassName.substring(pos + 1);
        }

        String modelObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelClassName);

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

        // Facade类
        String facadeClassName = String.format("%s.%s.%sClient", basePackage, packageInfo.getControllerPackage(), modelName);
        facadeClassName = super.parseJavaImportType(facadeClassName);
        model.setFacadeClassName(facadeClassName);
    }

    /**
     * 包名
     *
     * @return
     */
    @Override
    public String getJavaPackageName() {
        return generatorParam.getPackageInfo().getFacadePackage();
    }

    /**
     * 类名后缀
     *
     * @return
     */
    @Override
    public String getJavaClassNamePostfix() {
        return "Fallback";
    }

    /**
     * 类说明后缀
     *
     * @return
     */
    @Override
    public String getJavaClassRemarkPostfix() {
        return "";
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() throws Exception {
        if (KeyType.KEY_TYPE_UUID.equalsIgnoreCase(tableInfo.getKeyType())) {
            return "cloud/controller-facade-fallback-uuid.ftl";
        } else if (KeyType.KEY_TYPE_ID.equalsIgnoreCase(tableInfo.getKeyType())) {
            return "cloud/controller-facade-fallback-id.ftl";
        } else {
            throw new Exception("Unexpected key type = [" + tableInfo.getKeyType() + "]");
        }
    }

}
