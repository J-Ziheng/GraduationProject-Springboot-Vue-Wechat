package com.ziheng.utils.face;
/**
 * 封装人脸相似度比对状态码 与 相似度
 */
public class CompareFace {
    private int scoreCode;//比对相似度状态码
    private float score;//相似度

	public void setScoreCode(int scoreCode) {
		this.scoreCode = scoreCode;
	}
	public int getScoreCode() {
		return scoreCode;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public float getScore() {
		return score;
	}




}
