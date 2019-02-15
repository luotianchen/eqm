package start.contraststand;

public class type {
    private String range;
    private String deviation;
    public type(){
        super();
    }

    public String getRange() {
        return range;
    }

    public void setRange(String maxrange,String maxrange2,String minrange,String minrange2) {
        if(!(maxrange == null || maxrange.equals("") || maxrange.equals("null"))){
            this.range = "<"+maxrange;
        }
        if(!(maxrange2 == null || maxrange2.equals("") || maxrange2.equals("null"))){
            this.range = "<="+maxrange2;
        }
        if(!(minrange == null || minrange.equals("") || minrange.equals("null"))){
            this.range = ">"+minrange;
        }
        if(!(minrange2 == null || minrange2.equals("") || minrange2.equals("null"))){
            this.range = ">="+minrange2;
        }
    }

    public String getDeviation() {
        return deviation;
    }

    public void setDeviation(String updeviation,String downdeviation) {
        this.deviation = downdeviation+"~"+updeviation;
    }
}
