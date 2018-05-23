package cn.lu.hipster.model;

import lombok.Data;

/**
 * 数据库连接信息，目前只支持MySQL
 *
 * @author lutiehua
 * @date 2017/09/22
 */
@Data
public class DatabaseInfo {

    private String dbType;

    /**
     * MysQL地址
     */
    private String dbIP;

    /**
     * MysQL端口
     */
    private Integer dbPort;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * MysQL用户名
     */
    private String dbUsername;

    /**
     * MysQL密码
     */
    private String dbPassword;
}