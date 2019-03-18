package start.updatesafedisdevice;

import java.util.ArrayList;

public class updatesafedisdevicepost {
    private String dwgno;                               //图号
    private ArrayList<updatesafedisdevicepostdata> data;
    public updatesafedisdevicepost(){
        super();
    }

    public String getDwgno() {
        return dwgno;
    }

    public void setDwgno(String dwgno) {
        this.dwgno = dwgno;
    }

    public ArrayList<updatesafedisdevicepostdata> getData() {
        return data;
    }

    public void setData(ArrayList<updatesafedisdevicepostdata> data) {
        this.data = data;
    }
}
