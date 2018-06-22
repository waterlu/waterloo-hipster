package ${packageName};

import cn.lu.web.base.BaseService;
<#list imports as import>
import ${import.name};
</#list>

/**
 * ${classRemark}
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${className} extends BaseService<${modelClassName}, ${paramClassName}> {

}