package com.boroday.movieland.util;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Slf4j
public class TestDataSource implements DataSource {
    JdbcDataSource jdbcDataSource;
    Flyway flyway;

    public TestDataSource() {
        jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:mem:movieland;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=FALSE;CASE_INSENSITIVE_IDENTIFIERS=TRUE");
        jdbcDataSource.setUser("postgres");
        jdbcDataSource.setPassword("root");

        flyway = Flyway.configure().dataSource(jdbcDataSource).load();

    }

    public JdbcDataSource init() {
        log.info("DB migration started");
        flyway.migrate();
        log.info("DB migration finished. jdbcURL: " + jdbcDataSource.getURL());
        jdbcDataSource.getURL();
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
        return jdbcDataSource.getConnection(username, password);
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
