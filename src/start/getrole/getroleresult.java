package start.getrole;

import java.util.ArrayList;

public class getroleresult {
    private String result;
    private ArrayList<getroledata> data = new ArrayList<getroledata>();
    public getroleresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<getroledata> getData() {
        return data;
    }

    public void setData(ArrayList<getroledata> data) {
        this.data = data;
    }
}
