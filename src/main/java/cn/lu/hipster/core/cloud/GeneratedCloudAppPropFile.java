package cn.lu.hipster.core.cloud;

import cn.lu.hipster.core.GeneratedAppPropFile;
import cn.lu.hipster.model.GeneratorParam;

/**
 * 生成application.properties文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedCloudAppPropFile extends GeneratedAppPropFile {

    public GeneratedCloudAppPropFile(GeneratorParam generatorParam) {
        super(generatorParam);
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "cloud/app-properties-cloud.ftl";
    }
}
