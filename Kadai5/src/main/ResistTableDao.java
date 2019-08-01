package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * テーブルにおみくじの結果を登録する
 */
public class ResistTableDao {
	public static void ResistTable(int omikuji_id, Date birthday, Date uranai_date, Connection connection,
			PreparedStatement preparedStatement) throws SQLException {

		try {
			connection = DBManager.getConnection();
		//テーブルへの登録
		String insertday = "INSERT INTO result( uranai_date, birthday, omikuji_id) values(?,?,?) ";

		preparedStatement = connection.prepareStatement(insertday);
		preparedStatement.setDate(1, uranai_date);
		preparedStatement.setDate(2, birthday);
		preparedStatement.setInt(3, omikuji_id);

		preparedStatement.executeUpdate();


	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}finally {
		DBManager.close(connection);
		DBManager.close(preparedStatement);
	}
	}
}

