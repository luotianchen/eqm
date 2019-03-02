package start.getspecimenno;

import java.util.ArrayList;

public class getspecimennoresult {
    private String result;
    private ArrayList<getspecimennodata> data;
    public getspecimennoresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<getspecimennodata> getData() {
        return data;
    }

    public void setData(ArrayList<getspecimennodata> data) {
        this.data = data;
    }
}
