package program;

import java.util.Vector;

public class Rezervare {
    public Integer id = null;
    public String id_client = null; //cnp
    public Vector<Integer>  locuri = new Vector<Integer>(20);
    public Integer id_spectacol = null;

    @Override
    public String toString() {
        return "Rezervare{" +
                "id=" + id +
                ", id_client='" + id_client + '\'' +
                ", locuri=" + locuri +
                ", id_spectacol=" + id_spectacol +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public Vector<Integer> getLocuri() {
        return locuri;
    }

    public void setLocuri(Vector<Integer> locuri) {
        this.locuri = locuri;
    }

    public Integer getId_spectacol() {
        return id_spectacol;
    }

    public void setId_spectacol(Integer id_spectacol) {
        this.id_spectacol = id_spectacol;
    }

    public Rezervare(Integer id, String id_client, Vector<Integer> locuri, Integer id_spectacol) {
        this.id = id;
        this.id_client = id_client;
        this.locuri = locuri;
        this.id_spectacol = id_spectacol;
    }
    public void addLoc(Integer i){
        this.locuri.add(i);
    }
}
