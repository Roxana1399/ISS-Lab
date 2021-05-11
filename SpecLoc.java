package program;

public class SpecLoc {
    public String spectacol = null;
    public String data = null;
    public  String ora = null;
    public Integer nr = null;
    public String tip = null;
    public String pret = null;



    @Override
    public String toString() {
        return "SpecLoc{" +
                "spectacol='" + spectacol + '\'' +
                ", data='" + data + '\'' +
                ", ora='" + ora + '\'' +
                ", nr=" + nr +
                ", tip='" + tip + '\'' +
                ", pret='" + pret + '\'' +
                '}';
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public SpecLoc(String spectacol, String data, String ora, Integer nr, String tip, String pret) {
        this.spectacol = spectacol;
        this.data = data;
        this.ora = ora;
        this.nr = nr;
        this.tip = tip;
        this.pret = pret;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

    public String getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(String spectacol) {
        this.spectacol = spectacol;
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


}
