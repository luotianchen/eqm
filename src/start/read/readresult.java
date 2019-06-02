package start.read;

import java.util.ArrayList;

public class readresult {
    private String result;
    private ArrayList<readdata> data;

    public readresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<readdata> getData() {
        return data;
    }

    public void setData(ArrayList<readdata> data) {
        this.data = data;
    }
}
