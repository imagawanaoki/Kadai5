package main;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import Javabeans.uranai_dateBean;

/**
 *ResultDaoやアクションビーンからメソッドを呼び出し、各運勢の割合を計算する
 */
public class Path extends Action {
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {

		//アクションフォームBeanの値を取り出す
		uranai_dateBean uranai_dateBean = (uranai_dateBean)form;
		Date uranai = uranai_dateBean.getUranai_date();
		Date birth = uranai_dateBean.getBirthday();

		//ResultDaoから値を呼び出す
		Map<Date, String[]> resultbirth = ResultDao.birthdayresult(uranai, birth);

		request.setAttribute("resultbirth", resultbirth);

		return(mapping.findForward("go"));
}

}
