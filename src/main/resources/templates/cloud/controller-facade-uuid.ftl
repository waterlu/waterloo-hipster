package ${packageName};

import cn.lu.web.mvc.BizException;
import cn.lu.web.mvc.ListResponseData;
import cn.lu.web.mvc.ResponseData;
import cn.lu.web.mvc.SimpleResponseData;
import cn.lu.web.vo.ListResultVO;
import cn.lu.web.base.BaseController;
import cn.lu.web.base.BaseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.netflix.feign.FeignClient;

<#list imports as import>
import ${import.name};
</#list>

/**
 * ${classRemark}接口
 *
 * @author ${author}
 * @date ${date}
 */
@FeignClient(value = "${serviceName}", configuration = {${feignConfigClassName}.class}, fallback = ${fallbackClassName}.class)
public interface ${className} {

    /**
     * 创建
     *
     * @param param
     * @return
     * @throws BizException
     */
    @PostMapping(value = "/${classMapping}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseData<${voClassName}> create(@RequestBody ${dtoClassName} param) throws BizException;

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws BizException
     */
    @GetMapping(value = "/${classMapping}/{id}")
    ResponseData<${voClassName}> get(@PathVariable(value = "id") String id) throws BizException;

    /**
     * 更新
     *
     * @param id
     * @param param
     * @return
     * @throws BizException
     */
    @PutMapping(value = "/${classMapping}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    SimpleResponseData update(@PathVariable(value = "id") String id, @RequestBody ${dtoClassName} param) throws BizException;

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     * @throws BizException
     */
    @DeleteMapping(value = "/${classMapping}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    SimpleResponseData delete(@PathVariable(value = "id") String id) throws BizException;

    /**
     * 查询
     *
     * @param queryParam 对应${paramClassName}类
     * @return
     * @throws BizException
     */
    @PostMapping(value = "/${classMapping}/query", consumes = MediaType.APPLICATION_JSON_VALUE)
    ListResponseData<${voClassName}> query(@RequestBody ${paramClassName} queryParam) throws BizException;

    /**
     * 查询所有
     *
     * @param startRow
     * @param pageSize
     * @return
     * @throws BizException
     */
    @GetMapping(value = "/${classMapping}/queryAll")
    ListResponseData<${voClassName}> queryAll(@RequestParam(value = "startRow", required = false) Integer startRow,
        @RequestParam(value = "pageSize", required = false) Integer pageSize) throws BizException;
}