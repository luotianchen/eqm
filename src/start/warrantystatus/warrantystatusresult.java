package start.warrantystatus;

import java.util.ArrayList;

public class warrantystatusresult {
    private String result;
    private ArrayList<warrantystatusdata> data= new ArrayList<warrantystatusdata>();
    private int i=0;
    public warrantystatusresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<warrantystatusdata> getData() {
        return data;
    }

    public void setData(ArrayList<warrantystatusdata> data) {
        this.data = data;
    }
}
