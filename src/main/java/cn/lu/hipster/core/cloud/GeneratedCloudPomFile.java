package cn.lu.hipster.core.cloud;

import cn.lu.hipster.core.AbstractGeneratedFile;
import cn.lu.hipster.model.DataModel;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.PomModel;

/**
 * @author lutiehua
 * @date 2018-12-03
 */
public class GeneratedCloudPomFile extends AbstractGeneratedFile {

    private PomModel model;

    private String fileName;

    public GeneratedCloudPomFile(GeneratorParam generatorParam) {
        super(generatorParam);

        model = new PomModel();
        model.setProjectInfo(generatorParam.getProjectInfo());
        model.setDependencies(generatorParam.getDependencies());

        String rootDir = generatorParam.getPackageInfo().getProjectPath();
        fileName = rootDir + "/" + "pom.xml";

        model.setProjectType(generatorParam.getProjectInfo().getProjectType());
    }

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "cloud/pom-cloud.ftl";
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
