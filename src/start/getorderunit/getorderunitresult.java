package start.getorderunit;

import java.util.ArrayList;

public class getorderunitresult {
    private String result;
    private ArrayList<getorderunitdata> data;
    public getorderunitresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<getorderunitdata> getData() {
        return data;
    }

    public void setData(ArrayList<getorderunitdata> data) {
        this.data = data;
    }
}
