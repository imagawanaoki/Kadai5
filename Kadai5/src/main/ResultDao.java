package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import omikuji_unsei.Daikichi;
import omikuji_unsei.Kichi;
import omikuji_unsei.Kyou;
import omikuji_unsei.Shokichi;
import omikuji_unsei.Suekichi;
import omikuji_unsei.Tyuukichi;

/**
 * DBに接続し過去半年のレコード及び値を取り出す
 */

public class ResultDao {

	/**
	 *DBに接続しテーブルから値を取り出す
	 * @param omikuji_id おみくじの番号　　
	 * @return　　処理の中で取得した値を変数に返す
	 */
	public static Unsei CheckOmikujiId(Integer omikuji_id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			//SQL文の準備
			String join = "SELECT um.unsei_name, omi.gakumon, omi.negaigoto, omi.akinai, omi.unsei_id FROM UnseiMaster um "
					+ "INNER JOIN Omikuji omi ON um.unsei_id = omi.unsei_id WHERE omi.omikuji_id = ?";

			//SQLをDBに渡す
			connection = DBManager.getConnection();
			preparedStatement = connection.prepareStatement(join);
			preparedStatement.setInt(1, omikuji_id);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();

			//テーブルの値を取り出す
			Unsei omikuji = null;
			String gakumon = rs.getString("gakumon");
			String akinai = rs.getString("akinai");
			String negaigoto = rs.getString("negaigoto");
			int unsei_id = rs.getInt("unsei_id");

			switch (unsei_id) {
			case 1:
				omikuji = new Daikichi();
				break;

			case 2:
				omikuji = new Tyuukichi();
				break;

			case 3:
				omikuji = new Shokichi();
				break;

			case 4:
				omikuji = new Suekichi();
				break;

			case 5:
				omikuji = new Kichi();
				break;

			case 6:
				omikuji = new Kyou();
				break;

			default:
				break;
			}
			omikuji.setUnsei();
			omikuji.setNegaigoto(negaigoto);
			omikuji.setAkinai(akinai);
			omikuji.setGakumon(gakumon);

			return omikuji;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			DBManager.close(connection);
			DBManager.close(preparedStatement);
		}

	}

	/**
	 * 過去半年分の全てのレコードを取得
	 * @param uranai_date　占い日
	 * @return　取得した行数を返す
	 * @throws ClassNotFoundException 詳細メッセージなしでClassNotFoundExceptionを構築する
	 * @throws SQLException データベース・アクセス・エラーまたはその他のエラーに関する情報を提供する例外
	 */
	public static int allunsei(Date uranai_date) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();

		//過去の占い日を取得
		LocalDate lastMonth = LocalDate.now().minusMonths(6);
		Date last = Date.valueOf(lastMonth);

		//過去半年の行数を取得
		String sql = "Select COUNT(*) FROM result WHERE uranai_date >= ? AND uranai_date <= ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDate(1, last);
		preparedStatement.setDate(2, uranai_date);
		ResultSet rs = preparedStatement.executeQuery();

		//行数を取得
		rs.next();
		int allrecord = rs.getInt("count");
		return allrecord;
	}

	/**
	 * 半年分の各運勢レコードを取得
	 * @param uranai_date 占い日
	 * @param unsei_id 運勢の番号
	 * @param name 運勢名
	 * @return	運勢のレコードと運勢名を同時に紐づけて登録した値を返す
	 * @throws ClassNotFoundException 発生する例外クラスを指定
	 * @throws SQLException 発生する例外クラスを指定
	 */
	@SuppressWarnings("unused")
	public static Map<String, Integer> unsei(Date uranai_date) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();
		Map<String, Integer> anyfortune = new LinkedHashMap<>();

		//過去の占い日を取得
		LocalDate lastMonth = LocalDate.now().minusMonths(6);
		Date last = Date.valueOf(lastMonth);

		//unsei_idと同じ運勢のレコードを呼び出す
		String sql = "SELECT COUNT(*) cnt, um.unsei_name FROM result res INNER JOIN omikuji omi ON res.omikuji_id = omi.omikuji_id  INNER JOIN UnseiMaster um ON omi.unsei_id = um.unsei_id "
				+ "	WHERE res.uranai_date >= ? AND res.uranai_date < ? GROUP BY um.unsei_name ";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDate(1, last);
		preparedStatement.setDate(2, uranai_date);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {

			Integer uranaidate = rs.getInt("cnt");
			String fortune = rs.getString("unsei_name");

			if (uranaidate == null) {
				uranaidate = 0;
			}

			//運勢のレコードと運勢名を同時に紐づけて登録
			anyfortune.put(fortune, uranaidate);

		}
		return anyfortune;
	}

	/**
	 * 今日の1日分の行数全てを取得
	 * @param uranai_date 占い日
	 * @return レコードの取得
	 * @throws ClassNotFoundException 詳細メッセージなしでClassNotFoundExceptionを構築する
	 * @throws SQLException データベース・アクセス・エラーまたはその他のエラーに関する情報を提供する例外
	 */
	public static int todayrecord(Date uranai_date) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();

		//今日の1日分の行数全てを取得
		String sql = "Select COUNT(*) FROM result WHERE uranai_date = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDate(1, uranai_date);
		ResultSet rs = preparedStatement.executeQuery();

		//行数を取得
		rs.next();
		int todayrecord = rs.getInt("count");
		return todayrecord;
	}

	/**
	 *今日の運勢レコードを取得
	 * @param uranai_date 占い日
	 * @return レコードの数や運勢のレコードと運勢名を同時に紐づけて登録した値を返す
	 * @throws ClassNotFoundException 詳細メッセージなしでClassNotFoundExceptionを構築する
	 * @throws SQLException データベース・アクセス・エラーまたはその他のエラーに関する情報を提供する例外
	 */
	@SuppressWarnings("unused")
	public static Map<String, Integer> todayunsei(Date uranai_date) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();
		Map<String, Integer> unsei = new LinkedHashMap<>();

		//unsei_idと同じ運勢のレコードを呼び出す
		String sql = "SELECT  um.unsei_name, A.cnt FROM UnseiMaster um LEFT OUTER JOIN "
				+ "(SELECT omi.unsei_id, count(*) cnt  FROM omikuji omi INNER JOIN Result res ON omi.omikuji_id = res.omikuji_id "
				+ "WHERE res.uranai_date = ? GROUP BY omi.unsei_id ORDER BY omi.unsei_id ASC ) A ON um.unsei_id = A.unsei_id ";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDate(1, uranai_date);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {

			Integer count = rs.getInt("cnt");
			String fortune = rs.getString("unsei_name");

			if (count == null) {
				count = 0;
			}

			//運勢のレコードと運勢名を同時に紐づけて登録
			unsei.put(fortune, count);
		}
		return unsei;

	}

	/**
	 * 誕生日が同じもののレコードを取得
	 * @param birthday 入力された誕生日
	 * @param uranai_date 占い日
	 * @return resultTableに登録されている過去半年にでたおみくじの結果を誕生日を条件に取得し配列に格納したkeyと値を返す
	 * @throws ClassNotFoundException 詳細メッセージなしでClassNotFoundExceptionを構築する
	 * @throws SQLException データベース・アクセス・エラーまたはその他のエラーに関する情報を提供する例外
	 */
	public static Map<Date, String[]> birthdayresult(Date uranai_date, Date birthday)
			throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getConnection();
		Map<Date, String[]> map = new LinkedHashMap<>();

		//過去の占い日を取得
		LocalDate lastMonth = LocalDate.now().minusMonths(6);
		Date last = Date.valueOf(lastMonth);

		//resultTableに登録されている過去半年にでたおみくじの結果を誕生日を条件に取得
		String sql = "SELECT um.unsei_name, omi.gakumon, omi.negaigoto, omi.akinai, res.uranai_date FROM UnseiMaster um "
				+ "INNER JOIN Omikuji omi ON um.unsei_id = omi.unsei_id INNER JOIN result res ON omi.omikuji_id =res.omikuji_id"
				+ " WHERE res.uranai_date >= ? AND res.uranai_date <= ? AND res.birthday = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDate(1, last);
		preparedStatement.setDate(2, uranai_date);
		preparedStatement.setDate(3, birthday);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {

			//値を格納する配列
			String[] a = new String[4];
			a[0] = rs.getString("unsei_name");
			a[1] = rs.getString("gakumon");
			a[2] = rs.getString("negaigoto");
			a[3] = rs.getString("akinai");

			map.put(rs.getDate("uranai_date"), a);
		}
		return map;
	}
}
