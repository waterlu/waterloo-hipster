package cn.lu.hipster.api;

import cn.lu.hipster.core.*;
import cn.lu.hipster.core.cloud.GeneratedJavaErrorCodeClass;
import cn.lu.hipster.model.GeneratorParam;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * 普通Spring项目生成器
 *
 * @author lu
 * @date 2018/5/25
 */
@Component
public class SpringBootGenerator implements Generator {

    /**
     * 自动生成工程文件
     *
     * @param generatorParam
     * @throws Exception
     */
    @Override
    public void generateCode(GeneratorParam generatorParam) throws Exception {
        // Application
        GeneratedJavaAppClass generatedJavaAppClass = new GeneratedJavaAppClass(generatorParam);
        generatedJavaAppClass.generateFile();

        // ApplicationTest
        GeneratedJavaAppTestClass javaAppTestClass = new GeneratedJavaAppTestClass(generatorParam);
        javaAppTestClass.generateFile();

        // POM
        GeneratedPomFile generatedPomFile = new GeneratedPomFile(generatorParam);
        generatedPomFile.generateFile();

        // Properties
        GeneratedAppPropFile appPropFile = new GeneratedAppPropFile(generatorParam);
        appPropFile.generateFile();

        GeneratedAppDevPropFile appDevPropFile = new GeneratedAppDevPropFile(generatorParam);
        appDevPropFile.generateFile();

        GeneratedAppTestPropFile appTestPropFile = new GeneratedAppTestPropFile(generatorParam);
        appTestPropFile.generateFile();

        GeneratedBootPropFile bootPropFile = new GeneratedBootPropFile(generatorParam);
        bootPropFile.generateFile();

        // log4j2
        GeneratedLog4j2File log4j2File = new GeneratedLog4j2File(generatorParam);
        log4j2File.generateFile();

        // Git ignore
        GeneratedGitIgnoreFile gitIgnoreFile = new GeneratedGitIgnoreFile(generatorParam);
        gitIgnoreFile.generateFile();

        // Doc Template
        GeneratedDocTemplateFile docTemplateFile = new GeneratedDocTemplateFile(generatorParam);
        docTemplateFile.generateFile();

        // JSON配置文件
        GeneratedHipsterConfigFile hipsterConfigFile = new GeneratedHipsterConfigFile();
        hipsterConfigFile.generateCode(generatorParam);
    }
}
