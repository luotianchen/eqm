package start.login;

public class loginresult {                                                   //返回结果
    private String result;
    private String roll;                            //角色(权限)
    private String name;                            //用户真实姓名
    public loginresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
