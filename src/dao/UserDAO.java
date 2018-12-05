package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.UserEntry;
import model.RoleEntry;

public class UserDAO {
	public static List<UserEntry> getUsers() {
		List<UserEntry> users = new ArrayList<UserEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsNguoiDung()}");

			result = statement.executeQuery();

			while (result.next()) {
				UserEntry user = new UserEntry(
						result.getString(1),
						result.getString(2),
						result.getBoolean(3),
						result.getString(4),
						result.getDate(5),
						result.getString(6),
						new RoleEntry(result.getString(7)));
				users.add(user);
			}
		} catch (SQLException e) {

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
		return users;
	}
	
	public static UserEntry getUser(UserEntry user) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layNguoiDung(?, ?, ?, ?, ?, ?, ?)}");
			statement.setString(1, user.getId());
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.registerOutParameter(3, Types.BIT);
			statement.registerOutParameter(4, Types.VARCHAR);
			statement.registerOutParameter(5, Types.DATE);
			statement.registerOutParameter(6, Types.CHAR);
			statement.registerOutParameter(7, Types.INTEGER);
			
			statement.execute();
			
			user.setName(statement.getString(2));
			user.setSex(statement.getBoolean(3));
			user.setAddress(statement.getString(4));
			user.setBirthday(statement.getDate(5));
			user.setPhone(statement.getString(6));
			user.setRole(new RoleEntry(statement.getInt(7)));
		} catch (SQLException e) {

		} finally {
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
		return user;
	}

	public static List<UserEntry> searchUsers(List<UserEntry> users, String searchStr) {
		List<UserEntry> entries = new ArrayList<UserEntry>();

		if (searchStr == null) {
			searchStr = "";
		}
		searchStr = searchStr.toLowerCase();

		for (int i = 0; i < users.size(); i++) {
			UserEntry user = users.get(i);
			if (user.getName().toLowerCase().contains(searchStr)
				|| user.getId().toLowerCase().contains(searchStr)
				|| (user.getAddress() != null && user.getAddress().toLowerCase().contains(searchStr))
				|| (user.getPhone() != null && user.getPhone().toLowerCase().contains(searchStr))) {
				entries.add(user);
			}
		}

		return entries;
	}

	public static boolean insertUser(UserEntry user) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareCall("{call themNguoiDung(?, ?, ?, ?, ?, ?)}");

			statement.setString(1, user.getName());
			statement.setBoolean(2, user.getSex());
			statement.setString(3, user.getAddress());
			statement.setDate(4, user.getBirthday());
			statement.setString(5, user.getPhone());
			statement.setInt(6, user.getRole().getId());

			statement.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			error = true;
			try {
				con.rollback();
			}
			catch(SQLException ex) {
				
			}
		} finally {
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
		return error;
	}
	
	public static boolean deleteUser(UserEntry user) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaNguoiDung(?)}");

			statement.setString(1, user.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			error = true;
		} finally {
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
		return error;
	}
	
	public static boolean updateUser(UserEntry user) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call suaNguoiDung(?, ?, ?, ?, ?, ?, ?)}");

			statement.setString(1, user.getId());
			statement.setString(2, user.getName());
			statement.setBoolean(3, user.getSex());
			statement.setString(4, user.getAddress());
			statement.setDate(5, user.getBirthday());
			statement.setString(6, user.getPhone());
			statement.setInt(7, user.getRole().getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			error = true;
		} finally {
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
		return error;
	}
}
