package cn.lu.hipster.api;

import cn.lu.hipster.core.cloud.*;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.TableInfo;
import org.springframework.stereotype.Component;

/**
 * MVC代码生成器
 *
 * @author lutiehua
 * @date 2018-12-03
 */
@Component
public class SpringCloudMVCGenerator extends SpringMVCGenerator {

    @Override
    protected void generateController(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {

        // Client
        GeneratedJavaControllerFacadeClass controllerFacadeClass = new GeneratedJavaControllerFacadeClass(generatorParam, tableInfo);
        controllerFacadeClass.generateFile();

        // Controller
        GeneratedJavaControllerImplClass controllerImplClass = new GeneratedJavaControllerImplClass(generatorParam, tableInfo);
        controllerImplClass.generateFile();

        // Fallback
        GeneratedJavaControllerFacadeFallbackClass facadeFallbackClass = new GeneratedJavaControllerFacadeFallbackClass(generatorParam, tableInfo);
        facadeFallbackClass.generateFile();
    }

    @Override
    protected void generateModel(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {
        // DTO
        GeneratedJavaCloudDTOClass javaDTOClass = new GeneratedJavaCloudDTOClass(generatorParam, tableInfo);
        javaDTOClass.generateFile();

        // QueryParamDTO
        GeneratedJavaCloudQueryClass javaQueryParamClass = new GeneratedJavaCloudQueryClass(generatorParam, tableInfo);
        javaQueryParamClass.generateFile();

        // VO
        GeneratedJavaCloudVOClass javaVOClass = new GeneratedJavaCloudVOClass(generatorParam, tableInfo);
        javaVOClass.generateFile();
    }
}