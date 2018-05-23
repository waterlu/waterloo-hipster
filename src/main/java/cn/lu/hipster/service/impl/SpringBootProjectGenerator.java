package cn.lu.hipster.service.impl;

import cn.lu.hipster.core.*;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.service.ProjectGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生成SpringBoot项目
 *
 * @author lutiehua
 * @date 2017/11/10
 */
@Component("springBootGenerator")
public class SpringBootProjectGenerator implements ProjectGenerator {

    @Autowired
    private MybatisGenerator mybatisGenerator;

    @Autowired
    private SpringMVCGenerator springMVCGenerator;

    @Override
    public String generateProject(GeneratorParam generatorParam) throws Exception {
        // 生成项目
        generateProjectFile(generatorParam);

        // 生成CRUD
        mybatisGenerator.generateCode(generatorParam);

        // 生成Controller/Service/Model
        springMVCGenerator.generateCode(generatorParam);

        String message = String.format("成功生成项目到\"%s\"目录下", generatorParam.getPackageInfo().getProjectPath());
        return message;
    }

    private void generateProjectFile(GeneratorParam generatorParam) throws Exception {
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
    }


}