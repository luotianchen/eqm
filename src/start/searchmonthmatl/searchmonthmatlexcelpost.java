package start.searchmonthmatl;

public class searchmonthmatlexcelpost {
    private String matlcode;                                //材料代码
    private String inyear;                                  //入库年份
    private String inmonth;                                 //入库月份

    public searchmonthmatlexcelpost(){
        super();
    }

    public String getMatlcode() {
        return matlcode;
    }

    public void setMatlcode(String matlcode) {
        this.matlcode = matlcode;
    }

    public String getInyear() {
        return inyear;
    }

    public void setInyear(String inyear) {
        this.inyear = inyear;
    }

    public String getInmonth() {
        return inmonth;
    }

    public void setInmonth(String inmonth) {
        this.inmonth = inmonth;
    }
}
