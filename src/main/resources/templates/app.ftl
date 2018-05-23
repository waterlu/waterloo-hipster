package ${packageName};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ${classRemark}
 *
 * @author ${author}
 * @date ${date}
 */
@SpringBootApplication
@MapperScan("${mapperPackage}")
public class ${className} {

    public static void main(String[] args) {
        SpringApplication.run(${className}.class, args);
    }
}