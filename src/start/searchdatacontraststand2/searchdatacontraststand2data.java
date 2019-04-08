package start.searchdatacontraststand2;

import java.math.BigDecimal;
import java.util.ArrayList;

public class searchdatacontraststand2data {
    private double aweldmaxangul;
    private double bweldmaxangul;
    private double aweldmaxalign;
    private double bweldmaxalign;
    private double weldreinfs;
    private double weldreinfd;
    private double proheight;
    private double innerdia;
    private double roundness;
    private double length;
    private double straightness;
    private double outward;
    private double concave;
    private ArrayList<String> shthick;
    public searchdatacontraststand2data(){
        super();
    }

    public double getAweldmaxangul() {
        return aweldmaxangul;
    }

    public void setAweldmaxangul(double aweldmaxangul) {
        BigDecimal b = new BigDecimal(aweldmaxangul);
        this.aweldmaxangul = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getBweldmaxangul() {
        return bweldmaxangul;
    }

    public void setBweldmaxangul(double bweldmaxangul) {
        BigDecimal b = new BigDecimal(bweldmaxangul);
        this.bweldmaxangul = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getAweldmaxalign() {
        return aweldmaxalign;
    }

    public void setAweldmaxalign(double aweldmaxalign) {
        BigDecimal b = new BigDecimal(aweldmaxalign);
        this.aweldmaxalign = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getBweldmaxalign() {
        return bweldmaxalign;
    }

    public void setBweldmaxalign(double bweldmaxalign) {
        BigDecimal b = new BigDecimal(bweldmaxalign);
        this.bweldmaxalign = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getWeldreinfs() {
        return weldreinfs;
    }

    public void setWeldreinfs(double weldreinfs) {
        BigDecimal b = new BigDecimal(weldreinfs);
        this.weldreinfs = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getWeldreinfd() {
        return weldreinfd;
    }

    public void setWeldreinfd(double weldreinfd) {
        BigDecimal b = new BigDecimal(weldreinfd);
        this.weldreinfd = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getProheight() {
        return proheight;
    }

    public void setProheight(double proheight) {
        BigDecimal b = new BigDecimal(proheight);
        this.proheight = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getInnerdia() {
        return innerdia;
    }

    public void setInnerdia(double innerdia) {
        BigDecimal b = new BigDecimal(innerdia);
        this.innerdia = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getRoundness() {
        return roundness;
    }

    public void setRoundness(double roundness) {
        BigDecimal b = new BigDecimal(roundness);
        this.roundness = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        BigDecimal b = new BigDecimal(length);
        this.length = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getStraightness() {
        return straightness;
    }

    public void setStraightness(double straightness) {
        BigDecimal b = new BigDecimal(straightness);
        this.straightness = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getOutward() {
        return outward;
    }

    public void setOutward(double outward) {
        BigDecimal b = new BigDecimal(outward);
        this.outward = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getConcave() {
        return concave;
    }

    public void setConcave(double concave) {
        BigDecimal b = new BigDecimal(concave);
        this.concave = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public ArrayList<String> getShthick() {
        return shthick;
    }

    public void setShthick(ArrayList<String> shthick) {
        this.shthick = shthick;
    }
}
