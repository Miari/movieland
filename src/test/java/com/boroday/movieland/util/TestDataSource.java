package com.boroday.movieland.util;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class TestDataSource implements DataSource {
    JdbcDataSource jdbcDataSource;
    Flyway flyway;

    public TestDataSource() {
        jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:mem:movieland;MODE=PostgreSQL;DB_CLOSE_DELAY=-1");
        jdbcDataSource.setUser("postgres");
        jdbcDataSource.setPassword("root");
        flyway = Flyway.configure().dataSource(jdbcDataSource).load();
    }

    public JdbcDataSource init() {
        flyway.migrate();
        return jdbcDataSource;
    }

    public void cleanup() {
        flyway.clean();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return jdbcDataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
