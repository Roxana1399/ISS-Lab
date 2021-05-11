package program;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Vector;

public class RepoClient {
    Vector<Client> clienti = new Vector<Client>(20);

    public RepoClient() {

    }

    public List<Client> getClienti() {
        return clienti;
    }
    public Integer size(){
        return this.clienti.size();
    }

    public void setClienti(Vector<Client> clienti) {
        this.clienti = clienti;
    }

    public void add(Client client){
        this.clienti.add(client);
    }

    public RepoClient(Vector<Client> clienti) {
        this.clienti = clienti;
    }

    public void addDb(){
        try {
            String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM client");
            while ( rs.next() ) {
                String cnp = rs.getString("cnp");
                String nume = rs.getString("nume");
                //System.out.println(nume);
                String prenume = rs.getString("prenume");
                //System.out.println(prenume);
                String email = rs.getString("email");
                //System.out.println(email);
                String telefon = rs.getString("telefon");
                String parola = rs.getString("parola");
                Client clientdb = new Client(cnp,nume, prenume, email, telefon, parola);
                //System.out.println(clientdb.toString());
                this.add(clientdb);

            }
            conn.commit();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    private boolean checkWrite(String cnp){
        addDb();
        for (int i=0; i<this.clienti.size();i++){
            if(this.clienti.get(i).getCnp().equals(cnp)) return true;
        }
        return false;
    }

    public String insertClient(Client c){
        Integer max = 999999;
        Integer min = 100000;
        Integer i = (int) (Math.random() * ((max - min) + 1)) + min;
        try {
            String url = "jdbc:sqlite:C:\\Users\\Roxana\\Desktop\\SQLite\\mydatabase.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            String query = "INSERT INTO client(cnp, nume, prenume, email, telefon, parola) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, c.getCnp());
            preparedStmt.setString(2,c.getNume());
            preparedStmt.setString(3,c.getPrenume());
            preparedStmt.setString(4,c.getEmail());
            preparedStmt.setString(5,c.getTelefon());
            preparedStmt.setString(6,String.valueOf(i));
            preparedStmt.execute();
            conn.commit();
            preparedStmt.close();
            conn.close();
        } catch (Exception e) {
            return null;
        }
        return String.valueOf(i);

    }
    public String getPassCnp(String cnp){
        this.clienti.clear(); addDb();
        for (int i=0; i<this.clienti.size();i++){
            if(this.clienti.get(i).getCnp().equals(cnp))
            {//System.out.println(this.clienti.get(i).getParola());
                return this.clienti.get(i).getParola();}

    }
        return null;
    }
}
