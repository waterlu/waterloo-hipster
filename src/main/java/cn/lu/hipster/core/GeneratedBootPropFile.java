package cn.lu.hipster.core;


import cn.lu.hipster.model.GeneratorParam;

/**
 * 生成bootstrap.properties文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedBootPropFile extends AbstractGeneratedPropFile {

    private String fileName;

    public GeneratedBootPropFile(GeneratorParam generatorParam) {
        super(generatorParam);

        String rootDir = generatorParam.getPackageInfo().getProjectPath();
        String resourcePath = generatorParam.getPackageInfo().getResourcePath();
        fileName = rootDir + "/" + resourcePath + "/" + "bootstrap.properties";
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "boot-properties.ftl";
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
