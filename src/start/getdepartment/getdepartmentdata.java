package start.getdepartment;

public class getdepartmentdata {
    private int department;                                     //部门的id
    private String departmentname;                              //部门名称
    public getdepartmentdata(){
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
