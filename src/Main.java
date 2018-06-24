//Vergeet deze import niet
import java.sql.*;

public class Main {
    //Zorg ter voorbereiding dat je ojdbc.jar download en toevoegt aan je project.

    //Aanmaken van de variabelen die je connectie specificeren. In dit geval een gebruiker "harry" met password "harry"
    //Deze code gebruikt de tabel afdelingen van de casus uit het leerboek
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String DB_USER = "system";
    private static final String DB_PASS = "R1mI3xJ2vp";
    private static Connection conn;

    // De methode die met JDBC aan de slag gaat moet een SQLException opvangen of gooien
    public static void main(String[] args) throws SQLException{
        //Besluit welke driver je gaat gebruiken voor je verbinding
        try {
            Class.forName(DB_DRIV).newInstance();
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        // Leg de connectie met de database
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        //System.out.println("Connection made");

        // Een eerste SQL statement maken
        Statement stmt = conn.createStatement();
//        String strQuery = "DELETE FROM Afdelingen WHERE id = 3 ";
//
//        // Een SQL statement uitvoeren
//        stmt.executeUpdate(strQuery);

        // Een tweede statement maken dat een resultaat oplevert
        String queryText = "SELECT * FROM AFDELINGEN";

        // Een tweede statement uitvoeren
        ResultSet rs = stmt.executeQuery(queryText);

        // Iets doen met de resultaten
        int id;
        String naam;
        while (rs.next()) {
            id = rs.getInt("ANR");
            naam = rs.getString("NAAM");
            System.out.println(id + ", " + naam);
        }

        // De resultset, het statement en de verbinding sluiten
        rs.close();
        stmt.close();
        conn.close();

    }
}