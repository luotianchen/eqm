package start.searchnocertsitu;

public class searchnocertsituexcelpost {
    private String matlcode;                                //材料代码：入库编号第五位数字，null为不做要求
    public searchnocertsituexcelpost(){
        super();
    }

    public String getMatlcode() {
        return matlcode;
    }

    public void setMatlcode(String matlcode) {
        this.matlcode = matlcode;
    }
}