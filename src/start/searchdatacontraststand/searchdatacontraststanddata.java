package start.searchdatacontraststand;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class searchdatacontraststanddata {
    DecimalFormat df = new DecimalFormat("#.0");

    private ArrayList<String> aweldmaxangul;                                //A类焊缝最大棱角度
    private ArrayList<String> bweldmaxangul;                                //B类焊缝最大棱角度
    private ArrayList<String> aweldmaxalign;                                //A类焊缝最大错边量
    private ArrayList<String> bweldmaxalign;                                //B类焊缝最大错边量
    private ArrayList<String> weldreinfs;                                   //焊缝余高（单面坡口）
    private ArrayList<String> weldreinfd;                                   //焊缝余高（双面坡口）
    private String proheight;                                               //产品总高实测值
    private ArrayList<String> innerdia;                                     //筒体内径实测值
    private ArrayList<String> roundness;                                    //筒体圆度实测值
    private String length;                                                  //筒体长度标准值实测值
    private String straightness;                                            //筒体直线度实测值
    private ArrayList<Integer> outward;                                          //封头形状偏差外凸实测值
    private ArrayList<Integer> concave;
    private ArrayList<String> shthick;
    public searchdatacontraststanddata(){
        super();
    }

    public ArrayList<String> getAweldmaxangul() {
        return aweldmaxangul;
    }

    public void setAweldmaxangul(ArrayList<Double> aweldmaxangul) {
        for (int i = 0 ; i<aweldmaxangul.size();i++){
            this.aweldmaxangul.add(df.format(aweldmaxangul.get(i)));
        }

    }

    public ArrayList<String> getBweldmaxangul() {
        return bweldmaxangul;
    }

    public void setBweldmaxangul(ArrayList<Double> bweldmaxangul) {
        for (int i = 0 ; i<bweldmaxangul.size();i++){
            this.bweldmaxangul.add(df.format(bweldmaxangul.get(i)));
        }
    }

    public ArrayList<String> getAweldmaxalign() {
        return aweldmaxalign;
    }

    public void setAweldmaxalign(ArrayList<Double> aweldmaxalign) {
        for (int i = 0 ; i<aweldmaxalign.size();i++){
            this.aweldmaxalign.add(df.format(aweldmaxalign.get(i)));
        }
    }

    public ArrayList<String> getBweldmaxalign() {
        return bweldmaxalign;
    }

    public void setBweldmaxalign(ArrayList<Double> bweldmaxalign) {
        for (int i = 0 ; i<bweldmaxalign.size();i++){
            this.bweldmaxalign.add(df.format(bweldmaxalign.get(i)));
        }
    }

    public ArrayList<String> getWeldreinfs() {
        return weldreinfs;
    }

    public void setWeldreinfs(ArrayList<Double> weldreinfs) {
        for (int i = 0 ; i<weldreinfs.size();i++){
            this.weldreinfs.add(df.format(weldreinfs.get(i)));
        }
    }

    public ArrayList<String> getWeldreinfd() {
        return weldreinfd;
    }

    public void setWeldreinfd(ArrayList<Double> weldreinfd) {
        for (int i = 0 ; i<weldreinfd.size();i++){
            this.weldreinfd.add(df.format(weldreinfd.get(i)));
        }
    }

    public String getProheight() {
        return proheight;
    }

    public void setProheight(double proheight) {
        this.proheight = df.format(proheight);
    }

    public ArrayList<String> getInnerdia() {
        return innerdia;
    }

    public void setInnerdia(ArrayList<Double> innerdia) {
        for (int i = 0 ; i<innerdia.size();i++){
            this.innerdia.add(df.format(innerdia.get(i)));
        }
    }

    public ArrayList<String> getRoundness() {
        return roundness;
    }

    public void setRoundness(ArrayList<Double> roundness) {
        for (int i = 0 ; i<roundness.size();i++){
            this.roundness.add(df.format(roundness.get(i)));
        }
    }

    public String getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = df.format(length);
    }

    public ArrayList<Integer> getOutward() {
        return outward;
    }

    public void setOutward(ArrayList<Double> outward) {
        for (int i = 0 ; i<outward.size();i++){
            this.outward.add(outward.get(i).intValue());
        }
    }

    public ArrayList<Integer> getConcave() {
        return concave;
    }

    public void setConcave(ArrayList<Double> concave) {
        for (int i = 0 ; i<concave.size();i++){
            this.concave.add(concave.get(i).intValue());
        }
    }

    public String getStraightness() {
        return straightness;
    }

    public void setStraightness(double straightness) {
        this.straightness = df.format(straightness);
    }

    public ArrayList<String> getShthick() {
        return shthick;
    }

    public void setShthick(ArrayList<String> shthick) {
        this.shthick = shthick;
    }
}
