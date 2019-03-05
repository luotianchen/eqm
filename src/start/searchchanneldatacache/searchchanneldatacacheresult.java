package start.searchchanneldatacache;

import java.util.ArrayList;

public class searchchanneldatacacheresult {
    private String result;
    private ArrayList<searchchanneldatacachedata> data;
    public searchchanneldatacacheresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchchanneldatacachedata> getData() {
        return data;
    }

    public void setData(ArrayList<searchchanneldatacachedata> data) {
        this.data = data;
    }
}
