# 数据库配置
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://${datasourceUrl}?useUnicode=true&characterEncoding=utf8&useSSL=false
spring.datasource.username=${datasourceUsername}
spring.datasource.password=${datasourcePassword}

# Eureka配置
spring.cloud.inetutils.preferred-networks=172.18
eureka.client.service-url.defaultZone=http://127.0.0.1:10000/eureka/
eureka.instance.prefer-ip-address=true
eureka.instance.instance-id=${r'${spring.cloud.client.ipAddress}'}:${r'${spring.application.name}'}:${r'${server.port}'}