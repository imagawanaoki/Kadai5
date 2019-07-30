package fortune;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBに接続を行うための処理
 * @author n_imagawa
 *
 */
public class DBManager {

	//接続するためのDBのURL
	private static final String URL = "jdbc:postgresql://localhost:5432/Kadai";

	//DB接続するためのユーザー名
	private static final String USER = "n_imagawa";

	//DB接続するためのパスワード
	private static final String PASSWORD = "inny1891";

	/**
	 * DBと接続する
	 *@return DBコネクション
	 *@throws ClassNotFoundException ドライバクラスが見つからなかった場合
	 *@throws SQLException			 DB接続に失敗した場合
	 *
	 *
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		//System.out.println("DBに接続しました");

		return connection;
	}

	/**
	 * DBとの接続を切断する
	 *@param connection
	 *				DBとの接続情報
	 */
	public static void close(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
				//System.out.println("DBと切断しました");

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	/**
	 * 	PreparedStatementをクローズする
	 *
	 *@param preparedStatement
	 *				ステートメント情報
	 *
	 */

	public static void close(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {

				preparedStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 	ResultSetをクローズする
	 *
	 *@param resultSet
	 *				SQL検索結果
	 *
	 */

	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {

				resultSet.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}