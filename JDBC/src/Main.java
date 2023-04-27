import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc://mysql://localhost:3306/";
        String user = "root";
        String password = "12341234";

        Connection connection1 = DriverManager.getConnection(url, user, password);
        Connection connection2 = DriverManager.getConnection(url, user, password);

        String query = "UPDATE GOODS SET GD_STOCK = GD_STOCK - ? WHERE GD_ID = ?";

        PreparedStatement statement = connection1.prepareStatement(query);

        // ?에 값을 매핑 한다.
        statement.setInt(1, 3);
        statement.setInt(2, 1);
        //
        /**
         * 추천해준 드라마 재밌었었고, 오늘 하루 수고하셨어요
         * 추천해준 영화 자극적인이긴 해도
         */
        // 질의 실행
        int result = statement.executeUpdate();

    }

}
