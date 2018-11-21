package start.getuserform;

import java.util.ArrayList;

public class getuserformresult {
    private String result;
    private ArrayList<getuserformdata> data = new ArrayList<getuserformdata>();
    public getuserformresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<getuserformdata> getData() {
        return data;
    }

    public void setData(ArrayList<getuserformdata> data) {
        this.data = data;
    }
}
