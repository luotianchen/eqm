package start.searchusematerial;

public class searchusematerialpost {
    private int pageindex;                               //请求第n页，从1开始
    private int pagesize;                                //页面容量
    private String codedmarking;                        //入库编号
    public searchusematerialpost(){
        super();
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

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
    }
}
