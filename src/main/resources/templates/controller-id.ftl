package ${packageName};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import cn.lu.web.mvc.BizException;
import cn.lu.web.mvc.DBException;
import cn.lu.web.mvc.ResponseData;
import cn.lu.web.mvc.ResponseResult;
import cn.lu.web.vo.InsertGroup;
import cn.lu.web.base.BaseController;
import cn.lu.web.base.BaseService;

<#list imports as import>
import ${import.name};
</#list>

/**
 * ${classRemark}接口
 *
 * @author ${author}
 * @date ${date}
 */
@RestController
@RequestMapping(value = "/${classMapping}")
public class ${className} extends BaseController<${modelClassName}, ${dtoClassName}> {

    @Autowired
    private ${serviceClassName} ${serviceObjectName};

    /**
     * 获取服务实现类
     *
     * @return
     */
    @Override
    public BaseService<${modelClassName}> getService() {
        return (${serviceClassName}Impl) ${serviceObjectName};
    }

    /**
     * 更新前需要设置主键，底层不知道哪个字段是主键。
     *
     * @param entity
     * @param id
     */
    @Override
    public void setEntityId(${modelClassName} entity, Object id) {
        entity.set${keyFieldName}((Long)id);
    }

    /**
     * 将入参对象转换为与数据库对应的实体类对象，默认实现是DTO类和Entity类字段一对一转换，如果不满足要求请覆盖此方法。
     * 此方法在基类的create()方法中调用，如果覆盖了create()方法请忽略此方法。
     *
     * @param param
     * @return
     */
    @Override
    protected ${modelClassName} paramToEntity(${dtoClassName} param) {
        String jsonString = JSON.toJSONString(param);
        return JSON.parseObject(jsonString, getEntityType(0));
    }

    /**
     * 封装返回结果，默认直接返回实体类对象。
     * 如果需要进行处理，请将Entity类对象转换为VO对象，并放入ResponseData中返回。
     *
     * @param entity
     * @return
     */
    @Override
    protected ResponseData entityToVo(${modelClassName} entity) {
        return new ResponseData(entity);
    }
}