package fortuneInterFace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import omikuji_unsei.Omikuji;

/**
 *文字化け処理
 *プロパティファイルのキーを取得
 */

public interface Fortune {

	//文字化け対処
	static Properties loadUtf8Properties(String resourceName) {

		Properties result = new Properties();

		try (InputStream is = Omikuji.class.getResourceAsStream(resourceName);
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				BufferedReader reader = new BufferedReader(isr)) {

			result.load(reader);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	//文字化け処理

	Properties prop = loadUtf8Properties("/fortune.properties");

	//ResourceBundle rb = ResourceBundle.getBundle("fortune",new ResourceBundle);

	String DISP_STR = prop.getProperty("disp_str");

	String disp();
}
