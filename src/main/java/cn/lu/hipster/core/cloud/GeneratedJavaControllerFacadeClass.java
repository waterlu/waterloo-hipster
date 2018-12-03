package cn.lu.hipster.core.cloud;

import cn.lu.hipster.consts.KeyType;
import cn.lu.hipster.core.AbstractGeneratedJavaTemplateClass;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.PackageInfo;
import cn.lu.hipster.model.TableInfo;
import com.google.common.base.CaseFormat;

/**
 * FeignClient类
 *
 * @author lutiehua
 * @date 2018-12-03
 */
public class GeneratedJavaControllerFacadeClass extends AbstractGeneratedJavaTemplateClass {

    public GeneratedJavaControllerFacadeClass(GeneratorParam generatorParam, TableInfo tableInfo) {
        super(generatorParam, tableInfo);

        // 基础表名
        String tableName = tableInfo.getName();

        // 基础类名
        String modelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

        // Package信息
        PackageInfo packageInfo = generatorParam.getPackageInfo();

        // 基础包名
        String basePackage = packageInfo.getBasePackage();

        // 服务名
        model.setServiceName(generatorParam.getProjectInfo().getName());

        // Fallback类名
        String fallbackClassName = String.format("%s.%s.%sFallback", basePackage, packageInfo.getFacadePackage(), modelName);
        fallbackClassName = super.parseJavaImportType(fallbackClassName);
        model.setFallbackClassName(fallbackClassName);

        // FeignConfig类
        String feignConfigClassName = String.format("%s.%s.FeignConfig", basePackage, packageInfo.getConfigPackage(), modelName);
        feignConfigClassName = super.parseJavaImportType(feignConfigClassName);
        model.setFeignConfigClassName(feignConfigClassName);

        String keyFieldName = tableInfo.getKey();
        if (null != keyFieldName) {
            keyFieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, keyFieldName);
            model.setKeyFieldName(keyFieldName);
        }
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
        return "Client";
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
            return "cloud/controller-facade-uuid.ftl";
        } else if (KeyType.KEY_TYPE_ID.equalsIgnoreCase(tableInfo.getKeyType())) {
            return "cloud/controller-facade-id.ftl";
        } else {
            throw new Exception("Unexpected key type = [" + tableInfo.getKeyType() + "]");
        }
    }
}
