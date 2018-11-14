package start.searchnocertsitu;

public class searchnocertsitudata {
    private String matlname;                            //材料名称*
    private String codedmarking;                        //入库编号
    private String designation;                         //牌号*
    private String spec;                                //规格
    private String matlstand;                           //材料标准*
    private String supplier;                            //供货单位*
    private String qty;                                 //数量
    private String note;                                 //说明
    private String millunit;                            //生产单位*
    private String indate;                               //入库日期
    private String heatbatchno;                         //炉批号
    private String warrantyno;                          //质保书号
    public searchnocertsitudata(){
        super();
    }

    public String getMatlname() {
        return matlname;
    }

    public void setMatlname(String matlname) {
        this.matlname = matlname;
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

    public String getMatlstand() {
        return matlstand;
    }

    public void setMatlstand(String matlstand) {
        this.matlstand = matlstand;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMillunit() {
        return millunit;
    }

    public void setMillunit(String millunit) {
        this.millunit = millunit;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getHeatbatchno() {
        return heatbatchno;
    }

    public void setHeatbatchno(String heatbatchno) {
        this.heatbatchno = heatbatchno;
    }

    public String getWarrantyno() {
        return warrantyno;
    }

    public void setWarrantyno(String warrantyno) {
        this.warrantyno = warrantyno;
    }
}
