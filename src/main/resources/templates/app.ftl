package ${packageName};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cn.lu.web.annotation.EnableWaterlooWeb;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ${classRemark}
 *
 * @author ${author}
 * @date ${date}
 */
@SpringBootApplication
@MapperScan("${mapperPackage}")
@EnableWaterlooWeb
@EnableSwagger2
public class ${className} {

    public static void main(String[] args) {
        SpringApplication.run(${className}.class, args);
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("${packageName}"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("${classRemark} 接口文档")
            .description("${classRemark} 接口文档")
            .contact(new Contact("${author}", "", ""))
            .version("1.0.0")
            .build();
    }
}