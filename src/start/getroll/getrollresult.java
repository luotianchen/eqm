package start.getroll;

import java.util.ArrayList;

public class getrollresult {
    private String result;
    private ArrayList<getrolldata> data = new ArrayList<getrolldata>();
    public getrollresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<getrolldata> getData() {
        return data;
    }

    public void setData(ArrayList<getrolldata> data) {
        this.data = data;
    }
}
