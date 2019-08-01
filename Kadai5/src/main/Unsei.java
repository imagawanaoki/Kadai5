package main;

import fortuneInterFace.Fortune;

/**
 *抽象クラスの値の取得を行なっている
 */


public abstract class Unsei implements Fortune{



	protected String unsei;
	protected String negaigoto;
	protected String akinai;
	protected String gakumon;

	public abstract void setUnsei();

	public String getUnsei() {
		return unsei;
	}

	public void setUnsei(String unsei) {
		this.unsei = unsei;
	}

	public String getNegaigoto() {
		return negaigoto;
	}

	public void setNegaigoto(String negaigoto) {
		this.negaigoto = negaigoto;
	}

	public String getAkinai() {
		return akinai;
	}

	public void setAkinai(String akinai) {
		this.akinai = akinai;
	}

	public String getGakumon() {
		return gakumon;
	}

	public void setGakumon(String gakumon) {
		this.gakumon = gakumon;
	}


	/*
	 * CSVの値を格納
	 * @see omikuji.Fortune#disp()
	 */
	public String disp() {

		String ls = System.lineSeparator();
		//文字列結合
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(DISP_STR,getUnsei()));
		sb.append(ls);
		sb.append("願い事:" + getNegaigoto());
		sb.append(ls);
		sb.append("商い" + getAkinai());
		sb.append(ls);
		sb.append("学問:" + getGakumon());
		return sb.toString();


		}
}

