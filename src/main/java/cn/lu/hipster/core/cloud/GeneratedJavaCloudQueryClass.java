package cn.lu.hipster.core.cloud;

import cn.lu.hipster.core.GeneratedJavaQueryClass;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.TableInfo;

/**
 * @author lutiehua
 * @date 2018-12-03
 */
public class GeneratedJavaCloudQueryClass extends GeneratedJavaQueryClass {

    /**
     * 构造函数
     *
     * @param generatorParam
     * @param tableInfo
     */
    public GeneratedJavaCloudQueryClass(GeneratorParam generatorParam, TableInfo tableInfo) {
        super(generatorParam, tableInfo);
    }

    @Override
    public String getJavaPackageName() {
        return generatorParam.getPackageInfo().getFacadePackage();
    }

}
