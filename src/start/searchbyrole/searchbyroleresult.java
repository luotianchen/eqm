package start.searchbyrole;

public class searchbyroleresult {
    private String result;
    private String departmentname;                                  //部门名称
    private int departmentid;                                    //部门id
    public searchbyroleresult(){
        super();
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }
}
