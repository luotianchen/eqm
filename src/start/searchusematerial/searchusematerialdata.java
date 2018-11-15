package start.searchusematerial;

public class searchusematerialdata {
    private String codedmarking;                        //入库编号
    private String designation;                         //牌号*
    private String spec;                                //规格
    private String qty;                                 //数量
    private String prodno;                              //产品编号
    private String spartname;                           //零件名称*，即受压元件里的name
    private String prodname;                            //产品名称(入库编号查产品编号，产品编号查产品名称)
    public searchusematerialdata(){
        super();
    }

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }

    public String getSpartname() {
        return spartname;
    }

    public void setSpartname(String spartname) {
        this.spartname = spartname;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }
}
