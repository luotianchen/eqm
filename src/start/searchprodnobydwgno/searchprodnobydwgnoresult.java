package start.searchprodnobydwgno;

import java.util.ArrayList;

public class searchprodnobydwgnoresult {
    private String result;
    private ArrayList<String> prodnos = new ArrayList<String>();
    public searchprodnobydwgnoresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<String> getData() {
        return prodnos;
    }

    public void setData(ArrayList<String> data) {
        this.prodnos = data;
    }


}
