package cn.lu.hipster.api;

import cn.lu.hipster.consts.DBDataType;
import cn.lu.hipster.core.Generator;
import cn.lu.hipster.core.MybatisProgressCallback;
import cn.lu.hipster.core.MybatisShellCallback;
import cn.lu.hipster.model.*;
import cn.lu.hipster.tool.DatabaseTool;
import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成实体类对象和MAPPER文件
 *
 * @author lutiehua
 * @date 2017/11/09
 */
@Component
public class MybatisGenerator implements Generator {

    @Value("${mapper.base.class}")
    private String mapperBaseClass = "cn.lu.web.mapper.CrudMapper";

    @Value("${entity.base.class}")
    private String entityBaseClass = "cn.lu.web.base.BaseEntity";

    private MybatisShellCallback shellCallback = new MybatisShellCallback();

    private MybatisProgressCallback progressCallback = new MybatisProgressCallback();

    /**
     * 自动生成代码
     *
     * @param generatorParam
     */
    @Override
    public void generateCode(GeneratorParam generatorParam) throws Exception {
        for (TableInfo tableInfo : generatorParam.getTables()) {
            generateModelAndMapper(generatorParam, tableInfo);
        }
    }

    /**
     * 根据表结构自动生成持久化代码
     *
     * @param generatorParam
     * @param tableInfo
     */
    private void generateModelAndMapper(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {
        Context context = new Context(ModelType.FLAT);
        context.setId("waterlu");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        // 数据库连接配置，目前只支持MySQL
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        String dbIP = generatorParam.getDatabaseInfo().getDbIP();
        int dbPort = generatorParam.getDatabaseInfo().getDbPort();
        String dbName = generatorParam.getDatabaseInfo().getDbName();
        String jdbcUrl = String.format("jdbc:mysql://%s:%d/%s", dbIP, dbPort, dbName);
        String dbUsername = generatorParam.getDatabaseInfo().getDbUsername();
        String dbPassword = generatorParam.getDatabaseInfo().getDbPassword();
        jdbcConnectionConfiguration.setConnectionURL(jdbcUrl);
        jdbcConnectionConfiguration.setUserId(dbUsername);
        jdbcConnectionConfiguration.setPassword(dbPassword);
        jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        // TKMybatis插件
        PluginConfiguration pluginConfiguration = new PluginConfiguration();

        // 增加@Repository注解
        pluginConfiguration.setConfigurationType("cn.lu.hipster.core.MybatisMapperPlugin");

        // 基础表名
        String tableName = tableInfo.getName();

        // 基础类名
        String modelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

        // Package信息
        PackageInfo packageInfo = generatorParam.getPackageInfo();

        // 基础包名
        String basePackage = packageInfo.getBasePackage();

        // 类名
        String insertListClassName = String.format("%s.%s.%sInsertListMapper", basePackage, packageInfo.getDaoPackage(), modelName);

        // ID主键，定义InsertListMapper，并指定主键
//        String basicClassName = MAPPER_INTERFACE_ID + insertListClassName;

//        pluginConfiguration.addProperty("mappers", basicClassName);
        pluginConfiguration.addProperty("mappers", mapperBaseClass);
        pluginConfiguration.addProperty("forceAnnotation", "true");
        pluginConfiguration.addProperty("beginningDelimiter", "`");
        pluginConfiguration.addProperty("endingDelimiter", "`");
        context.addPluginConfiguration(pluginConfiguration);

        // Java实体类
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        String projectPath = generatorParam.getPackageInfo().getProjectPath();
        String javaPath = generatorParam.getPackageInfo().getJavaPath();
        javaModelGeneratorConfiguration.setTargetProject(projectPath + "/" + javaPath);
//        String basePackage = generatorParam.getPackageInfo().getBasePackage();
        String entityPackage = generatorParam.getPackageInfo().getEntityPackage();
        javaModelGeneratorConfiguration.setTargetPackage(basePackage + "." + entityPackage);
        if (!Strings.isNullOrEmpty(entityBaseClass)) {
            // 设置实体类的基类
            javaModelGeneratorConfiguration.addProperty("rootClass", entityBaseClass);
        }
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        // Mapper.xml文件
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        String resourcePath = generatorParam.getPackageInfo().getResourcePath();
        sqlMapGeneratorConfiguration.setTargetProject(projectPath + "/" +  resourcePath);
        sqlMapGeneratorConfiguration.setTargetPackage("mybatis/mapper");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        // JAVA Mapper类
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(projectPath + "/" + javaPath);
        String daoPackage = generatorParam.getPackageInfo().getDaoPackage();
        javaClientGeneratorConfiguration.setTargetPackage(basePackage + "." + daoPackage);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        // 数据源表
//        String tableName = tableInfo.getName();
//        String modelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (!Strings.isNullOrEmpty(modelName)) {
            tableConfiguration.setDomainObjectName(modelName);
        }

        List<DBField> dbFieldList = getTableColumns(generatorParam.getDatabaseInfo(), tableName);
        for (DBField field : dbFieldList) {
            // 设置JavaType，TINYINT(Byte => Integer)
            if (field.getDataType() == DBDataType.TINYINT || field.getDataType() == DBDataType.SMALLINT) {
                ColumnOverride columnOverride = new ColumnOverride(field.getColumnName());
                columnOverride.setJavaType("java.lang.Integer");
                tableConfiguration.addColumnOverride(columnOverride);
            }

            // 主键生成
            if (field.isPrimaryKey()) {
                if (field.getDataType() == DBDataType.BIGINT) {
                    tableConfiguration.setGeneratedKey(new GeneratedKey(field.getColumnName(), "JDBC", true, null));
                }
            }
        }

        context.addTableConfiguration(tableConfiguration);

        List<String> warnings = new ArrayList<>();
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            generator = new MyBatisGenerator(config, shellCallback, warnings);
            generator.generate(progressCallback);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (Strings.isNullOrEmpty(modelName)) {
            modelName = tableNameConvertUpperCamel(tableName);
        }
    }

    /**
     * 数据库表名 => 实体对象名
     * lower_underscore => lowerCamel
     *
     * @param tableName
     * @return
     */
    private String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }


    /**
     * 读取表结构
     *
     * @return
     * @throws Exception
     */
    protected List<DBField> getTableColumns(DatabaseInfo databaseInfo, String tableName) throws Exception {
        Connection connection = DatabaseTool.getConnection(databaseInfo);
        DatabaseMetaData metaData = connection.getMetaData();
        String primaryKeyFiledName = getTablePrimaryKey(metaData, tableName);
        ResultSet resultSet = metaData.getColumns(null, null, tableName, null);
        List<DBField> fieldList = new ArrayList<>();
        while (resultSet.next()) {
            DBField field = new DBField();
            field.setColumnName(resultSet.getString("COLUMN_NAME"));
            field.setDataType(resultSet.getInt("DATA_TYPE"));
            field.setTypeName(resultSet.getString("TYPE_NAME"));
            if (primaryKeyFiledName.equalsIgnoreCase(field.getColumnName())) {
                field.setPrimaryKey(true);
            } else {
                field.setPrimaryKey(false);
            }
            fieldList.add(field);
        }

        return fieldList;
    }

    /**
     * 读取主键
     *
     * @param metaData
     * @param tableName
     * @return
     * @throws Exception
     */
    protected String getTablePrimaryKey(DatabaseMetaData metaData, String tableName) throws Exception {
        ResultSet rs = metaData.getPrimaryKeys(null, null, tableName);
        while (rs.next()) {
            return rs.getString("COLUMN_NAME");
        }

        return null;
    }
}
