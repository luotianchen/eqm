package start.getdwgnoaudited;

public class getdwgnoauditeddata {
    private String dwgno;                                          // 图号
    private String prodname;                                       // 产品名称
    private String type;                                           // 容器类别
    private String mainstand;                                      // 产品标准
    private String designdate;                                     // 设计日期
    private String deconame;                                       // 设计单位
    private int sc;

    public getdwgnoauditeddata(){
        super();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMainstand() {
        return mainstand;
    }

    public void setMainstand(String mainstand) {
        this.mainstand = mainstand;
    }

    public String getDesigndate() {
        return designdate;
    }

    public void setDesigndate(String designdate) {
        this.designdate = designdate;
    }

    public String getDeconame() {
        return deconame;
    }

    public void setDeconame(String deconame) {
        this.deconame = deconame;
    }

    public int getSc() {
        return sc;
    }

    public void setSc(int sc) {
        this.sc = sc;
    }
}
