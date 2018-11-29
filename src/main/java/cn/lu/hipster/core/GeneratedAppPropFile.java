package cn.lu.hipster.core;


import cn.lu.hipster.model.DataModel;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.PropModel;

/**
 * 生成application.properties文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedAppPropFile extends AbstractGeneratedPropFile {

    private PropModel model;

    private String fileName;

    public GeneratedAppPropFile(GeneratorParam generatorParam) {
        super(generatorParam);

        model = new PropModel();

        int port = generatorParam.getProjectInfo().getPort();
        int managePort = port + 10000;

        String basePackageName = generatorParam.getPackageInfo().getBasePackage();
        String packageName = basePackageName + "." + generatorParam.getPackageInfo().getEntityPackage();

        model.setManagePort(managePort);
        model.setServiceName(generatorParam.getProjectInfo().getName());
        model.setServicePort(port);
        model.setEntityPackage(packageName);

        String rootDir = generatorParam.getPackageInfo().getProjectPath();
        String resourcePath = generatorParam.getPackageInfo().getResourcePath();
        fileName = rootDir + "/" + resourcePath + "/" + "application.properties";

        model.setProjectType(generatorParam.getProjectInfo().getProjectType());
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "app-properties.ftl";
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
