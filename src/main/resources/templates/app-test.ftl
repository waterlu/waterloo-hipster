package ${packageName};

import cn.lu.hipster.api.MybatisGenerator;
import cn.lu.hipster.api.SpringMVCGenerator;
import cn.lu.hipster.model.GeneratorParam;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import cn.lu.hipster.api.Swagger2Markdown;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * ${classRemark}
 *
 * @author ${author}
 * @date ${date}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ${className} {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() {

    }

    //@Test
    public void generateSourceCode() throws Exception {
        // 读取配置参数
        Resource configFile = new ClassPathResource("hipster-config.json");
        GeneratorParam generatorParam = JSON.parseObject(configFile.getInputStream(), GeneratorParam.class);

        MybatisGenerator mybatisGenerator = new MybatisGenerator();
        SpringMVCGenerator springMVCGenerator = new SpringMVCGenerator();

        // 生成CRUD
        mybatisGenerator.generateCode(generatorParam);

        // 生成Controller/Service/Model
        springMVCGenerator.generateCode(generatorParam);
    }

    //@Test
    public void generateDocTest() throws Exception {
        String outputDir = "doc/";
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");
        String swaggerJson = response.getContentAsString();
        Files.createDirectories(Paths.get(outputDir));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"))) {
            writer.write(swaggerJson);
        }
        Swagger2Markdown swagger2Markdown = new Swagger2Markdown();
        String sourcePath = Paths.get(outputDir, "swagger.json").toString();
        String outputPath = Paths.get(outputDir).toString();
        swagger2Markdown.convert(sourcePath, outputPath);
    }
}