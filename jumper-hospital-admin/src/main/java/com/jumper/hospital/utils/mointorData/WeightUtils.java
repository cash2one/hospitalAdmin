package com.jumper.hospital.utils.mointorData;

import java.util.HashMap;
import java.util.Map;

import com.jumper.hospital.entity.UserExtraInfo;

/**
 * 体重管理工具类
 * @author tanqing
 *
 */
public class WeightUtils {
	
	
	/**
	 * 根据身高体重计算BMI
	 * @param userExtra
	 * @return
	 */
	public static double getBmiByWeightAndHeight(UserExtraInfo userExtra){
		Double weigh =Double.valueOf(userExtra.getWeight().toString());//(kg)
		Double heigh = Double.valueOf(userExtra.getHeight())/100 ; //(m)
		Double BMI = (weigh/(heigh*heigh));
		return BMI;
	}
	
	/**
	 * 计算体重状态
	 * @param BMI BMI值
	 * @return 体重状态
	 */
	public static String getWeightState(Double BMI){
		String state = "";
		if(BMI < 18.5){
			state = "偏瘦";
		}else if(BMI >= 18.5 &&  BMI <= 24.9){
			state = "标准";
		}else if(BMI >= 25.0 &&  BMI <= 29.9){
			state = "超重";
		}else if(BMI >= 30.0){
			state = "肥胖";
		}
		return state;
	}
	/**
	 * 获取用户整个保健期的健康体重
	 * @param initBMI 孕前bmi
	 * @param initWeight 孕前体重
	 * @param totalDays 整个保健期具体日期的字符串数组
	 * @return 健康体重的map key=具体日期 value=健康区间
	 */
	public static Map<String,String> getHealthWeight(Double initBMI,Double initWeight,String[] totalDays){
		Map<String,String> healthWeight = new HashMap<String,String>();
		if(null != initWeight){
			//计算健康曲间
			for(int i=0;i<=totalDays.length-1;i++){
				if(i<=90){
					healthWeight.put(totalDays[i], i/90.0+initWeight+","+i/30.0+initWeight);
				}
				if(i>90){
					if(initBMI<18.5){
						healthWeight.put(totalDays[i], (i*23/380.0-169/38.0+initWeight)+","+(i*3/38.0-78/19.0+initWeight));
					}else if(initBMI<=24.9){
						healthWeight.put(totalDays[i], (i*21/380.0-151/38.0+initWeight)+","+(i*13/190.0-60/19.0+initWeight));
					}else if(initBMI<=29.9){
						healthWeight.put(totalDays[i], (i*3/95.0-35/19.0+initWeight)+","+(i*17/380.0-39/38.0+initWeight));
					}else{
						healthWeight.put(totalDays[i], (i*2/95.0-17/19.0+initWeight)+","+(i*3/95.0+3/19.0+initWeight));
					}
				}
			}
		}
		return healthWeight;
	}
}
