package start.searchusematerial;

import java.util.ArrayList;

public class searchusematerialresult {
    private String result;
    private int total;                                                  //总页数
    private ArrayList<searchusematerialdata> data;
    public searchusematerialresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<searchusematerialdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchusematerialdata> data) {
        this.data = data;
    }
}
