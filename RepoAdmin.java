package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class RepoAdmin {
    public Vector<Admin> admini = new Vector<Admin>(20);

    public RepoAdmin(){

    }
    public RepoAdmin(Vector<Admin> admini) {
        this.admini = admini;
    }

    public Vector<Admin> getAdmini() {
        return admini;
    }

    public void setAdmini(Vector<Admin> admini) {
        this.admini = admini;
    }
    public Integer size(){
        return this.admini.size();
    }
    public void add(Admin admin){
        this.admini.add(admin);
    }

    public void addDb(){
        try {
            String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM admin");
            while ( rs.next() ) {
                Integer id = rs.getInt("id");
                String nume = rs.getString("nume");
                //System.out.println(nume);
                String prenume = rs.getString("prenume");
                //System.out.println(prenume);
                String email = rs.getString("email");
                //System.out.println(email);
                String parola = rs.getString("parola");
                Admin admindb = new Admin(id,nume,prenume,email,parola);
                //System.out.println(clientdb.toString());
                this.add(admindb);
            }
            conn.commit();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
