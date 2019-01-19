package start.putemail;

import java.util.ArrayList;

public class putemailpost {
    private String system_email;                                //发送者邮箱
    private String authorization_code;                          //授权码
    private ArrayList<String> tosend_email;                     //接受者邮箱
    public putemailpost(){
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

    public ArrayList<String> getTosend_email() {
        return tosend_email;
    }

    public void setTosend_email(ArrayList<String> tosend_email) {
        this.tosend_email = tosend_email;
    }
}
