package ${packageName};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cn.lu.web.annotation.EnableWaterlooWeb;

/**
 * ${classRemark}
 *
 * @author ${author}
 * @date ${date}
 */
@SpringBootApplication
@MapperScan("${mapperPackage}")
@EnableWaterlooWeb
public class ${className} {

    public static void main(String[] args) {
        SpringApplication.run(${className}.class, args);
    }
}