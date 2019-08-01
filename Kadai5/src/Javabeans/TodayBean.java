package Javabeans;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

/**
 *占った日付を保存しているアクションビーン
 */
public class TodayBean extends ActionForm {

	private Date today;
	private int unsei_id;

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public int getUnsei_id() {
		return unsei_id;
	}

	public void setUnsei_id(int unsei_id) {
		this.unsei_id = unsei_id;
	}
}
