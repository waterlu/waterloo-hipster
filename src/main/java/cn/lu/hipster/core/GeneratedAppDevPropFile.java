package cn.lu.hipster.core;

import cn.lu.hipster.model.GeneratorParam;

/**
 * 生成application-dev.properties文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedAppDevPropFile extends AbstractGeneratedPropFile {

    private String fileName;

    public GeneratedAppDevPropFile(GeneratorParam generatorParam) {
        super(generatorParam);

        String rootDir = generatorParam.getPackageInfo().getProjectPath();
        String resourcePath = generatorParam.getPackageInfo().getResourcePath();
        fileName = rootDir + "/" + resourcePath + "/" + "application-dev.properties";

        model.setProjectType(generatorParam.getProjectInfo().getProjectType());
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "app-dev-properties.ftl";
    }

    /**
     * 文件名称
     *
     * @return
     */
    @Override
    public String getFileName() {
        return fileName;
    }
}
