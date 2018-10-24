package feifei.model;

//该类继承了log类，是因为SysLog类里面的oldValue和newValue这两个字段是text属性。所以生成的时候会继承syslog类，重新生成一个
public class SysLogWithBLOBs extends SysLog {
    private String oldValue;

    private String newValue;

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue == null ? null : oldValue.trim();
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue == null ? null : newValue.trim();
    }
}