package ${packageName};

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import cn.lu.web.mvc.BizException;
import cn.lu.web.mvc.ResponseData;
import cn.lu.web.mvc.ListResponseData;
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
public class ${className} extends BaseController<${modelClassName}, ${dtoClassName}, ${paramClassName}, ${voClassName}> implements ${facadeClassName} {

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
        entity.set${keyFieldName}(id.toString());
    }

    /**
     * 创建
     *
     * @param param
     * @return
     * @throws BizException
     */
    @Override
    @ApiOperation(value = "创建${classRemark}", response = ${voClassName}.class, notes = "创建${classRemark}接口描述")
    public ResponseData<${voClassName}> create(@RequestBody @Validated({InsertGroup.class}) ${dtoClassName} param) throws BizException {
        ResponseData responseData = new ResponseData();
        ${modelClassName} entity = super.createResource(param);
        ${voClassName} vo = entityToVo(entity);
        responseData.setData(vo);
        return responseData;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    @ApiOperation(value = "获取${classRemark}详情接口", response = ${voClassName}.class, notes = "获取${classRemark}详情接口描述")
    public ResponseData<${voClassName}> get(@PathVariable String id) throws BizException {
        ResponseData responseData = new ResponseData();
        ${modelClassName} entity = super.getResource(id);
        if(null == entity) {
            responseData.setData(entity);
        } else {
            ${voClassName} vo = entityToVo(entity);
            responseData.setData(vo);
        }
        return responseData;
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
    @ApiOperation(value = "更新${classRemark}接口", response = String.class, notes = "更新${classRemark}接口描述")
    public SimpleResponseData update(@PathVariable String id, @RequestBody @Validated({UpdateGroup.class}) ${dtoClassName} param) throws BizException {
        return super.updateResource(id, param);
    }

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    @ApiOperation(value = "逻辑删除${classRemark}接口", response = String.class, notes = "逻辑删除${classRemark}接口描述")
    public SimpleResponseData delete(@PathVariable String id) throws BizException {
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
    @ApiOperation(value = "查询${classRemark}接口", response = ${voClassName}.class, responseContainer = "List", notes = "查询${classRemark}接口描述")
    public ListResponseData<${voClassName}> query(@RequestBody @Validated ${paramClassName} param) throws BizException {
        ListResponseData<${voClassName}> responseData = new ListResponseData();
        List<${modelClassName}> list = super.queryResource(param);
        List<${voClassName}> listVO = new ArrayList<>();
        for (${modelClassName} entity : list) {
            ${voClassName} vo = entityToVo(entity);
            listVO.add(vo);
        }
        PageInfo pageInfo = new PageInfo(listVO);
        ListResultVO<${voClassName}> resultVO = new ListResultVO();
        resultVO.setCount(pageInfo.getTotal());
        resultVO.setPageCount(pageInfo.getPages());
        resultVO.setPageNum(pageInfo.getPageNum());
        resultVO.setPageSize(pageInfo.getPageSize());
        resultVO.setList(listVO);
        responseData.setData(resultVO);
        return responseData;
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
    @ApiOperation(value = "查询所有${classRemark}接口", response = ${voClassName}.class, responseContainer = "List", notes = "查询所有${classRemark}接口描述")
    @ApiImplicitParams({@ApiImplicitParam(name = "startRow", value = "分页开始下标,默认0", dataType = "Integer", required = false),
                        @ApiImplicitParam(name = "pageSize", value = "每页数量,默认为20", dataType = "Integer", required = false)})
    public ListResponseData<${voClassName}> queryAll(@RequestParam(required = false) Integer startRow,
                                   @RequestParam(required = false) Integer pageSize) throws BizException {
        ListResponseData<${voClassName}> responseData = new ListResponseData();
        ${paramClassName} param = new ${paramClassName}();
        if (null != startRow) {
            param.setStartRow(startRow);
        }
        if (null != pageSize) {
            param.setPageSize(pageSize);
        }
        List<${modelClassName}> list = super.queryResource(param);
        List<${voClassName}> listVO = new ArrayList<>();
        for (${modelClassName} entity : list) {
            ${voClassName} vo = entityToVo(entity);
            listVO.add(vo);
        }
        PageInfo pageInfo = new PageInfo(listVO);
        ListResultVO<${voClassName}> resultVO = new ListResultVO();
        resultVO.setCount(pageInfo.getTotal());
        resultVO.setPageCount(pageInfo.getPages());
        resultVO.setPageNum(pageInfo.getPageNum());
        resultVO.setPageSize(pageInfo.getPageSize());
        resultVO.setList(listVO);
        responseData.setData(resultVO);
        return responseData;
    }
}