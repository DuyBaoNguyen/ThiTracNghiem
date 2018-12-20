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
import model.QuestionEntry;

public class AnswerDAO {
	public static List<AnswerEntry> getAnswer(QuestionEntry question, Connection con) throws SQLException {
		List<AnswerEntry> answers = new ArrayList<AnswerEntry>();
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			statement = con.prepareCall("{call layDapAn(?)}");
			statement.setInt(1, question.getId());
			result = statement.executeQuery();

			while (result.next()) {
				AnswerEntry answer = new AnswerEntry(result.getInt(1), result.getString(2));
				answers.add(answer);
			}
		} catch (SQLException e) {
			throw e;
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
		}
		return answers;
	}

	public static void insertAnswer(QuestionEntry question, Connection con) throws SQLException {
		CallableStatement statement = null;

		try {
			for (AnswerEntry item : question.getAnswers()) {
				statement = con.prepareCall("{call themDapAn(?, ?)}");

				statement.setInt(1, question.getId());
				statement.setString(2, item.getContent());

				statement.executeUpdate();

				if (statement != null) {
					statement.close();
					statement = null;
				}
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public static int getAnswerId(QuestionEntry question, Connection con) throws SQLException {
		int answerId;

		CallableStatement statement = null;

		try {
			statement = con.prepareCall("{ ? = call layMaDapAn(?, ?)}");

			statement.registerOutParameter(1, Types.INTEGER);
			statement.setInt(2, question.getId());
			statement.setString(3, question.getRightAnswer().getContent());

			statement.execute();

			answerId = statement.getInt(1);

			if (statement != null) {
				statement.close();
				statement = null;
			}
		} catch (SQLException e) {
			throw e;
		}
		return answerId;
	}

	public static void updateAnswer(QuestionEntry question, Connection con) throws SQLException {
		CallableStatement statement = null;

		try {
			for (AnswerEntry item : question.getAnswers()) {
				statement = con.prepareCall("{call suaDapAn(?, ?, ?)}");

				statement.setInt(1, question.getId());
				statement.setInt(2, item.getId());
				statement.setString(3, item.getContent());

				statement.executeUpdate();

				if (statement != null) {
					statement.close();
					statement = null;
				}
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public static int getRightAnswerId(QuestionEntry question) {
		int answerId = 0;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{ ? = call layMaDapAnDung(?)}");

			statement.registerOutParameter(1, Types.INTEGER);
			statement.setInt(2, question.getId());

			statement.execute();

			answerId = statement.getInt(1);
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
		return answerId;
	}
}
