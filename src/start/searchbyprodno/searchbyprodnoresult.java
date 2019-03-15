package start.searchbyprodno;

import java.util.ArrayList;

public class searchbyprodnoresult {
    private String result;
    private String prodname;                                //产品名称(用图号再产品参数表查询)
    private String dwgno;                                   //图号(在产品制造参数表查询)
    private String user;
    private ArrayList<searchbyprodnodata> data = new ArrayList<searchbyprodnodata>();           //受压原件表查询
    public searchbyprodnoresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getDwgno() {
        return dwgno;
    }

    public void setDwgno(String dwgno) {
        this.dwgno = dwgno;
    }

    public ArrayList<searchbyprodnodata> getData() {
        return data;
    }

    public void setData(ArrayList<searchbyprodnodata> data) {
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
