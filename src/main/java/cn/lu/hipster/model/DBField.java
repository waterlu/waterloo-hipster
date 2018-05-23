package cn.lu.hipster.model;

/**
 * 数据库表字段信息
 *
 * @author lutiehua
 * @date 2017/09/22
 */
public class DBField {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 类型
     */
    private String typeName;

    /**
     * 类型（INT值）
     */
    private int dataType;

    /**
     * 长度
     */
    private int columnSize;

    /**
     * 小数点
     */
    private int decimalDigits;

    /**
     * 是否可空
     */
    private boolean isNullable = true;

    /**
     * 是否主键
     */
    private boolean isPrimaryKey = false;

    /**
     * 注释
     */
    private String remarks;

    /**
     * 默认值
     */
    private Object columnDef;


    /**
     * 是否自增
     */
    private boolean isAutoIncrement = false;

    /**
     * 字符最大字节数
     */
    private int charOctetLength;

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public boolean isQuery() {
        return query;
    }

    public void setQuery(boolean query) {
        this.query = query;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * 前端用
     */
    private boolean key = false;

    /**
     * 前端用
     */
    private boolean query = false;

    /**
     * 前端用
     */
    private String order = "None";

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Object getColumnDef() {
        return columnDef;
    }

    public void setColumnDef(Object columnDef) {
        this.columnDef = columnDef;
    }

    public int getCharOctetLength() {
        return charOctetLength;
    }

    public void setCharOctetLength(int charOctetLength) {
        this.charOctetLength = charOctetLength;
    }
}
