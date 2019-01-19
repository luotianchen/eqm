package start.searchprematl;

public class searchprematldata {
    private String spartname;                                       //零件名称-2*
    private String etrans;                                          //零件英文名称-2*
    private String partno;                                          //件号-2
    private String designation;                                     //牌号-1*
    private String spec;                                                //规格-2
    private String millunit;                                        //生产单位-1*
    private String millunitename;                                   //生产单位英文-1*
    private String heatcondi;                                       //热处理状态（供货状态）-1*
    private String codedmarking;                                    //入库编号
    public searchprematldata(){
        super();
    }

    public String getSpartname() {
        return spartname;
    }

    public void setSpartname(String spartname) {
        this.spartname = spartname;
    }

    public String getEtrans() {
        return etrans;
    }

    public void setEtrans(String etrans) {
        this.etrans = etrans;
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

    public String getMillunitename() {
        return millunitename;
    }

    public void setMillunitename(String millunitename) {
        this.millunitename = millunitename;
    }

    public String getHeatcondi() {
        return heatcondi;
    }

    public void setHeatcondi(String heatcondi) {
        this.heatcondi = heatcondi;
    }

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
    }
}
