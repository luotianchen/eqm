package start.email;

public class email {
    private String system_email;                                //发送者邮箱
    private String authorization_code;                          //授权码
    public email(){
        super();
    }

    public String getSystem_email() {
        return system_email;
    }

    public void setSystem_email(String system_email) {
        this.system_email = system_email;
    }

    public String getAuthorization_code() {
        return authorization_code;
    }

    public void setAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
    }
}
