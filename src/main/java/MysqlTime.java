
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class MysqlTime {
    final static String username = "root";
    final static String password = "";
    final static String connectingUrl = "jdbc:mysql://localhost:3306/farm";

    public static void save(String pleace, GregorianCalendar calendar) throws ParseException {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        try {
            Connection connection = DriverManager.getConnection(connectingUrl, username, password);
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO `time`(`vegColumn`, `ltime`) VALUES('"+pleace+"', '" + time + "') ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static GregorianCalendar get(String pleace) {
        GregorianCalendar calendar = new GregorianCalendar();
        try {
            Connection connection = DriverManager.getConnection(connectingUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM time WHERE vegColumn = '" + pleace + "'");
            while (resultSet.next()) {
                String s = resultSet.getString("ltime");
                Date parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
                calendar.setTime(parsedDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static void delete(String pleace) {
        try {
            Connection connection = DriverManager.getConnection(connectingUrl, username, password);
            Statement statement = connection.createStatement();
            statement.execute("Delete FROM time WHERE vegColumn = '" + pleace + "'");
        } catch (Exception e) {
        }
    }
}