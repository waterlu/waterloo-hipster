package cn.lu.hipster.api;

import cn.lu.hipster.consts.ProjectType;
import cn.lu.hipster.model.GeneratorParam;
import cn.lu.hipster.service.ProjectGenerator;
import cn.lu.web.mvc.ResponseCode;
import cn.lu.web.mvc.ResponseResult;
import cn.lu.web.mvc.SimpleResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 项目生成接口
 *
 * @author lutiehua
 * @date 2017/9/22
 */
@RestController
@RequestMapping("/api/project")
public class ProjectController extends BaseController {

    @Autowired
    @Qualifier("springBootGenerator")
    private ProjectGenerator springBootGenerator;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult<SimpleResponseData> generate(@RequestBody Map<String, Object> param) throws Exception {

        GeneratorParam generatorParam = super.getParamObject(param, GeneratorParam.class);

        if (generatorParam.getTables().size() == 0) {
            int errorCode = ResponseCode.PARAM_ERROR.code;
            String errorMessage = "待生成列表不能为空，请添加数据库表到待生成列表!";
            return new ResponseResult(errorCode, errorMessage);
        }

        String projectType = generatorParam.getProjectInfo().getProjectType();
        if (ProjectType.PROJECT_TYPE_BOOT.equalsIgnoreCase(projectType)) {
            // 生成SpringBoot项目
            String message = springBootGenerator.generateProject(generatorParam);
            SimpleResponseData responseData = new SimpleResponseData(message);
            ResponseResult<SimpleResponseData> responseResult = new ResponseResult(responseData);
            return responseResult;
        } else {
            // 不支持的类型
            int errorCode = ResponseCode.PARAM_ERROR.code;
            String errorMessage = String.format("unknown projectType [%s]", projectType);
            return new ResponseResult(errorCode, errorMessage);
        }
    }
}