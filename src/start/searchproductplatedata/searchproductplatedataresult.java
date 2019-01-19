package start.searchproductplatedata;

import java.util.ArrayList;

public class searchproductplatedataresult {
    private String result;
    private ArrayList<searchproductplatedatadata> data;
    public searchproductplatedataresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchproductplatedatadata> getData() {
        return data;
    }

    public void setData(ArrayList<searchproductplatedatadata> data) {
        this.data = data;
    }
}
