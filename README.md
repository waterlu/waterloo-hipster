# waterloo-hipster

类似JHipster可以自动生成Java后端项目和代码的自动化工具，适合创建Spring Boot和Spring Cloud微服务项目。

## 使用

### 生成项目

```java
import cn.lu.hipster.api.SpringProjectGenerator;
SpringProjectGenerator projectGenerator = new SpringProjectGenerator();
projectGenerator.generateCode(generatorParam);
```

生成项目工程文件，包括：

- 生成Application和ApplicationTests类
- 生成pom.xml和.gitignore文件
- 生成application.properties/application-dev.properties/application-test.properties和bootstrap.properties配置文件到/resources目录
- 生成log4j2.xml到/resources目录
- 生成hipster-config.json到/resources目录
- 生成dot.ftl到/resources/templates目录

### 生成Mybatis代码

```java
import cn.lu.hipster.api.MybatisGenerator;
MybatisGenerator mybatisGenerator = new MybatisGenerator();
mybatisGenerator.generateCode(generatorParam);
```

为选择的每一个数据库表生成Mapper接口和XML配置文件：

- 生成XxxMapper接口（继承CrudMapper接口）
- 生成XxxMapper.xml文件到/resources/mybatis/mapper目录（仅包括BaseResultMap声明）
- 生成Xxx实体类文件（与数据库表字段一一对应）

### 生成MVC代码

```java
import cn.lu.hipster.api.SpringMVCGenerator;
SpringMVCGenerator springMVCGenerator = new SpringMVCGenerator();
springMVCGenerator.generateCode(generatorParam);
```

为选择的每一个数据库表生成MVC代码

- 生成XxxController类
- 生成XxxService接口和XxxServiceImpl类
- 生成XxxDTO类（插入入参，含参数校验）
- 生成XxxQueryDTO类（查询入参，含参数校验）
- 生成XxxVO类（返回数据）

### 生成接口文档

```java
public class XxxApplicationTests {
  @Test
  public void generateDocTest() throws Exception {
  }
}
```

执行自动生成的ApplicationTests中的generateDocTest()自动生成接口文档

- 接口必须使用Swagger2注解声明，自动生成的XxxController中给出了CRUD接口的样例
- 访问/v2/api-doc得到源数据swagger.json
- 解析swagger.json生成对应的markdown格式文档
- markdown格式文档基于模板/resources/templates/doc.ftl生成，调整doc.ftl可调整接口文档格式

### 重新生成代码

第一次生成项目工程的代码以后，可能需要修改数据库表结构或者增加新的数据库表，可以执行自动生成的ApplicationTests中的generateMVCCode()再次生成MVC代码。

```java
public class XxxApplicationTests {
  @Test 
  public void generateSourceCode() throws Exception {
  }
}
```

- 将重新生成Mybatis代码和MVC代码
- 基于配置文件/resources/hipster-config.json生成，调整配置文件可控制生成代码的行为
- 如果新增表一般只需要修改hipster-config.json增加表名即可
- 如果修改表将会覆盖所有Mybatis代码和MVC代码，注意做好保存

> 特别注意：根据每一个表重新生成Controller/Service/Mapper/DTO/VO/Entity类等代码时，将会覆盖旧代码，一定注意不要冲掉自己添加的代码。代码自动合并涉及到冲突的问题比较复杂，暂时不会实现。

### 前提条件

(1) 自动生成项目代码依赖[waterloo-starter-web](https://github.com/waterlu/waterloo-starter-web) 项目，这个项目定义了基础类，使用waterloo-hipster前请先下载waterloo-starter-web项目并install到maven库。

(2) 代码根据数据库表生成，要求每个表都必须有主键。

> MySQL 5.5以后默认引擎为InnoDB，InnoDB为聚簇索引，数据根据主索引组织，从InnoDB的角度来看也必须要有主键。

(3) 主键目前只支持BigInt自增ID，不支持UUID等字符串作为主键。

> 使用UUID作为主键相比使用BigInt空间浪费是巨大的，并且效率也没有BigInt高，所以建议使用BigInt作为主键。分库分表情况可以考虑使用SnowFlake等算法生成BigInt主键。

(4) 由于定义了BaseEntity基类，所以每个表都必须有delete_flag、create_time和update_time三个字段。

> 不需要可以在生成实体类代码后删除`extends BaseEntity` 。

## 版本变更日志

### v1.0.5

- 支持定制package目录

### v1.0.4

- 支持Spring Cloud项目

### v1.0.3

- 优化ID和UUID支持

### V1.0.2

- 重新生成代码

### V1.0.1

- 生成接口文档

### V1.0.0

- 生成SpringBoot项目
- 生成Mybatis代码
- 生成MVC代码



### 实现内容

(1) 自动生成Spring Boot项目工程，包括：

- pom文件和.gitignore
- Controller类、Service类和ServiceImpl类
- 入参DTO类（含参数校验）和返回对象VO类
- Mapper接口和mapper.xml文件
- log4j2.xml
- application.properties（请自行修改数据库配置）

(2) 所有Mapper接口继承基类CrudMapper实现CRUD操作，mapper.xml文件初始化只有ResultMap定义；

(3) 提供BaseController和BaseService基类，给出CRUD操作的默认实现；

### TODO内容

(1) 通用的批量写入接口；

(2) 通用的列表查询接口；

(3) 创建Spring Cloud项目；

(4) 自动生成错误码文档。

### 定制化

请根据实际情况修改配置类的模板文件

- /resources/templates/app-dev-properties.ftl 修改开发环境默认的数据库配置
- /resources/templates/app-test-properties.ftl 修改测试环境默认的数据库配置
- /resources/templates/pom.ftl 添加其他默认依赖的项目
- /resources/templates/log4j2.ftl 修改日志文件默认目录