package start.getprodno;

import java.util.ArrayList;

public class getprodnoresult {
    private String result;
    private ArrayList<String> data = new ArrayList<String>();                       //产品编号
    public getprodnoresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }
}
