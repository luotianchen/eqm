package start.putsafedisdevicecache;

public class putsafedisdevicepostcachedata {
    private String name;                        //名称
    private String model;                       //型号
    private String qty;                         //数量
    private String spec;                        //规格
    public putsafedisdevicepostcachedata(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
