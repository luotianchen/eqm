package start.searchnocertsitu;

public class searchnocertsitupost {
    private int pageindex;                               //请求第n页，从1开始
    private int pagesize;                                //页面容量
    private String matlcode;                                //材料代码：入库编号第五位数字，null为不做要求
    public searchnocertsitupost(){
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

    public String getMatlcode() {
        return matlcode;
    }

    public void setMatlcode(String matlcode) {
        this.matlcode = matlcode;
    }
}
