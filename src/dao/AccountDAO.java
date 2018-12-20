package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.AccountEntry;
import model.UserEntry;

public class AccountDAO {
	public static List<AccountEntry> getAccounts() {
		List<AccountEntry> accs = new ArrayList<AccountEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsTaiKhoan}");
			result = statement.executeQuery();

			while (result.next()) {
				AccountEntry acc = new AccountEntry(result.getString(1),
						new UserEntry(result.getString(2), result.getString(3)));
				accs.add(acc);
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

		return accs;
	}

	public static List<AccountEntry> searchClasses(List<AccountEntry> accs, String searchStr) {
		List<AccountEntry> entries = new ArrayList<AccountEntry>();
		if (searchStr == null) {
			searchStr = "";
		}

		searchStr = searchStr.toLowerCase();

		for (int i = 0; i < accs.size(); i++) {
			AccountEntry acc = accs.get(i);
			if (acc.getUsername().toLowerCase().contains(searchStr)
					|| acc.getUser().getName().toLowerCase().contains(searchStr)) {
				entries.add(accs.get(i));
			}
		}

		return entries;
	}

	public static boolean checkAccount(AccountEntry acc) {
		boolean success = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{? = call kiemTraTaiKhoan(?, ?)}");

			statement.registerOutParameter(1, Types.BIT);
			statement.setString(2, acc.getUsername());
			statement.setString(3, acc.getPassword());

			statement.execute();
			
			if (statement.getBoolean(1) == true) {
				acc.setUser(UserDAO.getUser(con, acc));

				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}
	
	public static boolean checkPassword(AccountEntry acc) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{? = call kiemTraTaiKhoan(?, ?)}");

			statement.registerOutParameter(1, Types.BIT);
			statement.setString(2, acc.getUsername());
			statement.setString(3, acc.getPassword());

			statement.execute();
			
			if (statement.getBoolean(1) == false) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			error = true;
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return error;
	}

	public static boolean insertAccount(AccountEntry acc) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call taoTaiKhoan(?, ?)}");

			statement.setString(1, acc.getUsername());
			statement.setString(2, acc.getPassword());

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

	public static boolean deleteAccount(AccountEntry acc) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaTaiKhoan(?)}");

			statement.setString(1, acc.getUsername());

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

	public static boolean changePassword(AccountEntry acc) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call doiMatKhau(?, ?)}");

			statement.setString(1, acc.getUsername());
			statement.setString(2, acc.getPassword());

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
