package fortune;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 入力された誕生日の形式チェック
 * 異なる場合はテーブルにおみくじの結果を追加する
 * おみくじの結果をResult.jspにフォワードする
 * Servlet implementation class InputDisplay
 */
public class InputDisplay extends Action {
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {

		LocalDate date = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		//sessionを使う準備

		BirthdayBean birthdayBean = (BirthdayBean)form;
		String birthday = birthdayBean.getBirthday();

		//入力チェック
		boolean chkFlg = false;
		Matcher m = null;
		Date today = null;
		Date birth = null;

		//正規表現のチェック
		//現在の日付を取得
		date = LocalDate.now();

		// 正規表現のパターンを作成
		Pattern p = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
		m = p.matcher(birthday);
		chkFlg = m.find();

		//正規表現のパターンが正しくない場合
		if (!chkFlg) {

			return (mapping.findForward("fail"));

		} else {

				//Date型に変換
			today = Date.valueOf(date);
			birth = Date.valueOf(birthday);
			request.setAttribute("today", today);
			request.setAttribute("birth", birth);

			try {

			//おみくじを引くかどうかの判定及び行数から乱数を生成
			int omikuji_id = LineCheckDao.Gyo(birth, today);

			//おみくじ結果の取得
			Unsei omikuji = ResultDao.CheckOmikujiId(omikuji_id);
			request.setAttribute("omikuji", omikuji);

			if(Check.PatternCheck(birth, today, connection, preparedStatement)==0) {

			//おみくじ結果をDBに登録
			ResistTableDao.ResistTable(omikuji_id, birth, today, connection, preparedStatement);

			}
			} catch(Exception e) {
				e.printStackTrace();
				throw e;

			}
			return (mapping.findForward("success"));
		}
	}
}


