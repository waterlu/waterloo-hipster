package cn.lu.hipster.api;

import cn.lu.hipster.model.DBField;
import cn.lu.hipster.model.DBTable;
import cn.lu.hipster.model.DatabaseInfo;
import cn.lu.web.mvc.ResponseData;
import cn.lu.web.mvc.ResponseResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;

import static cn.lu.hipster.tool.DatabaseTool.getConnection;

/**
 * 读取库表接口
 *
 * @author lutiehua
 * @date 2017/9/22
 */
@RestController
@RequestMapping("/api/database")
public class DatabaseController {

    /**
     * 用于Boolean值判断
     */
    private final static String YES = "YES";

    /**
     * TINYINT数据类型
     */
    private final static String TINYINT = "TINYINT";

    /**
     * UNSIGNED TINYINT数据类型
     */
    private final static String UNSIGNED_TINYINT = "TINYINT UNSIGNED";

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public ResponseResult connect(@RequestBody DatabaseInfo databaseInfo) throws Exception {
        String schema = getConnection(databaseInfo).getSchema();
        ResponseData<String> responseData = new ResponseData<>(schema);
        ResponseResult responseResult = new ResponseResult(responseData);
        return responseResult;
    }

    @RequestMapping(value = "/tables", method = RequestMethod.POST)
    public ResponseResult getTables(@RequestBody DatabaseInfo databaseInfo) throws Exception {
        List<DBTable> tableList = getTableNames(databaseInfo);
        ResponseData responseData = new ResponseData(tableList);
        ResponseResult responseResult = new ResponseResult(responseData);
        return responseResult;
    }

    @RequestMapping(value = "/tables/{tableName}", method = RequestMethod.POST)
    public ResponseResult getTableColumns(@PathVariable String tableName, @RequestBody DatabaseInfo databaseInfo) throws Exception {
        List<DBField> fieldList = getTableColumns(databaseInfo, tableName);
        ResponseData responseData = new ResponseData(fieldList);
        ResponseResult responseResult = new ResponseResult(responseData);
        return responseResult;
    }

    private List<DBTable> getTableNames(DatabaseInfo databaseInfo) throws Exception {
        Connection connection = getConnection(databaseInfo);
        List<DBTable> tables = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        String[] types = { "TABLE", "VIEW" };
        ResultSet resultSet = metaData.getTables(null, databaseInfo.getDbUsername().toUpperCase(), null, types);
        while (resultSet.next()) {
            DBTable table = new DBTable();
            table.setName(resultSet.getString("TABLE_NAME"));
            table.setType(resultSet.getString("TABLE_TYPE"));
            table.setRemark(resultSet.getString("REMARKS"));
            tables.add(table);
        }
        resultSet.close();
        return tables;
    }

    private List<DBField> getTableColumns(DatabaseInfo databaseInfo, String tableName) throws Exception {
        Connection connection = getConnection(databaseInfo);
        DatabaseMetaData metaData = connection.getMetaData();
        String primaryKeyFiledName = getTablePrimaryKey(metaData, tableName);
        ResultSet resultSet = metaData.getColumns(null, null, tableName, null);
        List<DBField> fieldList = new ArrayList<>();
        while (resultSet.next()) {
            DBField field = new DBField();
            field.setColumnName(resultSet.getString("COLUMN_NAME"));
            field.setDataType(resultSet.getInt("DATA_TYPE"));
            field.setTypeName(resultSet.getString("TYPE_NAME"));
            field.setColumnSize(resultSet.getInt("COLUMN_SIZE"));
            field.setDecimalDigits(resultSet.getInt("DECIMAL_DIGITS"));
            field.setRemarks(resultSet.getString("REMARKS"));
            field.setNullable(YES.equalsIgnoreCase(resultSet.getString("IS_NULLABLE")));
            field.setAutoIncrement(YES.equalsIgnoreCase(resultSet.getString("IS_AUTOINCREMENT")));
            field.setColumnDef(resultSet.getObject("COLUMN_DEF"));
            field.setCharOctetLength(resultSet.getInt("CHAR_OCTET_LENGTH"));

            if (null != primaryKeyFiledName) {
                if (field.getColumnName().equalsIgnoreCase(primaryKeyFiledName)) {
                    field.setPrimaryKey(true);
                    field.setKey(true);
                } else {
                    field.setPrimaryKey(false);
                    field.setKey(false);
                }
            }

            fieldList.add(field);
        }

        resultSet.close();

        // 唯一索引MAP
        Map<String, String> uniqueIndexMap = new HashMap<>(20);

        // 索引MAP
        Map<String, String> indexMap = new HashMap<>(20);

        resultSet = metaData.getIndexInfo(null, null, tableName, false, false);
        while (resultSet.next()) {
            String indexName = resultSet.getString("INDEX_NAME");
            String columnName = resultSet.getString("COLUMN_NAME");
            boolean nonUnique  = resultSet.getBoolean("NON_UNIQUE");

            // 唯一索引
            if (!nonUnique) {
                if (!uniqueIndexMap.containsKey(indexName)) {
                    uniqueIndexMap.put(indexName, columnName);
                } else {
                    // 组合唯一索引
                    String value = uniqueIndexMap.get(indexName);
                    value = value + "," + columnName;
                    uniqueIndexMap.put(indexName, value);
                }
            }

            // 索引
            indexMap.put(columnName, columnName);
        }

//        // 设置key
//        for (String columnName : uniqueIndexMap.values()) {
//            // 联合索引不作为key
//            if (columnName.indexOf(",") == -1) {
//                for(DBField field: fieldList) {
//                    if (field.getColumnName().equalsIgnoreCase(columnName)) {
//                        field.setKey(true);
//                    }
//                }
//            }
//        }

        // 设置query
        for(DBField field: fieldList) {
            // key不作为查询条件
            if (field.isKey()) {
                continue;
            }

            // delete_flag不作为查询条件
            if ("delete_flag".equalsIgnoreCase(field.getColumnName())) {
                continue;
            }

            // tinyint作为查询条件
            if (TINYINT.equalsIgnoreCase(field.getTypeName()) || UNSIGNED_TINYINT.equalsIgnoreCase(field.getTypeName())) {
                field.setQuery(true);
            } else {
                // 索引作为查询条件
                if (indexMap.containsKey(field.getColumnName())) {
                    field.setQuery(true);
                }
            }
        }

        return fieldList;
    }

    private String getTablePrimaryKey(DatabaseMetaData metaData, String tableName) throws Exception {
        ResultSet rs = metaData.getPrimaryKeys(null, null, tableName);
        String key = null;
        while (rs.next()) {
            key = rs.getString("COLUMN_NAME");
        }
        rs.close();
        return key;
    }
}
