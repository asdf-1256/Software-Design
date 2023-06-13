import java.sql.*;
public class Login {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/login";
    private static final String USER = "root";
    private static final String PW = "0000";
    private static Connection connection;


    public static User login(String id, String pw) throws SQLException{
        if(connection == null)
            getConnection();
        String query = "SELECT * FROM user WHERE id = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, pw);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            resultSet.close();
            statement.close();
            return new User(id, pw);
        }

        resultSet.close();
        statement.close();
        return null;
    }
    public static boolean existUser(String id) throws SQLException{
        if(connection == null)
            getConnection();
        String query = "SELECT * FROM user WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            resultSet.close();
            statement.close();
            return true;
        }

        resultSet.close();
        statement.close();
        return false;
    }
    public static void signup(String id, String pw)throws SQLException{
        if(connection == null)
            getConnection();
        String query = "INSERT INTO user(id, password) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, pw);
        statement.executeUpdate();
        statement.close();
    }
    private static void getConnection(){
        try{
            connection = DriverManager.getConnection(DB_URL, USER, PW);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
