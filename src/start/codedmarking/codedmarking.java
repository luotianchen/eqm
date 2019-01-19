package start.codedmarking;

public class codedmarking {
    private String codedmarking;
    private int matlcode;
    public codedmarking(){
        super();
    }
    public codedmarking(String codedmarking){
        setCodedmarking(codedmarking);
        matlcode = 5;
    }

    public char Matlcode(){
        return getCodedmarking().charAt(getMatlcode()-1);
    }

    @Override
    public String toString() {
        return getCodedmarking();
    }

    public String getCodedmarking() {
        return codedmarking;
    }

    public void setCodedmarking(String codedmarking) {
        this.codedmarking = codedmarking;
    }

    public int getMatlcode() {
        return matlcode;
    }

    public void setMatlcode(int matlcode) {
        this.matlcode = matlcode;
    }
}
