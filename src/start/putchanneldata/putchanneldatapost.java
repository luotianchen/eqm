package start.putchanneldata;

import java.util.ArrayList;

public class putchanneldatapost {
    private String dwgno;                                       //图号
    private ArrayList<putchanneldatapostdata> data;
    public putchanneldatapost(){

    }

    public String getDwgno() {
        return dwgno;
    }

    public void setDwgno(String dwgno) {
        this.dwgno = dwgno;
    }

    public ArrayList<putchanneldatapostdata> getData() {
        return data;
    }

    public void setData(ArrayList<putchanneldatapostdata> data) {
        this.data = data;
    }
}
