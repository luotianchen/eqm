package start.getuserform;

public class getuserformdata {
    private String username;                            //用户名
    private int role;                                   //角色(权限)id_1
    private int role2;                                   //角色(权限)的id_2
    private int role3;                                   //角色(权限)的id_3
    private int role4;                                   //角色(权限)的id_4
    private int role5;                                   //角色(权限)的id_5
    private String name;                                //真实姓名
    private String email;                               //邮箱
    private String sign;                                    //签名
    private String note;                                    //备注
    public getuserformdata(){
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRole2() {
        return role2;
    }

    public void setRole2(int role2) {
        this.role2 = role2;
    }

    public int getRole3() {
        return role3;
    }

    public void setRole3(int role3) {
        this.role3 = role3;
    }

    public int getRole4() {
        return role4;
    }

    public void setRole4(int role4) {
        this.role4 = role4;
    }

    public int getRole5() {
        return role5;
    }

    public void setRole5(int role5) {
        this.role5 = role5;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
