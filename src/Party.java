import java.sql.*;
import java.util.ArrayList;
public class Party {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/login";
    private static final String USER = "root";
    private static final String PW = "0000";
    private static Connection connection;

    private static ArrayList<String> member;
    private static ArrayList<String> available_day;
    public static ArrayList<String> getMember() {
        return member;
    }

    public static ArrayList<String> getAvailable_day() {
        return available_day;
    }
    public static int create_party(String party_name){
        if(exist_party(party_name))
            return -1;//파티가 존재한다면

        String id = UI_Login.getUser().getId();
        String query = "UPDATE user SET party_name = ? WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, party_name);
            statement.setString(2, id);
            statement.executeUpdate();
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int join_party(String party_name){
        if(!exist_party(party_name))
            return -1;//파티 존재X

        String id = UI_Login.getUser().getId();
        String query = "UPDATE user SET party_name = ? WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, party_name);
            statement.setString(2, id);
            statement.executeUpdate();
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void exit_party(){
        String id = UI_Login.getUser().getId();

        String query = "UPDATE user SET party_name = NULL WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private static boolean exist_party(String party_name){
        if (connection == null)
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PW);
            } catch (Exception e) {
                e.printStackTrace();
            }

        String query = "SELECT * INTO user WHERE party_name = ?";
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, party_name);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                resultSet.close();
                statement.close();
                return true;
            }
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String get_party(){
        String id = UI_Login.getUser().getId();

        String query = "SELECT party_name FROM user WHERE id = ?";

        PreparedStatement statement = null;
        String party_name = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                party_name = resultSet.getString("party_name");
                resultSet.close();
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return party_name;
    }
    public void setDay(String day){
        String id = UI_Login.getUser().getId();

        String query = "UPDATE user SET available_day = ? WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, day);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void getDay(){
        member = new ArrayList<>();
        available_day = new ArrayList<>();

        String id = UI_Login.getUser().getId();

        String party_name = get_party();

        String query = "SELECT * FROM user WHERE party_name = ?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, party_name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("id").equals(id))
                    continue;
                member.add(resultSet.getString("id"));
                available_day.add("available_day");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
