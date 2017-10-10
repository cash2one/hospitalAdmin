package com.jumper.hospital.vo.familyDoctor;

/**
 * 测试项目的参数的描述
 * @author Administrator
 *
 */
public class TestItem {
	private String project;//检测项目名称
	private String range;//范围
	private Integer state;//状态 0 偏低  1 正常  2 偏高
	private Double normalMin=null;//最小值
	private Double normalMax=null;//最大值
	private String company;//单位
	
	public TestItem(String project,Double normalMin, Double normalMax, String company) {
		super();
		this.normalMin = normalMin;
		this.normalMax = normalMax;
		this.company = company;
		this.project=project;
		this.setRange();
	}
	public TestItem(Double normalMin, Double normalMax, String company) {
		super();
		this.normalMin = normalMin;
		this.normalMax = normalMax;
		this.company = company;
		this.setRange();
	}
	
  public int getType(){
	  int type=-1;
	  if(null!=this.normalMin && null!=this.normalMax){//最大值 和最小值都有值
		  type= 1;
		}else if(null==this.normalMin && null!=this.normalMax){//只有最大值
		 type= 2;
		}else if(null!=this.normalMin && null==this.normalMax){//只有最小值
	     type= 0;
		}
	return type;
  }
	

	public void setRange() {
		 int type = getType();
		 String range="";
		 if(type==1){
			 String min=this.getNormalMin()+"";
				min= min.endsWith(".0")? min=min.substring(0, min.length()-2):min;
			 String max=this.getNormalMax()+"";
			 	max=max.endsWith(".0")?max.substring(0, max.length()-2):max;
			 
			 range=min+"-"+max+this.getCompany();
		 }else if(type==2){
			 String max=this.getNormalMax()+"";
			 	max=max.endsWith(".0")?max.substring(0, max.length()-2):max;
			 range="大于"+max+this.getCompany();
		 }else if(type==0){
			 String min=this.getNormalMin()+"";
				min= min.endsWith(".0")? min=min.substring(0, min.length()-2):min;
			 range="小于"+min+this.getCompany();
		 }
		this.range=range;
	}

	 

	public Integer getState(Double val) {
		if(null==val)return null;
		 int type = getType();
		 int state=-1;
		 if(type==1){
			  if(val>=this.getNormalMin() && val<=this.getNormalMax()){
				  state=1;
			  }else if(val<this.getNormalMin()){
				  state=0;
			  }else if(val>this.getNormalMax()){
				  state=2;
			  }
		 }else if(type==2){//只有max
			 if(val>=this.getNormalMax()){
				 state=1;
			 }else {
				 state=0;
			 }
		 }else if(type==0){//只有最小值
			  if(val>this.getNormalMin()){
				  state=2;
			  }else if(val<=this.getNormalMin()){
				  state=1;
			  }
		 }
		return state;
	}

	 

	public Double getNormalMin() {
		return normalMin;
	}

	 

	public Double getNormalMax() {
		return normalMax;
	}

	 

	public String getCompany() {
		return company;
	}


	 

	public String getRange() {
		return range;
	}

	public static void main(String[] args) {
		/*TestItem t1= new TestItem(37.0,null,"℃");
		TestItem t2= new TestItem(60.0,89.0,"mmHg");*/
		TestItem t3= new TestItem(null,95.0,"%");
		System.out.println(t3.getRange());
		System.out.println(t3.getState(98d));
	}

	public String getProject() {
		return project;
	}
	@Override
	public String toString() {
		return "TestItem [range=" + range + ", state=" + state + ", normalMin="
				+ normalMin + ", normalMax=" + normalMax + ", company="
				+ company + "]";
	}

}
