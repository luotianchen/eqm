package start.millunit;

import java.util.ArrayList;

public class millunitresult {
    private String result;
    private ArrayList<millunitdata> data= new ArrayList<millunitdata>();
    public millunitresult(){
        super();
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<millunitdata> getData() {
        return data;
    }

    public void setData(ArrayList<millunitdata> data) {
        this.data = data;
    }
}
