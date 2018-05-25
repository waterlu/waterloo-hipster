package cn.lu.hipster.core;

import cn.lu.hipster.model.GeneratorParam;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;

/**
 * 生成Hipster配置文件
 *
 * @author lu
 * @date 2018/5/25
 */
public class GeneratedHipsterConfigFile implements Generator {

    @Override
    public void generateCode(GeneratorParam generatorParam) throws Exception {
        String rootDir = generatorParam.getPackageInfo().getProjectPath();
        String resourcePath = generatorParam.getPackageInfo().getResourcePath();
        String fileName = rootDir + "/" + resourcePath + "/" + "hipster-config.json";

        // 创建空文件
        int pos = fileName.lastIndexOf("/");
        String fileDir = fileName.substring(0, pos + 1);

        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        // 根据模板生成文件
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        JSON.writeJSONString(writer, generatorParam, new SerializerFeature[]{SerializerFeature.PrettyFormat});
    }
}
