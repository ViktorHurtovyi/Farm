import java.sql.*;
import java.util.GregorianCalendar;

public class mysql {
    final static String username = "root";
    final static String password = "";
    final static String connectingUrl = "jdbc:mysql://localhost:3306/farm";

    public static String[] get() throws ClassNotFoundException {
        String A1 = null, A2 = null, A3 = null, A4 = null, B1 = null, B2 = null, B3 = null, B4 = null;
        GregorianCalendar calendar = new GregorianCalendar();
        int money = 0;
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectingUrl, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from user");
            while (resultSet.next()) {
                money = resultSet.getInt("money");
                A1 = resultSet.getString("A1");
                A2 = resultSet.getString("A2");
                A3 = resultSet.getString("A3");
                A4 = resultSet.getString("A4");
                B1 = resultSet.getString("B1");
                B2 = resultSet.getString("B2");
                B3 = resultSet.getString("B3");
                B4 = resultSet.getString("B4");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String comb[] = {A1, A2, A3, A4, B1, B2, B3, B4, String.valueOf(money)};
        return  comb;
    }

    public static void save(int money, String A1, String A2, String A3, String A4, String B1, String B2, String B3, String B4) {
        try (Connection connection = DriverManager.getConnection(connectingUrl, username, password)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `user` SET `money`="+money+", A1='"+A1+ "', A2='"
                    +A2+ "', A3='"+A3+"', A4='"+A4+"', B1='"+B1+"', B2='"+B2+"', B3='"+B3+"', B4='"+B4+"' WHERE id=1 ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}