package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.ExamEntry;
import model.CandidateEntry;
import model.ClassEntry;
import model.FieldEntry;

public class ExamDAO {
	public static List<ExamEntry> getExams() {
		List<ExamEntry> exams = new ArrayList<ExamEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsDeThi()}");

			result = statement.executeQuery();

			while (result.next()) {
				ExamEntry exam = new ExamEntry(result.getInt(1), result.getString(2), result.getInt(3),
						result.getInt(4), new FieldEntry(result.getString(5)));
				exams.add(exam);
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
		return exams;
	}

	public static List<ExamEntry> searchExams(List<ExamEntry> exams, String searchStr) {
		List<ExamEntry> entries = new ArrayList<ExamEntry>();

		if (searchStr == null) {
			searchStr = "";
		}
		searchStr = searchStr.toLowerCase();

		for (int i = 0; i < exams.size(); i++) {
			if (exams.get(i).getName().toLowerCase().contains(searchStr)) {
				entries.add(exams.get(i));
			}
		}

		return entries;
	}
	
	public static ExamEntry getExam(ExamEntry exam) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDeThi(?, ?, ?, ?, ?)}");
			statement.setInt(1, exam.getId());
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.registerOutParameter(3, Types.INTEGER);
			statement.registerOutParameter(4, Types.INTEGER);
			statement.registerOutParameter(5, Types.INTEGER);

			statement.execute();

			exam.setName(statement.getString(2));
			exam.setTotalTime(statement.getInt(3));
			exam.setTotalQuestion(statement.getInt(4));
			exam.setField(new FieldEntry(statement.getInt(5)));
			
			exam.setExamStructures(ExamStructureDAO.getExamStructures(exam, con));
			
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
		return exam;
	}
	
	public static ExamEntry getExamWithName(ExamEntry exam) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layTenDeThi(?, ?)}");
			statement.setInt(1, exam.getId());
			statement.registerOutParameter(2, Types.VARCHAR);

			statement.execute();

			exam.setName(statement.getString(2));
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
		return exam;
	}

	public static boolean assignExamForClass(ClassEntry classEntry, ExamEntry exam) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call ganDeThiChoLop(?, ?)}");

			statement.setString(1, classEntry.getId());
			statement.setInt(2, exam.getId());

			statement.executeQuery();
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

	public static boolean unassignExamforClass(ClassEntry classEntry, ExamEntry exam) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call boGanDeThiChoLop(?, ?)}");

			statement.setString(1, classEntry.getId());
			statement.setInt(2, exam.getId());

			statement.executeQuery();
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

	public static boolean insertExam(ExamEntry exam) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareCall("{call themDeThi(?, ?, ?, ?)}");

			statement.setString(1, exam.getName());
			statement.setInt(2, exam.getTotalTime());
			statement.setInt(3, exam.getTotalQuestion());
			statement.setInt(4, exam.getField().getId());
			statement.executeUpdate();
			
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (SQLException e) {

				}
			}
			
			statement = con.prepareCall("{ ? = call layMaDeThi()}");
			statement.registerOutParameter(1, Types.INTEGER);
			statement.execute();
			
			exam.setId(statement.getInt(1));
			
			ExamStructureDAO.insertExamStructure(exam, con);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
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

	public static boolean updateExam(ExamEntry exam) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareCall("{call suaDeThi(?, ?, ?, ?, ?)}");

			statement.setInt(1, exam.getId());
			statement.setString(2, exam.getName());
			statement.setInt(3, exam.getTotalTime());
			statement.setInt(4, exam.getTotalQuestion());
			statement.setInt(5, exam.getField().getId());
			statement.executeUpdate();
			
			ExamStructureDAO.updateExamStructure(exam, con);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
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

	public static boolean deleteExam(ExamEntry exam) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaDeThi(?)}");

			statement.setInt(1, exam.getId());

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
	
	public static List<ExamEntry> getNotTakenExams(CandidateEntry cdd, ClassEntry classEntry) {
		List<ExamEntry> exams = new ArrayList<ExamEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDeThiChuaThi(?, ?)}");
			statement.setString(1, cdd.getId());
			statement.setString(2, classEntry.getId());
			
			result = statement.executeQuery();

			while (result.next()) {
				ExamEntry exam = new ExamEntry(result.getInt(1), result.getString(2));
				
				exams.add(exam);
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
		return exams;
	}
	
	public static List<ExamEntry> getTakenExams(CandidateEntry cdd, ClassEntry classEntry) {
		List<ExamEntry> exams = new ArrayList<ExamEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDeThiDaThi(?, ?)}");
			statement.setString(1, cdd.getId());
			statement.setString(2, classEntry.getId());
			
			result = statement.executeQuery();

			while (result.next()) {
				ExamEntry exam = new ExamEntry(result.getInt(1), result.getString(2));
				
				exams.add(exam);
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
		return exams;
	}

}