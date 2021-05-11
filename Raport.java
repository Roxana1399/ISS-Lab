package program;

public class Raport {
    public Integer id = null;
    public String nume= null;
    public String data= null;
    public String ora= null;
    public  Integer loc_o = null;
    public  Integer loc_l = null;
    public String pret_l= null;
    public String pret_r= null;

    public Raport(Integer id,String nume, String data, String ora, Integer loc_o, Integer loc_l, String pret_l, String pret_r) {
        this.id=id;
        this.nume = nume;
        this.data = data;
        this.ora = ora;
        this.loc_o = loc_o;
        this.loc_l = loc_l;
        this.pret_l = pret_l;
        this.pret_r = pret_r;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public Integer getLoc_o() {
        return loc_o;
    }

    public void setLoc_o(Integer loc_o) {
        this.loc_o = loc_o;
    }

    public Integer getLoc_l() {
        return loc_l;
    }

    public void setLoc_l(Integer loc_l) {
        this.loc_l = loc_l;
    }

    public String getPret_l() {
        return pret_l;
    }

    public void setPret_l(String pret_l) {
        this.pret_l = pret_l;
    }

    public String getPret_r() {
        return pret_r;
    }

    public void setPret_r(String pret_r) {
        this.pret_r = pret_r;
    }

    @Override
    public String toString() {
        return "Raport{" +
                "nume='" + nume + '\'' +
                ", data='" + data + '\'' +
                ", ora='" + ora + '\'' +
                ", loc_o=" + loc_o +
                ", loc_l=" + loc_l +
                ", pret_l='" + pret_l + '\'' +
                ", pret_r='" + pret_r + '\'' +
                '}';
    }
}
