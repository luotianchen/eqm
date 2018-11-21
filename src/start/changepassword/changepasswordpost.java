package start.changepassword;

public class changepasswordpost {
    private String username;                                //用户名
    private String password;                                //原密码
    private String newpassword;                             //新密码
    public changepasswordpost(){
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

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
