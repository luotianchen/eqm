package start.searchsubstitutestatus;

public class searchsubstitutestatuspost {
    private int design_status;                                                   //设计是否审核
    private int matl_status;                                                      //材料是否审核
    private int welding_status;                                                 //焊接审核人
    private int process_status;                                                     //工艺审核人
    private int inspection_status;                                              //检验审核人
    private int status_b;                                                           //设计审核人
    private int status_c;                                                           //技术负责人
    public searchsubstitutestatuspost(){
        super();
    }

    public int getDesign_status() {
        return design_status;
    }

    public void setDesign_status(int design_status) {
        this.design_status = design_status;
    }

    public int getMatl_status() {
        return matl_status;
    }

    public void setMatl_status(int matl_status) {
        this.matl_status = matl_status;
    }

    public int getWelding_status() {
        return welding_status;
    }

    public void setWelding_status(int welding_status) {
        this.welding_status = welding_status;
    }

    public int getProcess_status() {
        return process_status;
    }

    public void setProcess_status(int process_status) {
        this.process_status = process_status;
    }

    public int getInspection_status() {
        return inspection_status;
    }

    public void setInspection_status(int inspection_status) {
        this.inspection_status = inspection_status;
    }

    public int getStatus_b() {
        return status_b;
    }

    public void setStatus_b(int status_b) {
        this.status_b = status_b;
    }

    public int getStatus_c() {
        return status_c;
    }

    public void setStatus_c(int status_c) {
        this.status_c = status_c;
    }
}
