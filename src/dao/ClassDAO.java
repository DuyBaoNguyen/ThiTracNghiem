package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.ClassEntry;
import model.FieldEntry;

public class ClassDAO {
	public static List<ClassEntry> getClasses() {
		List<ClassEntry> classes = new ArrayList<ClassEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{call layDsLopHoc}");
			result = statement.executeQuery();

			while (result.next()) {
				ClassEntry entry = new ClassEntry(result.getString("ma_lop_hoc"), result.getString("ten_lop_hoc"),
						result.getInt("so_luong_thi_sinh"));
				classes.add(entry);
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

		return classes;
	}

	public static ClassEntry getClass(ClassEntry entry) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layLopHoc(?, ?, ?)}");
			statement.setString(1, entry.getId());
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.registerOutParameter(3, Types.INTEGER);

			statement.execute();

			entry.setName(statement.getString(2));
			entry.setField(new FieldEntry(statement.getInt(3)));
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
		return entry;
	}

	public static boolean insertClass(ClassEntry entry) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call themLopHoc(?, ?)}");

			statement.setString(1, entry.getName());
			statement.setInt(2, entry.getField().getId());

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

	public static boolean updateClass(ClassEntry entry) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call suaLopHoc(?, ?, ?)}");

			statement.setString(1, entry.getId());
			statement.setString(2, entry.getName());
			statement.setInt(3, entry.getField().getId());

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

	public static boolean deleteClass(ClassEntry entry) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaLopHoc(?)}");

			statement.setString(1, entry.getId());

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
	
	public static List<ClassEntry> searchClasses(List<ClassEntry> classes, String searchStr) {
		List<ClassEntry> entries = new ArrayList<ClassEntry>();
		if (searchStr == null) {
			searchStr = "";
		}
		
		searchStr = searchStr.toLowerCase();
		
		for (int i = 0; i < classes.size(); i++) {
			if (classes.get(i).getId().toLowerCase().contains(searchStr)
				|| classes.get(i).getName().toLowerCase().contains(searchStr)) {
				entries.add(classes.get(i));
			}
		}
		
		return entries;
	}
}
