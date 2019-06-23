package start.getputmaterial;


import java.util.ArrayList;

public class getputmaterialdata {
    private ArrayList<warrantystatusdata> warrantysitu=new ArrayList<warrantystatusdata>();                         //质保书情况
    private ArrayList<String> matlname = new ArrayList<String>();                                       //材料名称
    private ArrayList<String> millunit = new ArrayList<String>();                                       //生产单位
    private ArrayList<String> supplier = new ArrayList<String>();                                   //供货单位*
    private ArrayList<String> matlstand = new ArrayList<String>();                                      //材料标准
    private ArrayList<String> modelstand = new ArrayList<String>();                                     //型号标准*
    private ArrayList<String> designation = new ArrayList<String>();                                       //牌号*
    private ArrayList<String> heatcondi = new ArrayList<String>();                                       //热处理状态*
    public getputmaterialdata(){
        super();
    }
    public ArrayList<warrantystatusdata> getWarrantysitu() {
        return warrantysitu;
    }

    public void setWarrantysitu(ArrayList<warrantystatusdata> warrantysitu) {
        this.warrantysitu = warrantysitu;
    }

    public ArrayList<String> getMatlname() {
        return matlname;
    }

    public void setMatlname(ArrayList<String> matlname) {
        this.matlname = matlname;
    }

    public ArrayList<String> getMillunit() {
        return millunit;
    }

    public void setMillunit(ArrayList<String> millunit) {
        this.millunit = millunit;
    }

    public ArrayList<String> getMatlstand() {
        return matlstand;
    }

    public void setMatlstand(ArrayList<String> matlstand) {
        this.matlstand = matlstand;
    }

    public ArrayList<String> getModelstand() {
        return modelstand;
    }

    public void setModelstand(ArrayList<String> modelstand) {
        this.modelstand = modelstand;
    }

    public ArrayList<String> getSupplier() {
        return supplier;
    }

    public void setSupplier(ArrayList<String> supplier) {
        this.supplier = supplier;
    }

    public ArrayList<String> getDesignation() {
        return designation;
    }

    public void setDesignation(ArrayList<String> designation) {
        this.designation = designation;
    }

    public ArrayList<String> getHeatcondi() {
        return heatcondi;
    }

    public void setHeatcondi(ArrayList<String> heatcondi) {
        this.heatcondi = heatcondi;
    }

}
