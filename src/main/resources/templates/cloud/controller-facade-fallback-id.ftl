package ${packageName};

import org.springframework.stereotype.Component;
import cn.lu.web.mvc.BizException;
import cn.lu.web.mvc.ResponseResult;
import cn.lu.web.mvc.ResponseCode;
import cn.lu.web.vo.ListResultVO;

<#list imports as import>
import ${import.name};
</#list>

/**
 * ${classRemark}接口
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
    public ResponseResult<${voClassName}> create(${dtoClassName} param) throws BizException {
        ResponseResult responseResult = new ResponseResult(ResponseCode.UNAVAILABLE);
        return responseResult;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    public ResponseResult<${voClassName}> get(Long id) throws BizException {
        ResponseResult responseResult = new ResponseResult(ResponseCode.UNAVAILABLE);
        return responseResult;
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
    public ResponseResult<String> update(Long id, ${dtoClassName} param) throws BizException {
        ResponseResult responseResult = new ResponseResult(ResponseCode.UNAVAILABLE);
        return responseResult;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws BizException
     */
    @Override
    public ResponseResult<String> delete(Long id) throws BizException {
        ResponseResult responseResult = new ResponseResult(ResponseCode.UNAVAILABLE);
        return responseResult;
    }

    /**
     * 查询
     *
     * @param queryParam 对应${paramClassName}类
     * @return
     * @throws BizException
     */
    @Override
    public ResponseResult<ListResultVO<${voClassName}>> query(${paramClassName} queryParam) throws BizException {
        ResponseResult responseResult = new ResponseResult(ResponseCode.UNAVAILABLE);
        return responseResult;
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
    public ResponseResult<ListResultVO<${voClassName}>> queryAll(Integer startRow, Integer pageSize) throws BizException {
        ResponseResult responseResult = new ResponseResult(ResponseCode.UNAVAILABLE);
        return responseResult;
    }
}