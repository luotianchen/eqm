package start.searchsubstitutestatus;

import java.util.ArrayList;

public class searchsubstitutestatusresult {
    private String result;
    private ArrayList<searchsubstitutestatusdata> data = new ArrayList<searchsubstitutestatusdata>();
    public searchsubstitutestatusresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchsubstitutestatusdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchsubstitutestatusdata> data) {
        this.data = data;
    }
}
