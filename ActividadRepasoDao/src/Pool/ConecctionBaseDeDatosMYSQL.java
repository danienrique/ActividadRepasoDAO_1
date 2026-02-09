package Pool;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConecctionBaseDeDatosMYSQL {
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource dataSource;
	static{
		config.setJdbcUrl("jdbc:mysql://localhost/tienda_db?serverTimezone=UTC&");
		config.setUsername("root");
		config.setPassword("alumno");
		
		dataSource = new HikariDataSource(config);
	}
	private ConecctionBaseDeDatosMYSQL() {
		
	}
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
