package cn.lu.hipster.core.cloud;

import cn.lu.hipster.core.AbstractGeneratedJavaTemplateClass;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.TableInfo;

/**
 * 错误码类
 *
 * @author lutiehua
 * @date 2018-12-05
 */
public class GeneratedJavaErrorCodeClass extends AbstractGeneratedJavaTemplateClass {

    public GeneratedJavaErrorCodeClass(GeneratorParam generatorParam, TableInfo tableInfo) {
        super(generatorParam, tableInfo);

        model.setBaseErrorCode(generatorParam.getProjectInfo().getPort());
    }

    @Override
    public String getJavaPackageName() {
        return generatorParam.getPackageInfo().getExceptionPackage();
    }

    @Override
    public String getJavaClassNamePostfix() {
        return "ErrorCode";
    }

    @Override
    public String getJavaClassRemarkPostfix() {
        return "";
    }

    @Override
    public String getTemplateName() throws Exception {
        return "error-code.ftl";
    }
}
