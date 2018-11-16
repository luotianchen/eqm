package start.searchmonthmatl;

public class searchmonthmatlpost {
    private String matlcode;                                //材料代码
    private String inyear;                                  //入库年份
    private String inmonth;                                 //入库月份
    private int pageindex;                               //请求第n页，从1开始
    private int pagesize;                                //页面容量

    public searchmonthmatlpost(){
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

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
}
