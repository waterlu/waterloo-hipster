package cn.lu.hipster.core.cloud;

import cn.lu.hipster.core.GeneratedAppDevPropFile;
import cn.lu.hipster.model.GeneratorParam;

/**
 * 生成application-dev.properties文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedCloudAppDevPropFile extends GeneratedAppDevPropFile {

    public GeneratedCloudAppDevPropFile(GeneratorParam generatorParam) {
        super(generatorParam);
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "cloud/app-dev-properties-cloud.ftl";
    }

}
