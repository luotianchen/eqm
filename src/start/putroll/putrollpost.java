package start.putroll;

public class putrollpost {
    private String rollname;                                    //角色名称
    private String department;                                  //角色所处部门名称
    public putrollpost(){
        super();
    }

    public String getRollname() {
        return rollname;
    }

    public void setRollname(String rollname) {
        this.rollname = rollname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
