package start.changeroll;

public class changerollpost {
    private String username;                            //用户名
    private int roll;                                   //角色(权限)的id
    public changerollpost(){
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }
}
