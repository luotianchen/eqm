package start.searchprenotibynodw;

public class searchprenotibynodwpost {
    private String prodno;                                          //产品编号，可能为null
    private String dwgno;                                           //图号
    private String ppart;                                           //试压部位
    public searchprenotibynodwpost(){
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

    public String getPpart() {
        return ppart;
    }

    public void setPpart(String ppart) {
        this.ppart = ppart;
    }
}
