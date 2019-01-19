package start.searchmatlnotice;

public class searchmatlnoticedata {
    private String matlname;                            //材料名称*
    private String audit_user;                          //审核人
    private String designation;                         //牌号*
    private String codedmarking;                        //入库编号
    private String spec;                                //规格
    private String qty;                                 //数量
    private String warrantyno;                          //质保书号
    private String matlstand;                           //材料标准*
    private String heatbatchno;                         //炉批号
    private String warrantysitu;                        //质保书情况*
    private String millunit;                            //生存单位*
    private String supplier;                            //供货单位*
    private String dimension;                             //尺寸
    private String indate;                               //入库日期
    private String modelstand;                          //型号标准*
    //焊材才拥有以下属性，否则全为null
    //以下通过焊材名称在焊材牌号表中查询
    private String pack;                                //包装
    private String humisitu;                            //受潮情况
    private String coating;                             //药皮
    private String rustsitu;                            //锈蚀情况
    private String eccentricity;                        //偏心度
    private String packcheck;                           //包装检查
    private String rustsitu1;                           //锈蚀情况1
    private String diamtest;                            //直径实测尺寸
    private String packcheck1;                          //包装检查1
    private String humisitu1;                           //受潮情况1
    private String graincheck;                          //颗粒检查
    public searchmatlnoticedata(){
        super();
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

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
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

    public String getWarrantyno() {
        return warrantyno;
    }

    public void setWarrantyno(String warrantyno) {
        this.warrantyno = warrantyno;
    }

    public String getMatlstand() {
        return matlstand;
    }

    public void setMatlstand(String matlstand) {
        this.matlstand = matlstand;
    }

    public String getHeatbatchno() {
        return heatbatchno;
    }

    public void setHeatbatchno(String heatbatchno) {
        this.heatbatchno = heatbatchno;
    }

    public String getWarrantysitu() {
        return warrantysitu;
    }

    public void setWarrantysitu(String warrantysitu) {
        this.warrantysitu = warrantysitu;
    }

    public String getMillunit() {
        return millunit;
    }

    public void setMillunit(String millunit) {
        this.millunit = millunit;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getModelstand() {
        return modelstand;
    }

    public void setModelstand(String modelstand) {
        this.modelstand = modelstand;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getHumisitu() {
        return humisitu;
    }

    public void setHumisitu(String humisitu) {
        this.humisitu = humisitu;
    }

    public String getCoating() {
        return coating;
    }

    public void setCoating(String coating) {
        this.coating = coating;
    }

    public String getRustsitu() {
        return rustsitu;
    }

    public void setRustsitu(String rustsitu) {
        this.rustsitu = rustsitu;
    }

    public String getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(String eccentricity) {
        this.eccentricity = eccentricity;
    }

    public String getPackcheck() {
        return packcheck;
    }

    public void setPackcheck(String packcheck) {
        this.packcheck = packcheck;
    }

    public String getRustsitu1() {
        return rustsitu1;
    }

    public void setRustsitu1(String rustsitu1) {
        this.rustsitu1 = rustsitu1;
    }

    public String getDiamtest() {
        return diamtest;
    }

    public void setDiamtest(String diamtest) {
        this.diamtest = diamtest;
    }

    public String getPackcheck1() {
        return packcheck1;
    }

    public void setPackcheck1(String packcheck1) {
        this.packcheck1 = packcheck1;
    }

    public String getHumisitu1() {
        return humisitu1;
    }

    public void setHumisitu1(String humisitu1) {
        this.humisitu1 = humisitu1;
    }

    public String getGraincheck() {
        return graincheck;
    }

    public void setGraincheck(String graincheck) {
        this.graincheck = graincheck;
    }

    public String getAudit_user() {
        return audit_user;
    }

    public void setAudit_user(String audit_user) {
        this.audit_user = audit_user;
    }
}
