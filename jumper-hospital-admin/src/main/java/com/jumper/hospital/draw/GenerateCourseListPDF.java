package com.jumper.hospital.draw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jumper.hospital.entity.SchoolCourseDetail;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;

public class GenerateCourseListPDF {

	/**
	 * 获取中文字体
	 * @param fontSize 字体大小
	 * @param fontStyle 字体样式(正常、加粗)
	 * @return Font
	 */
	private static Font getChineseFont(Integer fontSize, Integer fontStyle){
		try {
			BaseFont chinese = BaseFont.createFont(Consts.PDF_FONT_PATH+"font/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font fontChinese = new Font(chinese, fontSize, fontStyle);
			return fontChinese;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 这是PDF文件Title
	 * @param title
	 * @return
	 */
	private static Paragraph setTitle(String title){
		Paragraph titleParagraph = new Paragraph(title, getChineseFont(14, Font.BOLD));
		titleParagraph.setAlignment(1);
		return titleParagraph;
	}
	/**
	 * 设置二级标题
	 * @param title
	 * @return
	 */
	private static Paragraph setSecendLevelTitle(String title){
		Paragraph secendLevelTitle = new Paragraph(title, getChineseFont(13, Font.NORMAL));
		secendLevelTitle.setAlignment(1);
		secendLevelTitle.setSpacingBefore(10);
		secendLevelTitle.setSpacingAfter(10);
		return secendLevelTitle;
	}
	private static PdfPTable setHR(){
		PdfPTable hTable = new PdfPTable(1);
		hTable.setWidthPercentage(100);
		hTable.setSpacingAfter(5);
		hTable.setSpacingBefore(5);
		PdfPCell hCell;
		
		hCell = new PdfPCell();
		hCell.setBorder(0);
		hCell.setBorderColor(BaseColor.LIGHT_GRAY);
		hCell.setBorderWidthBottom(0.1f);
		hTable.addCell(hCell);
		return hTable;
	}
	/**
	 * 内容页Table嵌套
	 * @return
	 * @throws DocumentException
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private static PdfPTable setContentInfo(SchoolCourseDetail detail, Integer index) throws DocumentException, MalformedURLException, IOException{
		PdfPTable contentTable = new PdfPTable(2);
		contentTable.setSpacingBefore(10);
		contentTable.setWidthPercentage(100);
		float[] widths = {0.1f, 0.1f};
		widths[0] = 70f;
		widths[1] = 30f;
		contentTable.setWidths(widths);
		PdfPCell contentCell;
		
		contentCell = new PdfPCell(setLeftContent(detail, index));
		contentCell.setBorder(0);
		contentTable.addCell(contentCell);
		
		Image image = Image.getInstance(Consts.BASE_FILE_URL+detail.getCourseQrUrl());
		image.scaleAbsolute(75f, 75f);
		
		contentCell = new PdfPCell(image);
		contentCell.setBorder(0);
		contentTable.addCell(contentCell);
		
		return contentTable;
	}
	/**
	 * 课程信息显示
	 * @param detail
	 * @param index
	 * @return
	 */
	private static PdfPTable setLeftContent(SchoolCourseDetail detail, Integer index){
		PdfPTable courseTable = new PdfPTable(1);
		courseTable.setWidthPercentage(100);
		PdfPCell nameCell;
		
		Paragraph name = new Paragraph("课程"+index, getChineseFont(10, Font.NORMAL));
		nameCell = new PdfPCell(name);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setPaddingLeft(100f);
		nameCell.setBorder(0);
		nameCell.setMinimumHeight(20f);
		courseTable.addCell(nameCell);
		
		Paragraph age = new Paragraph("课程名称: "+detail.getCourseName(), getChineseFont(10, Font.NORMAL));
		nameCell = new PdfPCell(age);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setPaddingLeft(100f);
		nameCell.setBorder(0);
		nameCell.setMinimumHeight(20f);
		courseTable.addCell(nameCell);
		
		Paragraph prageny = new Paragraph("授课医师: "+detail.getCourseDoctor(), getChineseFont(10, Font.NORMAL));
		nameCell = new PdfPCell(prageny);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setPaddingLeft(100f);
		nameCell.setBorder(0);
		nameCell.setMinimumHeight(20f);
		courseTable.addCell(nameCell);
		
		Paragraph mobile = new Paragraph("上课时间: "+detail.getStartTime()+" - "+detail.getEndTime(), getChineseFont(10, Font.NORMAL));
		nameCell = new PdfPCell(mobile);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setPaddingLeft(100f);
		nameCell.setBorder(0);
		nameCell.setMinimumHeight(20f);
		courseTable.addCell(nameCell);
		
		return courseTable;
	}
	/**
	 * 二维码图片
	 * @return
	 */
	public static PdfPTable setQRCode(){
		try {
			PdfPTable qrTable = new PdfPTable(1);
			qrTable.setWidthPercentage(80);
			qrTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			Image image = Image.getInstance("http://10.0.3.67:8888/group1/M00/01/58/CgADQ1bFGPqANPQMAAAC0y7nYE8786.png");
			image.scaleAbsolute(20f,20f);
			qrTable.addCell(image);
			return qrTable;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 设置页码
	 * @param document
	 * @param cb 
	 * @param text
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void setFooterPage(Document document, PdfContentByte cb, String text) throws DocumentException, IOException{
		cb.saveState();
        cb.beginText();
        //cb.setFontAndSize(BaseFont.createFont("/data/font/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED), 8);
        cb.setFontAndSize(BaseFont.createFont(Consts.PDF_FONT_PATH+"font/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED), 8);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text, 300, 10, 0);
        cb.endText();
        cb.restoreState();
	}
	
	public static String generatePDF(HttpServletRequest request, String hospitalName, Date date, List<SchoolCourseDetail> list){
		Document document = new Document(PageSize.A4);
		PdfWriter writer = null;
		String filePath = request.getSession().getServletContext().getRealPath("/")+"temp"+File.separator+TimeUtils.convertTime(date, "yyyy-MM-dd")+".pdf";
		
		try {
			FileOutputStream outputStream  = new FileOutputStream(filePath);
			
			writer = PdfWriter.getInstance(document, outputStream);
			document.setMargins(0, 0, 10, 0);
			document.open();
			
			PdfContentByte cb = writer.getDirectContent();
			setFooterPage(document, cb, "第1页");
			
			/** 标题 **/
			Paragraph title = setTitle(hospitalName);
			document.add(title);
			/** 二级标题 **/
			String dateStr = TimeUtils.convertTime(date, "yyyy年MM月dd日");
			String week = TimeUtils.getWeekOfDate(date);
			Paragraph secendLevelTitle = setSecendLevelTitle(dateStr+"课程表("+week+")");
			document.add(secendLevelTitle);
			
			if(ArrayUtils.isNotEmpty(list)){
				for(int i = 0;i < list.size();i++){
					if(i != 0 && i % 5 == 0){
						document.newPage();
						/** 创建页码 **/
						PdfContentByte cbSecond = writer.getDirectContent();
						int page = i / 5 + 1;
						setFooterPage(document, cbSecond, "第"+ page +"页");
					}
					document.add(setContentInfo(list.get(i), i+1));
					if(i != list.size() - 1){
						document.add(setHR());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(document != null){
				document.close();
			}
		}
		return filePath;
	}
	
	public static String generatePDF(String hospitalName, String date){
		Document document = new Document(PageSize.A4);
		PdfWriter writer = null;
		String filePath = "F://test.pdf";
		
		try {
			FileOutputStream outputStream  = new FileOutputStream(filePath);
			
			writer = PdfWriter.getInstance(document, outputStream);
			document.setMargins(0, 0, 10, 0);
			document.open();
			
			PdfContentByte cb = writer.getDirectContent();
			setFooterPage(document, cb, "第1页  共1页");
			
			/** 标题 **/
			Paragraph title = setTitle("西乡人民医院");
			document.add(title);
			/** 二级标题 **/
			Paragraph secendLevelTitle = setSecendLevelTitle("2016年2月19日课程表(星期六)");
			document.add(secendLevelTitle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(document != null){
				document.close();
			}
		}
		System.out.println(filePath);
		return filePath;
	}
	
	public static void main(String[] args) {
		
	}
}
