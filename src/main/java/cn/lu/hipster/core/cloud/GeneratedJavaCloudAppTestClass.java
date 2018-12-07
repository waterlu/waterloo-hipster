package cn.lu.hipster.core.cloud;

import cn.lu.hipster.core.GeneratedJavaAppTestClass;
import cn.lu.hipster.model.GeneratorParam;

/**
 * 生成ApplicationTests类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaCloudAppTestClass extends GeneratedJavaAppTestClass {

    private String fileName;

    public GeneratedJavaCloudAppTestClass(GeneratorParam generatorParam) {
        super(generatorParam);
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "cloud/app-test-cloud.ftl";
    }
}
