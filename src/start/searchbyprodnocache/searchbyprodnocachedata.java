package start.searchbyprodnocache;

public class searchbyprodnocachedata {
    private String spartname;                           //零件名称*
    private String spec;                                //规格
    private String dimension;                           //尺寸
    private String partno;                              //件号
    private String designation;                         //牌号*
    private String qty;                                 //数量
    private String codedmarking;                        //入库编号
    private String issuedate;                           //发料日期
    private String picker;                              //领料人*
    private String issuematl;                           //发料人*
    private String ispresspart;                         //是否为主要受压元件
    private String weldno;                              //焊缝号
    private int returnqty;                           //退回数量
    private String note;                                //备注
    private String heatbatchno;                             //炉批号
    public searchbyprodnocachedata(){
        super();
    }

    public String getSpartname() {
        return spartname;
    }

    public void setSpartname(String spartname) {
        this.spartname = spartname;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getPartno() {
        return partno;
    }

    public void setPartno(String partno) {
        this.partno = partno;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getPicker() {
        return picker;
    }

    public void setPicker(String picker) {
        this.picker = picker;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIspresspart() {
        return ispresspart;
    }

    public void setIspresspart(String ispresspart) {
        this.ispresspart = ispresspart;
    }

    public String getWeldno() {
        return weldno;
    }

    public void setWeldno(String weldno) {
        this.weldno = weldno;
    }

    public int getReturnqty() {
        return returnqty;
    }

    public void setReturnqty(int returnqty) {
        this.returnqty = returnqty;
    }

    public String getIssuematl() {
        return issuematl;
    }

    public void setIssuematl(String issuematl) {
        this.issuematl = issuematl;
    }

    public String getHeatbatchno() {
        return heatbatchno;
    }

    public void setHeatbatchno(String heatbatchno) {
        this.heatbatchno = heatbatchno;
    }
}
