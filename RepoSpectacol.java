package program;

import java.sql.*;
import java.util.Vector;

public class RepoSpectacol {
    Vector<Spectacol> spectacole = new Vector<Spectacol>(20);

    public RepoSpectacol(Vector<Spectacol> spectacole) {
        this.spectacole = spectacole;
    }

    public RepoSpectacol() {

    }

    public Vector<Spectacol> getSpectacole() {
        return spectacole;
    }

    public void setSpectacole(Vector<Spectacol> spectacole) {
        this.spectacole = spectacole;
    }
    public void add(Spectacol spectacol){
        this.spectacole.add(spectacol);
    }
    public Integer size(){
        return this.spectacole.size();
    }
    public Integer getIdName(String nume){
        Integer id =null;
        for (int i =0; i< this.spectacole.size();i++){
            Spectacol spectacol = this.spectacole.get(i);
            if (spectacol.getNume().equals(nume))
                id= spectacol.getId();
        }
        return id;
    }
    public String getNumeID(Integer id){
        String nume=null;
        for (int i =0; i< this.spectacole.size();i++){
            Spectacol spectacol = this.spectacole.get(i);
            if (spectacol.getId().equals(id))
                nume = spectacol.getNume();
        }
        return  nume;
    }
    public String getPretLId(Integer id){
        String nume=null;
        for (int i =0; i< this.spectacole.size();i++){
            Spectacol spectacol = this.spectacole.get(i);
            if (spectacol.getId().equals(id))
                nume = spectacol.getPret_l();
        }
        return  nume;
    }
    public String getPretRId(Integer id){
        String nume=null;
        for (int i =0; i< this.spectacole.size();i++){
            Spectacol spectacol = this.spectacole.get(i);
            if (spectacol.getId().equals(id))
                nume = spectacol.getPret_r();
        }
        return  nume;
    }
    public String getDataId(Integer id){
        String nume=null;
        for (int i =0; i< this.spectacole.size();i++){
            Spectacol spectacol = this.spectacole.get(i);
            if (spectacol.getId().equals(id))
                nume = spectacol.getData();
        }
        return  nume;
    }
    public String getOraId(Integer id){
        String nume=null;
        for (int i =0; i< this.spectacole.size();i++){
            Spectacol spectacol = this.spectacole.get(i);
            if (spectacol.getId().equals(id))
                nume = spectacol.getOra();
        }
        return  nume;
    }
    public void addDb(){
        try {
            String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM spectacol");
            while ( rs.next() ) {
                Integer id = rs.getInt("id");
                String nume = rs.getString("nume");
                //System.out.println(nume);
                String data = rs.getString("data");
                //System.out.println(prenume);
                String ora = rs.getString("ora");
                //System.out.println(email);
                String pret_l = rs.getString("pret_l");
                String pret_r= rs.getString("pret_r");
                Spectacol sperctacoldb = new Spectacol(id,nume, data, ora, pret_l, pret_r);
                System.out.println(sperctacoldb.toString());
                this.add(sperctacoldb);

            }
            conn.commit();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    public boolean verifData(String data){
        for (int i=0; i<this.spectacole.size();i++)
        {
            if (this.spectacole.get(i).getData().equals(data)){System.out.println("false");
                return false; }
        }
        System.out.println("true");
        return true;
    }

    public boolean writeBD(Spectacol spectacol){ addDb();
        if(spectacol.validSpectacol() ){ System.out.println(spectacol.validSpectacol());
            if(verifData(spectacol.getData())){System.out.println(verifData(spectacol.getData()));
                try {
                    String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection(url);
                    conn.setAutoCommit(false);
                    Statement stmt = conn.createStatement();
                    String query = "INSERT INTO spectacol(nume, data, ora, pret_l, pret_r) " + "VALUES(?,?,?,?,?)";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, spectacol.getNume());
                    preparedStmt.setString(2,spectacol.getData());
                    preparedStmt.setString(3,spectacol.getOra());
                    preparedStmt.setString(4,spectacol.getPret_l());
                    preparedStmt.setString(5,spectacol.getPret_r());
                    preparedStmt.execute();
                    conn.commit();
                    preparedStmt.close();
                    conn.close();
                } catch (Exception e) {
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            }else return false;
        }
        else return false;
        if (checkWrite(spectacol.getNume())) return true; else  return false;
    }
    private boolean checkWrite(String nume){
        this.spectacole.clear();
        addDb();
        for(int i=0; i<=this.spectacole.size();i++){
            if(this.spectacole.get(i).getNume().equals(nume))
            {return true;}
        }
        return false;
    }
    public boolean stergerDB(Integer id) {
        addDb();
        Integer m = this.spectacole.size();
        try {
            String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            String query = "DELETE FROM spectacol WHERE id = ? ";
            //String query = "delete from users where id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
            conn.commit();
            preparedStmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        this.spectacole.clear();
        addDb();
        if (m > this.spectacole.size()) {
            return true;
        } else return false;
    }
    private boolean checkId(Integer id){
        addDb();
        for(int i=0; i<=this.spectacole.size();i++){
            if(this.spectacole.get(i).getId().equals(id))
            return true;}
        return false;
    }
    public boolean modifData(Integer id, String data){
        if (checkId(id)==false) return false;
        else{
            try {
                String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection(url);
                conn.setAutoCommit(false);
                String query = "UPDATE spectacol SET data=? WHERE id = ? ";
                //String query = "delete from users where id = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, data);
                preparedStmt.setInt(2, id);
                preparedStmt.execute();
                conn.commit();
                preparedStmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        return  true;
    }

    public boolean modifOra(Integer id, String data){
        if (checkId(id)==false) return false;
        else{
            try {
                String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection(url);
                conn.setAutoCommit(false);
                String query = "UPDATE spectacol SET ora=? WHERE id = ? ";
                //String query = "delete from users where id = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, data);
                preparedStmt.setInt(2, id);
                preparedStmt.execute();
                conn.commit();
                preparedStmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        return  true;
    }
    public boolean modifPretl(Integer id, String data){
        if (checkId(id)==false) return false;
        else{
            try {
                String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection(url);
                conn.setAutoCommit(false);
                String query = "UPDATE spectacol SET pret_l=? WHERE id = ? ";
                //String query = "delete from users where id = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, data);
                preparedStmt.setInt(2, id);
                preparedStmt.execute();
                conn.commit();
                preparedStmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        return  true;
    }
    public boolean modifPretr(Integer id, String data){
        if (checkId(id)==false) return false;
        else{
            try {
                String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection(url);
                conn.setAutoCommit(false);
                String query = "UPDATE spectacol SET pret_r=? WHERE id = ? ";
                //String query = "delete from users where id = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, data);
                preparedStmt.setInt(2, id);
                preparedStmt.execute();
                conn.commit();
                preparedStmt.close();
                conn.close();
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
        return  true;
    }

}
