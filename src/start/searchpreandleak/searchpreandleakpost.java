package start.searchpreandleak;

public class searchpreandleakpost {
    private String prodno;                                  //产品编号
    private String ppart;                                   //试压部位（通道名称）
    private String datetype;                                //dated1或者dated2、dated3
    public searchpreandleakpost(){
        super();
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }

    public String getPpart() {
        return ppart;
    }

    public void setPpart(String ppart) {
        this.ppart = ppart;
    }

    public String getDatetype() {
        return datetype;
    }

    public void setDatetype(String datetype) {
        this.datetype = datetype;
    }
}
