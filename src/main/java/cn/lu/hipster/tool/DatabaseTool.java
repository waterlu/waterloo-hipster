package cn.lu.hipster.tool;

import cn.lu.hipster.consts.DBType;
import cn.lu.hipster.model.DatabaseInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 数据库工具类
 *
 * @author lu
 * @date 2018/5/23
 */
public class DatabaseTool {

    /**
     * 获取数据库连接
     *
     * @param databaseInfo
     * @return
     * @throws Exception
     */
    public static Connection getConnection(DatabaseInfo databaseInfo) throws Exception {
        DriverManager.setLoginTimeout(3);
        DBType dbType = DBType.valueOf(databaseInfo.getDbType());
        Class.forName(dbType.getDriverClass());
        String url = getConnectionURL(databaseInfo);
        Properties props =new Properties();
        props.setProperty("user", databaseInfo.getDbUsername());
        props.setProperty("password", databaseInfo.getDbPassword());
        //设置可以获取remarks信息
        props.setProperty("remarks", "true");
        //设置可以获取tables remarks信息
        props.setProperty("useInformationSchema", "true");
        Connection connection = DriverManager.getConnection(url, props);
        return connection;
    }

    /**
     *
     * @param databaseInfo
     * @return
     * @throws ClassNotFoundException
     */
    private static String getConnectionURL(DatabaseInfo databaseInfo) throws ClassNotFoundException {
        DBType dbType = DBType.valueOf(databaseInfo.getDbType());
        String connectionRUL = String.format(dbType.getConnectionUrlPattern(), databaseInfo.getDbIP(),
                databaseInfo.getDbPort(), databaseInfo.getDbName());
        return connectionRUL;
    }

}
