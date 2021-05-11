package program;

import java.sql.*;
import java.util.Vector;

public class RunClient {
    private static Object PreparedStatement;

    public static void main(String[] args) {
        String s1="Sachin ";
        String s2="Tendulkar";
        String s3=s1.concat(" ").concat(s2);
        System.out.println(s3);
        /*Integer max = 999999;
        Integer min = 100000;
        Integer i = (int) (Math.random() * ((max - min) + 1)) + min;
        System.out.println(i);*/
    }
}
       /*ServiceLocSpec locDisp = new ServiceLocSpec();
       locDisp.locruri_Disponibile(3);
       locDisp.toString();
         try {
            String url = "jdbc:postgresql://localhost:5432/Teatrudb";
            Connection conn = DriverManager.getConnection(url,"postgres","123123");
            Statement stmt = conn.createStatement();
            ResultSet rs;
           // DriverManager.getConnection(url,"postgres","123123").createStatement().executeUpdate("UPDATE spectacol SET data= '2020-05-10'WHERE id = 1");
          //  DriverManager.getConnection(url,"postgres", "123123").createStatement().execute("DELETE FROM spectacol WHERE id = 1");
             String cnp = "1982105025412";
             Integer id_l = 2;
             Integer id_s  = 2;
             Integer id_r = 4;
             String query = "INSERT INTO rezervare(cnp, id_loc, id_spectacol) " + "VALUES(?,?,?)";
             PreparedStatement preparedStmt = conn.prepareStatement(query);
             //preparedStmt.setInt(1,id_r);
             preparedStmt.setString(1, cnp);
             preparedStmt.setInt(2,id_l);
             preparedStmt.setInt(3,id_s);
             preparedStmt.execute();}
                     //DriverManager.getConnection(url,"postgres", "123123").createStatement().execute("INSERT INTO rezervare( id,cnp, id_loc, id_spectacol) ; " +


                     /*rs = stmt.executeQuery("SELECT * FROM client");
            while ( rs.next() ) {
                Integer id = rs.getInt("id");
                String nume = rs.getString("cnp");
                System.out.println(nume);
               Date data = rs.getDate("data");
                //System.out.println(prenume);
                Time ora = rs.getTime("ora");
                //System.out.println(email);
                String pret_l = rs.getString("pret_l");
                String pret_r= rs.getString("pret_r");
                Spectacol sperctacoldb = new Spectacol(id,nume, data, ora, pret_l, pret_r);
                System.out.println(sperctacoldb.toString());

            }
            conn.close();*/
         /*catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

}*/
