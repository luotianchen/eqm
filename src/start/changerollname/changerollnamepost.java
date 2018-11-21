package start.changerollname;

public class changerollnamepost {
    private int roll;                                       //角色的id
    private String rollname;                                //角色名称
    public changerollnamepost(){
        super();
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getRollname() {
        return rollname;
    }

    public void setRollname(String rollname) {
        this.rollname = rollname;
    }
}
