package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class RepoLoc {
    Vector<Loc> locuri = new Vector<Loc>(500);

    public RepoLoc(Vector<Loc> locuri) {
        this.locuri = locuri;
    }
    public RepoLoc(){

    }

    public Vector<Loc> getLocuri() {
        return locuri;
    }

    public void setLocuri(Vector<Loc> locuri) {
        this.locuri = locuri;
    }
    public Loc getElement(int i){
        return this.locuri.get(i);
    }
   public void add(Loc loc){
        this.locuri.add(loc);
   }
   public Integer size(){
        return this.locuri.size();
   }
    public void addDb(){
        try {
            String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM loc");
            while ( rs.next() ) {
                Integer id = rs.getInt("id");
                String tip = rs.getString("tip");
                //System.out.println(nume);
                Integer numar = rs.getInt("numar");
                //System.out.println(prenume);
                String status = rs.getString("status");
                //System.out.println(email);
                Loc locdb = new Loc(id,tip,numar,status);
              //  System.out.println(locdb.toString());
                this.add(locdb);

            }
            conn.commit();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    public Loc getElId( Integer id){
        Loc lfinal = new Loc();
        for(int i=0;i<this.locuri.size();i++ ){
            Loc loc=this.locuri.get(i);
            if (loc.getId().equals(id))
                lfinal= loc;
        }
        return  lfinal;
    }
    public Integer getIdNumar(Integer numar){
        Integer id =null;
        for (int i =0; i< this.locuri.size();i++){
           Loc spectacol = this.locuri.get(i);
            if (spectacol.getNumar().equals(numar))
                id= spectacol.getId();
        }
        return id;
    }
}
