package com.jumper.hospital.draw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.jumper.hospital.entity.MonitorOrderConsumer;
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VoRemoteData;

public class GenerateMonitorPDF {

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
	private static Paragraph setReportTitle(String title){
		Paragraph reportTitle = new Paragraph(title, getChineseFont(14, Font.BOLD));
		/** 设置居中显示 **/
		reportTitle.setAlignment(1);
		/** 设置下边距20 **/
		return reportTitle;
	}
	
	private static PdfPTable setHospitalInfo(String hospitalName){
		PdfPTable hTable = new PdfPTable(1);
		hTable.setWidthPercentage(100);
		PdfPCell hCell;
		
		Paragraph hospitalInfo = new Paragraph(hospitalName, getChineseFont(10, Font.NORMAL));
		hCell = new PdfPCell(hospitalInfo);
		hCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		hCell.setBorder(0);
		hCell.setBorderColor(BaseColor.GRAY);
		hCell.setBorderWidthBottom(0.5f);
		hCell.setMinimumHeight(20f);
		hTable.addCell(hCell);
		return hTable;
	}
	
	/**
	 * 内容页Table嵌套
	 * @return
	 * @throws DocumentException
	 */
	private static PdfPTable setContentInfo(VoRemoteData data, String reason, String docName) throws DocumentException{
		PdfPTable contentTable = new PdfPTable(2);
		contentTable.setSpacingBefore(10);
		contentTable.setWidthPercentage(100);
		float[] widths = {0.1f, 0.1f};
		widths[0] = 40f;
		widths[1] = 60f;
		contentTable.setWidths(widths);
		PdfPCell contentCell;
		
		contentCell = new PdfPCell(setUserInfo(data, reason, docName));
		contentCell.setBorder(0);
		contentTable.addCell(contentCell);
		
		contentCell = new PdfPCell(setData());
		contentCell.setBorder(0);
		contentTable.addCell(contentCell);
		
		return contentTable;
	}
	
	private static PdfPTable setUserInfo(VoRemoteData data, String reason, String docName){
		PdfPTable userTable = new PdfPTable(2);
		userTable.setWidthPercentage(100);
		PdfPCell nameCell;
		
		Paragraph name = new Paragraph("姓      名:  "+data.getUserName(), getChineseFont(10, Font.NORMAL));
		userTable.addCell(infoCellStyle(name, 1));
		
		Paragraph age = new Paragraph("年      龄:  "+data.getUserAge()+"岁", getChineseFont(10, Font.NORMAL));
		userTable.addCell(infoCellStyle(age, 1));
		
		/***************************************************/
		Paragraph prageny = new Paragraph("孕      周:  "+data.getPreganyWeek(), getChineseFont(10, Font.NORMAL));
		userTable.addCell(infoCellStyle(prageny, 1));
		
		Paragraph mobile = new Paragraph("手机号码:  "+data.getMobile(), getChineseFont(10, Font.NORMAL));
		userTable.addCell(infoCellStyle(mobile, 1));
		
		/***************************************************/
		Paragraph start = new Paragraph("监护开始时间:  "+data.getHeart().getTime(), getChineseFont(10, Font.NORMAL));
		userTable.addCell(infoCellStyle(start, 2));
		
		/***************************************************/
		Paragraph end = new Paragraph("监护结束时间:  "+data.getHeart().getEndTime(), getChineseFont(10, Font.NORMAL));
		userTable.addCell(infoCellStyle(end, 2));
		
		/***************************************************/
		Paragraph time = new Paragraph("监护时长:   "+data.getHeart().getMinute(), getChineseFont(10, Font.NORMAL));
		userTable.addCell(infoCellStyle(time, 2));
		
		/***************************************************/
		Paragraph remark = new Paragraph("备      注:", getChineseFont(10, Font.NORMAL));
		nameCell = new PdfPCell();
		nameCell.addElement(remark);
		if(StringUtils.isNotEmpty(reason)){
			Paragraph reasonPara = new Paragraph(reason, getChineseFont(10, Font.NORMAL));
			reasonPara.setIndentationLeft(38);
			nameCell.addElement(reasonPara);
		}
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setBorder(0);
		nameCell.setMinimumHeight(60f);
		nameCell.setColspan(2);
		userTable.addCell(nameCell);
		
		/***************************************************/
		Paragraph docInfo = new Paragraph("监护医生 : "+docName+"  日期 : "+TimeUtils.getCurrentTime(Consts.FORMAT_TIME_THREE), getChineseFont(10, Font.NORMAL));
		docInfo.setIndentationLeft(80);
		nameCell = new PdfPCell();
		nameCell.addElement(docInfo);
		nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameCell.setBorder(0);
		nameCell.setPaddingTop(10);
		nameCell.setMinimumHeight(30f);
		nameCell.setColspan(2);
		userTable.addCell(nameCell);
		
		/***************************************************/
		Paragraph desc = new Paragraph("说明：1.本报告仅对监测样本负责，仅供临床参考，不做诊断证明之用", getChineseFont(10, Font.NORMAL));
		Paragraph desc1 = new Paragraph("2.请依据医生建议按时复诊或孕妇自感异常随时复诊", getChineseFont(10, Font.NORMAL));
		desc1.setIndentationLeft(30);
		nameCell = new PdfPCell();
		nameCell.addElement(desc);
		nameCell.addElement(desc1);
		nameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		nameCell.setBorder(0);
		nameCell.setMinimumHeight(20f);
		nameCell.setColspan(2);
		userTable.addCell(nameCell);
		
		return userTable;
	}
	
	private static PdfPCell infoCellStyle(Paragraph paragraph, Integer colspan){
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(0);
		cell.setMinimumHeight(20f);
		cell.setColspan(colspan);
		return cell;
	}
	
	private static PdfPTable setData() throws DocumentException{
		PdfPTable dataTable = new PdfPTable(5);
		float[] resultWidth = {9f, 9f, 20f, 34f, 28f};
		dataTable.setWidths(resultWidth);
		dataTable.setWidthPercentage(100);
		PdfPCell dataCell;
		
		Paragraph menu1 = new Paragraph("胎心基线", getChineseFont(9, Font.NORMAL));
		dataCell = infoCellStyle(menu1);
		dataCell.setColspan(2);
		dataTable.addCell(dataCell);
		
		Paragraph menu2_1 = new Paragraph("【  】 正常", getChineseFont(9, Font.NORMAL));
		Paragraph menu2_2 = new Paragraph("(110~160bpm)", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(menu2_1, menu2_2));
		
		Paragraph menu3_1 = new Paragraph("【  】 胎心过速(>160bpm)", getChineseFont(9, Font.NORMAL));
		Paragraph menu3_2 = new Paragraph("【  】 胎心过缓(<110bpm)", getChineseFont(9, Font.NORMAL));
		Paragraph menu3_3 = new Paragraph("  不伴基线变异缺失", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(menu3_1, menu3_2, menu3_3));
		
		Paragraph menu4_1 = new Paragraph("【  】 胎心过缓(<110bpm)", getChineseFont(9, Font.NORMAL));
		Paragraph menu4_2 = new Paragraph("  伴基线变异缺失", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(menu4_1, menu4_2));
		
		/************************************************************/
		
		Paragraph ji = new Paragraph("基线变异", getChineseFont(9, Font.NORMAL));
		dataCell = infoCellStyle(ji);
		dataCell.setColspan(2);
		dataTable.addCell(dataCell);
		
		Paragraph ji2_1 = new Paragraph("【  】 中度", getChineseFont(9, Font.NORMAL));
		Paragraph ji2_2 = new Paragraph("(6~25bpm)", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(ji2_1, ji2_2));
		
		Paragraph ji3_1 = new Paragraph("【  】 变异缺失(0bpm)", getChineseFont(9, Font.NORMAL));
		Paragraph ji3_2 = new Paragraph("  不伴反复出现的晚期减速", getChineseFont(9, Font.NORMAL));
		Paragraph ji3_3 = new Paragraph("【  】 微小变异(<5bpm)", getChineseFont(9, Font.NORMAL));
		Paragraph ji3_4 = new Paragraph("【  】 显著变异(>25bpm)", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(ji3_1, ji3_2, ji3_3, ji3_4));
		
		Paragraph ji4_1 = new Paragraph("【  】 变异缺失(0bpm)", getChineseFont(9, Font.NORMAL));
		Paragraph ji4_2 = new Paragraph("  伴胎心过缓", getChineseFont(9, Font.NORMAL));
		Paragraph ji4_3 = new Paragraph("  或反复出现变异减速", getChineseFont(9, Font.NORMAL));
		Paragraph ji4_4 = new Paragraph("  或晚期减速", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(ji4_1, ji4_2, ji4_3, ji4_4));
		
		/***********************************************************************/
		Paragraph speed1 = new Paragraph("胎心加速", getChineseFont(9, Font.NORMAL));
		dataCell = infoCellStyle(speed1);
		dataCell.setColspan(2);
		dataCell.setMinimumHeight(18f);
		dataTable.addCell(dataCell);
		
		Paragraph speed2 = new Paragraph("【  】 有或无 【  】 ", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(speed2));
		
		Paragraph speed3 = new Paragraph("【  】 刺激胎儿后仍缺失", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(speed3));
		
		Paragraph speed4 = new Paragraph("【  】 无", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(speed4));
		
		/***********************************************************************/
		Paragraph nspeed1 = new Paragraph("减速", getChineseFont(9, Font.NORMAL));
		dataCell = infoCellStyle(nspeed1);
		dataCell.setRowspan(3);
		dataTable.addCell(dataCell);
		
		Paragraph nspeed1_1 = new Paragraph("早期减速", getChineseFont(9, Font.NORMAL));
		dataCell = infoCellStyle(nspeed1_1);
		dataCell.setMinimumHeight(20f);
		dataTable.addCell(dataCell);
		
		Paragraph nspeed1_2 = new Paragraph("【  】 有或无 【  】 ", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(nspeed1_2));
		
		Paragraph speed1_3 = new Paragraph("【  】 无", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(speed1_3));
		
		Paragraph speed1_4 = new Paragraph("【  】 无", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(speed1_4));
		/*****************/
		Paragraph nspeed2_1 = new Paragraph("变异减速", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(nspeed2_1));
		
		Paragraph nspeed2_2 = new Paragraph("【  】 无 ", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(nspeed2_2));
		
		Paragraph nspeed2_3_1 = new Paragraph("【  】 反复出现伴微小变异或中度变异", getChineseFont(9, Font.NORMAL));
		Paragraph nspeed2_3_2 = new Paragraph("【  】 延长减速(>2min但<10min)", getChineseFont(9, Font.NORMAL));
		Paragraph nspeed2_3_3 = new Paragraph("【  】 非特异性变异减速", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(nspeed2_3_1, nspeed2_3_2, nspeed2_3_3));
		
		Paragraph nspeed2_4 = new Paragraph("【  】 反复出现伴基线变异缺失", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(nspeed2_4));
		/********************/
		Paragraph lspeed3_1 = new Paragraph("晚期减速", getChineseFont(9, Font.NORMAL));
		dataCell = infoCellStyle(lspeed3_1);
		dataCell.setMinimumHeight(20f);
		dataTable.addCell(dataCell);
		
		Paragraph lspeed3_2 = new Paragraph("【  】 无 ", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(lspeed3_2));
		
		Paragraph lspeed3_3 = new Paragraph("【  】 反复出现伴基线中度变异", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(lspeed3_3));
		
		Paragraph lspeed3_4 = new Paragraph("【  】 反复出现伴基线变异缺失", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(lspeed3_4));
		
		/*****************************************************************/
		Paragraph sin1 = new Paragraph("正弦曲线", getChineseFont(9, Font.NORMAL));
		dataCell = infoCellStyle(sin1);
		dataCell.setColspan(2);
		dataCell.setMinimumHeight(18f);
		dataTable.addCell(dataCell);
		
		Paragraph sin2 = new Paragraph("【  】 无", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(sin2));
		
		Paragraph sin3 = new Paragraph("【  】 无", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(sin3));
		
		Paragraph sin4 = new Paragraph("【  】 有", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(sin4));
		
		/*****************************************************************/
		Paragraph result1 = new Paragraph("胎心监护结果", getChineseFont(9, Font.NORMAL));
		dataCell = infoCellStyle(result1);
		dataCell.setColspan(2);
		dataTable.addCell(dataCell);
		
		Paragraph result2 = new Paragraph("【  】 正常(I级)", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(result2));
		
		Paragraph result3 = new Paragraph("【  】 可疑(II级)", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(result3));
		
		Paragraph result4 = new Paragraph("【  】 异常(III级)", getChineseFont(9, Font.NORMAL));
		dataTable.addCell(infoCellStyle(result4));
		
		return dataTable;
	}
	
	@SuppressWarnings("static-access")
	private static PdfPCell infoCellStyle(Paragraph... paragraphs){
		PdfPCell cell = new PdfPCell();
		for(Paragraph p : paragraphs){
			cell.addElement(p);
		}
		cell.setUseAscender(true);
		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		return cell;
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
        cb.setFontAndSize(BaseFont.createFont(Consts.PDF_FONT_PATH+"font/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED), 8);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text, 420, 15, 0);
        cb.endText();
        cb.restoreState();
	}
	
	/**
	 * 绑定胎心数据
	 * @param dataStr
	 */
	public static int[] bindHeartData(String dataStr, Integer offset){
		String[] heartData = null;
		if(StringUtils.isNotEmpty(dataStr) && dataStr.length() > 3){
			int end = dataStr.length() - 3;
			String str = dataStr.substring(1, end);
			heartData = str.split(",");
		}
		int[] intArray = null;
		if(heartData != null && heartData.length > 0){
			intArray = new int[heartData.length];
			for(int i = 0; i < heartData.length; i++){
				intArray[i]=Integer.parseInt(heartData[i]);
			}
			/*if(offset != null && offset < intArray.length){
				if(intArray.length >= 2401+offset){
					intArray = Arrays.copyOfRange(intArray, offset, 2401+offset);
				}else{
					intArray = Arrays.copyOfRange(intArray, offset, intArray.length);
				}
			}else{
				intArray = null;
			}*/
		}
		return intArray;
	}
	
	/**
	 * 绑定胎动数据
	 * @param fetalMoveStr
	 */
	public static int[] bindFetalMoveData(String fetalMoveStr){
		String[] fetalMoveData = null;
		if(StringUtils.isNotEmpty(fetalMoveStr) && fetalMoveStr.length() > 0){
			fetalMoveData = fetalMoveStr.split(",");
		}else{
			/** 胎心数据为空，置空胎心数组 **/
			//DrawConst.fetal_move_array = null;
			return null;
		}
		int[] fetalMoveArray = null;
		if(fetalMoveData != null && fetalMoveData.length > 0){
			fetalMoveArray = new int[fetalMoveData.length];
			for(int i = 0; i < fetalMoveData.length; i++){
				fetalMoveArray[i]=Integer.parseInt(fetalMoveData[i]);
			}
			//DrawConst.fetal_move_array = fetalMoveArray;
		}
		return fetalMoveArray;
	}
	
	public static String generatePDF(HttpServletRequest request, VoRemoteData data, MonitorOrderConsumer consumer, String reason, String doctorName, Integer offset,
			Integer start, Integer end){
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter writer = null;
		String filePath = request.getSession().getServletContext().getRealPath("/")+"temp"+File.separator+TimeUtils.randomOrderId()+".pdf";
		//String filePath = "F://ccc.pdf";
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.setMargins(10, 10, 10, 10);
			document.open();
			
			/** 处理胎心数据 **/
			String dataStr = data.getHeart().getData();
			String fetalMoveStr = data.getHeart().getFetalMoveData();
			int[] array = bindHeartData(dataStr, offset);
			int[] intArray = Arrays.copyOfRange(array, start, end);
			int[] fetalMoveArray = bindFetalMoveData(fetalMoveStr);
			
			offset = offset == null ? 0 : offset;
			
			PdfContentByte cb = writer.getDirectContent();
			if(intArray != null && intArray.length > 1080){
				setFooterPage(document, cb, "第1页  共2页");
			}else{
				setFooterPage(document, cb, "第1页  共1页");
			}
			
			/** 标题 **/
			String titleStr = "院外远程胎心监护报告单";
			if(StringUtils.isNotEmpty(data.getRemoteType()) && data.getRemoteType().equals(RemoteType.院内监护.name())){
				titleStr = "院内胎心监护报告单";
			}
			Paragraph reportTitle = setReportTitle(titleStr);
			document.add(reportTitle);
			
			/** 医院 **/
			PdfPTable hospitalInfo = setHospitalInfo(consumer.getMonitorOrderId().getMonitorHospitalId().getHospitalId().getName());
			document.add(hospitalInfo);
			
			/** 用户信息+固定表格 **/
			PdfPTable contentTable = setContentInfo(data, reason, doctorName);
			document.add(contentTable);
			
			if(intArray != null && intArray.length > 0){
				int[] dataArray;
				if(intArray.length >= 1080){
					dataArray = Arrays.copyOfRange(intArray, 0, 1080);
				}else{
					dataArray = Arrays.copyOfRange(intArray, 0, intArray.length);
				}
				/** 第一段胎心曲线 **/
				ChartDrawInterface firstFetal = new ChartDrawInterface(dataArray, 1, fetalMoveArray, start);
				VerticalPositionMark vm = new VerticalPositionMark(firstFetal, 0);
				document.add(vm);
			}else{
				/** 为空 **/
				ChartDrawInterface firstFetal = new ChartDrawInterface(null, 1, null, start);
				VerticalPositionMark vm1 = new VerticalPositionMark(firstFetal, 0);
				document.add(vm1);
				return filePath;
			}
			
			/**
			 * 第二段胎心曲线
			 */
			if(intArray.length > 1080){
				/** 创建新页面 **/
				document.newPage();
				/** 创建页码 **/
				PdfContentByte cbSecond = writer.getDirectContent();
				setFooterPage(document, cbSecond, "第2页  共2页");
				
				if(intArray.length <= 2160){
					int[] dataArray = Arrays.copyOfRange(intArray, 1081, intArray.length);
					ChartDrawInterface secondFetal = new ChartDrawInterface(dataArray, 2, fetalMoveArray, start);
					VerticalPositionMark vm2 = new VerticalPositionMark(secondFetal, 0);
					document.add(vm2);
				}else{
					int[] dataArray = Arrays.copyOfRange(intArray, 1081, 2160);
					ChartDrawInterface secondFetal = new ChartDrawInterface(dataArray, 2, fetalMoveArray, start);
					VerticalPositionMark vm2 = new VerticalPositionMark(secondFetal, 0);
					document.add(vm2);
				}
			}
			
			/**
			 * 第三段胎心曲线
			 */
			if(intArray.length > 2160){
				if(intArray.length <= 2400){
					int[] dataArray = Arrays.copyOfRange(intArray, 2161, intArray.length);
					ChartDrawInterface thirdFetal = new ChartDrawInterface(dataArray, 3, fetalMoveArray, start);
					VerticalPositionMark vm3 = new VerticalPositionMark(thirdFetal, 0);
					document.add(vm3);
				}else{
					int[] dataArray = Arrays.copyOfRange(intArray, 2161, 2400);
					ChartDrawInterface thirdFetal = new ChartDrawInterface(dataArray, 3, fetalMoveArray, start);
					VerticalPositionMark vm3 = new VerticalPositionMark(thirdFetal, 0);
					document.add(vm3);
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
	/**
	 * 因为家庭医生不能传入MonitorOrderConsumer对象,在原方法中只需要医院名称所以,重载这个版本(绘制胎心报告单到缓存文件夹,并返回路径)
	 * @param request
	 * @param data
	 * @param hospitalName
	 * @param reason
	 * @param doctorName
	 * @param offset
	 * @return
	 */
	public static String generatePDF(HttpServletRequest request, VoRemoteData data, String hospitalName, String reason, String doctorName, Integer offset){
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter writer = null;
		String filePath = request.getSession().getServletContext().getRealPath("/")+"temp"+File.separator+TimeUtils.randomOrderId()+".pdf";
		//String filePath = "F://ccc.pdf";
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.setMargins(10, 10, 10, 10);
			document.open();
			
			/** 处理胎心数据 **/
			String dataStr = data.getHeart().getData();
			String fetalMoveStr = data.getHeart().getFetalMoveData();
			int[] intArray = bindHeartData(dataStr, offset);
			int[] fetalMoveArray = bindFetalMoveData(fetalMoveStr);
			
			offset = offset == null ? 0 : offset;
			
			PdfContentByte cb = writer.getDirectContent();
			if(intArray != null && intArray.length > 1080){
				setFooterPage(document, cb, "第1页  共2页");
			}else{
				setFooterPage(document, cb, "第1页  共1页");
			}
			
			/** 标题 **/
			String titleStr = "院外远程胎心监护报告单";
			if(StringUtils.isNotEmpty(data.getRemoteType()) && data.getRemoteType().equals(RemoteType.院内监护.name())){
				titleStr = "院内胎心监护报告单";
			}
			Paragraph reportTitle = setReportTitle(titleStr);
			document.add(reportTitle);
			
			/** 医院 **/
			PdfPTable hospitalInfo = setHospitalInfo(hospitalName);
			document.add(hospitalInfo);
			
			/** 用户信息+固定表格 **/
			PdfPTable contentTable = setContentInfo(data, reason, doctorName);
			document.add(contentTable);
			
			if(intArray != null && intArray.length > 0){
				int[] dataArray;
				if(intArray.length >= 1080){
					dataArray = Arrays.copyOfRange(intArray, 0, 1080);
				}else{
					dataArray = Arrays.copyOfRange(intArray, 0, intArray.length);
				}
				/** 第一段胎心曲线 **/
				ChartDrawInterface firstFetal = new ChartDrawInterface(dataArray, 1, fetalMoveArray, offset);
				VerticalPositionMark vm = new VerticalPositionMark(firstFetal, 0);
				document.add(vm);
			}else{
				/** 为空 **/
				ChartDrawInterface firstFetal = new ChartDrawInterface(null, 1, null, offset);
				VerticalPositionMark vm1 = new VerticalPositionMark(firstFetal, 0);
				document.add(vm1);
				return filePath;
			}
			
			/**
			 * 第二段胎心曲线
			 */
			if(intArray.length > 1080){
				/** 创建新页面 **/
				document.newPage();
				/** 创建页码 **/
				PdfContentByte cbSecond = writer.getDirectContent();
				setFooterPage(document, cbSecond, "第2页  共2页");
				
				if(intArray.length <= 2160){
					int[] dataArray = Arrays.copyOfRange(intArray, 1081, intArray.length);
					ChartDrawInterface secondFetal = new ChartDrawInterface(dataArray, 2, fetalMoveArray, offset);
					VerticalPositionMark vm2 = new VerticalPositionMark(secondFetal, 0);
					document.add(vm2);
				}else{
					int[] dataArray = Arrays.copyOfRange(intArray, 1081, 2160);
					ChartDrawInterface secondFetal = new ChartDrawInterface(dataArray, 2, fetalMoveArray, offset);
					VerticalPositionMark vm2 = new VerticalPositionMark(secondFetal, 0);
					document.add(vm2);
				}
			}
			
			/**
			 * 第三段胎心曲线
			 */
			if(intArray.length > 2160){
				if(intArray.length <= 2400){
					int[] dataArray = Arrays.copyOfRange(intArray, 2161, intArray.length);
					ChartDrawInterface thirdFetal = new ChartDrawInterface(dataArray, 3, fetalMoveArray, offset);
					VerticalPositionMark vm3 = new VerticalPositionMark(thirdFetal, 0);
					document.add(vm3);
				}else{
					int[] dataArray = Arrays.copyOfRange(intArray, 2161, 2400);
					ChartDrawInterface thirdFetal = new ChartDrawInterface(dataArray, 3, fetalMoveArray, offset);
					VerticalPositionMark vm3 = new VerticalPositionMark(thirdFetal, 0);
					document.add(vm3);
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
	
}
