package start.liststring;

import java.util.ArrayList;
import java.util.Arrays;

public class liststring {
    public String listtostring(ArrayList<String> as,String a){
        String re=new String();
        for(int i = 0;i<as.size();i++){
            if(i!= as.size()-1){
                re = re+as.get(i)+"#";
            }else {
                re = re+as.get(i);
            }
        }
        return re;
    }
    public ArrayList<String> stringtolist(String re,String a){
        ArrayList<String> as = new ArrayList<String>(Arrays.asList(re.split(a)));
        return as;
    }
}
