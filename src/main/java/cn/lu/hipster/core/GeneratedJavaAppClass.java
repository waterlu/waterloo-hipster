package cn.lu.hipster.core;

import cn.lu.hipster.model.DataModel;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.model.JavaClassModel;
import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 生成Application类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaAppClass extends AbstractGeneratedFile {

    protected JavaClassModel model;

    private String fileName;

    public GeneratedJavaAppClass(GeneratorParam generatorParam) {
        super(generatorParam);

        model = new JavaClassModel();

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(now);
        String packageName = generatorParam.getPackageInfo().getBasePackage();
        String author = generatorParam.getPackageInfo().getAuthor();
        String projectName = generatorParam.getProjectInfo().getName();
        projectName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, projectName.toLowerCase());
        String className = projectName + "Application";
        String classRemark = generatorParam.getProjectInfo().getDescription();
        String mapperPackageName = packageName + "." + generatorParam.getPackageInfo().getDaoPackage();

        model.setBasePackage(generatorParam.getProjectInfo().getGroupId());
        model.setPackageName(packageName);
        model.setAuthor(author);
        model.setDate(date);
        model.setClassName(className);
        model.setClassRemark(classRemark);
        model.setMapperPackage(mapperPackageName);
        model.setProjectType(generatorParam.getProjectInfo().getProjectType());

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

    /**
     * 模板
     *
     * @return
     */
    @Override
    public String getTemplateName() {
        return "app.ftl";
    }

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
}
