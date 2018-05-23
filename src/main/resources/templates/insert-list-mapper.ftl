package ${packageName};

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.provider.SpecialProvider;

import java.util.List;

/**
 * ${classRemark}
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${className}<T> {

    /**
     * 批量写入（自增ID）
     *
     * @param recordList
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "${keyFieldName}")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);
}