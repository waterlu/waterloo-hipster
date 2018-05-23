package cn.lu.hipster.core;

import cn.lu.hipster.model.*;
import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 生成Java类的基类
 *
 * 数据都封装在JavaClassModel，基类处理时间、作者、包名、类名、类说明、文件名
 *
 * @author lutiehua
 * @date 2017/11/14
 */
public abstract class AbstractGeneratedJavaClass extends AbstractGeneratedFile {

    /**
     * 模型数据
     */
    protected JavaClassModel model;

    /**
     * 生成文件名
     */
    protected String fileName;

    /**
     * 数据库表信息
     */
    protected TableInfo tableInfo;

    /**
     * 需要导入的包
     *
     */
    protected Map<String, String> importPackage = new HashMap<>();

    /**
     * 属性列表
     *
     */
    protected List<JavaField> javaFieldList = new ArrayList<>();

    public AbstractGeneratedJavaClass(GeneratorParam generatorParam, TableInfo tableInfo) {
        super(generatorParam);
        this.tableInfo = tableInfo;

        model = new JavaClassModel();

        // 日期
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(now);
        model.setDate(date);

        // 作者
        String author = generatorParam.getPackageInfo().getAuthor();
        model.setAuthor(author);

        // 包名
        String basePackageName = generatorParam.getPackageInfo().getBasePackage();
        String packageName = basePackageName + "." + getJavaPackageName();
        model.setPackageName(packageName);

        // 类名
        String className = getJavaClassName();
        model.setClassName(className);

        // 类说明
        String classRemark = getJavaClassRemark();
        model.setClassRemark(classRemark);

        // 文件名
        String rootDir = generatorParam.getPackageInfo().getProjectPath();
        String javaPath = generatorParam.getPackageInfo().getJavaPath();
        List<String> list = Splitter.on(".").splitToList(packageName);
        StringBuffer buffer = new StringBuffer();
        for (String path : list) {
            buffer.append(path);
            buffer.append("/");
        }
        String packagePath = buffer.toString();
        fileName = rootDir + "/" + javaPath + "/" + packagePath + className + ".java";
    }

    public String parseJavaImportType(String type) {
        if (importPackage.containsKey(type)) {
            return importPackage.get(type);
        } else {
            int pos = type.lastIndexOf(".");
            if (pos > 0) {
                String javaType = type.substring(pos + 1);
                importPackage.put(type, javaType);
                return javaType;
            } else {
                return type;
            }
        }
    }

    /**
     * 包名
     *
     * @return
     */
    public abstract String getJavaPackageName();

    /**
     * 留给子类覆盖用
     *
     * @return
     */
    protected String getJavaClassName() {
        String tableName = tableInfo.getName();
        String modelClassName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
        String className = modelClassName + getJavaClassNamePostfix();
        return className;
    }

    /**
     * 类名后缀
     *
     * @return
     */
    public abstract String getJavaClassNamePostfix();

    /**
     * 留给子类覆盖用
     *
     * @return
     */
    protected String getJavaClassRemark() {
        String classRemark = tableInfo.getRemark();
        classRemark = classRemark + getJavaClassRemarkPostfix();
        return classRemark;
    }

    /**
     * 类说明后缀
     *
     * @return
     */
    public abstract String getJavaClassRemarkPostfix();

    /**
     * 变量数据
     *
     * @return
     */
    @Override
    public DataModel getDataModel() {
        return model;
    }

    /**
     * 文件名称
     *
     * @return
     */
    @Override
    public String getFileName() {
        return fileName;
    }

    /**
     * 生成文件前的准备工作
     */
    @Override
    public void beforeGenerateFile() {
        super.beforeGenerateFile();

        if (importPackage.size() > 0) {
            List<JavaImport> importList = new ArrayList<>();
            for (Map.Entry<String, String> entry : importPackage.entrySet()) {
                JavaImport javaImport = new JavaImport();
                javaImport.setName(entry.getKey());
                importList.add(javaImport);
            }

            model.setImports(importList);
        }

        if (javaFieldList.size() > 0) {
            model.setFields(javaFieldList);
        }
    }
}
