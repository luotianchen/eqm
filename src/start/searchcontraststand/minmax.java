package start.searchcontraststand;

public class minmax {
    private String min;
    private String max;
    public minmax(){
        super();
    }
    public minmax(String min,String max){
        this.setMax(max);
        this.setMin(min);
    }
    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        if(!(min == null || min.equals(""))){
            this.min = min;
        }else {
            this.min = "0";
        }
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        if(!(max == null || max.equals(""))){
            this.max = max;
        }else {
            this.max = "9999";
        }
    }
}
