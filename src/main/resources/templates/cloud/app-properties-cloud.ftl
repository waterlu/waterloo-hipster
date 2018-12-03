# 切换环境
spring.profiles.active=dev

# 服务端口
server.port=${servicePort?c}

# 日志配置
logging.config=classpath:log4j2.xml

# mybatis配置
mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
mapper.not-empty=false
mapper.identity=MYSQL
mapper.before=true
mapper.mappers=cn.lu.web.mapper.CrudMapper
pagehelper.helperDialect=mysql
pagehelper.row-bounds-with-count=true
pagehelper.reasonable=false
pagehelper.offset-as-page-num=false
pagehelper.supportMethodsArguments=true
pagehelper.params=count=countSql

# Mapper接口基类
mapper.base.class=cn.lu.web.mapper.CrudMapper

# 实体类基类
entity.base.class=cn.lu.web.base.BaseEntity

# 关闭hystrix
feign.hystrix.enabled=false

# 使用httpclient，解决@FeignClient的GET方法使用对象参数的问题
feign.httpclient.enabled=true

# 配置Hystrix的超时时间为5秒，默认1秒
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=5000

# 关闭自动热部署
spring.devtools.restart.enabled=false

# actuator接口使用的端口
#management.port=${managePort?c}

# actuator接口不需要密码验证
management.security.enabled=false

# 启用actuator的shutdown
endpoints.shutdown.enabled=true

# actuator的shutdown接口是否需要密码验证
endpoints.shutdown.sensitive=false

# actuator的health接口是否需要密码验证
endpoints.metrics.sensitive=false

# actuator的health接口是否需要密码验证
endpoints.health.sensitive=false