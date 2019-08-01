package Javabeans;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

/**
 *占った日付と入力された誕生日を保存するアクションビーン
 */
public class uranai_dateBean extends ActionForm{
	protected Date uranai_date;
	protected Date birthday;

	public Date getUranai_date() {
		return uranai_date;
	}

	public void setUranai_date(Date uranai_date) {
		this.uranai_date = uranai_date;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
