package start.putmatlsubstitution;

public class putmatlsubstitutionpostdata {
    private String name;                                        //零件名称
    private String designmatl;                                  //设计材料材质
    private String designspec;                                  //设计材料规格
    private String substitutematl;                              //代用材料材质
    private String substitutespec;                              //代用材料规格
    private String type;                                        //代用材料类别
    public putmatlsubstitutionpostdata(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignmatl() {
        return designmatl;
    }

    public void setDesignmatl(String designmatl) {
        this.designmatl = designmatl;
    }

    public String getDesignspec() {
        return designspec;
    }

    public void setDesignspec(String designspec) {
        this.designspec = designspec;
    }

    public String getSubstitutematl() {
        return substitutematl;
    }

    public void setSubstitutematl(String substitutematl) {
        this.substitutematl = substitutematl;
    }

    public String getSubstitutespec() {
        return substitutespec;
    }

    public void setSubstitutespec(String substitutespec) {
        this.substitutespec = substitutespec;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
