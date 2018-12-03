package ${packageName};

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author ${author}
 * @date ${date}
*/
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}