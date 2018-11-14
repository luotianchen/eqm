package start.searchallmaterial;

import java.util.ArrayList;

public class searchallmaterialresult {
    private String result;
    private int total;                                                  //总页数
    private ArrayList<searchallmaterialdata> data;
    public searchallmaterialresult(){
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

    public ArrayList<searchallmaterialdata> getData() {
        return data;
    }

    public void setData(ArrayList<searchallmaterialdata> data) {
        this.data = data;
    }
}
