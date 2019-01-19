package start.searchpromanparlist;

import java.util.ArrayList;

public class searchpromanparlistresult {
    private String result;
    private ArrayList<searchpromanparlistdata> data;
    public searchpromanparlistresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchpromanparlistdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchpromanparlistdata> data) {
        this.data = data;
    }
}
