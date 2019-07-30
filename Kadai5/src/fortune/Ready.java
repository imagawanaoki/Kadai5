package fortune;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * CSVファイルの値をリストに格納
 *
 */
public class Ready {

public static void unseiList(Connection connection,PreparedStatement preparedStatement) {


	try {
	//CSVファイルの読み込み
	File f = new File("Omikuji.csv");
	BufferedReader br = new BufferedReader(new FileReader(f));

	//読み込んだファイルを１行ずつ画面出力する
	String line;
	int omikuji_id = 1;

	// 1行ずつCSVファイルを読み込む
	while ((line = br.readLine()) != null) {

		// 行をカンマ区切りで配列に変換
		String[] data = line.split(",", 0);
		String unsei = data[0];
		int unsei_id = Integer.parseInt(unsei);
		String negaigoto = data[1];
		String akinai = data[2];
		String gakumon = data[3];


		//INSERTを行う、おみくじテーブルにレコード登録
		String insertday = "INSERT INTO omikuji(omikuji_id, unsei_id, negaigoto, akinai, gakumon ) values(?,?,?,?,?) ";

		preparedStatement = connection.prepareStatement(insertday);
		preparedStatement.setInt(1, omikuji_id);
		preparedStatement.setInt(2,unsei_id);
		preparedStatement.setString(3, negaigoto);
		preparedStatement.setString(4,akinai);
		preparedStatement.setString(5, gakumon);

		 preparedStatement.executeUpdate();

		 omikuji_id++;

		}br.close();
		 connection.commit();



	}catch (Exception e) {

	}

	}
}

