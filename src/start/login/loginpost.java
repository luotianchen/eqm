package start.login;

public class loginpost {
    private String username;                //用户名
    private String password;                //密码
    public loginpost(){
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
