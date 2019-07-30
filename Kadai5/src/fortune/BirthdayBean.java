package fortune;

import org.apache.struts.validator.ValidatorForm;

/**
 *入力した誕生日を保存するアクションビーン
 */
public class BirthdayBean extends ValidatorForm{
	String birthday;


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthday() {
		return birthday;
	}

}
