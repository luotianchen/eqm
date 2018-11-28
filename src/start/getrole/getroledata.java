package start.getrole;

public class getroledata {
    private int role;                                        //角色(权限)的id
    private String rolename;                                    //角色(权限)名字
    private int department;                                     //部门id
    public getroledata(){
        super();
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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
