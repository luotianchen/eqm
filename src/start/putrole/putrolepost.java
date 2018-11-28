package start.putrole;

public class putrolepost {
    private String rolename;                                    //角色名称
    private String department;                                  //角色所处部门名称
    public putrolepost(){
        super();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
