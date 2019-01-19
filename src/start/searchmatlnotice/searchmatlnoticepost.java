package start.searchmatlnotice;

public class searchmatlnoticepost {
    private String matlcode;                            //材料代码：入库编号第五位数字，null为不做要求
    private String year;                                //年份，null为不做要求
    private String month;                               //月份，null为不做要求
    private String codedmarking;                        //入库编号，null为不做要求
    public searchmatlnoticepost(){
        super();
    }

    public String getMatlcode() {
        return matlcode;
    }

    public void setMatlcode(String matlcode) {
        this.matlcode = matlcode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
    }
}
