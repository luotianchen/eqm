package start.searchnocertsitu;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class searchnocertsituresult {
    private String result;
    private int total;                                                  //总页数
    private ArrayList<searchnocertsitudata> data;
    private ResponseEntity<byte[]> download;                            //下载excel报表
    public searchnocertsituresult(){
        super();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<searchnocertsitudata> getData() {
        return data;
    }

    public void setData(ArrayList<searchnocertsitudata> data) {
        this.data = data;
    }

    public ResponseEntity<byte[]> getDownload() {
        return download;
    }

    public void setDownload(ResponseEntity<byte[]> download) {
        this.download = download;
    }
}
