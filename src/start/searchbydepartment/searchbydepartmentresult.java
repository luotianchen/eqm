package start.searchbydepartment;

import java.util.ArrayList;

public class searchbydepartmentresult {
    private String result;
    private ArrayList<searchbydepartmentroles> roles = new ArrayList<searchbydepartmentroles>();
    public searchbydepartmentresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<searchbydepartmentroles> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<searchbydepartmentroles> roles) {
        this.roles = roles;
    }
}
