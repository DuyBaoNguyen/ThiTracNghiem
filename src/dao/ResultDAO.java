package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import db.DbConnection;
import model.ResultEntry;

public class ResultDAO {
	public static void getResult(ResultEntry result) {
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layKetQua(?, ?, ?, ?, ?, ?, ?)}");

			statement.setString(1, result.getCandidate().getId());
			statement.setInt(2, result.getExam().getId());
			statement.registerOutParameter(3, Types.INTEGER);
			statement.registerOutParameter(4, Types.FLOAT);
			statement.registerOutParameter(5, Types.INTEGER);
			statement.registerOutParameter(6, Types.INTEGER);
			statement.registerOutParameter(7, Types.TIMESTAMP);

			statement.execute();
			
			result.setId(statement.getInt(3));
			result.setScore(statement.getFloat(4));
			result.setTotalRightAnswers(statement.getInt(5));
			result.setTotalQuestion(statement.getInt(6));
			result.setTime(statement.getTimestamp(7));
			
			result.setResultDetails(ResultDetailDAO.getResultDetails(con, result));
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
	}
	
	public static boolean insertResult(ResultEntry result) {
		boolean error = false;
		Connection con = null;
		CallableStatement statement = null;

		try {
			con = DbConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareCall("{call themKetQua(?, ?, ?, ?, ?, ?)}");

			statement.setString(1, result.getCandidate().getId());
			statement.setInt(2, result.getExam().getId());
			statement.setFloat(3, result.getScore());
			statement.setInt(4, result.getTotalRightAnswers());
			statement.setInt(5, result.getTotalQuestion());
			statement.setTimestamp(6, result.getTime());

			statement.executeUpdate();

			if (statement != null) {
				statement.close();
				statement = null;
			}

			statement = con.prepareCall("{ ? = call layMaKetQua()}");
			statement.registerOutParameter(1, Types.INTEGER);
			statement.execute();

			result.setId(statement.getInt(1));

			ResultDetailDAO.insertResulDetail(con, result);
			
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
}
