package start.searchpressurepart;

import java.util.ArrayList;

public class searchpressurepartresult {
    private String result;
    private int total;
    private ArrayList<searchpressurepartdata1> data1 = new ArrayList<searchpressurepartdata1>();
    private ArrayList<searchpressurepartdata2> data2 = new ArrayList<searchpressurepartdata2>();
    public searchpressurepartresult(){
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

    public ArrayList<searchpressurepartdata1> getData1() {
        return data1;
    }

    public void setData1(ArrayList<searchpressurepartdata1> data1) {
        this.data1 = data1;
    }

    public ArrayList<searchpressurepartdata2> getData2() {
        return data2;
    }

    public void setData2(ArrayList<searchpressurepartdata2> data2) {
        this.data2 = data2;
    }
}
