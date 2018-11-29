package cn.lu.hipster.core;


import cn.lu.hipster.model.DataModel;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.PropModel;

/**
 * 属性文件生成类的抽象基类
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public abstract class AbstractGeneratedPropFile extends AbstractGeneratedFile {

    protected PropModel model;

    public AbstractGeneratedPropFile(GeneratorParam generatorParam) {
        super(generatorParam);

        model = new PropModel();

        int port = generatorParam.getProjectInfo().getPort();
        int managePort = port + 10000;

        String serverAddress = generatorParam.getDatabaseInfo().getDbIP();
        Integer serverPort = generatorParam.getDatabaseInfo().getDbPort();
        String dbName = generatorParam.getDatabaseInfo().getDbName();

        String url = String.format("%s:%d/%s", serverAddress, serverPort, dbName);

        model.setDatasourceUrl(url);
        model.setDatasourceUsername(generatorParam.getDatabaseInfo().getDbUsername());
        model.setDatasourcePassword(generatorParam.getDatabaseInfo().getDbPassword());
        model.setManagePort(managePort);
        model.setServiceName(generatorParam.getProjectInfo().getName());
        model.setServicePort(port);
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
}
