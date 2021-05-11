package program;

public class Loc {
    public Integer id=null;
    public String tip = null;
    public Integer numar = null;
    public String status = "liber";

    public Loc(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Loc(Integer id, String tip, Integer numar, String status) {
        this.id = id;
        this.tip = tip;
        this.numar = numar;
        this.status = status;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getNumar() {
        return numar;
    }

    public void setNumar(Integer numar) {
        this.numar = numar;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Loc{" +
                "tip='" + tip + '\'' +
                ", numar=" + numar +
                ", status='" + status + '\'' +
                '}';
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void changeStatus(){
        this.status = "rezervat";
    }
    public void changeStatusRemove(){
        this.status="liber";
    }
}
