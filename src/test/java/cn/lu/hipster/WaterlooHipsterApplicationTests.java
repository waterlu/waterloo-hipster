package cn.lu.hipster;

import cn.lu.hipster.api.*;
import cn.lu.hipster.consts.KeyType;
import cn.lu.hipster.consts.ProjectType;
import cn.lu.hipster.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
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
        projectInfo.setGroupId("cn.lu.test.boot");
        projectInfo.setArtifactId("boot-demo");
        projectInfo.setName("boot-demo");
        projectInfo.setPort(8010);
        projectInfo.setVersion("1.0.0");
        projectInfo.setJavaVersion("1.8");
        projectInfo.setSpringBootVersion("1.5.13.RELEASE");
        projectInfo.setSpringCloudVersion("Dalston.SR4");
        projectInfo.setDescription("Demo project for Spring Boot");
        projectInfo.setProjectType(ProjectType.PROJECT_TYPE_BOOT);
        generatorParam.setProjectInfo(projectInfo);

        DatabaseInfo databaseInfo = new DatabaseInfo();
        databaseInfo.setDbType("MySQL");
        databaseInfo.setDbIP("127.0.0.1");
        databaseInfo.setDbPort(3306);
        databaseInfo.setDbUsername("lu");
        databaseInfo.setDbPassword("123");
        databaseInfo.setDbName("peanut");
        generatorParam.setDatabaseInfo(databaseInfo);

        PackageInfo packageInfo = new PackageInfo();
        packageInfo.setAuthor("waterlu");
        packageInfo.setProjectPath("/home/lu/workspace/test1");
        packageInfo.setJavaPath("src/main/java");
        packageInfo.setResourcePath("src/main/resources");
        packageInfo.setTestPath("src/test/java");
        packageInfo.setBasePackage("cn.lu.test.boot.demo");
        packageInfo.setConfigPackage("config");
        packageInfo.setControllerPackage("api");
        packageInfo.setServicePackage("service");
        packageInfo.setDaoPackage("dao");
        packageInfo.setEntityPackage("domain");
        packageInfo.setDtoPackage("dto");
        packageInfo.setVoPackage("vo");
        packageInfo.setFacadePackage("facade");
        generatorParam.setPackageInfo(packageInfo);

        List<DependencyInfo> dependencyList = new ArrayList<>();
        DependencyInfo dependencyWeb = new DependencyInfo();
        dependencyWeb.setName("waterloo-web");
        dependencyWeb.setGroupId("cn.waterlu");
        dependencyWeb.setArtifactId("waterloo-starter-web");
        dependencyWeb.setVersion("1.0.3");
        dependencyList.add(dependencyWeb);
        DependencyInfo dependencyHipster = new DependencyInfo();
        dependencyHipster.setName("waterloo-hipster");
        dependencyHipster.setGroupId("cn.waterlu");
        dependencyHipster.setArtifactId("waterloo-hipster");
        dependencyHipster.setVersion("1.0.4");
        dependencyList.add(dependencyHipster);
        DependencyInfo dependencySwagger = new DependencyInfo();
        dependencySwagger.setName("swagger2");
        dependencySwagger.setGroupId("io.springfox");
        dependencySwagger.setArtifactId("springfox-swagger2");
        dependencySwagger.setVersion("2.9.2");
        dependencyList.add(dependencySwagger);
        DependencyInfo dependencySwaggerUI = new DependencyInfo();
        dependencySwaggerUI.setName("swagger-ui");
        dependencySwaggerUI.setGroupId("io.springfox");
        dependencySwaggerUI.setArtifactId("springfox-swagger-ui");
        dependencySwaggerUI.setVersion("2.9.2");
        dependencyList.add(dependencySwaggerUI);
        generatorParam.setDependencies(dependencyList);

        List<TableInfo> tableList = new ArrayList<>();
        TableInfo tableBuyer = new TableInfo();
        tableBuyer.setName("buyer");
        tableBuyer.setRemark("购买方");
        tableBuyer.setType("Single");
        tableBuyer.setKey("id");
        tableBuyer.setKeyType(KeyType.KEY_TYPE_ID);
        tableBuyer.setQuery(new ArrayList<>());
        tableList.add(tableBuyer);
        TableInfo tableSupplier= new TableInfo();
        tableSupplier.setName("supplier");
        tableSupplier.setRemark("供应商");
        tableSupplier.setType("Single");
        tableSupplier.setKey("supplier_uuid");
        tableSupplier.setKeyType(KeyType.KEY_TYPE_UUID);
        tableSupplier.setQuery(new ArrayList<>());
        tableList.add(tableSupplier);
        generatorParam.setTables(tableList);

        SpringBootGenerator projectGenerator = new SpringBootGenerator();
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

    @Test
    public void testGenerateSpringCloudProject() {
        GeneratorParam generatorParam = new GeneratorParam();

        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setGroupId("cn.lu.test.cloud");
        projectInfo.setArtifactId("cloud-demo");
        projectInfo.setName("cloud-demo");
        projectInfo.setPort(8020);
        projectInfo.setVersion("1.0.0");
        projectInfo.setJavaVersion("1.8");
        projectInfo.setSpringBootVersion("1.5.13.RELEASE");
        projectInfo.setSpringCloudVersion("Dalston.SR4");
        projectInfo.setDescription("Demo project for Spring Cloud");
        projectInfo.setProjectType(ProjectType.PROJECT_TYPE_CLOUD);
        generatorParam.setProjectInfo(projectInfo);

        DatabaseInfo databaseInfo = new DatabaseInfo();
        databaseInfo.setDbType("MySQL");
        databaseInfo.setDbIP("127.0.0.1");
        databaseInfo.setDbPort(3306);
        databaseInfo.setDbUsername("lu");
        databaseInfo.setDbPassword("123");
        databaseInfo.setDbName("peanut");
        generatorParam.setDatabaseInfo(databaseInfo);

        PackageInfo packageInfo = new PackageInfo();
        packageInfo.setAuthor("waterlu");
        packageInfo.setProjectPath("/home/lu/workspace/test2");
        packageInfo.setJavaPath("src/main/java");
        packageInfo.setResourcePath("src/main/resources");
        packageInfo.setTestPath("src/test/java");
        packageInfo.setBasePackage("cn.lu.test.cloud.demo");
        packageInfo.setConfigPackage("config");
        packageInfo.setControllerPackage("web");
        packageInfo.setServicePackage("service");
        packageInfo.setDaoPackage("mapper");
        packageInfo.setEntityPackage("entity");
        packageInfo.setDtoPackage("facade");
        packageInfo.setVoPackage("facade");
        packageInfo.setFacadePackage("facade");
        generatorParam.setPackageInfo(packageInfo);

        List<DependencyInfo> dependencyList = new ArrayList<>();
        DependencyInfo dependencyWeb = new DependencyInfo();
        dependencyWeb.setName("waterloo-web");
        dependencyWeb.setGroupId("cn.waterlu");
        dependencyWeb.setArtifactId("waterloo-starter-web");
        dependencyWeb.setVersion("1.0.3");
        dependencyList.add(dependencyWeb);
        DependencyInfo dependencyHipster = new DependencyInfo();
        dependencyHipster.setName("waterloo-hipster");
        dependencyHipster.setGroupId("cn.waterlu");
        dependencyHipster.setArtifactId("waterloo-hipster");
        dependencyHipster.setVersion("1.0.4");
        dependencyList.add(dependencyHipster);
        DependencyInfo dependencySwagger = new DependencyInfo();
        dependencySwagger.setName("swagger2");
        dependencySwagger.setGroupId("io.springfox");
        dependencySwagger.setArtifactId("springfox-swagger2");
        dependencySwagger.setVersion("2.9.2");
        dependencyList.add(dependencySwagger);
        DependencyInfo dependencySwaggerUI = new DependencyInfo();
        dependencySwaggerUI.setName("swagger-ui");
        dependencySwaggerUI.setGroupId("io.springfox");
        dependencySwaggerUI.setArtifactId("springfox-swagger-ui");
        dependencySwaggerUI.setVersion("2.9.2");
        dependencyList.add(dependencySwaggerUI);
        generatorParam.setDependencies(dependencyList);

        List<TableInfo> tableList = new ArrayList<>();
        TableInfo tableBuyer = new TableInfo();
        tableBuyer.setName("buyer");
        tableBuyer.setRemark("购买方");
        tableBuyer.setType("Single");
        tableBuyer.setKey("id");
        tableBuyer.setKeyType(KeyType.KEY_TYPE_ID);
        tableBuyer.setQuery(new ArrayList<>());
        tableList.add(tableBuyer);
        TableInfo tableSupplier= new TableInfo();
        tableSupplier.setName("supplier");
        tableSupplier.setRemark("供应商");
        tableSupplier.setType("Single");
        tableSupplier.setKey("supplier_uuid");
        tableSupplier.setKeyType(KeyType.KEY_TYPE_UUID);
        tableSupplier.setQuery(new ArrayList<>());
        tableList.add(tableSupplier);
        generatorParam.setTables(tableList);

        SpringCloudGenerator projectGenerator = new SpringCloudGenerator();
        MybatisGenerator mybatisGenerator = new MybatisGenerator();
        SpringCloudMVCGenerator springMVCGenerator = new SpringCloudMVCGenerator();

        try {
            projectGenerator.generateCode(generatorParam);
            mybatisGenerator.generateCode(generatorParam);
            springMVCGenerator.generateCode(generatorParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}