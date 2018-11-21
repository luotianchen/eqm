package start.getroll;

public class getrolldata {
    private int roll;                                        //角色(权限)的id
    private String rollname;                                    //角色(权限)名字
    private int department;                                     //部门id
    public getrolldata(){
        super();
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getRollname() {
        return rollname;
    }

    public void setRollname(String rollname) {
        this.rollname = rollname;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }
}
