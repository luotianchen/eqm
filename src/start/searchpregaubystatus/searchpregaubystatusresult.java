package start.searchpregaubystatus;

import java.util.ArrayList;

public class searchpregaubystatusresult {
    private String result;
    private ArrayList<searchpregaubystatusdata> data;
    public searchpregaubystatusresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchpregaubystatusdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchpregaubystatusdata> data) {
        this.data = data;
    }
}
