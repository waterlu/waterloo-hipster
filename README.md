# waterloo-hipster

像JHipster一样可以自动生成Java后端项目和代码的自动化工具，适合创建Spring Boot和Spring Cloud微服务项目。

## 使用

启动项目后，打开http://localhost:8080页面，填写项目相关信息，点击“生成微服务工程代码”按钮自动生成项目工程和源代码到本地磁盘目录。

### 前提条件

(1) 自动生成项目代码依赖[waterloo-starter-web](https://github.com/waterlu/waterloo-starter-web)项目，这个项目定义了基础类，使用waterloo-hipster前请先下载waterloo-starter-web项目并install到maven库。

(2) 代码根据数据库表生成，要求每个表都必须有主键。

> MySQL 5.5以后默认引擎为InnoDB，InnoDB为聚簇索引，数据根据主索引组织，从InnoDB的角度来看也必须要有主键。

(3) 主键目前只支持BigInt自增ID，不支持UUID等字符串作为主键。

> 使用UUID作为主键相比使用BigInt空间浪费是巨大的，并且效率也没有BigInt高，所以建议使用BigInt作为主键。分库分表情况可以考虑使用SnowFlake等算法生成BigInt主键。

(4) 由于定义了BaseEntity基类，所以每个表都必须有delete_flag、create_time和update_time三个字段。

> 不需要可以在生成实体类代码后删除`extends BaseEntity` 。

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

(1) 通过Swagger注解自动生成接口文档；

(2) 通用的批量写入接口；

(3) 通用的列表查询接口；

(4) 创建Spring Cloud项目；

### 定制化

(1) 请根据实际情况修改配置类的模板文件

- /resources/templates/app-dev-properties.ftl 修改开发环境默认的数据库配置
- /resources/templates/app-test-properties.ftl 修改测试环境默认的数据库配置
- /resources/templates/pom.ftl 添加其他默认依赖的项目
- /resources/templates/log4j2.ftl 修改日志文件默认目录

(2) 请根据实际情况修改/resources/static/js/controllers/project.js文件更改页面默认配置

- $scope.groupId - 默认组织名称
- $scope.artifactId - 默认项目名称
- $scope.springBootVersion - SpringBoot版本（默认1.5.13.RELEASE）
- $scope.author - 默认作者
- $scope.basePackage - 默认基础包目录
- $scope.projectDir - 默认生成项目所在目录
- $scope.dbIP - 默认连接数据库地址（读取库表结构用）
- $scope.dbUsername - 默认连接数据库用户名
- scope.dbPassword - 默认连接数据库密码
- $scope.controllerPackage - Controller类所在包名称（默认web）
- $scope.daoPackage - Mapper类所在包名称（默认mapper）
- $scope.entityPackage - 实体类所在包名称（默认entity）
- $scope.dtoPackage - DTO类所在包名称（默认vo）
- $scope.voPackage - VO类所在包名称（默认vo）