package start.getwmedia;

import java.util.ArrayList;

public class getwmediaresult {
    private String result;
    private ArrayList<getwmediadata> data;
    public getwmediaresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<getwmediadata> getData() {
        return data;
    }

    public void setData(ArrayList<getwmediadata> data) {
        this.data = data;
    }
}
