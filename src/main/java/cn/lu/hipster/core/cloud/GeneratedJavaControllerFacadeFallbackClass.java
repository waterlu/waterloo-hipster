package cn.lu.hipster.core.cloud;

import cn.lu.hipster.consts.KeyType;
import cn.lu.hipster.core.AbstractGeneratedJavaTemplateClass;
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
public class GeneratedJavaControllerFacadeFallbackClass extends AbstractGeneratedJavaTemplateClass {

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

        // Facade类
        String facadeClassName = String.format("%s.%s.%sClient", basePackage, packageInfo.getFacadePackage(), modelName);
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
