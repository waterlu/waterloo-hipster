package cn.lu.hipster.core;


import cn.lu.hipster.model.GeneratorParam;

/**
 * 生成application-test.properties文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedAppTestPropFile extends AbstractGeneratedPropFile {

    private String fileName;

    public GeneratedAppTestPropFile(GeneratorParam generatorParam) {
        super(generatorParam);

        String rootDir = generatorParam.getPackageInfo().getProjectPath();
        String resourcePath = generatorParam.getPackageInfo().getResourcePath();
        fileName = rootDir + "/" + resourcePath + "/" + "application-test.properties";
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "app-test-properties.ftl";
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
