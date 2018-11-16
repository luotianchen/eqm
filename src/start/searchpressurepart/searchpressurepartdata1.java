package start.searchpressurepart;

public class searchpressurepartdata1 {
    private String codedmarking;                        //入库编号
    private String designation;                         //牌号*
    private String spec;                                //规格
    private String modelstand;                          //型号标准*
    private String indate;                               //入库日期
    private String qty;                                 //数量
    public searchpressurepartdata1(){
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

    public String getModelstand() {
        return modelstand;
    }

    public void setModelstand(String modelstand) {
        this.modelstand = modelstand;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
