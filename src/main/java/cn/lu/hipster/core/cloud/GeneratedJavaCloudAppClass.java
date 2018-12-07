package cn.lu.hipster.core.cloud;

import cn.lu.hipster.core.GeneratedJavaAppClass;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.JavaClassModel;

/**
 * 生成Application类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaCloudAppClass extends GeneratedJavaAppClass {

    protected JavaClassModel model;

    private String fileName;

    public GeneratedJavaCloudAppClass(GeneratorParam generatorParam) {
        super(generatorParam);
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "cloud/app-cloud.ftl";
    }
}
