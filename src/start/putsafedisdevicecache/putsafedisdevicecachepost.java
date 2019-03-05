package start.putsafedisdevicecache;

import java.util.ArrayList;

public class putsafedisdevicecachepost {
    private String dwgno;                               //图号
    private ArrayList<putsafedisdevicepostcachedata> data;
    public putsafedisdevicecachepost(){
        super();
    }

    public String getDwgno() {
        return dwgno;
    }

    public void setDwgno(String dwgno) {
        this.dwgno = dwgno;
    }

    public ArrayList<putsafedisdevicepostcachedata> getData() {
        return data;
    }

    public void setData(ArrayList<putsafedisdevicepostcachedata> data) {
        this.data = data;
    }
}
