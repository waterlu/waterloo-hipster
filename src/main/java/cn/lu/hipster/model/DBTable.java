package cn.lu.hipster.model;

/**
 * 数据库表信息
 *
 * @author lutiehua
 * @date 2017/09/22
 */
public class DBTable {

    private boolean selected;

    private String name;

    private String type;

    private String remark;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
