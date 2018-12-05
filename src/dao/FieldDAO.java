package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import db.DbConnection;
import model.FieldEntry;

public class FieldDAO {
	public static List<FieldEntry> getFields() {
		List<FieldEntry> fields = new ArrayList<FieldEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsLinhVuc}");
			result = statement.executeQuery();

			while (result.next()) {
				FieldEntry field = new FieldEntry(result.getInt("ma_linh_vuc"), result.getString("ten_linh_vuc"));
				fields.add(field);
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

		return fields;
	}
	
	public static FieldEntry getField(FieldEntry field) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layLinhVuc(?, ?)}");
			statement.setInt(1, field.getId());
			statement.registerOutParameter(2, Types.VARCHAR);

			statement.execute();

			field.setName(statement.getString(2));
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
		return field;
	}

	public static boolean insertField(FieldEntry field) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call themLinhVuc(?)}");
			statement.setString(1, field.getName());

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
	
	public static boolean deleteField(FieldEntry field) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaLinhVuc(?)}");
			statement.setInt(1, field.getId());

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
	
	public static boolean updateField(FieldEntry field) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call suaLinhVuc(?, ?)}");
			statement.setInt(1, field.getId());
			statement.setString(2, field.getName());

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
	
	public static List<FieldEntry> searchFields(List<FieldEntry> fields, String searchStr) {
		List<FieldEntry> entries = new ArrayList<FieldEntry>();
		if (searchStr == null) {
			searchStr = "";
		}
		
		searchStr = searchStr.toLowerCase();
		
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).getName().toLowerCase().contains(searchStr)) {
				entries.add(fields.get(i));
			}
		}
		
		return entries;
	}
}
