package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.AnswerEntry;
import model.QuestionEntry;
import model.ResultDetailEntry;
import model.ResultEntry;

public class ResultDetailDAO {
	public static List<ResultDetailEntry> getResultDetails(Connection con, ResultEntry result) {
		List<ResultDetailEntry> resultDetails = new ArrayList<ResultDetailEntry>();
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = con.prepareCall("{call layChiTietKetQua(?)}");
			statement.setInt(1, result.getId());
			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ResultDetailEntry resultDetail = new ResultDetailEntry();
				resultDetail.setQuestion(new QuestionEntry(resultSet.getInt(1)));
				resultDetail.setUserAnswer(new AnswerEntry(resultSet.getInt(2)));
				resultDetail.setStatus(resultSet.getBoolean(3));
				
				QuestionDAO.getQuestion(resultDetail.getQuestion());
				
				resultDetails.add(resultDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return resultDetails;
	}
	
	public static void insertResulDetail(Connection con, ResultEntry result) {
		CallableStatement statement = null;

		try {
			for (ResultDetailEntry item : result.getResultDetails()) {
				statement = con.prepareCall("{call themChiTietKetQua(?, ?, ?, ?, ?)}");

				statement.setInt(1, result.getId());
				statement.setInt(2, item.getQuestion().getId());
				if (item.getUserAnswer() == null) {
					statement.setNull(3, Types.INTEGER);
				} else {
					statement.setInt(3, item.getUserAnswer().getId());
				}
				statement.setBoolean(4, item.getStatus());
				statement.setInt(5, item.getOrder());

				statement.executeUpdate();

				if (statement != null) {
					statement.close();
					statement = null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {

				}
			}
		}
	}
}
