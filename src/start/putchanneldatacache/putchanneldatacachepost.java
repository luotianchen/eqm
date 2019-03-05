package start.putchanneldatacache;

import java.util.ArrayList;

public class putchanneldatacachepost {
    private String dwgno;                                       //图号
    private ArrayList<putchanneldatacachepostdata> data;
    public putchanneldatacachepost(){

    }

    public String getDwgno() {
        return dwgno;
    }

    public void setDwgno(String dwgno) {
        this.dwgno = dwgno;
    }

    public ArrayList<putchanneldatacachepostdata> getData() {
        return data;
    }

    public void setData(ArrayList<putchanneldatacachepostdata> data) {
        this.data = data;
    }
}
