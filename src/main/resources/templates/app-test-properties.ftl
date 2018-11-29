# 数据库配置
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://${datasourceUrl}?useUnicode=true&characterEncoding=utf8&useSSL=false
spring.datasource.username=${datasourceUsername}
spring.datasource.password=${datasourcePassword}