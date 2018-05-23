package cn.lu.hipster.api;

import com.alibaba.fastjson.JSON;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Map;
import java.util.Set;

/**
 * Controller的基类
 *
 * @author lutiehua
 * @date 2017/9/28.
 */
public class BaseController {

    protected <T> T getParamObject(Map<String, Object> param, Class<T> className) throws ParamValidationException {
        String jsonString = JSON.toJSONString(param);
        T object = JSON.parseObject(jsonString, className);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, Default.class);
        if (constraintViolations.size() == 0) {
            return object;
        }

        StringBuffer buffer = new StringBuffer();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            buffer.append(constraintViolation.getPropertyPath().toString());
            buffer.append(constraintViolation.getMessage());
            buffer.append("; ");
        }
        ParamValidationException exception = new ParamValidationException(buffer.toString(), 400);
        throw exception;
    }

}
