import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1111";


    public static void main(String[] args) throws SQLException {
        SimpleDataSource dataSource = new SimpleDataSource();
        // подключение к базе данных
        Connection connection = dataSource.openConnection(URL, USER, PASSWORD);
        // создаем выражение для отправки запросов в бд
        Statement statement = connection.createStatement();
        // получаем результат запроса
        ResultSet resultSet = statement.executeQuery("select * from smartphones");
        // пробегаем по результирующему множеству
        while (resultSet.next()) {
            // выводим информацию по каждому столбцу каждой строки
            System.out.println("Brand_id: " + resultSet.getInt("brand_id"));
            System.out.println("Model: " + resultSet.getString("model"));
            System.out.println("Price: " + resultSet.getInt("price"));
            System.out.println();
        }
        System.out.println("-------------------");

        resultSet.close();

        resultSet = statement.executeQuery("select sm.brand_id as s_id, *\n" +
                "from smartphones  sm\n" +
                "         full outer join storage st on sm.brand_id = st." +
                "id;");

        while (resultSet.next()) {
            System.out.println("ID " + resultSet.getInt("s_id"));
        }

        connection.close();
    }
}

