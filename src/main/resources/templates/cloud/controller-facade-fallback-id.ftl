package ${packageName};

import org.springframework.stereotype.Component;
import cn.lu.web.mvc.BizException;
import cn.lu.web.mvc.ListResponseData;
import cn.lu.web.mvc.ResponseData;
import cn.lu.web.mvc.SimpleResponseData;
import cn.lu.web.mvc.ResponseCode;
import cn.lu.web.vo.ListResultVO;

<#list imports as import>
import ${import.name};
</#list>

/**
 * ${classRemark}接口降级逻辑
 *
 * @author ${author}
 * @date ${date}
 */
@Component
public class ${className} implements ${facadeClassName} {

    /**
     * 创建
     *
     * @param param
     * @return
     * @throws BizException
     */
    @Override
    public ResponseData<${voClassName}> create(${dtoClassName} param) throws BizException {
        return new ResponseData(ResponseCode.UNAVAILABLE);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    public ResponseData<${voClassName}> get(Long id) throws BizException {
        return new ResponseData(ResponseCode.UNAVAILABLE);
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
    public SimpleResponseData update(Long id, ${dtoClassName} param) throws BizException {
        return new SimpleResponseData(ResponseCode.UNAVAILABLE);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    public SimpleResponseData delete(Long id) throws BizException {
        return new SimpleResponseData(ResponseCode.UNAVAILABLE);
    }

    /**
     * 查询
     *
     * @param queryParam 对应${paramClassName}类
     * @return
     * @throws BizException
     */
    @Override
    public ListResponseData<${voClassName}> query(${paramClassName} queryParam) throws BizException {
        return new ListResponseData(ResponseCode.UNAVAILABLE);
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
    public ListResponseData<${voClassName}> queryAll(Integer startRow, Integer pageSize) throws BizException {
        return new ListResponseData(ResponseCode.UNAVAILABLE);
    }
}