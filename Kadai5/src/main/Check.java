package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *入力された誕生日の形式チェック
 *異なる場合はテーブルにおみくじの結果を追加する
 */

public class Check {

	public static List<Date> BirthdayCheck(String birthday, LocalDate date) throws IOException {

		boolean chkFlg = false;
		Matcher m = null;
		List<Date> localList = new ArrayList<>();

		BufferedReader bu = new BufferedReader(new InputStreamReader(System.in));

		//正規表現のチェック
		do {
			System.out.println("誕生日を入力してください");

			birthday = bu.readLine();//入力待機
			date = LocalDate.now();//現在の日付を取得

			// 正規表現のパターンを作成
			Pattern p = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
			m = p.matcher(birthday);
			chkFlg = m.find();

			//正規表現のパターンが正しくない場合
			if (!chkFlg) {
				System.out.println("正しい形式ではありません。");

			} else {

				//形式表示

				Date da = Date.valueOf(date);
				Date lo = Date.valueOf(birthday);
				localList.add(da);
				localList.add(lo);

			}

		} while (!chkFlg);
		return localList;
	}

	/**
	 * @throws NumberFormatException 例外処理
	 * @throws IOException			例外処理
	 * @throws SQLException			例外処理
	 *@param preparedStatement ステートメントの作成
	 *@param birthday ユーザーに入力された値
	 *@param uranai_date テーブルに登録されたカラム名
	 *@param connection DBと接続する
	 *@return resultSet.getInt("omikuji_id") おみくじを事前に一度引いていた場合以前引いたおみくじコードを返却する
	 *@return 0 おみくじを一度も引いていなかった場合0を返す
	 */

	public static int PatternCheck(Date birthday, Date uranai_date, Connection connection,
			PreparedStatement preparedStatement)
			throws NumberFormatException, IOException, SQLException {
		try {
			connection = DBManager.getConnection();

			//SQLの条件式　引いたか引いてないか
			String idCheck = "SELECT omikuji_id FROM result WHERE uranai_date = ? AND birthday = ?";

			//ステートメント作成
			preparedStatement = connection.prepareStatement(idCheck);
			preparedStatement.setDate(1, uranai_date);
			preparedStatement.setDate(2, birthday);

			//検索結果の格納,表示
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt("omikuji_id");
			} else {

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return 0;

	}

	/**
	 * SQLで乱数を取得
	 *@param preparedStatement ステートメントの作成
	 *@param connection DBと接続する
	 *@throws SQLException 例外処理
	 *@return pa 獲得した行数を格納
	 *
	 */

	public static int Gyo(Connection connection, PreparedStatement preparedStatement) throws SQLException {
		//行数を取得する文
		String para = "select COUNT(*) from omikuji";

		//SQLを準備する
		preparedStatement = connection.prepareStatement(para);

		ResultSet rset = preparedStatement.executeQuery();

		//結果を格納
		rset.next();
		int pa = rset.getInt("count");

		return pa;

	}

	/**
	 *
	 *テーブルへの登録処理
	 *@throws SQLException 例外処理
	 *@param preparedStatement ステートメントの作成
	 *@param connection DBと接続する
	 *@param omikuji_id  テーブルに登録されているカラム名、おみくじコード
	 *@param birthday  ユーザーに入力された値
	 *@param uranai_date テーブルに登録されたカラム名

	 *	 */

	/*	public static void ResistTable(int omikuji_id, Date birthday, Date uranai_date, Connection connection,
				PreparedStatement preparedStatement) throws SQLException {

			//テーブルへの登録
			String insertday = "INSERT INTO result( uranai_date, birthday, omikuji_id) values(?,?,?) ";

			preparedStatement = connection.prepareStatement(insertday);
			preparedStatement.setDate(1, uranai_date);
			preparedStatement.setDate(2, birthday);
			preparedStatement.setInt(3, omikuji_id);

			preparedStatement.executeUpdate();
	*/
}
