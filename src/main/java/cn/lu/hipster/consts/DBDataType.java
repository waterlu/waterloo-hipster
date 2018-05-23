package cn.lu.hipster.consts;

/**
 * 数据库字段类型
 *
 * @author lutiehua
 * @date 2017/9/27
 */
public interface DBDataType {

    int CHAR = 1;       // CHAR / JSON

    int VARCHAR = 12;   // VARCHAR

    int TINYINT = -6;   // TINYINT / UNSIGNED TINYINT

    int INT = 4;        // INT / UNSIGNED INT

    int BIGINT = -5;    // BIGINT

    int DATETIME = 93;  // DATETIME

    int DECIMAL = 3;    // DECIMAL / UNSIGNED DECIMAL



    int BLOB = -4;

    int TEXT = -1;

    int SMALLINT = 5;

    int FLOAT = 7;

    int DOUBLE = 8;

    int DATE = 91;

    int TIME = 92;


}
