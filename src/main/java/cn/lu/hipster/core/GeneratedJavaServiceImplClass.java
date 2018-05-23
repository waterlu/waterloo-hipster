package cn.lu.hipster.core;

import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.TableInfo;
import com.google.common.base.CaseFormat;

/**
 * 生成Service实现类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaServiceImplClass extends AbstractGeneratedJavaTemplateClass {

    public GeneratedJavaServiceImplClass(GeneratorParam generatorParam, TableInfo tableInfo) {
        super(generatorParam, tableInfo);

        String keyFieldName = tableInfo.getKey();
        if (null != keyFieldName) {
            keyFieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, keyFieldName);
            model.setKeyFieldName(keyFieldName);
        }
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() throws Exception {
        return "service-impl-id.ftl";
    }

    /**
     * 包名
     *
     * @return
     */
    @Override
    public String getJavaPackageName() {
        return generatorParam.getPackageInfo().getServicePackage() + ".impl";
    }

    /**
     * 类名后缀
     *
     * @return
     */
    @Override
    public String getJavaClassNamePostfix() {
        return "ServiceImpl";
    }

    /**
     * 类说明后缀
     *
     * @return
     */
    @Override
    public String getJavaClassRemarkPostfix() {
        return "服务实现类";
    }
}
