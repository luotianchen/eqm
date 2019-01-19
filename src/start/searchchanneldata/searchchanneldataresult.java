package start.searchchanneldata;

import java.util.ArrayList;

public class searchchanneldataresult {
    private String result;
    private ArrayList<searchchanneldatadata> data;
    public searchchanneldataresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchchanneldatadata> getData() {
        return data;
    }

    public void setData(ArrayList<searchchanneldatadata> data) {
        this.data = data;
    }
}
