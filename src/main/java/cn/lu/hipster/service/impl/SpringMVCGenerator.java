package cn.lu.hipster.service.impl;

import cn.lu.hipster.core.*;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.TableInfo;
import org.springframework.stereotype.Component;

/**
 * @author lutiehua
 * @date 2017/11/14
 */
@Component
public class SpringMVCGenerator implements Generator {

    /**
     * 自动生成代码
     *
     * @param generatorParam
     */
    @Override
    public void generateCode(GeneratorParam generatorParam) throws Exception {
        for (TableInfo tableInfo : generatorParam.getTables()) {
            generateController(generatorParam, tableInfo);
            generateService(generatorParam, tableInfo);
            generateModel(generatorParam, tableInfo);
//            refreshEntity(generatorParam, tableInfo);
//            generateInsertListMapper(generatorParam, tableInfo);
        }

//        generateConfig(generatorParam);
    }

    private void generateController(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {
        // Controller
        GeneratedJavaControllerClass javaControllerClass = new GeneratedJavaControllerClass(generatorParam, tableInfo);
        javaControllerClass.generateFile();
    }

    private void generateService(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {
        // Service
        GeneratedJavaServiceClass javaServiceClass = new GeneratedJavaServiceClass(generatorParam, tableInfo);
        javaServiceClass.generateFile();

        // ServiceImpl
        GeneratedJavaServiceImplClass javaServiceImplClass = new GeneratedJavaServiceImplClass(generatorParam, tableInfo);
        javaServiceImplClass.generateFile();
    }

    private void generateModel(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {
        // DTO
        GeneratedJavaDTOClass javaDTOClass = new GeneratedJavaDTOClass(generatorParam, tableInfo);
        javaDTOClass.generateFile();

        // QueryParamDTO
        GeneratedJavaQueryClass javaQueryParamClass = new GeneratedJavaQueryClass(generatorParam, tableInfo);
        javaQueryParamClass.generateFile();

        // VO
        GeneratedJavaVOClass javaVOClass = new GeneratedJavaVOClass(generatorParam, tableInfo);
        javaVOClass.generateFile();
    }

//    private void generateConfig(GeneratorParam generatorParam) throws Exception {
//        // WebConfig
//        GeneratedJavaConfigClass javaConfigClass = new GeneratedJavaConfigClass(generatorParam, null);
//        javaConfigClass.generateFile();
//    }
//
//    private void generateInsertListMapper(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {
//        GeneratedJavaInsertListMapperClass insertListMapperClass = new GeneratedJavaInsertListMapperClass(generatorParam, tableInfo);
//        insertListMapperClass.generateFile();
//    }
}
