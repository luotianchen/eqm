package start.changerolename;

public class changerolenamepost {
    private int role;                                       //角色的id
    private String rolename;                                //角色名称
    public changerolenamepost(){
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
}
