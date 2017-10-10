package com.jumper.hospital.utils;

import java.util.HashMap;
import java.util.Map;

import com.jumper.hospital.vo.familyDoctor.TestItem;

public class TestItemUtil {
	private static Map<String,TestItem> initMap=new HashMap<String,TestItem>();
	public static String heart="heart";
	public static String oxygen="oxygen";
	public static String pressureLow="pressureLow";
	public static String pressureHeight="pressureHeight";
	public static String sugar="sugar";
	public static String temperature="temperature";
	public static String weight="weight";
	static{
		TestItem heartItem = new TestItem("胎心",110d,160d,"次/分");//胎心规则 
		TestItem oxygenItem = new TestItem("血氧",null,95d,"%");//血氧规则
		TestItem pressureLowItem = new TestItem("血压(收缩压)",90d, 139d, "mmHg");//血压(收缩压)
		TestItem pressureHeightItem = new TestItem("血压(舒张压 )",60d, 89d, "mmHg");//血压(舒张压 )
		TestItem sugarItem = new TestItem("血糖",3.9, 6.1, "mmol/L");//血糖
		TestItem temperatureItem = new TestItem("体温",37.0, null, "℃");//体温
		TestItem weightItem = new TestItem("体重",null, null, "Kg");//体温
		initMap.put("heart", heartItem);
		initMap.put("oxygen",oxygenItem );
		initMap.put("pressureLow",pressureLowItem );
		initMap.put("pressureHeight", pressureHeightItem);
		initMap.put("sugar",sugarItem );
		initMap.put("temperature",temperatureItem );
		initMap.put("weight",weightItem );
	}
	public static TestItem getTestItem(String item){
		return initMap.get(item);
	}
	
	public static Map<String, TestItem> getInitMap() {
		return initMap;
	}

	public static void main(String[] args) {
		TestItemUtil.getTestItem(TestItemUtil.temperature).getRange();
	}
}
