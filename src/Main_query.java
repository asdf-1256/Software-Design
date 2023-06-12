import java.sql.*;

public class Main_query {
    // MySQL 데이터베이스 연결 정보
    private static final String DB_URL = "jdbc:mysql://localhost:3306/login";
    private static final String USER = "newuser";
    private static final String PASS = "0000";

    public static void main(String[] args) {
        String userId = "root"; // 사용자가 입력한 ID
        String password = "1234"; // 사용자가 입력한 비밀번호

        try {
            // MySQL 데이터베이스에 연결
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 사용자 검색 쿼리 실행
            String query = "SELECT * FROM user WHERE id = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, userId);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // 로그인 성공
                System.out.println("로그인에 성공했습니다.");
                // 추가 작업 수행 또는 메인 기능으로 이동
            } else {
                // 로그인 실패
                System.out.println("로그인에 실패했습니다.");
            }

            // 리소스 정리
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}