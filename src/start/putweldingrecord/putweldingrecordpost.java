package start.putweldingrecord;

import java.util.ArrayList;

public class putweldingrecordpost {
    private String prodno;
    private String dwgno;
    private String prodname;
    private String user;
    private ArrayList<putweldingrecordpostdata> data;

    public putweldingrecordpost(){
        super();
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }

    public String getDwgno() {
        return dwgno;
    }

    public void setDwgno(String dwgno) {
        this.dwgno = dwgno;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<putweldingrecordpostdata> getData() {
        return data;
    }

    public void setData(ArrayList<putweldingrecordpostdata> data) {
        this.data = data;
    }
}
