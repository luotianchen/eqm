package start.changedepartmentname;

import com.sun.scenario.effect.impl.prism.PrImage;

public class changedepartmentnamepost {
    private int department;                                 //部门id
    private String departmentname;                          //部门名称
    public changedepartmentnamepost(){
        super();
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }
}
