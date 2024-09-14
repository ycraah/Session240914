package ycraah.web.w2.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum ConnectionUtil {
  INSTANCE;

  private HikariDataSource ds;

  ConnectionUtil(){
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:mysql://localhost:3306/javaweb");
    config.setUsername("root");
    config.setPassword("root");
    config.setDriverClassName("com.mysql.jdbc.Driver");
    config.addDataSourceProperty("cachePrepStmts", "true"); // 캐시 사용 여부
    config.addDataSourceProperty("prepStmtCacheSize", "250"); // 캐시 크기 설정
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //SQL문 최대 크기 설정

    ds = new HikariDataSource(config);
  }

  public Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
