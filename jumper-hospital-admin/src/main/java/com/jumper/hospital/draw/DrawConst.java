package com.jumper.hospital.draw;

import java.awt.Color;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.jumper.hospital.utils.Consts;

/**
 * Graphics2D图标配置
 * @author rent
 * @date 2015-12-09
 */
public class DrawConst {

	/** 总宽度，9分钟的曲线在72DPI环境下宽度为765.46 **/
	public static float total_width = 796.5f;
	/** 垂直每一格所代表的像素点**/
	public static float vertical_size = 10f;
	/** 水平每一格所代表的像素点，一格20秒(一厘米) **/
	public static float horizontal_size = 29.5f;
	/** 垂直多少个Cell **/
	public static int required_vertical = 17;
	/** 水平多少个Cell **/
	public static int required_horizontal = 27;
	/** 左边距38个点 **/
	public static int margin_left = 23;
	/** 上边距100个点 **/
	public static int margin_top = 360;
	
	/** 线条颜色 **/
	public static Color line_color = new Color(241, 137, 168);
	/** 正常区域颜色 **/
	public static Color normal_color = new Color(67, 205, 128, 50);
	/** 黑色 **/
	public static Color black_color = new Color(0, 0, 0, 255);
	
	/** Y轴坐标值总量 **/
	public static int y_label_count = 210;
	
	/**
	 * 获取中文字体
	 * @param fontSize 字体大小
	 * @param fontStyle 字体样式(正常、加粗)
	 * @return Font
	 */
	public static Font getChineseFont(float fontSize, Integer fontStyle){
		try {
			BaseFont chinese = BaseFont.createFont(Consts.PDF_FONT_PATH+"font/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font fontChinese = new Font(chinese, fontSize, fontStyle);
			return fontChinese;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
