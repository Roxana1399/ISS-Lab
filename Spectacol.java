package program;

import java.sql.Date;
import java.sql.Time;

public class Spectacol {
    public Integer id= null;
    public String nume=null;
    public String data =null;
    public String ora = null;
    public String pret_l = null;
    public String pret_r = null;

    public Spectacol(Integer id, String nume, String data, String ora, String pret_l, String pret_r) {
        this.id = id;
        this.nume = nume;
        this.data = data;
        this.ora = ora;
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
        return "Spectacol{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", data=" + data +
                ", ora=" + ora +
                ", pret_l='" + pret_l + '\'' +
                ", pret_r='" + pret_r + '\'' +
                '}';
    }
    public boolean validSpectacol(){
        if(this.nume!=" " && this.data!= " " && this.ora!=" " && this.pret_l!=" " && this.pret_r!=" ")
            return true;
        return false;
    }
}
