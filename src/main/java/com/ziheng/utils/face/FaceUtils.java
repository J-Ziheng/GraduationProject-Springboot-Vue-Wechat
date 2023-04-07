package com.ziheng.utils.face;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

@Data
@Component
public class FaceUtils {

    @Value("${face.appId}")
    public  String appId1;
    @Value("${face.sdkKey}")
    public  String sdkKey1;
    @Value("${face.dllPath}")
    public  String dllPath1;
    public static String appId;
    public static String sdkKey;
    public static String dllPath;
   
    @PostConstruct
    public void init(){
        appId = this.appId1;
        sdkKey = this.sdkKey1;
        dllPath = this.dllPath1;
    }
    /**
     * 初始化虹软引擎
     */
    public static FaceEngine getFaceEngine() {
        // 实例化一个面部引擎
    	 FaceEngine faceEngine = new FaceEngine(dllPath);
        //激活引擎
        // 这个SKDK几乎每一个调用引擎的操作都会返回一个int类型的结果，称为错误码
        // 每一种错误码对应一种程序执行的结果，如果错误，可以去官网查看错误类型
        int errorCode = faceEngine.activeOnline(appId, sdkKey);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }
        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        // 设置为单张高精度识别
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        // 人脸不旋转，为零度
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        // 识别的最小人脸识别 = 图片长边/人脸框长边的比值
        engineConfiguration.setDetectFaceMaxNum(10);
        // 设置最多能检测的人脸数量
        engineConfiguration.setDetectFaceScaleVal(16);

        //以下为功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        // 年龄检测
        functionConfiguration.setSupportAge(true);
        // 启用3D检测
        functionConfiguration.setSupportFace3dAngle(true);
        // 启用支持人脸检测
        functionConfiguration.setSupportFaceDetect(true);
        // 启用人脸识别
        functionConfiguration.setSupportFaceRecognition(true);
        // 启用性别识别
        functionConfiguration.setSupportGender(true);
        // 启用RGB活体检测
        functionConfiguration.setSupportLiveness(true);
        // 启用IR活体检测
        functionConfiguration.setSupportIRLiveness(true);

        // 将功能配置注入到引擎中
        engineConfiguration.setFunctionConfiguration(functionConfiguration);
        //引擎设置注入 初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        // errCode可以去官网直接输入状态码查询错误原因
        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
        return faceEngine;
    }



    /**
     * 开始人脸相似度匹配
     *
     * 获取一张照片的特征值
     * @param bytes
     * @return
     */
    public static FaceData getFaceData(byte[] bytes){
        FaceData faceData = new FaceData();
        //人脸检测失败
        ImageInfo imageInfo = getRGBData(bytes);
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        FaceEngine faceEngine = FaceUtils.getFaceEngine();
        int errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
        System.out.println("人脸检测状态码："+errorCode);
        faceData.setValidateFace(errorCode);
        if(faceInfoList.size()==0) {
        	 faceData.setValidateFace(-1);
        	 return faceData;
        }
        //人脸特征提取
        FaceFeature faceFeature = new FaceFeature();
        // 向引擎传入从图片分离的信息数据
        int code=faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);
        System.out.println("特征值状态码：" + code);
        faceData.setValidatePoint(code);
        faceData.setFaceData(faceFeature.getFeatureData());
        //引擎卸载
        faceEngine.unInit();
        return faceData;
    }

    /**
     *  特征值比对（返回相似度）
     * @param sources
     * @param target
     * @return
     */
    public static CompareFace compare(byte[] sources,byte[] target){
        CompareFace compareFace = new CompareFace();
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(target);
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(sources);
        FaceSimilar faceSimilar = new FaceSimilar();
        FaceEngine faceEngine = FaceUtils.getFaceEngine();
        int scoreCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
        System.out.println("相似度状态码："+scoreCode);
        compareFace.setScoreCode(scoreCode);
        compareFace.setScore(faceSimilar.getScore());
        //引擎卸载
        faceEngine.unInit();
        return compareFace;
    }
}
