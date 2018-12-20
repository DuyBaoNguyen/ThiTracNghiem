package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.AnswerEntry;
import model.FieldEntry;
import model.QuestionEntry;
import model.TypeQuestionEntry;

public class QuestionDAO {
	public static List<QuestionEntry> getQuestions() {
		List<QuestionEntry> questions = new ArrayList<QuestionEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{call layDsCauHoi}");
			result = statement.executeQuery();

			while (result.next()) {
				QuestionEntry question = new QuestionEntry(result.getInt(1), result.getString(2),
						new FieldEntry(result.getString(3)), new TypeQuestionEntry(result.getString(4)));
				questions.add(question);
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

		return questions;
	}

	public static QuestionEntry getQuestion(QuestionEntry question) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layCauHoi(?, ?, ?, ?, ?, ?)}");
			statement.setInt(1, question.getId());
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.registerOutParameter(3, Types.INTEGER);
			statement.registerOutParameter(4, Types.INTEGER);
			statement.registerOutParameter(5, Types.INTEGER);
			statement.registerOutParameter(6, Types.VARCHAR);

			statement.execute();

			question.setContent(statement.getString(2));
			question.setField(new FieldEntry(statement.getInt(3)));
			question.setType(new TypeQuestionEntry(statement.getInt(4)));
			question.setRightAnswer(new AnswerEntry(statement.getInt(5), statement.getString(6)));

			question.setAnswers(AnswerDAO.getAnswer(question, con));

		} catch (SQLException e) {
			e.printStackTrace();
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
		return question;
	}

	public static List<QuestionEntry> searchQuestions(List<QuestionEntry> questions, String searchStr) {
		List<QuestionEntry> entries = new ArrayList<QuestionEntry>();
		if (searchStr == null) {
			searchStr = "";
		}

		searchStr = searchStr.toLowerCase();

		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getContent().toLowerCase().contains(searchStr)
					|| questions.get(i).getField().getName().toLowerCase().contains(searchStr)
					|| questions.get(i).getType().getName().toLowerCase().contains(searchStr)) {
				entries.add(questions.get(i));
			}
		}

		return entries;
	}

	public static boolean insertQuestion(QuestionEntry question) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareCall("{call themCauHoi(?, ?, ?, ?)}");

			statement.registerOutParameter(1, Types.INTEGER);
			statement.setString(2, question.getContent());
			statement.setInt(3, question.getField().getId());
			statement.setInt(4, question.getType().getId());

			statement.executeUpdate();

			question.setId(statement.getInt(1));

			AnswerDAO.insertAnswer(question, con);
			int rightAnswerId = AnswerDAO.getAnswerId(question, con);

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {

				}
			}

			statement = con.prepareCall("{call themDapAnDung(?, ?)}");
			statement.setInt(1, question.getId());
			statement.setInt(2, rightAnswerId);
			statement.executeQuery();

			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			error = true;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
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

	public static boolean insertQuestions(List<QuestionEntry> questions) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			for (QuestionEntry question : questions) {
				statement = con.prepareCall("{call themCauHoi(?, ?, ?, ?)}");

				statement.registerOutParameter(1, Types.INTEGER);
				statement.setString(2, question.getContent());
				statement.setInt(3, question.getField().getId());
				statement.setInt(4, question.getType().getId());

				statement.executeUpdate();

				question.setId(statement.getInt(1));

				AnswerDAO.insertAnswer(question, con);
				int rightAnswerId = AnswerDAO.getAnswerId(question, con);

				if (statement != null) {
					statement.close();
					statement = null;
				}

				statement = con.prepareCall("{call themDapAnDung(?, ?)}");
				statement.setInt(1, question.getId());
				statement.setInt(2, rightAnswerId);
				statement.executeQuery();

				if (statement != null) {
					statement.close();
					statement = null;
				}
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
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

	public static boolean deleteQuestion(QuestionEntry question) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call xoaCauHoi(?)}");

			statement.setInt(1, question.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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

	public static boolean updateQuestion(QuestionEntry question) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareCall("{call suaCauHoi(?, ?, ?, ?, ?)}");

			statement.setInt(1, question.getId());
			statement.setString(2, question.getContent());
			statement.setInt(3, question.getField().getId());
			statement.setInt(4, question.getType().getId());
			statement.setInt(5, question.getRightAnswer().getId());

			statement.executeUpdate();

			AnswerDAO.updateAnswer(question, con);

			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
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

	public static List<QuestionEntry> getQuestionsByFieldAndType(FieldEntry field, TypeQuestionEntry typeQuestion) {
		List<QuestionEntry> questions = new ArrayList<QuestionEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();

			statement = con.prepareCall("{call layDsCauHoiTheoLinhVucVaLoai(?, ?)}");
			statement.setInt(1, field.getId());
			statement.setInt(2, typeQuestion.getId());
			result = statement.executeQuery();

			while (result.next()) {
				QuestionEntry question = new QuestionEntry(result.getInt(1));
				questions.add(question);
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

		return questions;
	}
}
