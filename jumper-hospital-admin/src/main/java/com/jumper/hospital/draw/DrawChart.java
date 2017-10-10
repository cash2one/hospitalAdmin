package com.jumper.hospital.draw;
/**
 * Graphics2D画胎心图
 * @author rent
 * @date 2015-12-10
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class DrawChart {

	/**
	 * 画图总方法
	 * @param graph
	 */
	public void drawMethod(Graphics2D graph, int[] dataArray, Integer graphType, int[] fetalMoveArray, int offset){
		Integer marginTop = 30; 
		switch (graphType) {
			case 1:
				marginTop = 360;
				break;
			case 2:
				marginTop = 30;
				break;
			case 3:	
				marginTop = 230;
				break;
			default:
				break;
		}
		/** X轴 **/
		drawXLine(graph, marginTop);
		/** Y轴 **/
		drawYLine(graph, marginTop);
		/** X坐标 **/
		drawXLabel(graph, graphType, marginTop);
		/** Y坐标 **/
		drawYLabel(graph, marginTop);
		/** 正常区域 **/
		randerNormalArea(graph, marginTop);
		/** 胎心曲线 **/
		randerValue(graph, dataArray, marginTop);
		/** 渲染胎动 **/
		randerFetalMoveData(graph, graphType, marginTop, fetalMoveArray, offset);
		/** 绘画完毕清除graph对象(这里不影响下一次绘画) **/
		graph.finalize();
	}
	
	/**
	 * 画X轴
	 * @param graph
	 */
	public void drawXLine(Graphics2D graph, Integer marginTop){
		for(int i = 0; i < DrawConst.required_vertical; i++){
			int offset_y = (int) (marginTop + Math.ceil(DrawConst.vertical_size * i));
			if(i == 5 || i == 10){
				graph.setStroke(new BasicStroke(2.0f));
			}else{
				graph.setStroke(new BasicStroke(1f));
			}
			graph.setColor(DrawConst.line_color);
			graph.drawLine(DrawConst.margin_left, offset_y, (int)Math.ceil(DrawConst.total_width + DrawConst.margin_left), offset_y);
		}
	}
	
	/**
	 * 画Y轴
	 * @param graph
	 */
	public void drawYLine(Graphics2D graph, Integer marginTop){
		for(int j = 0; j <= DrawConst.required_horizontal; j++){
			int offset_x = (int) (DrawConst.margin_left + Math.round(DrawConst.horizontal_size * j));
			/** if(j != 0 && j % 3 == 0 && j != DrawConst.required_horizontal){
				graph.setStroke(new BasicStroke(1.0f));
				graph.drawLine(offset_x, bbbb + 1, offset_x, (int) (bbbb + Math.ceil(DrawConst.vertical_size * (DrawConst.required_vertical - 1)) - 1));
			}else{
				graph.setStroke(new BasicStroke(0.4f));
				graph.drawLine(offset_x, bbbb, offset_x, (int) (bbbb + Math.ceil(DrawConst.vertical_size * (DrawConst.required_vertical - 1))));
			} **/
			graph.setStroke(new BasicStroke(1.0f));
			graph.drawLine(offset_x, marginTop + 1, offset_x, (int) (marginTop + Math.ceil(DrawConst.vertical_size * (DrawConst.required_vertical - 1)) - 1));
			graph.setColor(DrawConst.line_color);
		}
	}
	
	/**
	 * X轴坐标
	 * @param graph
	 */
	public void drawXLabel(Graphics2D graph, Integer graphType, Integer marginTop){
		float x_label_top = marginTop + DrawConst.vertical_size * DrawConst.required_vertical + 3;
		for(int o = 0; o <= DrawConst.required_horizontal; o++){
			if(o % 3 == 0){
				float offset_x = DrawConst.margin_left + DrawConst.horizontal_size * o - 1.5f;
				graph.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 8));
				graph.setColor(Color.BLACK);
				int offset = o + DrawConst.required_horizontal * (graphType - 1);
				graph.drawString(String.valueOf(offset / 3)+"´", offset_x, x_label_top);
			}
		}
	}
	
	/**
	 * Y轴坐标
	 * @param graph
	 */
	public void drawYLabel(Graphics2D graph, Integer marginTop){
		for(int n = 1; n <= 2; n++){
			for(int k = 0; k < DrawConst.required_vertical; k++){
				if(k % 3 == 0){
					int y_label = DrawConst.y_label_count - 10 * k;
					graph.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 7));
					graph.setColor(Color.BLACK);
					graph.drawString(String.valueOf(y_label),DrawConst.margin_left + DrawConst.horizontal_size * n * 9 - 5 , marginTop + k * DrawConst.vertical_size + 3.6f);
				}
			}
		}
	}
	
	/**
	 * 渲染正常区域背景
	 * @param graph
	 */
	public void randerNormalArea(Graphics2D graph, Integer marginTop){
		int x = DrawConst.margin_left;
		int y = (int)(marginTop + DrawConst.vertical_size * 5);
		int width = (int)(DrawConst.horizontal_size * DrawConst.required_horizontal + 1);
		int height = (int)(DrawConst.vertical_size * 5);
		graph.setColor(DrawConst.normal_color);
		graph.fillRect(x, y , width, height);
	}
	
	/**
	 * 渲染胎心曲线值
	 * @param graph
	 */
	public void randerValue(Graphics2D graph, int[] dataArray, Integer marginTop){
		if(dataArray != null && dataArray.length > 0){
			for(int v = 0; v < dataArray.length; v++){
				if(v < dataArray.length - 1){
					int abs_value = Math.abs(dataArray[v + 1] - dataArray[v]);
					if(abs_value > 30 || dataArray[v] == 0){
						continue;
					}
					int x1 = DrawConst.margin_left + Math.round(DrawConst.total_width / 1080 * v);
					int y1 = marginTop + Math.round(210 - dataArray[v]);
					int x2 = DrawConst.margin_left + Math.round(DrawConst.total_width / 1080 * (v + 1));
					int y2 = marginTop + Math.round(210 - dataArray[v + 1]);
					graph.setStroke(new BasicStroke(0.4f));
					graph.setColor(Color.BLACK);
					graph.drawLine(x1 , y1, x2, y2);
				}
			}
		}
	}
	
	/**
	 * 渲染胎动值
	 * @param graph
	 */
	public void randerFetalMoveData(Graphics2D graph, Integer graphType, Integer marginTop, int[] fetalMoveArray, int offset){
		int offTime = Math.round(offset / 2);
		if(fetalMoveArray != null && fetalMoveArray.length > 0){
			for(int v = 0; v < fetalMoveArray.length; v++){
				int x = 0;
				switch (graphType) {
				case 1:
					if(fetalMoveArray[v] <= 540 + offTime){
						x = DrawConst.margin_left + Math.round(DrawConst.total_width / 540 * (fetalMoveArray[v] - Math.round(offset / 2)));
					}
					break;
				case 2:
					if(fetalMoveArray[v] > (540 + offTime) && fetalMoveArray[v] <= (1080 + offTime)){
						x = DrawConst.margin_left + Math.round(DrawConst.total_width / 540 * (fetalMoveArray[v] - 540 - Math.round(offset / 2)));
					}
					break;
				case 3:
					if(fetalMoveArray[v] > (1080 + offTime) && fetalMoveArray[v] <= (1200 + offTime)){
						x = DrawConst.margin_left + Math.round(DrawConst.total_width / 540 * (fetalMoveArray[v] - 1080 - Math.round(offset / 2)));
					}
					break;
				default:
					break;
				}
				
				if(x >= DrawConst.margin_left){
					int y = marginTop + 142;
					graph.setColor(DrawConst.black_color);
					graph.drawRect(x, y, 2, 5);
					graph.fillRect(x, y, 2, 5);
				}
			}
		}
	}
}
