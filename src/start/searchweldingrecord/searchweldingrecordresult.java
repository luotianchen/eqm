package start.searchweldingrecord;

import java.util.ArrayList;

public class searchweldingrecordresult {
    private String result;
    private ArrayList<searchweldingrecorddata> data;
    public searchweldingrecordresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchweldingrecorddata> getData() {
        return data;
    }

    public void setData(ArrayList<searchweldingrecorddata> data) {
        this.data = data;
    }
}
