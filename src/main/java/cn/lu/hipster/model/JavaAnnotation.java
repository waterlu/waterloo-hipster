package cn.lu.hipster.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Java注解
 *
 * @author lutiehua
 * @date 2017/09/26
 */
public class JavaAnnotation {

    /**
     * 注解名称
     */
    private String name;

    private List<JavaAnnotationProp> props = new ArrayList<>();

    /**
     * 添加属性
     *
     * @param key
     * @param value
     * @return
     */
    public boolean addProp(String key, Integer value) {
        JavaAnnotationProp prop = new JavaAnnotationProp();
        prop.setKey(key);
        prop.setType(JavaAnnotationProp.NUMBER);
        prop.setValue(Integer.toString(value));
        return props.add(prop);
    }

    public boolean addProp(String key, String value) {
        JavaAnnotationProp prop = new JavaAnnotationProp();
        prop.setKey(key);
        prop.setType(JavaAnnotationProp.STRING);
        prop.setValue(value);
        return props.add(prop);
    }

    public boolean addProp(String key, boolean value) {
        JavaAnnotationProp prop = new JavaAnnotationProp();
        prop.setKey(key);
        prop.setType(JavaAnnotationProp.BOOLEAN);
        prop.setValue(Boolean.toString(value));
        return props.add(prop);
    }

    /**
     * 对象属性
     *
     * @param key
     * @param value
     * @return
     */
    public boolean addObjectProp(String key, String value) {
        JavaAnnotationProp prop = new JavaAnnotationProp();
        prop.setKey(key);
        prop.setType(JavaAnnotationProp.OBJECT);
        prop.setValue(value);
        return props.add(prop);
    }

    public String getShow() {
        if (props.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(name);
            buffer.append("(");
            for (int i=0; i<props.size(); i++) {
                if (i > 0) {
                    buffer.append(", ");
                }
                JavaAnnotationProp prop = props.get(i);
                buffer.append(prop.getKey());
                buffer.append(" = ");
                if (prop.getType() == JavaAnnotationProp.STRING) {
                    // 字符串加引号
                    buffer.append("\"");
                    buffer.append(prop.getValue());
                    buffer.append("\"");
                } else {
                    buffer.append(prop.getValue());
                }
            }
            buffer.append(")");
            return buffer.toString();
        } else {
            return name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JavaAnnotationProp> getProps() {
        return props;
    }

    public void setProps(List<JavaAnnotationProp> props) {
        this.props = props;
    }
}