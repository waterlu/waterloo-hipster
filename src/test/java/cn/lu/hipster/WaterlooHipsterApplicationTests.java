package cn.lu.hipster;

import cn.lu.hipster.api.MybatisGenerator;
import cn.lu.hipster.api.SpringMVCGenerator;
import cn.lu.hipster.api.SpringProjectGenerator;
import cn.lu.hipster.consts.KeyType;
import cn.lu.hipster.consts.ProjectType;
import cn.lu.hipster.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo project for Spring Cloud
 *
 * @author waterlu
 * @date 2018-05-24
 */
@RunWith(SpringRunner.class)
public class WaterlooHipsterApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGenerateSpringBootProject() {
        GeneratorParam generatorParam = new GeneratorParam();

        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setGroupId("cn.waterlu");
        projectInfo.setArtifactId("service-demo");
        projectInfo.setName("service-demo");
        projectInfo.setPort(3310);
        projectInfo.setVersion("1.0.0");
        projectInfo.setJavaVersion("1.8");
        projectInfo.setSpringBootVersion("1.5.13.RELEASE");
        projectInfo.setSpringCloudVersion("Dalston.SR4");
        projectInfo.setDescription("Demo project for Spring Boot");
        projectInfo.setProjectType(ProjectType. PROJECT_TYPE_BOOT);
        generatorParam.setProjectInfo(projectInfo);

        DatabaseInfo databaseInfo = new DatabaseInfo();
        databaseInfo.setDbType("MySQL");
        databaseInfo.setDbIP("192.168.75.159");
        databaseInfo.setDbPort(3306);
        databaseInfo.setDbUsername("zj_admin");
        databaseInfo.setDbPassword("123456");
        databaseInfo.setDbName("bookshop_user");
        generatorParam.setDatabaseInfo(databaseInfo);

        PackageInfo packageInfo = new PackageInfo();
        packageInfo.setAuthor("waterlu");
        packageInfo.setProjectPath("c:/tmp/service-demo");
        packageInfo.setJavaPath("src/main/java");
        packageInfo.setResourcePath("src/main/resources");
        packageInfo.setTestPath("src/test/java");
        packageInfo.setBasePackage("cn.lu.demo");
        packageInfo.setConfigPackage("config");
        packageInfo.setControllerPackage("web");
        packageInfo.setServicePackage("service");
        packageInfo.setDaoPackage("mapper");
        packageInfo.setEntityPackage("entity");
        packageInfo.setDtoPackage("vo");
        packageInfo.setVoPackage("vo");
        generatorParam.setPackageInfo(packageInfo);

        List<DependencyInfo> dependencyList = new ArrayList<>();
        DependencyInfo dependencyWeb = new DependencyInfo();
        dependencyWeb.setName("waterloo-web");
        dependencyWeb.setGroupId("cn.waterlu");
        dependencyWeb.setArtifactId("waterloo-starter-web");
        dependencyWeb.setVersion("1.0.1");
        dependencyList.add(dependencyWeb);
        DependencyInfo dependencyHipster = new DependencyInfo();
        dependencyHipster.setName("waterloo-hipster");
        dependencyHipster.setGroupId("cn.waterlu");
        dependencyHipster.setArtifactId("waterloo-hipster");
        dependencyHipster.setVersion("1.0.1");
        dependencyList.add(dependencyHipster);
        generatorParam.setDependencies(dependencyList);

        List<TableInfo> tableList = new ArrayList<>();
        TableInfo tableUser = new TableInfo();
        tableUser.setName("user");
        tableUser.setRemark("用户");
        tableUser.setType("Single");
        tableUser.setKey("user_id");
        tableUser.setKeyType(KeyType.KEY_TYPE_ID);
        List<String> queryField = new ArrayList<>();
        queryField.add("user_mobile");
        queryField.add("user_login_name");
        tableUser.setQuery(queryField);
        tableList.add(tableUser);
        generatorParam.setTables(tableList);

        SpringProjectGenerator projectGenerator = new SpringProjectGenerator();
        MybatisGenerator mybatisGenerator = new MybatisGenerator();
        SpringMVCGenerator springMVCGenerator = new SpringMVCGenerator();

        try {
            projectGenerator.generateCode(generatorParam);
            mybatisGenerator.generateCode(generatorParam);
            springMVCGenerator.generateCode(generatorParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}