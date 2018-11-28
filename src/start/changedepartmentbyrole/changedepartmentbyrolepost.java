package start.changedepartmentbyrole;

public class changedepartmentbyrolepost {
    private int role;                                //角色id
    private int department;                         //部门id
    public changedepartmentbyrolepost(){
        super();
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }
}
