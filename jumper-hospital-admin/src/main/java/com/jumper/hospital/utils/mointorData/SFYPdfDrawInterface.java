package com.jumper.hospital.utils.mointorData;
/**
 * 省妇幼胎心胎心报告胎心曲线图画图接口
 * @author rent
 * @date 2016-04-14 14:55
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AsianFontMapper;
import com.itextpdf.text.pdf.FontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.draw.DrawInterface;

public class SFYPdfDrawInterface implements DrawInterface {
	
	/** 总宽度，13.5分钟的曲线在72DPI环境下宽度为765.46 **/
	private static float total_width = 796.5f;
	/** 垂直每一格所代表的像素点**/
	private static float vertical_size = 11f;
	/** 左边距38个点 **/
	private static int margin_left = 23;
	/** 上边距100个点 **/
	private static int margin_top = 210;
	/** 正常区域颜色 **/
	private static Color normal_color = new Color(162, 162, 162, 100);
	/** 黑色 **/
	private static Color black_color = new Color(0, 0, 0, 255);
	/** 浅黑色 **/
	private static Color light_black_color = new Color(90, 90, 90, 120);
	/** Y轴坐标值总量 **/
	private static int y_label_count = 200;
	/** 宫缩Y轴总刻度 **/
	private static int uterus_count = 100;
	
	/** 胎心曲线数据 **/
	protected  int[] dataArray = {};
	protected  int[] uterusArray = {};
	/** 页码，1、2 **/
	protected  int page;
	/** 胎动数据 **/
	protected  int[] fetalMoveArray = {};
	/** 走纸速度，类型有(1,2,3,20,30) **/
	protected  int paperSpeed;
	/** 开始时间，用于计算胎动数据X点 **/
	protected  int start;
	/** 假定一个测试开始时间 **/
	protected Timestamp testTime = TimeUtils.getCurrentTime();
	
	@SuppressWarnings("static-access")
	public SFYPdfDrawInterface(Integer margin_top, Integer page, int[] dataArray, int[] uterusArray, int[] fetalMoveArray,Integer paperSpeed, Integer start, Timestamp testTime){
		this.margin_top = margin_top;
		this.page = page;
		this.dataArray = dataArray;
		this.uterusArray = uterusArray;
		this.fetalMoveArray = fetalMoveArray;
		this.paperSpeed = paperSpeed;
		this.start = start;
		this.testTime = testTime;
	}
	
	public SFYPdfDrawInterface(){
		
	}
	
	public void draw(PdfContentByte canvas, float left, float bottom, float right, float top, float oy) {
		/** 设置画布区域大小，这里设置为A4纸张的大小 **/
		Rectangle rect = PageSize.A4.rotate();
		/** 设置画图字体支持中文 **/
		FontMapper fm = new AsianFontMapper(AsianFontMapper.ChineseSimplifiedFont, AsianFontMapper.ChineseSimplifiedEncoding_H);
		Graphics2D graph = canvas.createGraphics(rect.getWidth() - 20, rect.getHeight(), fm);
		/** 去掉线条毛边 **/
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		DrawParam param = null;
		switch (paperSpeed) {
			case 1:
				param = new DrawParam(17, 27, 29.5f, 1, page);
				break;
			case 2:
				param = new DrawParam(17, 27, 29.5f, 2, page);
				break;
			case 3:
				param = new DrawParam(17, 27, 29.5f, 3, page);
				break;
			case 20:
				param = new DrawParam(17, 40, 19.91f, 20, page);
				break;
			case 30:
				param = new DrawParam(17, 30, 26.55f, 30, page);
				break;
			default:
				break;
		}
		
		drawFetalHeartXLine(graph, margin_top, param);
		drawFetalHeartYLine(graph, margin_top, param);
		drawFetalHeartXLabel(graph, margin_top, param);
		drawFetalHeartYLabel(graph, margin_top, param);
		randerNormalArea(graph, margin_top);
		randerValue(graph, page, margin_top, param);
		randerFetalMoveData(graph, page, margin_top, fetalMoveArray, param);
		
		/** 宫缩的图 **/
		drawUterusXLine(graph, margin_top + 197 + 28);
		drawUterusYLine(graph, margin_top + 197 + 28, param);
		drawUterusYLabel(graph, margin_top + 197 + 28, param);
		randerUterusValue(graph, margin_top + 197 + 28, param);
	}
	
	/**
	 * 画X轴
	 * @param graph 画布对象
	 * @param marginTop 绝对定位的上边距
	 */
	protected void drawFetalHeartXLine(Graphics2D graph, Integer marginTop, DrawParam param){
		for(int i = 0; i < param.v_point; i++){
			int offset_y = (int) (marginTop + Math.ceil(vertical_size * i));
			if(i % 2 == 0){
				 graph.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 2}, 0));
			}else{
				graph.setStroke(new BasicStroke(0.5f));
			}
			graph.setColor(light_black_color);
			graph.drawLine(margin_left, offset_y, (int)Math.ceil(total_width + margin_left - 0.5), offset_y);
		}
	}
	
	/**
	 * 画Y轴
	 * @param graph
	 * @param marginTop 绝对定位的上边距
	 */
	protected static void drawFetalHeartYLine(Graphics2D graph, Integer marginTop, DrawParam param){
		for(int j = 0; j <= param.h_point; j++){
			int offset_x = (int) (margin_left + Math.round(param.h_each_size * j));
			graph.setStroke(new BasicStroke(0.5f));
			graph.drawLine(offset_x, marginTop + 1, offset_x, (int) (marginTop + Math.ceil(vertical_size * (param.v_point - 1)) - 1));
			graph.setColor(light_black_color);
		}
	}

	/**
	 * 计算时间，这里提出来
	 * @param time 测试开始的时间
	 * @param start 生成报告截取的偏移量
	 * @param next 往后多长时间 单位秒
	 * @return
	 */
	protected static String getTime(Timestamp time, Integer start){
		if(time == null){
			return "";
		}
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.SECOND, start);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(c.getTime());
	}
	
	/**
	 * X轴坐标
	 * @param graph
	 * @param marginTop 绝对定位的上边距
	 */
	protected  void drawFetalHeartXLabel(Graphics2D graph, Integer marginTop, DrawParam param){
		float x_label_top = marginTop + vertical_size * param.v_point + 3;
		for(int o = 0; o <= param.h_point; o++){
			float offset_x = margin_left + param.h_each_size * o - 1.5f;
			graph.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 8));
			graph.setColor(Color.BLACK);
			switch (param.speed) {
				case 1: 
				case 2: 
				case 3: 
				case 30:
					if(o == 6 || o == 18){
						if(param.speed == 1){
							graph.drawString(o + param.h_point * (param.page - 1) +"分0秒", offset_x, x_label_top);
						}else if(param.speed == 2){
							int min = (int) (param.page % 2 == 0 ? o / 2 + 13 * (param.page - 1) : o / 2 + 13 * (param.page - 1) + (param.page - 1) * 0.5);
							int sec = param.page % 2 == 0 ? 30 : 0;
							graph.drawString(min + "分" + sec + "秒", offset_x, x_label_top);
						}else if(param.speed == 3){
							graph.drawString(o / 3 + (param.page - 1) * 9+"分0秒", offset_x, x_label_top);
						}else{
							graph.drawString(o + 30 * (param.page - 1) +"分0秒", offset_x, x_label_top);
						}
					}
					if(o % 12 == 0){
						int second = 0;
						if(param.speed == 1){
							second = (o + param.h_point * (param.page - 1)) * 60;
						}else if(param.speed == 2){
							second = (int) (o / 2 + 13.5 * (param.page - 1)) * 60;
						}else if(param.speed == 3){
							second = (o / 3 + (param.page - 1) * 9) * 60;
						}else{
							second = (o + 30 * (param.page - 1)) * 60;
						}
						graph.drawString("↑"+getTime(testTime, second + start), offset_x - 2, x_label_top);
					}
					break;
				case 20:
					/** 详细时间 **/
					if(o == 0 || o == 24){
						int second = (o / 2 + 20 * (param.page - 1)) * 60;
						graph.drawString("↑"+getTime(testTime, second + start), offset_x - 2, x_label_top);
					}
					/** 时间轴 **/
					if(o == 12 || o == 36){
						graph.drawString(o / 2 + 20 * (param.page - 1) +"分0秒", offset_x, x_label_top);
					}
					break;
				default: break;
			}
		}
	}
	
	/**
	 * Y轴坐标
	 * @param graph
	 * @param marginTop 绝对定位的上边距
	 */
	protected static void drawFetalHeartYLabel(Graphics2D graph, Integer marginTop, DrawParam param){
		for(int n = 1; n <= 2; n++){
			for(int k = 0; k < param.v_point; k++){
				if(k % 2 == 0 && k < param.v_point - 1){
					graph.setColor(Color.WHITE);
					switch (param.speed) {
						case 1: case 2: case 3: case 30:
							graph.fillRect((int)(margin_left + param.h_each_size * n * 6 - 5), (int)(marginTop + (k + 1) * vertical_size - 2), 14, 8);
							break;
						case 20:
							graph.fillRect((int)(margin_left + param.h_each_size * n * 12 - 5), (int)(marginTop + (k + 1) * vertical_size - 2), 14, 8);
							break;
						default: break;
					}
				}
			}
			for(int k = 0; k < param.v_point; k++){
				if(k % 2 == 0 && k < param.v_point - 1){
					int y_label = y_label_count - 10 * k;
					graph.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 8));
					graph.setColor(Color.BLACK);
					switch (param.speed) {
						case 1: case 2: case 3: case 30:
							graph.drawString(String.valueOf(y_label), margin_left + param.h_each_size * n * 6 - 5 , marginTop + (k + 1) * vertical_size + 3.6f);
							break;
						case 20:
							graph.drawString(String.valueOf(y_label), margin_left + param.h_each_size * n * 12 - 5 , marginTop + (k + 1) * vertical_size + 3.6f);
							break;
						default: break;
					}
				}
			}
		}
	}
	
	/**
	 * 渲染正常区域背景
	 * @param graph
	 */
	protected void randerNormalArea(Graphics2D graph, Integer marginTop){
		int x = margin_left;
		int y = (int)(marginTop + vertical_size * 5);
		int width = (int)(total_width);
		int height = (int)(vertical_size * 5);
		graph.setColor(normal_color);
		graph.fillRect(x, y , width, height);
	}
	
	/**
	 * 渲染胎心曲线值
	 * @param graph
	 */
	protected void randerValue(Graphics2D graph, Integer page, Integer marginTop, DrawParam param){
		int totalPoint = 0;
		if(dataArray.length < 1){
			return;
		}
		switch (param.speed) {
			case 1: totalPoint = 3240; break;
			case 2: totalPoint = 1620; break;
			case 3: totalPoint = 1080; break;
			case 20: totalPoint = 2400; break;
			case 30: totalPoint = 3600; break;
			default: break;
		}
				
		if(dataArray != null && dataArray.length > 0){
			for(int v = 0; v < dataArray.length - 1; v++){
				/** 两点差值大于30将不计入图表中 **/
				int abs_value = Math.abs(dataArray[v + 1] - dataArray[v]);
				if(abs_value > 30 || dataArray[v] == 0){
					continue;
				}
				int x1 = margin_left + Math.round(total_width / totalPoint * v);
				int y1 = (int) (marginTop + Math.round(210 - dataArray[v]) * vertical_size / 10);
				int x2 = margin_left + Math.round(total_width / totalPoint * (v + 1));
				int y2 = (int) (marginTop + Math.round(210 - dataArray[v + 1]) * vertical_size / 10);
				graph.setStroke(new BasicStroke(0.5f));
				graph.setColor(Color.BLACK);
				graph.drawLine(x1 , y1, x2, y2);
			}
		}
	}
	
	/**
	 * 渲染胎动值
	 * @param graph
	 */
	protected void randerFetalMoveData(Graphics2D graph, Integer page, Integer marginTop, int[] fetalMoveArray, DrawParam param){
		int offTime = Math.round(start / 2);
		if(fetalMoveArray != null && fetalMoveArray.length > 0){
			for(int v = 0; v < fetalMoveArray.length; v++){
				int totalTime = 0;
				int x = 0;
				switch (param.speed) {
					case 1: totalTime = 1620; break;
					case 2: totalTime = 810; break;
					case 3: totalTime = 540; break;
					case 20: totalTime = 1200; break;
					case 30: totalTime = 1800; break;
					default: break;
				}
				
				if(fetalMoveArray[v] > (totalTime*(param.page - 1) + offTime) && fetalMoveArray[v] <= (totalTime*param.page + offTime)){
					x = margin_left + Math.round(total_width / totalTime * (fetalMoveArray[v] - Math.round(start / 2) - totalTime*(param.page - 1)));
				}
				
				if(x >= margin_left){
					int y = (int) (marginTop + vertical_size * param.v_point + 25);
					graph.setColor(black_color);
					graph.drawRect(x, y, 2, 5);
					graph.fillRect(x, y, 2, 5);
				}
			}
		}
	}
	
	/**
	 * 画宫缩X轴
	 * @param graph 画布对象
	 * @param marginTop 绝对定位的上边距
	 */
	protected void drawUterusXLine(Graphics2D graph, Integer marginTop){
		for(int i = 0; i < 11; i++){
			int offset_y = (int) (marginTop + Math.ceil(vertical_size * i));
			if(i % 2 == 0){
				graph.setStroke(new BasicStroke(0.5f));
			}else{
				graph.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4, 2}, 0));
			}
			graph.setColor(light_black_color);
			graph.drawLine(margin_left, offset_y, (int)Math.ceil(total_width + margin_left), offset_y);
		}
	}
	
	/**
	 * 画宫缩Y轴
	 * @param graph
	 * @param marginTop 绝对定位的上边距
	 */
	protected void drawUterusYLine(Graphics2D graph, Integer marginTop, DrawParam param){
		for(int j = 0; j <= param.h_point; j++){
			int offset_x = (int) (margin_left + Math.round(param.h_each_size * j));
			graph.setStroke(new BasicStroke(0.5f));
			graph.drawLine(offset_x, marginTop + 1, offset_x, (int) (marginTop + Math.ceil(vertical_size * (11 - 1)) - 1));
			graph.setColor(light_black_color);
		}
	}
	
	/**
	 * 宫缩Y轴坐标
	 * @param graph
	 * @param marginTop 绝对定位的上边距
	 */
	protected void drawUterusYLabel(Graphics2D graph, Integer marginTop, DrawParam param){
		for(int n = 1; n <= 2; n++){
			for(int k = 0; k < 11; k++){
				if(k % 2 == 0){
					graph.setColor(Color.WHITE);
					switch (param.speed) {
						case 1: case 2: case 3: case 30:
							graph.fillRect((int)(margin_left + param.h_each_size * n * 6 - 5), (int)(marginTop + k * vertical_size - 2), 14, 8);
							break;
						case 20:
							graph.fillRect((int)(margin_left + param.h_each_size * n * 12 - 5), (int)(marginTop + k * vertical_size - 2), 14, 8);
							break;
						default:
							break;
					}
				}
			}
			for(int k = 0; k < 11; k++){
				if(k % 2 == 0){
					int y_label = uterus_count - 10 * k;
					graph.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 8));
					graph.setColor(Color.BLACK);
					switch (param.speed) {
						case 1: case 2: case 3: case 30:
							graph.drawString(String.valueOf(y_label), margin_left + param.h_each_size * n * 6 - 3 , marginTop + k * vertical_size + 3.6f);
							break;
						case 20:
							graph.drawString(String.valueOf(y_label), margin_left + param.h_each_size * n * 12 - 3 , marginTop + k * vertical_size + 3.6f);
							break;
						default:
							break;
					}
				}
			}
		}
	}
	
	/**
	 * 渲染宫缩值
	 * @param graph
	 */
	protected void randerUterusValue(Graphics2D graph, Integer marginTop, DrawParam param){
		int totalPoint = 0;
		/*if(uterusArray.length < 1){
			return;
		}*/
		if(null == uterusArray || uterusArray.length < 1){
			return;
		}
		switch (param.speed) {
			case 1: totalPoint = 3240; break;
			case 2: totalPoint = 1620; break;
			case 3: totalPoint = 1080; break;
			case 20: totalPoint = 2400; break;
			case 30: totalPoint = 3600; break;
			default: break;
		}
		
		if(uterusArray != null && uterusArray.length > 0){
			for(int v = 0; v < uterusArray.length - 1; v++){
				int abs_value = Math.abs(uterusArray[v + 1] - uterusArray[v]);
				if(abs_value > 30 || uterusArray[v] == 0){
					continue;
				}
				int x1 = margin_left + Math.round(total_width / totalPoint * v);
				int y1 = (int) (marginTop + Math.round(100 - uterusArray[v]) * vertical_size / 10);
				int x2 = margin_left + Math.round(total_width / totalPoint * (v + 1));
				int y2 = (int) (marginTop + Math.round(100 - uterusArray[v + 1]) * vertical_size / 10);
				graph.setStroke(new BasicStroke(0.5f));
				graph.setColor(Color.BLACK);
				graph.drawLine(x1 , y1, x2, y2);
			}
		}
	}
	
	public class DrawParam{
		
		/** 垂直多少个Cell **/
		private int v_point;
		/** 水平多少个Cell **/
		private int h_point;
		/** 水平每一格Cell的宽度 **/
		private float h_each_size;
		/** 走纸速度 **/
		private int speed;
		/** 页数 **/
		private int page;
		
		public DrawParam() {
			
		}
		public DrawParam(int v_point, int h_point, float h_each_size, int speed, int page) {
			this.v_point = v_point;
			this.h_point = h_point;
			this.h_each_size = h_each_size;
			this.speed = speed;
			this.page = page;
		}

		public int getV_point() {
			return v_point;
		}
		public void setV_point(int v_point) {
			this.v_point = v_point;
		}
		public int getH_point() {
			return h_point;
		}
		public void setH_point(int h_point) {
			this.h_point = h_point;
		}
		public float getH_each_size() {
			return h_each_size;
		}
		public void setH_each_size(float h_each_size) {
			this.h_each_size = h_each_size;
		}
		public int getSpeed() {
			return speed;
		}
		public void setSpeed(int speed) {
			this.speed = speed;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		
	}
	
}