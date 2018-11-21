package start.getdepartment;

import java.util.ArrayList;

public class getdepartmentresult {
    private String result;
    private ArrayList<getdepartmentdata> data = new ArrayList<getdepartmentdata>();
    public getdepartmentresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<getdepartmentdata> getData() {
        return data;
    }

    public void setData(ArrayList<getdepartmentdata> data) {
        this.data = data;
    }
}
