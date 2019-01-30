package start.searchmatlstandbydes;

import java.util.ArrayList;

public class searchmatlstandbydesresult {
    private String result;
    private ArrayList<String> matlstand;
    public searchmatlstandbydesresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<String> getMatlstand() {
        return matlstand;
    }

    public void setMatlstand(ArrayList<String> matlstand) {
        this.matlstand = matlstand;
    }
}
