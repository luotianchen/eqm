package start.searchnocertsitu;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class searchnocertsituresult {
    private String result;
    private int total;                                                  //总页数
    private ArrayList<searchnocertsitudata> data;
    public searchnocertsituresult(){
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

    public ArrayList<searchnocertsitudata> getData() {
        return data;
    }

    public void setData(ArrayList<searchnocertsitudata> data) {
        this.data = data;
    }

}