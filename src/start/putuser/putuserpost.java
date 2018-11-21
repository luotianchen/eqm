package start.putuser;

public class putuserpost {
    private String name;                                        //真实姓名
    private String username;                                    //用户名
    private int roll;                                        //角色的id
    public putuserpost(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
