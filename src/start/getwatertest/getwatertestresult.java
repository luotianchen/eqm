package start.getwatertest;

import java.util.ArrayList;

public class getwatertestresult {
    private String result;
    private ArrayList<getwatertestdata> data;
    public getwatertestresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<getwatertestdata> getData() {
        return data;
    }

    public void setData(ArrayList<getwatertestdata> data) {
        this.data = data;
    }
}
