package cn.lu.hipster.core;

import org.mybatis.generator.api.ProgressCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * MyBatis生成器的进度显示
 *
 * @author lutiehua
 * @date 2017/11/09
 */
@Component
public class MybatisProgressCallback implements ProgressCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void introspectionStarted(int totalTasks) {

    }

    @Override
    public void generationStarted(int totalTasks) {
        logger.info("生成mybatis代码开始，一共{}步", totalTasks);
    }

    @Override
    public void saveStarted(int totalTasks) {
        logger.info("开始写文件到磁盘，一共{}步", totalTasks);
    }

    @Override
    public void startTask(String taskName) {
        logger.info("开始 {}", taskName);
    }

    @Override
    public void done() {
        logger.info("任务完成");
    }

    @Override
    public void checkCancel() throws InterruptedException {

    }
}
