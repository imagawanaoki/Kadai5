package fortune;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

/**
 *占った日付を保存しているアクションビーン
 */
public class TodayBean extends ActionForm {

	private Date today;


	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}
}
