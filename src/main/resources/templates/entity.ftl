package ${classPackage};

<#list imports as import>
import ${import.name};
</#list>

/**
 * ${classRemark}
 *
 * @author ${author}
 * @date ${date}
 */
public class ${className} implements Serializable {
    <#list fields as field>

    /**
    * ${field.remark}
    */
    private ${field.type} ${field.name};
    </#list>

    <#list fields as field>

    public ${field.type} get${field.name?cap_first}() {
        return ${field.name};
    }

    public void set${field.name?cap_first}(${field.type} ${field.name}) {
        this.${field.name} = ${field.name};
    }
    </#list>

}