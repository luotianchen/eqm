package start.putsafedisdevice;

import java.util.ArrayList;

public class putsafedisdevicepost {
    private String dwgno;                               //图号
    private ArrayList<putsafedisdevicepostdata> data;
    public putsafedisdevicepost(){
        super();
    }

    public String getDwgno() {
        return dwgno;
    }

    public void setDwgno(String dwgno) {
        this.dwgno = dwgno;
    }

    public ArrayList<putsafedisdevicepostdata> getData() {
        return data;
    }

    public void setData(ArrayList<putsafedisdevicepostdata> data) {
        this.data = data;
    }
}
