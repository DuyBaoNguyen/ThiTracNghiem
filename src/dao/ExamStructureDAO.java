package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ExamEntry;
import model.ExamStructureEntry;
import model.TypeQuestionEntry;

public class ExamStructureDAO {
	public static void insertExamStructure(ExamEntry exam, Connection con)
			throws SQLException {
		CallableStatement statement = null;

		try {
			for (ExamStructureEntry item : exam.getExamStructures()) {
				statement = con.prepareCall("{call themCauTrucDeThi(?, ?, ?)}");

				statement.setInt(1, exam.getId());
				statement.setInt(2, item.getTypeQuestion().getId());
				statement.setInt(3, item.getNumberQuestions());

				statement.executeUpdate();

				if (statement != null) {
					try {
						statement.close();
						statement = null;
					} catch (SQLException e) {

					}
				}
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static List<ExamStructureEntry> getExamStructures(ExamEntry exam, Connection con) throws SQLException {
		List<ExamStructureEntry> structs = new ArrayList<ExamStructureEntry>();
		CallableStatement statement = null;
		ResultSet result = null;

		try {
			statement = con.prepareCall("{call layDsCauTrucDeThi(?)}");
			statement.setInt(1, exam.getId());
			result = statement.executeQuery();

			while (result.next()) {
				ExamStructureEntry struct = new ExamStructureEntry(
						new TypeQuestionEntry(result.getInt(1), result.getString(2)),
						result.getInt(3));
				structs.add(struct);
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
		return structs;
	}
	
	public static void updateExamStructure(ExamEntry exam, Connection con)
			throws SQLException {
		CallableStatement statement = null;

		try {
			for (ExamStructureEntry item : exam.getExamStructures()) {
				statement = con.prepareCall("{call suaCauTrucDeThi(?, ?, ?)}");

				statement.setInt(1, exam.getId());
				statement.setInt(2, item.getTypeQuestion().getId());
				statement.setInt(3, item.getNumberQuestions());

				statement.executeUpdate();

				if (statement != null) {
					try {
						statement.close();
						statement = null;
					} catch (SQLException e) {

					}
				}
			}
		} catch (SQLException e) {
			throw e;
		}
	}
}
