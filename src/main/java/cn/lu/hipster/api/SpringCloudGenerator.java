package cn.lu.hipster.api;

import cn.lu.hipster.core.*;
import cn.lu.hipster.core.cloud.*;
import cn.lu.hipster.model.GeneratorParam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lutiehua
 * @date 2018-11-30
 */
@Component
public class SpringCloudGenerator implements Generator {

    @Override
    public void generateCode(GeneratorParam generatorParam) throws Exception {
        List<AbstractGeneratedFile> generatedFileList = new ArrayList();

        // POM.xml
        generatedFileList.add(new GeneratedCloudPomFile(generatorParam));

        // Git ignore
        generatedFileList.add(new GeneratedGitIgnoreFile(generatorParam));

        // Application
        generatedFileList.add(new GeneratedJavaCloudAppClass(generatorParam));

        // ApplicationTest
        generatedFileList.add(new GeneratedJavaCloudAppTestClass(generatorParam));

        // application.properties
        generatedFileList.add(new GeneratedCloudAppPropFile(generatorParam));

        // application-dev.properties
        generatedFileList.add(new GeneratedCloudAppDevPropFile(generatorParam));

        // application-test.properties
        generatedFileList.add(new GeneratedCloudAppTestPropFile(generatorParam));

        // boot.properties
        generatedFileList.add(new GeneratedBootPropFile(generatorParam));

        // log4j2
        generatedFileList.add(new GeneratedLog4j2File(generatorParam));

        // Doc Template
        GeneratedDocTemplateFile docTemplateFile = new GeneratedDocTemplateFile(generatorParam);
        docTemplateFile.generateFile();

        // JSON配置文件
        GeneratedHipsterConfigFile hipsterConfigFile = new GeneratedHipsterConfigFile();
        hipsterConfigFile.generateCode(generatorParam);

        // FeignConfig
        generatedFileList.add(new GeneratedJavaFeignConfigClass(generatorParam, null));


        /**
         * 统一生成文件
         */

        // 生成代码
        for (AbstractGeneratedFile generatedFile : generatedFileList) {
            generatedFile.generateFile();
        }
    }
}
