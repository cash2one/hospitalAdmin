package com.jumper.hospital.utils.mointorData;

import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Arrays;

import com.jumper.hospital.utils.Const;
import com.jumper.hospital.vo.monitorData.UserPrintVO;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class GenerateSFYMonitorPDF {

	/** 胎心数据 **/
	protected  int[] dataArray = {};
	/** 宫缩数据 **/
	protected  int[] uterusArray ={};
	/** 胎动数据 **/
	protected  int[] fetalMoveArray ={};
	public GenerateSFYMonitorPDF(){
	}
	public GenerateSFYMonitorPDF(int[] dataArray, int[] uterusArray,int[] fetalMoveArray){
		this.dataArray = dataArray;
		this.uterusArray = uterusArray;
		this.fetalMoveArray = fetalMoveArray;
	}
	/**
	 * 获取中文字体
	 * @param fontSize 字体大小
	 * @param fontStyle 字体样式(正常、加粗)
	 * @return Font
	 */
	private static Font getChineseFont(Integer fontSize, Integer fontStyle){
		try {
			BaseFont chinese = BaseFont.createFont(Const.MONITOR_PDF_FONT_PATH+"font/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font fontChinese = new Font(chinese, fontSize, fontStyle);
			return fontChinese;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 此处可将用户信息传入，作为动态生成用户信息Table，本范例暂写死
	 * @return PdfPTable
	 * @throws DocumentException 
	 */
	@SuppressWarnings("static-access")
	private static PdfPTable renderUserInfoTable(UserPrintVO print) throws DocumentException{
		PdfPTable userTable = new PdfPTable(3);
		userTable.setWidthPercentage(96);
		float[] resultWidth = {30f, 30f, 40f};
		userTable.setWidths(resultWidth);
		userTable.setHorizontalAlignment(userTable.ALIGN_CENTER);
		userTable.setSpacingBefore(8f);
		PdfPCell cell;
		
//		Paragraph archives = new Paragraph("保健号: "+print.getHealthNum(), getChineseFont(10, Font.NORMAL));
//		cell = new PdfPCell(archives);
//		cell.setBorder(0);
//		userTable.addCell(cell);
		
		Paragraph name = new Paragraph("姓  名: "+print.getName(), getChineseFont(10, Font.NORMAL));
		cell = new PdfPCell(name);
		cell.setBorder(0);
		userTable.addCell(cell);
		
		Paragraph age = new Paragraph("年  龄: "+print.getAge(), getChineseFont(10, Font.NORMAL));
		cell = new PdfPCell(age);
		cell.setBorder(0);
		userTable.addCell(cell);
		
		Paragraph time = new Paragraph("监护时间: "+print.getMonitorTime(), getChineseFont(10, Font.NORMAL));
		cell = new PdfPCell(time);
		cell.setBorder(0);
		userTable.addCell(cell);
		
		Paragraph weeks = new Paragraph("孕  周: "+print.getPregnant(), getChineseFont(10, Font.NORMAL));
		cell = new PdfPCell(weeks);
		cell.setBorder(0);
		cell.setColspan(3);
		userTable.addCell(cell);
		
		return userTable;
	}
	
	/**
	 * krebs评分表与评分
	 * @return
	 * @throws DocumentException 
	 */
	private static PdfPTable krebsTable() throws DocumentException{
		PdfPTable krebs = new PdfPTable(2);
		krebs.setWidthPercentage(96);
		float[] widths = {55f, 45f};
		krebs.setWidths(widths);
		PdfPCell cell;
		
		cell = new PdfPCell(krebsSourceSheet());
		cell.setBorder(0);
		krebs.addCell(cell);
		
		cell = new PdfPCell(krebsJudgeSource());
		cell.setBorder(0);
		cell.setPaddingLeft(20f);
		krebs.addCell(cell);
		
		return krebs;
	}
	
	private static PdfPTable krebsSourceSheet() throws DocumentException{
		PdfPTable sheetTable = new PdfPTable(7);
		float[] width = {7f, 20f, 13f, 12f, 19f, 19f, 10f};
		sheetTable.setWidths(width);
		sheetTable.setSpacingBefore(6);
		sheetTable.setWidthPercentage(100);
		PdfPCell cell;
		
		Paragraph title = new Paragraph("Kreb‘S评分表", getChineseFont(10, Font.NORMAL));
		cell = new PdfPCell(title);
		cell.setColspan(7);
		cell.setBorder(0);
		sheetTable.addCell(cell);
		
		sheetTable.addCell(krebsBorderCell("评分"));
		sheetTable.addCell(krebsBorderCell("胎心率基线"));
		sheetTable.addCell(krebsBorderCell("变异振幅"));
		sheetTable.addCell(krebsBorderCell("变异频率"));
		sheetTable.addCell(krebsBorderCell("加速(次/30分钟)"));
		sheetTable.addCell(krebsBorderCell("减速(次/30分钟)"));
		sheetTable.addCell(krebsBorderCell("胎动次数"));
		
		sheetTable.addCell(krebsBorderCell("0分"));
		sheetTable.addCell(krebsBorderCell("<100或 >180"));
		sheetTable.addCell(krebsBorderCell("<5"));
		sheetTable.addCell(krebsBorderCell("<3"));
		sheetTable.addCell(krebsBorderCell("0"));
		sheetTable.addCell(krebsBorderCell(">=2"));
		sheetTable.addCell(krebsBorderCell("0"));
		
		sheetTable.addCell(krebsBorderCell("1分"));
		sheetTable.addCell(krebsBorderCell("100-119,161-180"));
		sheetTable.addCell(krebsBorderCell("5-9,>25"));
		sheetTable.addCell(krebsBorderCell("3-6"));
		sheetTable.addCell(krebsBorderCell("1-4"));
		sheetTable.addCell(krebsBorderCell("1"));
		sheetTable.addCell(krebsBorderCell("1-4"));
		
		sheetTable.addCell(krebsBorderCell("2分"));
		sheetTable.addCell(krebsBorderCell("120-160"));
		sheetTable.addCell(krebsBorderCell("10-25"));
		sheetTable.addCell(krebsBorderCell(">6"));
		sheetTable.addCell(krebsBorderCell(">=5"));
		sheetTable.addCell(krebsBorderCell("无或早减"));
		sheetTable.addCell(krebsBorderCell(">=5"));
		
		return sheetTable;
	}
	
	@SuppressWarnings("static-access")
	private static PdfPCell krebsBorderCell(String desc){
		Paragraph paragraph = new Paragraph(desc, getChineseFont(9, Font.NORMAL));
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setUseAscender(true);
		cell.setHorizontalAlignment(cell.ALIGN_CENTER);
		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		cell.setMinimumHeight(15f);
		return cell;
	}
	
	private static PdfPTable krebsJudgeSource() throws DocumentException{
		PdfPTable judgeTable = new PdfPTable(6);
		float[] width = {15f, 20f, 15f, 15f, 15f, 20f};
		judgeTable.setWidths(width);
		judgeTable.setSpacingBefore(6);
		judgeTable.setWidthPercentage(100);
		PdfPCell cell;
		
		Paragraph title = new Paragraph("结果：Kreb‘S评分        分", getChineseFont(10, Font.NORMAL));
		cell = new PdfPCell(title);
		cell.setColspan(6);
		cell.setBorder(0);
		cell.setMinimumHeight(30f);
		cell.setPaddingTop(15f);
		judgeTable.addCell(cell);
		
		judgeTable.addCell(krebsJudgeCell("NST", 1));
		judgeTable.addCell(krebsJudgeCell("有反应", 1));
		judgeTable.addCell(krebsJudgeCell("无反应", 1));
		judgeTable.addCell(krebsJudgeCell("正弦曲线", 2));
		judgeTable.addCell(krebsJudgeCell("不满意", 1));
		
		judgeTable.addCell(krebsJudgeCell("OCT", 1));
		judgeTable.addCell(krebsJudgeCell("OST或NS-CST", 1));
		judgeTable.addCell(krebsJudgeCell("阳性", 1));
		judgeTable.addCell(krebsJudgeCell("阴性", 1));
		judgeTable.addCell(krebsJudgeCell("可疑", 1));
		judgeTable.addCell(krebsJudgeCell("不满意", 1));
		
		return judgeTable;
	}
	
	@SuppressWarnings("static-access")
	private static PdfPCell krebsJudgeCell(String desc, Integer colspan){
		Paragraph paragraph = new Paragraph(desc, getChineseFont(9, Font.NORMAL));
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setUseAscender(true);
		cell.setBorder(0);
		cell.setHorizontalAlignment(cell.ALIGN_LEFT);
		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		cell.setMinimumHeight(20f);
		cell.setColspan(colspan);
		return cell;
	}
	
	private static PdfPTable dateAndSignTable(Integer paperSpeed, Integer totalPage) throws DocumentException{
		PdfPTable signTable = new PdfPTable(2);
		float[] width = {45f, 55f};
		signTable.setWidths(width);
		signTable.setSpacingBefore(15);
		signTable.setWidthPercentage(96);
		PdfPCell cell;
		
		Paragraph sign = null;
		switch (paperSpeed) {
			case 1:
				sign = new Paragraph("走纸速度：1厘米/分钟    第 1 页  共 "+totalPage+" 页  签名：                              ", getChineseFont(10, Font.NORMAL));
				break;
			case 2:
				sign = new Paragraph("走纸速度：2厘米/分钟    第 1 页  共 "+totalPage+" 页  签名：                              ", getChineseFont(10, Font.NORMAL));
				break;
			case 3:
				sign = new Paragraph("走纸速度：3厘米/分钟    第 1 页  共 "+totalPage+" 页  签名：                              ", getChineseFont(10, Font.NORMAL));
				break;
			case 20:
				sign = new Paragraph("走纸速度：2厘米/分钟  比例65%    第 1 页  共 "+totalPage+" 页  签名：                              ", getChineseFont(10, Font.NORMAL));
				break;
			case 30:
				sign = new Paragraph("走纸速度：1厘米/分钟  比例90%    第 1 页  共 "+totalPage+" 页  签名：                              ", getChineseFont(10, Font.NORMAL));
				break;
			default:
				break;
		}
		cell = new PdfPCell(sign);
		cell.setBorder(0);
		signTable.addCell(cell);
		
		Paragraph date = new Paragraph("日期：", getChineseFont(10, Font.NORMAL));
		cell = new PdfPCell(date);
		cell.setBorder(0);
		signTable.addCell(cell);
		
		return signTable;
	}
	
	/**
	 * 生成PDF文件方法
	 * PDF文件中医院及kerbs信息都可不动，因PDF有两页，所以要调用画图接口SFYPdfDrawInterface两次
	 * 第一次调用margin_top值传入210高度，第二次margin_top传入30的高度
	 * page参数说明，page 参数用于标明胎心及宫缩为PDF第几页内容，主要用于显示不同数据内容
	 * 此处未作生成的报告上传到文件服务器，未讲临时报告文件删除
	 * @param start 胎心数据偏移量起始位置(偏移量代表偏移了多少数据点) - 报告生成，胎心数据及起始点的校验请自己完成
	 * @param end 胎心数据偏移量结束位置
	 * @param paperSpeed 走纸速度(1厘米/分，2厘米/分，3厘米/分，20分钟一页，30分钟一页) 这里取的类型是前面的1,2,3,20,30
	 * @param testTime 测试开始时间
	 * @return
	 */
	public String generate(Integer start, Integer end, Integer paperSpeed, Timestamp testTime,UserPrintVO print){
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter writer = null;
		String filePath = Const.MONITOR_REPORT_FILE_PATH;
		
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			/** 设置边距，顺序：左、右、上、下 **/
			document.setMargins(10, 10, 10, 10);
			document.open();
			
			/** 医院名标题 **/
			Paragraph hospitalNameTitle = new Paragraph(Const.HOSPITAL_NAME, getChineseFont(15, Font.BOLD));
			hospitalNameTitle.setAlignment(1);/** 设置居中显示 **/
			document.add(hospitalNameTitle);
			
			/** 文档类型副标题 **/
			Paragraph subTitle = new Paragraph(Const.REPORT_TITLE, getChineseFont(12, Font.BOLD));
			subTitle.setAlignment(1);
			subTitle.setLeading(0);
			subTitle.setSpacingBefore(16f);
			document.add(subTitle);
			
			/** 用户信息的Table **/
			PdfPTable userInfoTable = renderUserInfoTable(print);
			document.add(userInfoTable);
			
			/** Kreb‘S评分表与评分 **/
			PdfPTable krebsTable = krebsTable();
			document.add(krebsTable);
			
			/** 地址电话信息 **/
			PdfContentByte cb = writer.getDirectContent();
			cb.saveState();
	        cb.beginText();
	        cb.setFontAndSize(BaseFont.createFont(Const.MONITOR_PDF_FONT_PATH+"font/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED), 9);
	        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "地址：                    电话：", 23, 30, 0);
	        cb.endText();
	        cb.restoreState();
			
			/**
			 * 此处开始计算页数及画图数据
			 */
			Integer totalPoint = 0;
			switch (paperSpeed) {
				case 1: totalPoint = 3240; break;
				case 2: totalPoint = 1620; break;
				case 3: totalPoint = 1080; break;
				case 20: totalPoint = 2400; break;
				case 30: totalPoint = 3600; break;
				default: break;
			}
			
			int[] drawData = Arrays.copyOfRange(dataArray, start, end);
			int totalPage = drawData.length % totalPoint == 0 ? drawData.length / totalPoint : drawData.length / totalPoint + 1;
			
			/** 签名日期和时间 **/
			PdfPTable signAndDate = dateAndSignTable(paperSpeed, totalPage);
			document.add(signAndDate);
			
			if(totalPage > 0){
				for(int i = 1; i <= totalPage; i++){
					int marginTop = i == 1 ? 210 : 30;
					if(dataArray.length<(totalPoint * (i - 1))){
						break;
					}
					int[] data = Arrays.copyOfRange(dataArray, start + totalPoint * (i - 1), end);
					int[] uterusData = {};
					int[] fetalMoveData = {};
					if(null != uterusArray && uterusArray.length > 0){
						uterusData = Arrays.copyOfRange(uterusArray, start + totalPoint * (i - 1), end);
					}
					if(null != fetalMoveArray && fetalMoveArray.length > 0){
						int copyStart = 0 + totalPoint * (i - 1);
						if(copyStart > fetalMoveArray.length){
							copyStart = fetalMoveArray.length;
						}
						fetalMoveData = Arrays.copyOfRange(fetalMoveArray,copyStart, fetalMoveArray.length);
					}
					
					if(i != 1){
						document.newPage();
					}
					SFYPdfDrawInterface draw = new SFYPdfDrawInterface(marginTop, i, data, uterusData,fetalMoveData, paperSpeed, start, testTime);
					VerticalPositionMark vm = new VerticalPositionMark(draw, 0);
					document.add(vm);
				}
			}
	        
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(document != null){
				document.newPage();
				document.close();
			}
			if(writer != null){
				writer.close();
			}
		}
		return filePath;
	}
	
	public static void main(String[] args) {
		GenerateSFYMonitorPDF pdf = new GenerateSFYMonitorPDF(null,null,null);
		/** 2厘米/分的走纸速度 **/
		pdf.generate(0, 3727, 3, TimeUtils.getCurrentTime(),new UserPrintVO());
	}
}
