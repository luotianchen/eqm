package start.searchbydepartment;

public class searchbydepartmentroles {
    private int role;                                       //角色id
    private String rolename;                                //角色名称
    public searchbydepartmentroles(){
        super();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
