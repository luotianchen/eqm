package start.searchmatlnotice;

import java.util.ArrayList;

public class searchmatlnoticeresult {
    private String result;
    private ArrayList<searchmatlnoticedata> data = new ArrayList<searchmatlnoticedata>();
    public searchmatlnoticeresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchmatlnoticedata> getData() {
        return data;
    }

    public void setData(ArrayList<searchmatlnoticedata> data) {
        this.data = data;
    }
}
