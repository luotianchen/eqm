package start.matlname;

import java.util.ArrayList;

public class matlnameresult {
    private String result;
    private ArrayList<String> data= new ArrayList<String>();
    public matlnameresult(){
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
