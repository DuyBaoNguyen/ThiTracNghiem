package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.CandidateEntry;
import model.ClassEntry;

public class CandidateDAO {
	public static List<CandidateEntry> getCandidates() {
		List<CandidateEntry> cdds = new ArrayList<CandidateEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsThiSinh()}");

			result = statement.executeQuery();

			while (result.next()) {
				CandidateEntry cdd = new CandidateEntry(
						result.getString(1),
						result.getString(2),
						result.getBoolean(3),
						result.getString(4),
						result.getDate(5),
						result.getString(6));
				cdds.add(cdd);
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
		return cdds;
	}
	
	public static CandidateEntry getCandidate(CandidateEntry cdd) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layThiSinh(?, ?, ?, ?, ?, ?)}");
			statement.setString(1, cdd.getId());
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.registerOutParameter(3, Types.BIT);
			statement.registerOutParameter(4, Types.VARCHAR);
			statement.registerOutParameter(5, Types.DATE);
			statement.registerOutParameter(6, Types.CHAR);
			
			statement.execute();
			
			cdd.setName(statement.getString(2));
			cdd.setSex(statement.getBoolean(3));
			cdd.setAddress(statement.getString(4));
			cdd.setBirthday(statement.getDate(5));
			cdd.setPhone(statement.getString(6));
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
		return cdd;
	}
	
	public static List<CandidateEntry> getCandidateInClass(ClassEntry entry) {
		List<CandidateEntry> cdds = new ArrayList<CandidateEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsThiSinhMotLop(?)}");

			statement.setString(1, entry.getId());

			result = statement.executeQuery();

			while (result.next()) {
				CandidateEntry cdd = new CandidateEntry(result.getString(1), result.getString(2));
				cdds.add(cdd);
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
		return cdds;
	}

	public static List<CandidateEntry> searchCandidates(List<CandidateEntry> cdds, String searchStr) {
		List<CandidateEntry> entries = new ArrayList<CandidateEntry>();

		if (searchStr == null) {
			searchStr = "";
		}
		searchStr = searchStr.toLowerCase();

		for (int i = 0; i < cdds.size(); i++) {
			CandidateEntry cdd = cdds.get(i);
			if (cdd.getName().toLowerCase().contains(searchStr)
				|| cdd.getId().toLowerCase().contains(searchStr)
				|| (cdd.getAddress() != null && cdd.getAddress().toLowerCase().contains(searchStr))
				|| (cdd.getPhone() != null && cdd.getPhone().toLowerCase().contains(searchStr))) {
				entries.add(cdd);
			}
		}

		return entries;
	}

	public static List<CandidateEntry> getCandidateOutClass(ClassEntry entry) {
		List<CandidateEntry> cdds = new ArrayList<CandidateEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsThiSinhNgoaiLop(?)}");

			statement.setString(1, entry.getId());

			result = statement.executeQuery();

			while (result.next()) {
				CandidateEntry cdd = new CandidateEntry(result.getString(1), result.getString(2));
				cdds.add(cdd);
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
		return cdds;
	}

	public static boolean insertCddToClass(ClassEntry classEntry, CandidateEntry cdd) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call themThiSinhVaoLop(?, ?)}");

			statement.setString(1, classEntry.getId());
			statement.setString(2, cdd.getId());

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
	
	public static boolean deleteCddFromClass(ClassEntry classEntry, CandidateEntry cdd) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaThiSinhTrongLop(?, ?)}");

			statement.setString(1, classEntry.getId());
			statement.setString(2, cdd.getId());

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
	
	public static boolean insertCandidate(CandidateEntry cdd) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareCall("{call themThiSinh(?, ?, ?, ?, ?)}");

			statement.setString(1, cdd.getName());
			statement.setBoolean(2, cdd.getSex());
			statement.setString(3, cdd.getAddress());
			statement.setDate(4, cdd.getBirthday());
			statement.setString(5, cdd.getPhone());

			statement.executeUpdate();
			
			con.commit();
		} catch (SQLException e) {
			error = true;
			try {
				con.rollback();
			} catch (SQLException ex) {
				
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
	
	public static boolean deleteCandidate(CandidateEntry cdd) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaNguoiDung(?)}");

			statement.setString(1, cdd.getId());

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
	
	public static boolean updateCandidate(CandidateEntry cdd) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call suaThiSinh(?, ?, ?, ?, ?, ?)}");

			statement.setString(1, cdd.getId());
			statement.setString(2, cdd.getName());
			statement.setBoolean(3, cdd.getSex());
			statement.setString(4, cdd.getAddress());
			statement.setDate(5, cdd.getBirthday());
			statement.setString(6, cdd.getPhone());

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
