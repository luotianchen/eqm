package start.searchallmaterial;

public class searchallmaterialpostdata {
    private String codedmarking;                                    //入库编号
    private String matlname;                                        //材料名称*
    private String designation;                                     //牌号*
    private String spec;                                            //规格
    private String millunit;                                        //生产单位*
    private int status;                                             //审核状态
    private String indate;                                              //入库日期
    public searchallmaterialpostdata(){
        super();
    }

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
    }

    public String getMatlname() {
        return matlname;
    }

    public void setMatlname(String matlname) {
        this.matlname = matlname;
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

    public String getMillunit() {
        return millunit;
    }

    public void setMillunit(String millunit) {
        this.millunit = millunit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }
}
