package cn.lu.hipster.api;

import cn.lu.hipster.model.*;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import io.swagger.models.*;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;
import io.swagger.parser.SwaggerParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * 读取Swagger返回的JSON串并生成Markdown格式文档
 *
 * @author lutiehua
 * @date 2018/5/24
 */
@Component
public class Swagger2Markdown {

    private final Logger logger = LoggerFactory.getLogger(Swagger2Markdown.class);

    private String templateName = "doc.ftl";

    private final static Splitter SPLITTER = Splitter.on("Using");

    private final static String RESPONSE_OK = "200";

    private final static String TAG_LIST = "List";

    /**
     * 文档转换接口
     *
     * @param sourcePath swagger.json源文件
     * @param outputPath 文档输出目录
     * @throws Exception
     */
    public void convert(String sourcePath, String outputPath) throws Exception {
        // 参数校验
        if(Strings.isNullOrEmpty(sourcePath)) {
            throw new IllegalArgumentException("sourcePath不能为空");
        }
        if(Strings.isNullOrEmpty(outputPath)) {
            throw new IllegalArgumentException("outputPath不能为空");
        }

        // 读取并解析swagger.json源文件
        Swagger swagger = readSwagger(sourcePath);

        // 解析
        Map<String, Model> definitions = swagger.getDefinitions();
        Map<String, List<DocParamModel>> definitionResult = readSwaggerDefinitions(definitions);

        // 生成markdown文档
        generateDocFile(swagger.getPaths(), outputPath, definitionResult);
        logger.info("convert to markdown document is done");
    }

    /**
     * 解析swagger.json
     *
     * @param swaggerPath
     * @return
     */
    private Swagger readSwagger(String swaggerPath) {
        logger.info("begin to parse swagger.json");
        Swagger swagger = new SwaggerParser().read(swaggerPath);
        if (swagger == null) {
            throw new IllegalArgumentException("解析swagger.json失败");
        }
        logger.info("parse swagger.json is done");
        return swagger;
    }

    /**
     * 解析预定义对象
     *
     * @param definitions
     * @return
     */
    private Map<String, List<DocParamModel>> readSwaggerDefinitions(Map<String, Model> definitions) {
        logger.info("begin to build swagger definitions");
        Map<String, List<DocParamModel>> definitionResult = new HashMap<>();
        List<DocParamModel> dataModelList;
        DocParamModel dataModel;
        if (definitions != null) {
            for (Map.Entry<String, Model> definition : definitions.entrySet()) {
                dataModelList = new ArrayList<>();
                Map<String, Property> properties = definition.getValue().getProperties();
                if (properties != null) {
                    for (Map.Entry<String, Property> property : properties.entrySet()) {
                        dataModel = new DocParamModel(property.getKey(), property.getValue().getRequired(),
                                property.getValue().getType(), property.getValue().getDescription());
                        dataModelList.add(dataModel);
                    }
                    definitionResult.put(definition.getKey(), dataModelList);
                    logger.info("load definitions:{}", definition.getKey());
                }
            }
        }

        return definitionResult;
    }

    /**
     * 生成Markdown文件
     *
     * @param paths swagger目录
     * @param filePath markdown目录
     * @param dataModel
     * @throws Exception
     */
    public void generateDocFile(Map<String, Path> paths, String filePath,
                                Map<String, List<DocParamModel>> dataModel) throws Exception {
        logger.info("begin generate doc file");
        for(Map.Entry<String, Path> entry : paths.entrySet()) {
            String key = entry.getKey();
            Path path = entry.getValue();
            for(Operation operation : path.getOperations()) {
                // 遍历每一个接口，生成接口文档
                DocInterfaceModel interfaceModel = buildInterfaceModel(key, operation, dataModel);
                generateFile(filePath, interfaceModel);
            }
        }
    }

    /**
     * 接口数据模型
     *
     * @param key
     * @param operation
     * @param dataModel
     * @return
     */
    private DocInterfaceModel buildInterfaceModel(String key, Operation operation,
                                                  Map<String, List<DocParamModel>> dataModel) {
        DocInterfaceModel model = new DocInterfaceModel();

        // 基本信息
        model.setName(operation.getSummary());
        model.setDesc(operation.getDescription());
        model.setUrl(key);
        List<String> split = SPLITTER.splitToList(operation.getOperationId());
        model.setMethod(split.get(split.size() - 1).split("_")[0]);

        // 请求参数
        DocRequestModel requestModel = buildRequestModel(operation, dataModel, model);
        model.setRequest(requestModel);

        // 返回结果
        DocResponseModel responseModel = buildResponseModel(operation, dataModel);
        model.setResponse(responseModel);

        return model;
    }

    /**
     * 请求参数数据模型
     *
     * @param operation
     * @param dataModel
     * @return
     */
    private DocRequestModel buildRequestModel(Operation operation, Map<String, List<DocParamModel>> dataModel,
                                              DocInterfaceModel interfaceModel) {
        DocRequestModel requestModel = new DocRequestModel();
        List<DocParamModel> paramList = new ArrayList<>();

        // 解析参数
        for (Parameter parameter : operation.getParameters()) {
            if (parameter instanceof BodyParameter) {
                Model model = ((BodyParameter)parameter).getSchema();
                if (model instanceof RefModel) {
                    paramList.addAll(dataModel.get(((RefModel) model).getSimpleRef()));
                } else if (model instanceof ModelImpl) {
                    DocParamModel paramModel = new DocParamModel();
                    paramModel.setName(parameter.getName());
                    paramModel.setRequired(parameter.getRequired());
                    paramModel.setType(parameter.getIn());
                    paramModel.setDesc(parameter.getDescription());
                    paramList.add(paramModel);
                }
            } else if (parameter instanceof QueryParameter){
                // RequestParam 参数
                DocParamModel paramModel = new DocParamModel();
                paramModel.setName(parameter.getName());
                paramModel.setRequired(parameter.getRequired());
                QueryParameter queryParameter = (QueryParameter)parameter;
                paramModel.setType(queryParameter.getType());
                paramModel.setDesc(parameter.getDescription());
                paramList.add(paramModel);
            }
        }

        requestModel.setParams(paramList);

        // 例子代码
        String example = buildRequestSample(interfaceModel, paramList);
        requestModel.setSample(example);
        return requestModel;
    }

    /**
     * 请求参数例子
     *
     * @param model
     * @param params
     * @return
     */
    private String buildRequestSample(DocInterfaceModel model, List<DocParamModel> params) {
        String sample;
        Map<String, Object> paramsMap = buildMockData(params);
        if (Objects.equals(model.getMethod(), HttpMethod.GET.name())) {
            final String[] paramStr = {""};
            paramsMap.forEach((k, v) -> paramStr[0] += k + "=" + v + "&");
            if (!Strings.isNullOrEmpty(paramStr[0])) {
                sample = model.getMethod() + " " + model.getUrl() + "?" + paramStr[0].substring(0, paramStr[0].length() - 1);
            } else {
                sample = model.getMethod() + " " + model.getUrl();
            }
        } else {
            if (paramsMap.size() > 0) {
                sample = model.getMethod() + " " + model.getUrl() + "\n\nBody\n" + format(JSON.toJSONString(paramsMap));
            } else {
                sample = model.getMethod() + " " + model.getUrl();
            }
        }
        return sample;
    }

    /**
     * 返回结果数据模型
     *
     * @param operation
     * @param dataModel
     * @return
     */
    private DocResponseModel buildResponseModel(Operation operation, Map<String, List<DocParamModel>> dataModel) {
        DocResponseModel responseModel = new DocResponseModel();
        Map<String, Response> responses = operation.getResponses();
        for(Map.Entry<String, Response> responseEntry : responses.entrySet()){
            String key = responseEntry.getKey();
            Response response = responseEntry.getValue();
            // 所有请求都返回 HTTP 200
            if (!RESPONSE_OK.equalsIgnoreCase(key)) {
                continue;
            }

            Property schema = response.getSchema();
            if (schema instanceof StringProperty) {
                // 字符串类型返回值示例
                DocResponseData result = new DocResponseData();
                result.setCode(200);
                result.setMessage("成功");
                List<DocParamModel> paramList = new ArrayList<>();
                DocParamModel paramModel = new DocParamModel();
                paramModel.setName("data");
                paramModel.setType("string");
                paramModel.setRequired(true);
                paramModel.setDesc("返回数据");
                paramList.add(paramModel);
                responseModel.setResult(paramList);
                result.setData("string");
                responseModel.setSample(format(JSON.toJSONString(result)));
                continue;
            }

            if ((schema instanceof RefProperty)) {
                // 对象类型返回值示例
                DocResponseData result = new DocResponseData();
                result.setCode(200);
                result.setMessage("成功");

                // 引用的Definition名
                RefProperty refProperty = (RefProperty) schema;
                String refName = refProperty.getSimpleRef();

                List<DocParamModel> resultModel = dataModel.get(refName);
                Map<String, Object> resultVO = buildMockData(resultModel);
                if (TAG_LIST.equalsIgnoreCase(operation.getTags().get(0))) {
                    // List
                    DocListResultVO<Map<String, Object>> listResultVO = new DocListResultVO<>();
                    listResultVO.setCount(1L);
                    listResultVO.setList(Lists.newArrayList(resultVO));
                    listResultVO.setPageNum(1);
                    listResultVO.setPageCount(1);
                    listResultVO.setPageSize(20);
                    result.setData(listResultVO);

                    DocParamModel paramModel = new DocParamModel();
                    paramModel.setName("count");
                    paramModel.setType("integer");
                    paramModel.setRequired(true);
                    paramModel.setDesc("数据总条数");
                    resultModel.add(paramModel);

                    paramModel = new DocParamModel();
                    paramModel.setName("pageCount");
                    paramModel.setType("integer");
                    paramModel.setRequired(true);
                    paramModel.setDesc("数据总页数");
                    resultModel.add(paramModel);

                    paramModel = new DocParamModel();
                    paramModel.setName("pageNum");
                    paramModel.setType("integer");
                    paramModel.setRequired(true);
                    paramModel.setDesc("当前第几页");
                    resultModel.add(paramModel);

                    paramModel = new DocParamModel();
                    paramModel.setName("pageSize");
                    paramModel.setType("integer");
                    paramModel.setRequired(true);
                    paramModel.setDesc("每页数据条数");
                    resultModel.add(paramModel);
                } else {
                    // VO
                    result.setData(resultVO);
                }

                responseModel.setResult(resultModel);

                // 例子代码
                responseModel.setSample(format(JSON.toJSONString(result)));
                continue;
            }
        }

        return responseModel;
    }

    /**
     * 生成mock响应数据
     *
     * @param dataModels
     * @return
     */
    private Map<String, Object> buildMockData(List<DocParamModel> dataModels) {
        Map<String, Object> mockResult = new HashMap<>(32);
        dataModels.forEach(i -> mockResult.put(i.getName(), buildMockDataByType(i.getType())));
        return mockResult;
    }

    /**
     * 根据字段类型生成mock数据
     *
     * @param type
     * @return
     */
    private Object buildMockDataByType(String type) {
        switch (type) {
            case "string":
                return "string";
            case "integer":
                return 0;
            case "number":
                return 0;
            case "date":
                return "2018-01-01";
            default:
                return "";
        }
    }

    /**
     * 将文件通过模版生成出来
     *
     * @param filePath
     * @param model
     * @throws Exception
     */
    private void generateFile(String filePath, DocInterfaceModel model) throws Exception {
        // 读取模板
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template template = cfg.getTemplate(this.templateName);

        // 创建空文件
        String fileName = model.getUrl().replaceAll("/", "-").substring(1) + "-" + model.getMethod() + ".md";

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(filePath + File.separator + fileName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();

        // 根据模板生成文件
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        template.process(model, writer);
        writer.flush();
        writer.close();
        logger.info("generate {} finished", fileName);
    }

    public String format(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();
    }

    private String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
}
