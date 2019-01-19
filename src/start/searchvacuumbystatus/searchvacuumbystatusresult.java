package start.searchvacuumbystatus;

import java.util.ArrayList;

public class searchvacuumbystatusresult {
    private String result;
    private ArrayList<searchvacuumbystatusdata> data;
    public searchvacuumbystatusresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchvacuumbystatusdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchvacuumbystatusdata> data) {
        this.data = data;
    }
}
