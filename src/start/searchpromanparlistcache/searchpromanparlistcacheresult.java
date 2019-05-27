package start.searchpromanparlistcache;

import java.util.ArrayList;

public class searchpromanparlistcacheresult {
    private String result;
    private ArrayList<searchpromanparlistcachedata> data;
    public searchpromanparlistcacheresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchpromanparlistcachedata> getData() {
        return data;
    }

    public void setData(ArrayList<searchpromanparlistcachedata> data) {
        this.data = data;
    }
}
