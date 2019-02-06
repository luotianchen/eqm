package start.putpressurepartscache;

import java.util.ArrayList;

public class putpressurepartscachepost {
    private String prodno;
    private ArrayList<putpressurepartscachepostdata> data = new ArrayList<putpressurepartscachepostdata>();
    public putpressurepartscachepost(){
        super();
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno;
    }

    public ArrayList<putpressurepartscachepostdata> getData() {
        return data;
    }

    public void setData(ArrayList<putpressurepartscachepostdata> data) {
        this.data = data;
    }
}
