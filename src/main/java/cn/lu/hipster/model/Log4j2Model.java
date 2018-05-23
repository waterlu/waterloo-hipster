package cn.lu.hipster.model;


/**
 * Log4j2文件数据模型
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class Log4j2Model extends DataModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    private String name;

    private String mapperPackage;

}
