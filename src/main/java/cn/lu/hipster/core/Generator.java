package cn.lu.hipster.core;

import cn.lu.hipster.model.GeneratorParam;

/**
 * 通用接口
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public interface Generator {

    /**
     * 自动生成代码
     *
     * @param generatorParam
     * @throws Exception
     */
    void generateCode(GeneratorParam generatorParam) throws Exception;
}
