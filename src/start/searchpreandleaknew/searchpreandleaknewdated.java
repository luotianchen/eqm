package start.searchpreandleaknew;

public class searchpreandleaknewdated {
    private String date;
    private searchpreandleaknewpre press;                                  //试压参数
    private searchpreandleaknewleak leak;                                  //泄漏参数
    public searchpreandleaknewdated(){
        super();
    }

    public searchpreandleaknewpre getPress() {
        return press;
    }

    public void setPress(searchpreandleaknewpre press) {
        this.press = press;
    }

    public searchpreandleaknewleak getLeak() {
        return leak;
    }

    public void setLeak(searchpreandleaknewleak leak) {
        this.leak = leak;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
