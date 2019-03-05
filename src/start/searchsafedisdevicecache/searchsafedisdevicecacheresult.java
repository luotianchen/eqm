package start.searchsafedisdevicecache;

import java.util.ArrayList;

public class searchsafedisdevicecacheresult {
    private String result;
    private ArrayList<searchsafedisdevicecachedata> data ;
    public searchsafedisdevicecacheresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchsafedisdevicecachedata> getData() {
        return data;
    }

    public void setData(ArrayList<searchsafedisdevicecachedata> data) {
        this.data = data;
    }
}
