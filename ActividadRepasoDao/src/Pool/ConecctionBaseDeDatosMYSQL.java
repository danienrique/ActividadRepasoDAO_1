package Pool;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConecctionBaseDeDatosMYSQL {
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource dataSource;
	static{
		config.setJdbcUrl("jdbc:mysql://localhost/tienda_bd?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false");
		config.setUsername("root");
		config.setPassword("alumno");
		config.addDataSourceProperty("maximumPoolSize", 1);
		config.addDataSourceProperty ("cachePrepStmts","true");
		config.addDataSourceProperty ("prepStmtCacheSize","250");
		config.addDataSourceProperty ("prepStmtCacheSqlLimit","2048");
		
		dataSource = new HikariDataSource(config);
	}
	private ConecctionBaseDeDatosMYSQL() {
		
	}
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
