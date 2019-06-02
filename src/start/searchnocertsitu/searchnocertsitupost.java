package start.searchnocertsitu;

public class searchnocertsitupost {
    private int pageindex;                               //请求第n页，从1开始
    private int pagesize;                                //页面容量
    private String matlcode;                                //材料代码：入库编号第五位数字，null为不做要求
    private String designation;                               //牌号
    private String codedmarking;
    private String supplier;
    private String heatbatchno;
    private String warrantyno;
    public searchnocertsitupost(){
        super();
    }
    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getMatlcode() {
        return matlcode;
    }

    public void setMatlcode(String matlcode) {
        this.matlcode = matlcode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
