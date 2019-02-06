package start.searchbyprodnocache;

import java.util.ArrayList;

public class searchbyprodnocacheresult {
    private String result;
    private String prodname;                                //产品名称(用图号再产品参数表查询)
    private String dwgno;                                   //图号(在产品制造参数表查询)
    private ArrayList<searchbyprodnocachedata> data = new ArrayList<searchbyprodnocachedata>();           //受压原件表查询
    public searchbyprodnocacheresult(){
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

    public ArrayList<searchbyprodnocachedata> getData() {
        return data;
    }

    public void setData(ArrayList<searchbyprodnocachedata> data) {
        this.data = data;
    }
}
