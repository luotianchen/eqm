package start.getreportprecontainer;

import java.util.ArrayList;

public class searchdatacontraststanddata {
    private ArrayList<Double> aweldmaxangul;                                //A类焊缝最大棱角度
    private ArrayList<Double> bweldmaxangul;                                //B类焊缝最大棱角度
    private ArrayList<Double> aweldmaxalign;                                //A类焊缝最大错边量
    private ArrayList<Double> bweldmaxalign;                                //B类焊缝最大错边量
    private ArrayList<Double> weldreinfs;                                   //焊缝余高（单面坡口）
    private ArrayList<Double> weldreinfd;                                   //焊缝余高（双面坡口）
    private double proheight;                                               //产品总高实测值
    private ArrayList<Double> innerdia;                                     //筒体内径实测值
    private ArrayList<Double> roundness;                                    //筒体圆度实测值
    private double length;                                                  //筒体长度标准值实测值
    private double straightness;                                            //筒体直线度实测值
    private ArrayList<Double> outward;                                          //封头形状偏差外凸实测值
    private ArrayList<Double> concave;                                      
    private ArrayList<String> shthick;
    public searchdatacontraststanddata(){
        super();
    }

    public ArrayList<Double> getAweldmaxangul() {
        return aweldmaxangul;
    }

    public void setAweldmaxangul(ArrayList<Double> aweldmaxangul) {
        this.aweldmaxangul = aweldmaxangul;
    }

    public ArrayList<Double> getBweldmaxangul() {
        return bweldmaxangul;
    }

    public void setBweldmaxangul(ArrayList<Double> bweldmaxangul) {
        this.bweldmaxangul = bweldmaxangul;
    }

    public ArrayList<Double> getAweldmaxalign() {
        return aweldmaxalign;
    }

    public void setAweldmaxalign(ArrayList<Double> aweldmaxalign) {
        this.aweldmaxalign = aweldmaxalign;
    }

    public ArrayList<Double> getBweldmaxalign() {
        return bweldmaxalign;
    }

    public void setBweldmaxalign(ArrayList<Double> bweldmaxalign) {
        this.bweldmaxalign = bweldmaxalign;
    }

    public ArrayList<Double> getWeldreinfs() {
        return weldreinfs;
    }

    public void setWeldreinfs(ArrayList<Double> weldreinfs) {
        this.weldreinfs = weldreinfs;
    }

    public ArrayList<Double> getWeldreinfd() {
        return weldreinfd;
    }

    public void setWeldreinfd(ArrayList<Double> weldreinfd) {
        this.weldreinfd = weldreinfd;
    }

    public double getProheight() {
        return proheight;
    }

    public void setProheight(double proheight) {
        this.proheight = proheight;
    }

    public ArrayList<Double> getInnerdia() {
        return innerdia;
    }

    public void setInnerdia(ArrayList<Double> innerdia) {
        this.innerdia = innerdia;
    }

    public ArrayList<Double> getRoundness() {
        return roundness;
    }

    public void setRoundness(ArrayList<Double> roundness) {
        this.roundness = roundness;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public ArrayList<Double> getOutward() {
        return outward;
    }

    public void setOutward(ArrayList<Double> outward) {
        this.outward = outward;
    }

    public ArrayList<Double> getConcave() {
        return concave;
    }

    public void setConcave(ArrayList<Double> concave) {
        this.concave = concave;
    }

    public double getStraightness() {
        return straightness;
    }

    public void setStraightness(double straightness) {
        this.straightness = straightness;
    }

    public ArrayList<String> getShthick() {
        return shthick;
    }

    public void setShthick(ArrayList<String> shthick) {
        this.shthick = shthick;
    }
}
