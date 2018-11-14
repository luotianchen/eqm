package start.searchallmaterial;

public class searchallmaterialpost {
    private int pageindex;                               //请求第n页，从1开始
    private int pagesize;                                //页面容量
    private searchallmaterialpostdata searchdata;           //查询数据
    public searchallmaterialpost(){
        super();
    }

    public searchallmaterialpostdata getSearchdata() {
        return searchdata;
    }

    public void setSearchdata(searchallmaterialpostdata searchdata) {
        this.searchdata = searchdata;
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
