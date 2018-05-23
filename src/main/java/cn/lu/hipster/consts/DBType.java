package cn.lu.hipster.consts;

/**
 * 数据库类型
 *
 * @author lutiehua
 * @date 2017/9/27
 */
public enum DBType {
    /**
     * Oracle数据库
     */
	Oracle("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%d:%s"),

    /**
     * MySQL数据库
     */
    MySQL("com.mysql.jdbc.Driver", "jdbc:mysql://%s:%d/%s?useSSL=false&characterEncoding=utf-8"),

    /**
     * PostgreSQL数据库
     */
    PostgreSQL("org.postgresql.Driver", "jdbc:postgresql://%s:%d/%s");

	private final String driverClass;
    private final String connectionUrlPattern;

    DBType(String driverClass, String connectionUrlPattern) {
        this.driverClass = driverClass;
        this.connectionUrlPattern = connectionUrlPattern;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getConnectionUrlPattern() {
        return connectionUrlPattern;
    }
}