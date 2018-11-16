package start.searchpressurepart;

public class searchpressurepartpost {
    private String prodno;                          //产品编号
    private int pageindex;                               //请求第n页，从1开始
    private int pagesize;                                //页面容量
    public searchpressurepartpost(){
        super();
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
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
