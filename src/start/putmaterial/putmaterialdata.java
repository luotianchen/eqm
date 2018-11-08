package start.putmaterial;

import java.util.ArrayList;

public class putmaterialdata {
    private ArrayList<warrantystatusdata> warrantysitu=new ArrayList<warrantystatusdata>();             //质保书情况
    private ArrayList<String> matlname = new ArrayList<String>();                                       //材料名称
    private ArrayList<String> millunit = new ArrayList<String>();                                       //生存单位
    private ArrayList<String> matlstand = new ArrayList<String>();                                      //材料标准
    private ArrayList<String> modelstand = new ArrayList<String>();                                     //型号标准
    public putmaterialdata(){
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
}
