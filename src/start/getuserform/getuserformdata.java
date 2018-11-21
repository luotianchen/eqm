package start.getuserform;

public class getuserformdata {
    private String username;                            //用户名
    private int roll;                                   //角色(权限)
    private String name;                                //真实姓名
    public getuserformdata(){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
