package start.login;

public class loginresult {                                                   //返回结果
    private String result;
    private String email;                                   //email地址
    private String role;                                   //角色(权限)的name
    private String role2;                                   //角色(权限)的name
    private String role3;                                   //角色(权限)的name
    private String role4;                                   //角色(权限)的name
    private String role5;                                   //角色(权限)的name
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole2() {
        return role2;
    }

    public void setRole2(String role2) {
        this.role2 = role2;
    }

    public String getRole3() {
        return role3;
    }

    public void setRole3(String role3) {
        this.role3 = role3;
    }

    public String getRole4() {
        return role4;
    }

    public void setRole4(String role4) {
        this.role4 = role4;
    }

    public String getRole5() {
        return role5;
    }

    public void setRole5(String role5) {
        this.role5 = role5;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
