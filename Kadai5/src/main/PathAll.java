package main;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import Javabeans.TodayBean;

/**
 *ResultDaoやアクションビーンからメソッドを呼び出し、各運勢の割合を計算する
 *計算した値をsetしJSPにフォワードする
 */
public class PathAll extends Action {

	/**
	 * ResultDaoやアクションビーンからメソッドを呼び出し、各運勢の割合を計算する
	 */
	public  ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {

		//アクションフォームBeanの値を取り出す
		TodayBean todayBean = (TodayBean) form;
		Date today = todayBean.getToday();

		//Resultdaoから値を呼びだす
		int allrecord = ResultDao.allunsei(today);
		Map<String, Integer> anyfortune = ResultDao.unsei(today);
		int todayrecord = ResultDao.todayrecord(today);
		Map<String, Integer> unsei = ResultDao.todayunsei(today);
		Map<String, BigDecimal> result = new LinkedHashMap<String, BigDecimal>();


		//過去半年の運勢結果の割合を取得
		for (String fortune : anyfortune.keySet()) {
			//運勢の行数を取得
			double line = anyfortune.get(fortune);
			//割合の計算
			double rate = (((double) line / (double) allrecord) * 100);

			//割合を四捨五入
			BigDecimal bd = new BigDecimal(rate);
			BigDecimal re = bd.setScale(1, RoundingMode.HALF_UP);
			result.put(fortune, re);

		}

		//今日の割合の計算
		Map<String, BigDecimal> resulttoday = new LinkedHashMap<String, BigDecimal>();

		for (String fortune : unsei.keySet()) {
			//運勢の行数を取得
			double line = unsei.get(fortune);
			//割合の計算
			double rate = (((double) line / (double) todayrecord) * 100);

			//割合を四捨五入
			BigDecimal bd = new BigDecimal(rate);
			BigDecimal re = bd.setScale(1, RoundingMode.HALF_UP);
			resulttoday.put(fortune, re);

		}
		request.setAttribute("result", result);
		request.setAttribute("resulttoday", resulttoday);

		return (mapping.findForward("move"));

	}
}