package com.ziheng.utils.face;


public class FaceData {

    private int validateFace;//检测人脸的状态码（判断是否检测到人脸）
    private int validatePoint;//获取特征值状态码（判断是否采集成功）
    private  byte[] faceData;//特征值
	public int getValidateFace() {
		return validateFace;
	}
	public void setValidateFace(int validateFace) {
		this.validateFace = validateFace;
	}
	public int getValidatePoint() {
		return validatePoint;
	}
	public void setValidatePoint(int validatePoint) {
		this.validatePoint = validatePoint;
	}
	public byte[] getFaceData() {
		return faceData;
	}
	public void setFaceData(byte[] faceData) {
		this.faceData = faceData;
	}
    
    
    
}
