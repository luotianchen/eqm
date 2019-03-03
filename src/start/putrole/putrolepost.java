package start.putrole;

public class putrolepost {
    private String rolename;                                    //角色名称
    private int department;                                  //角色所处部门名称
    public putrolepost(){
        super();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }
}
