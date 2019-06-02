package start.putmessage;

public class putmessagepost {
    private String recieve_user;
    private String title;
    private String content;
    private String send_type;
    private String send_user;

    public putmessagepost(){
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }

    public String getSend_user() {
        return send_user;
    }

    public void setSend_user(String send_user) {
        this.send_user = send_user;
    }

    public String getRecieve_user() {
        return recieve_user;
    }

    public void setRecieve_user(String recieve_user) {
        this.recieve_user = recieve_user;
    }
}
