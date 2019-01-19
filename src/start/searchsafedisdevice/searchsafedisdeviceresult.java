package start.searchsafedisdevice;

import java.util.ArrayList;

public class searchsafedisdeviceresult {
    private String result;
    private ArrayList<searchsafedisdevicedata> data ;
    public searchsafedisdeviceresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchsafedisdevicedata> getData() {
        return data;
    }

    public void setData(ArrayList<searchsafedisdevicedata> data) {
        this.data = data;
    }
}
