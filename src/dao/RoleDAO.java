package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.RoleEntry;

public class RoleDAO {
	public static List<RoleEntry> getRoles() {
		List<RoleEntry> roles = new ArrayList<RoleEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsVaiTro}");
			result = statement.executeQuery();

			while (result.next()) {
				RoleEntry role = new RoleEntry(result.getInt(1), result.getString(2));
				roles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {

				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {

				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

				}
			}
		}

		return roles;
	}
}
