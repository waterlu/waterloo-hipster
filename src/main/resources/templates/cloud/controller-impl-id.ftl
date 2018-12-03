package ${packageName};

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import cn.lu.web.mvc.BizException;
import cn.lu.web.mvc.DBException;
import cn.lu.web.mvc.ResponseData;
import cn.lu.web.mvc.ResponseResult;
import cn.lu.web.mvc.SimpleResponseData;
import cn.lu.web.vo.InsertGroup;
import cn.lu.web.vo.UpdateGroup;
import cn.lu.web.vo.QueryParam;
import cn.lu.web.vo.ListResultVO;
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
@Api(value = "/${classMapping}", description= "${classRemark}接口")
public class ${className} extends BaseController<${modelClassName}, ${dtoClassName}, ${paramClassName}> implements ${facadeClassName} {

    @Autowired
    private ${serviceClassName} ${serviceObjectName};

    /**
     * 获取服务实现类
     *
     * @return
     */
    @Override
    public BaseService<${modelClassName}, ${paramClassName}> getService() {
        return ${serviceObjectName};
    }

    /**
     * 更新前需要设置主键，底层不知道哪个字段是主键。
     *
     * @param entity
     * @param id
     */
    @Override
    public void setEntityId(${modelClassName} entity, Object id) {
        Long rowId = Long.parseLong(id.toString());
        entity.set${keyFieldName}(rowId);
    }

    /**
     * 创建
     *
     * @param param
     * @return
     * @throws BizException
     */
    @Override
    @PostMapping(value = "")
    @ApiOperation(value = "创建${classRemark}", response = ${voClassName}.class, notes = "创建${classRemark}接口描述")
    public ResponseResult create(@RequestBody @Validated({InsertGroup.class}) ${dtoClassName} param) throws BizException {
        return super.createResource(param);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取${classRemark}详情接口", response = ${voClassName}.class, notes = "获取${classRemark}详情接口描述")
    public ResponseResult get(@PathVariable Long id) throws BizException {
        return super.getResource(id);
    }

    /**
     * 更新
     *
     * @param id
     * @param param
     * @return
     * @throws BizException
     */
    @Override
    @PutMapping(value = "/{id}")
    @ApiOperation(value = "更新${classRemark}接口", response = String.class, notes = "更新${classRemark}接口描述")
    public ResponseResult update(@PathVariable Long id, @RequestBody @Validated({UpdateGroup.class}) ${dtoClassName} param) throws BizException {
        return super.updateResource(id, param);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除${classRemark}接口", response = String.class, notes = "删除${classRemark}接口描述")
    public ResponseResult delete(@PathVariable Long id) throws BizException {
        return super.deleteResource(id);
    }

    /**
     * 查询
     *
     * @param param 对应${paramClassName}类
     * @return
     * @throws BizException
     */
    @Override
    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "查询${classRemark}接口", response = ${voClassName}.class, responseContainer = "List", notes = "查询${classRemark}接口描述")
    public ResponseResult query(@RequestBody @Validated ${paramClassName} param) throws BizException {
        return super.queryResource(param);
    }

    /**
     * 查询所有
     *
     * @param startRow
     * @param pageSize
     * @return
     * @throws BizException
     */
    @Override
    @GetMapping(value = "/queryAll")
    @ApiOperation(value = "查询所有${classRemark}接口", response = ${voClassName}.class, responseContainer = "List", notes = "查询所有${classRemark}接口描述")
    @ApiImplicitParams({@ApiImplicitParam(name = "startRow", value = "分页开始下标,默认0", dataType = "Integer", required = false),
                        @ApiImplicitParam(name = "pageSize", value = "每页数量,默认为20", dataType = "Integer", required = false)})
    public ResponseResult queryAll(@RequestParam(required = false) Integer startRow,
                                   @RequestParam(required = false) Integer pageSize) throws BizException {
        ${paramClassName} param = new ${paramClassName}();
        if (null != startRow) {
            param.setStartRow(startRow);
        }
        if (null != pageSize) {
            param.setPageSize(pageSize);
        }
        return super.queryResource(param);
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