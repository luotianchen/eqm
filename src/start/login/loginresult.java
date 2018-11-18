package start.login;

public class loginresult {                                                   //返回结果
    private String result;
    private String role;                            //角色(权限)
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
