package program;

import java.sql.*;
import java.util.Vector;

public class RepoRezervare {
    public Vector<Rezervare> rezervari = new Vector<Rezervare>(50);

    public RepoRezervare(Vector<Rezervare> rezervari) {
        this.rezervari = rezervari;
    }
    public RepoRezervare(){

    }
    public Vector<Rezervare> getRezervari() {
        return rezervari;
    }

    public void setRezervari(Vector<Rezervare> rezervari) {
        this.rezervari = rezervari;
    }
    public Integer size(){
        return this.rezervari.size();
    }
    public void add(Rezervare rezervare){
        this.rezervari.add(rezervare);
    }
    public void addBd(){
        try {
            String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM rezervare");
            while ( rs.next() ) {
                Integer ok=0;
                Integer id = rs.getInt("id");
                String cnp = rs.getString("cnp");
                //System.out.println(cnp);
                Integer id_loc = rs.getInt("id_loc");
                //System.out.println(id_loc);
                Integer id_spectacol = rs.getInt("id_spectacol");
                //System.out.println(id_spectacol);

                if (this.rezervari.size() >0) {
                    for (int i = 0; i < this.rezervari.size(); i++) {
                        Rezervare rez = this.rezervari.get(i);
                        //System.out.println("Rezervare" + i);

                        String cnp_c = rez.getId_client();
                      //  System.out.println(cnp_c);

                        Integer id_s = rez.getId_spectacol();
                        if (cnp.equals(cnp_c) && id_spectacol.equals(id_s)) {
                            //System.out.println(id_loc);

                            rez.addLoc(id_loc);
                            ok = 1;
                           // System.out.println(rez.toString());
                        }
                    }
                }
                if (ok==0){
                    Vector<Integer> vec_l = new Vector<Integer>(50);
                    vec_l.add(id_loc);
                    Rezervare rezervarebd = new Rezervare(id,cnp, vec_l,id_spectacol);
                   // System.out.println(rezervarebd.toString());
                    this.add(rezervarebd);
                }
            }
            conn.commit();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    public boolean verifRez(Integer id_s, Integer id_l){
        Integer id = 0;
        try {
            String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM rezervare");
            while ( rs.next() ) {
                Integer ok=0;
                Integer id_loc = rs.getInt("id_loc");
                Integer id_spectacol = rs.getInt("id_spectacol");
                if(id_loc.equals(id_l) && id_spectacol.equals(id_s)){
                    id=rs.getInt("id");
                }
            }
            conn.commit();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        if (id !=0){return false;} else {return true;}
    }
    public boolean adaugareRezervare(String cnp, Integer loc, Integer sp){
        addBd();
        Integer m = this.rezervari.size();
        try {
            String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            String query = "INSERT INTO rezervare(cnp, id_loc, id_spectacol) " + "VALUES(?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, cnp);
            preparedStmt.setInt(2, loc);
            preparedStmt.setInt(3, sp);
            preparedStmt.execute();
            conn.commit();
            preparedStmt.close();
            conn.close();
            return true;

        } catch (Exception e) {

            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return false;
    }
    public boolean anulareRez(String cnp, Integer loc, Integer sp){
        addBd(); Integer n=this.rezervari.size();
        try {
            String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            String query = "DELETE FROM rezervare WHERE cnp = ? and id_loc = ? and id_spectacol = ?  ";
            //String query = "delete from users where id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, cnp);
            preparedStmt.setInt(2, loc);
            preparedStmt.setInt(3, sp);
            preparedStmt.execute();
            conn.commit();
            preparedStmt.close();
            conn.close();
            //if (make ==false){error2.setText("Data invalide !");} else {error2.setText("Anulare realizata cu succes !");}
        } catch (Exception e) {
            return false;
        }
        this.rezervari.clear();addBd();
        if(this.rezervari.size()<n) return true; else return false;
    }


}
