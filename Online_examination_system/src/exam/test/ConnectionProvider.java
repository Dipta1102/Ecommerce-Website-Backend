package exam.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

	private static Connection con;

	public static Connection getConnection() {
		try {

			if (con == null) {
				// driver class load
				Class.forName("com.mysql.cj.jdbc.Driver");

				// create a connection..
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "Bhanu@123");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public class Main {
		public static void main(String s[]) {
			System.out.println("connection" + ConnectionProvider.getConnection());

			new Registration();

		}
	}
}
