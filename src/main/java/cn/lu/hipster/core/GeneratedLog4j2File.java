package cn.lu.hipster.core;

import cn.lu.hipster.model.DataModel;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.Log4j2Model;

/**
 * 生成log4j2.xml文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedLog4j2File extends AbstractGeneratedFile {

    private Log4j2Model model;

    private String fileName;

    public GeneratedLog4j2File(GeneratorParam generatorParam) {
        super(generatorParam);

        String basePackageName = generatorParam.getPackageInfo().getBasePackage();
        String mapperPackageName = basePackageName + "." + generatorParam.getPackageInfo().getDaoPackage();

        model = new Log4j2Model();
        model.setName(generatorParam.getProjectInfo().getName());
        model.setMapperPackage(mapperPackageName);

        String rootDir = generatorParam.getPackageInfo().getProjectPath();
        String resourcePath = generatorParam.getPackageInfo().getResourcePath();
        fileName = rootDir + "/" + resourcePath + "/" + "log4j2.xml";
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "log4j2.ftl";
    }

    /**
     * 变量数据
     *
     * @return
     */
    @Override
    public DataModel getDataModel() {
        return model;
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
