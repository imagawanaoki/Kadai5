package omikuji_unsei;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import main.Check;
import main.DBManager;
import main.Ready;

/**
 * おみくじの番号を選出
 * おみくじを引いていた場合、引いていなかった場合の条件分岐
 */
public class Omikuji {
	/**
	 *
	* mainメソッド
	*@param args 使用しない
	 *@throws Exception 例外処理
	 *
	 *
	*/

	public static void main(String[] args) throws Exception  {

		String birthday = null;
		LocalDate date1= null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			//DBに接続
			connection = DBManager.getConnection();

			//リスト作成、おみくじ全体が入っている箱作成
			Ready.unseiList(connection, preparedStatement);
			List<Date>localList = Check.BirthdayCheck(birthday, date1);



			//0かomikuji_idを代入
			int omikuji_id = Check.PatternCheck(localList.get(1), localList.get(0), connection, preparedStatement);

			//0を上書きする
			if (omikuji_id == 0) {

			Random rand = new Random();

			//行数の取得
			int ra = Check.Gyo(connection,preparedStatement);
			 omikuji_id = rand.nextInt(ra);


			}

			//Unsei omikuji = Check.CheckOmikuji_id(omikuji_id,connection, preparedStatement);
			//String sb =  omikuji.disp();
			//System.out.println(sb);


			//終了メッセージをコンソール上に出力する
		//	if (Check.PatternCheck(localList.get(1), localList.get(0), connection, preparedStatement) == 0) {
			//	Check.ResistTable(omikuji_id,localList.get(1), localList.get(0),connection, preparedStatement);



		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DBManager.close(connection);

		}
	}
}
