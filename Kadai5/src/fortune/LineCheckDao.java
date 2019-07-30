package fortune;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * SQLで乱数を取得
 */
public class LineCheckDao {
	/**
	 *おみくじを引いたかどうかを判断する
	 * @param birth 入力された誕生日
	 * @param today 占い日
	 * @return おみくじを引いた時と引いていない時の戻り値
	 * @throws SQLException 例外処理
	 * @throws IOException	例外処理
	 * @throws ClassNotFoundException 詳細メッセージなしでClassNotFoundExceptionを構築する
	 */
	public static int Gyo(Date birth, Date today) throws SQLException, IOException, ClassNotFoundException {


		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int omikuji_id = 0;

		try {
			//DBに接続
			connection = DBManager.getConnection();

			//行数を取得する文
			String para = "select COUNT(*) from omikuji";

			//SQLを準備する
			preparedStatement = connection.prepareStatement(para);
			ResultSet rset = preparedStatement.executeQuery();
			rset.next();


			//0かomikuji_idを代入,引いたか引いてないか判定
			omikuji_id = Check.PatternCheck(birth, today, connection, preparedStatement);

			if (omikuji_id ==0 ) {
				Random rand = new Random();

				//行数の取得
				omikuji_id = rand.nextInt(rset.getInt("count"));
				return omikuji_id;

			} else {
				return omikuji_id;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			DBManager.close(connection);
			DBManager.close(preparedStatement);
		}
	}
	public static int judge(int omikuji_id) {


		return 0;
	}

}
