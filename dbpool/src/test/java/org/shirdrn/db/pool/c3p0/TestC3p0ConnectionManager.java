package org.shirdrn.db.pool.c3p0;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Test;
import org.shirdrn.db.pool.ConnectionManager;

public class TestC3p0ConnectionManager {

	ConnectionManager cm;
	
	@Test
	public void getConnection() throws Exception {
		cm = new C3p0ConnectionManager();
		Connection conn = cm.getConnection();
		assertNotNull(conn);
		
		select(conn);
	}
	
	@Test
	public void getConnection2() throws Exception {
		cm = new C3p0ConnectionManager("c3p0-test.properties");
		Connection conn = cm.getConnection();
		assertNotNull(conn);
		
		select(conn);
	}

	private void select(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from wp_users");
		while(rs.next()) {
			String userLogin = rs.getString("user_login");
			System.out.println(userLogin);
		}
	}
	
	@After
	public void close() throws IOException {
		cm.close();
	}
}
