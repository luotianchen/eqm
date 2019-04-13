package start.searchledgerbynotedate;

import java.util.ArrayList;

public class searchledgerbynotedateresult {
    private String result;
    private ArrayList<searchledgerbynotedatedata> data;
    public searchledgerbynotedateresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchledgerbynotedatedata> getData() {
        return data;
    }

    public void setData(ArrayList<searchledgerbynotedatedata> data) {
        this.data = data;
    }
}
