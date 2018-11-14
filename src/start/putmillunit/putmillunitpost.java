package start.putmillunit;

public class putmillunitpost {
    private String millunit;                            //生存单位
    private String millunitename;                       //生产单位英文名称
    public putmillunitpost(){
        super();
    }

    public String getMillunit() {
        return millunit;
    }

    public void setMillunit(String millunit) {
        this.millunit = millunit;
    }

    public String getMillunitename() {
        return millunitename;
    }

    public void setMillunitename(String millunitename) {
        this.millunitename = millunitename;
    }
}
