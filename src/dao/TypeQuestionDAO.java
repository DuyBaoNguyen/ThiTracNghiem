package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import model.TypeQuestionEntry;

public class TypeQuestionDAO {
	public static List<TypeQuestionEntry> getTypeQuestions() {
		List<TypeQuestionEntry> typeQuestions = new ArrayList<TypeQuestionEntry>();
		Connection con = null;
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			con = DbConnection.getInstance().getConnection();
			statement = con.prepareCall("{call layDsLoaiCauHoi()}");

			result = statement.executeQuery();

			while (result.next()) {
				TypeQuestionEntry typeQuestion = new TypeQuestionEntry(result.getInt(1), result.getString(2));
				typeQuestions.add(typeQuestion);
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
		return typeQuestions;
	}
}
