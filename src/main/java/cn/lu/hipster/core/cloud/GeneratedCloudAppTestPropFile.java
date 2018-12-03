package cn.lu.hipster.core.cloud;

import cn.lu.hipster.core.GeneratedAppTestPropFile;
import cn.lu.hipster.model.GeneratorParam;

/**
 * 生成application-test.properties文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedCloudAppTestPropFile extends GeneratedAppTestPropFile {

    public GeneratedCloudAppTestPropFile(GeneratorParam generatorParam) {
        super(generatorParam);
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "cloud/app-test-properties-cloud.ftl";
    }

}
