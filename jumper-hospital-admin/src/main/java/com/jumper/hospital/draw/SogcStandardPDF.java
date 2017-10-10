package com.jumper.hospital.draw;
/**
 * 加拿大SOGC标准评分标准胎心加宫缩胎心报告
 * @author rent
 * @date 2016-08-15
 */
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

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
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VoRemoteData;
import com.jumper.hospital.vo.VoRemoteHeart;

public class SogcStandardPDF {

	/**
	 * 内容页Table嵌套
	 * @return
	 * @throws DocumentException
	 */
	private static PdfPTable setContentInfo(VoRemoteData data, String reason, String docName, String paperSpeed, String items) throws DocumentException{
		PdfPTable contentTable = new PdfPTable(2);
		contentTable.setSpacingBefore(10);
		contentTable.setWidthPercentage(100);
		float[] widths = {40f, 60f};
		contentTable.setWidths(widths);
		PdfPCell contentCell;
		
		contentCell = new PdfPCell(setUserInfo(data, reason, docName, paperSpeed));
		contentCell.setBorder(0);
		contentTable.addCell(contentCell);
		
		contentCell = new PdfPCell(setStandard(items));
		contentCell.setBorder(0);
		contentTable.addCell(contentCell);
		
		return contentTable;
	}
	
	private static PdfPTable setUserInfo(VoRemoteData data, String reason, String docName, String paperSpeed){
		PdfPTable userTable = new PdfPTable(2);
		userTable.setWidthPercentage(100);
		PdfPCell cell;
		float fontSize = 10f;
		float cellHeight = 19f;
		
		cell = infoCellStyle(0, 0, cellHeight, fontSize, "姓    名:  "+data.getUserName());
		cell.setBorder(0);
		userTable.addCell(cell);
		
		cell = infoCellStyle(0, 0, cellHeight, fontSize, "年    龄:  "+data.getUserAge()+"岁");
		cell.setBorder(0);
		userTable.addCell(cell);
		/***************************************************/
		cell = infoCellStyle(0, 0, cellHeight, fontSize, "孕    周:  "+data.getPreganyWeek());
		cell.setBorder(0);
		userTable.addCell(cell);
		
		cell = infoCellStyle(0, 0, cellHeight, fontSize, "手机号码:  "+data.getMobile());
		cell.setBorder(0);
		userTable.addCell(cell);
		/***************************************************/
		cell = infoCellStyle(0, 2, cellHeight, fontSize, "开始时间:  "+data.getHeart().getTime());
		cell.setBorder(0);
		userTable.addCell(cell);
		/***************************************************/
		cell = infoCellStyle(0, 2, cellHeight, fontSize, "结束时间:  "+data.getHeart().getEndTime());
		cell.setBorder(0);
		userTable.addCell(cell);
		/***************************************************/
		cell = infoCellStyle(0, 2, cellHeight, fontSize, "监护时长:  "+data.getHeart().getMinute());
		cell.setBorder(0);
		userTable.addCell(cell);
		/***************************************************/
		Paragraph remark = new Paragraph("备    注:", DrawConst.getChineseFont(10, Font.NORMAL));
		cell = new PdfPCell();
		cell.addElement(remark);
		if(StringUtils.isNotEmpty(reason)){
			Paragraph reasonPara = new Paragraph(reason, DrawConst.getChineseFont(8.5f, Font.NORMAL));
			reasonPara.setIndentationLeft(38);
			cell.addElement(reasonPara);
		}
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(0);
		cell.setMinimumHeight(60f);
		cell.setColspan(2);
		userTable.addCell(cell);
		/***************************************************/
		cell = infoCellStyle(0, 0, cellHeight, fontSize, "监护医生 : "+docName);
		cell.setBorder(0);
		userTable.addCell(cell);
		/***************************************************/
		cell = infoCellStyle(0, 0, cellHeight, fontSize, "日期 : "+TimeUtils.getCurrentTime(Consts.FORMAT_TIME_THREE));
		cell.setBorder(0);
		userTable.addCell(cell);
		/***************************************************/
		cell = infoCellStyle(0, 2, cellHeight, fontSize, paperSpeed);
		cell.setBorder(0);
		userTable.addCell(cell);
		
		return userTable;
	}
	
	private static PdfPTable setStandard(String items) throws DocumentException{
		PdfPTable dataTable = new PdfPTable(5);
		float[] resultWidth = {9f, 9f, 20f, 34f, 28f};
		dataTable.setWidths(resultWidth);
		dataTable.setWidthPercentage(100);
		float fontSize = 8.5f;
		
		dataTable.addCell(infoCellStyle(0, 2, 0, fontSize, "胎心基线"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "1-2-1")+" 正常", "(110～160bpm)"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "1-3-1")+" 胎心过速(>160bpm)", checkItemsSelected(items, "1-3-2")+" 胎心过缓(<110bpm)", "       不伴基线变异缺失"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "1-4-1")+" 胎心过缓(<110bpm)", "       伴基线变异缺失"));
		/************************************************************/
		dataTable.addCell(infoCellStyle(0, 2, 0, fontSize, "基线变异"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "2-2-1")+" 中度", "(6～25bpm)"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "2-3-1")+" 变异缺失(0bpm)", "       不伴反复出现的晚期减速", checkItemsSelected(items, "2-3-2")+" 微小变异(<5bpm)", checkItemsSelected(items, "2-3-3")+" 显著变异(>25bpm)"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "2-4-1")+" 变异缺失(0bpm)", "       伴胎心过缓", "       或反复出现变异减速", "       或晚期减速"));
		/***********************************************************************/
		dataTable.addCell(infoCellStyle(0, 2, 15f, fontSize, "胎心加速"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "3-2-1")+" 有或无  "+checkItemsSelected(items, "3-2-2")));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "3-3-1")+" 刺激胎儿后仍缺失"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "3-4-1")+" 无"));
		/***********************************************************************/
		dataTable.addCell(infoCellStyle(3, 0, 0, fontSize, "减速"));
		dataTable.addCell(infoCellStyle(0, 0, 15f, fontSize, "早期减速"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "4-2-1")+" 有或无  "+checkItemsSelected(items, "4-2-2")));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "4-3-1")+" 无"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "4-4-1")+" 无"));
		/*****************/
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, "变异减速"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "5-2-1")+" 无 "));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "5-3-1")+" 反复出现伴微小变异或中度变异", checkItemsSelected(items, "5-3-2")+" 延长减速(>2min但<10min)", checkItemsSelected(items, "5-3-3")+" 非特异性变异减速"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "5-4-1")+" 反复出现伴基线变异缺失"));
		/********************/
		dataTable.addCell(infoCellStyle(0, 0, 15f, fontSize, "晚期减速"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "6-2-1")+" 无 "));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "6-3-1")+" 反复出现伴基线中度变异"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "6-4-1")+" 反复出现伴基线变异缺失"));
		/*****************************************************************/
		dataTable.addCell(infoCellStyle(0, 2, 15f, fontSize, "正弦曲线"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "7-2-1")+" 无"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "7-3-1")+" 无"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "7-4-1")+" 有"));
		/*****************************************************************/
		dataTable.addCell(infoCellStyle(0, 2, 15f, fontSize, "胎心监护结果"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "8-2-1")+" 正常(I级)"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "8-3-1")+" 可疑(II级)"));
		dataTable.addCell(infoCellStyle(0, 0, 0, fontSize, checkItemsSelected(items, "8-4-1")+" 异常(III级)"));
		
		return dataTable;
	}
	
	@SuppressWarnings("static-access")
	private static PdfPCell infoCellStyle(Integer rowspan, Integer colspan, float height, float fontSize, String... info){
		PdfPCell cell = new PdfPCell();
		for(String str : info){
			Paragraph paragraph = new Paragraph(str, DrawConst.getChineseFont(fontSize, Font.NORMAL));
			cell.addElement(paragraph);
		}
		if(rowspan != 0){
			cell.setRowspan(rowspan);
		}
		if(colspan != 0){
			cell.setColspan(colspan);
		}
		if(height > 0){
			cell.setMinimumHeight(height);
		}
		cell.setUseAscender(true);
		cell.setVerticalAlignment(cell.ALIGN_MIDDLE);
		return cell;
	}
	
	private static String checkItemsSelected(String items, String item){
		if(StringUtils.isEmpty(items) || StringUtils.isEmpty(item)){
			return "【  】";
		}
		List<String> itemList = Arrays.asList(items.split(","));
		if(itemList.contains(item)){
			return "【√】";
		}
		return "【  】";
	}
	
	public static int[] convertData(String dataStr, Integer offset){
		String[] heartData = null;
		if(StringUtils.isEmpty(dataStr)){
			return null;
		}
		if(dataStr.indexOf("[") != -1){
			dataStr = dataStr.replace("[", "");
		}
		if(dataStr.indexOf("]") != -1){
			dataStr = dataStr.replace("]", "");
		}
		if(dataStr.indexOf("\r\n") != -1){
			dataStr = dataStr.replace("\r\n", "");
		}
		if(StringUtils.isEmpty(dataStr)){
			return null;
		}
		heartData = dataStr.split(",");
		int[] intArray = null;
		if(heartData != null && heartData.length > 0){
			intArray = new int[heartData.length];
			for(int i = 0; i < heartData.length; i++){
				intArray[i]=Integer.parseInt(heartData[i]);
			}
		}
		return intArray;
	}
	
	public static String generatePDF(HttpServletRequest request, Integer offset, VoRemoteData data, Integer speed, 
			String doctorName, String remark, Integer start, Integer end, String items){
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter writer = null;
		String filePath = request.getSession().getServletContext().getRealPath("/")+"temp"+File.separator+TimeUtils.randomOrderId()+".pdf";
		//String filePath = "F://测试啊.pdf";
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.setMargins(20, 22, 0, 0);
			document.open();
			
			/** 医院名标题 **/
			String hospitalName = data.getHospitalInfo().getName();
			//String hospitalName = "深圳市宝安区妇幼保健院";
			if(StringUtils.isNotEmpty(data.getRemoteType()) && data.getRemoteType().equals(RemoteType.院内监护.name())){
				hospitalName = "院内胎心监护报告单";
			}
			Paragraph hospitalNameTitle = new Paragraph(hospitalName, DrawConst.getChineseFont(15, Font.BOLD));
			hospitalNameTitle.setAlignment(1);/** 设置居中显示 **/
			document.add(hospitalNameTitle);
			
			/** 文档类型副标题 **/
			Paragraph subTitle = new Paragraph("电 脑 胎 儿 监 护 图", DrawConst.getChineseFont(12, Font.BOLD));
			subTitle.setAlignment(1);
			subTitle.setLeading(0);
			subTitle.setSpacingBefore(16f);
			document.add(subTitle);
			
			/**
			 * 此处开始计算页数及画图数据
			 */
			Integer totalPoint = 0;
			switch (speed) {
				case 1: totalPoint = 3240; break;
				case 2: totalPoint = 1620; break;
				case 3: totalPoint = 1080; break;
				case 20: totalPoint = 2400; break;
				case 30: totalPoint = 3600; break;
				default: break;
			}
			
			int[] drawData = convertData(data.getHeart().getData(), offset);
			int[] uterusData = convertData(data.getHeart().getUterusData(), offset);
			int[] fetalMoveArray = convertData(data.getHeart().getFetalMoveData(), 0);
			int totalPage = (end - start) % totalPoint == 0 ? (end - start) / totalPoint : (end - start) / totalPoint + 1;
			totalPage = totalPage == 0 ? 1 : totalPage;
			
			/** 走纸速度和时间 **/
			String paper_speed = "";
			switch (speed) {
				case 1:
				case 2:
				case 3:
					paper_speed = speed+"厘米/分钟";
					break;
				case 20:
				case 30:
					paper_speed = speed+"分钟/页";
					break;
			}
			String speedLabel = "走纸速度："+paper_speed+"    第 1 页  共 "+totalPage+" 页 ";
			
			/** 用户信息+固定表格 **/
			PdfPTable contentTable = setContentInfo(data, remark, doctorName, speedLabel, items);
			document.add(contentTable);
			
			if(totalPage > 0){
				for(int i = 1; i <= totalPage; i++){
					int marginTop = i == 1 ? 255 : 30;
					int[] fetal = null;
					int[] uterus = null;
					if(drawData != null && drawData.length > 0){
						fetal = Arrays.copyOfRange(drawData, start + totalPoint * (i - 1), end < start + totalPoint * i ? end : start + totalPoint * i);
					}
					if(uterusData != null && uterusData.length > 0){
						uterus = Arrays.copyOfRange(uterusData, start + totalPoint * (i - 1), end < start + totalPoint * i ? end : start + totalPoint * i);
					}
					
					if(i != 1){
						document.newPage();
					}
					SFYPdfDrawInterface draw = new SFYPdfDrawInterface(marginTop, i, fetal, uterus, speed, start, end,
							TimeUtils.getTimestampDate(data.getHeart().getTime(), Consts.FORMAT_TIME_ONE), fetalMoveArray);
					VerticalPositionMark vm = new VerticalPositionMark(draw, 0);
					document.add(vm);
				}
			}
			
			PdfContentByte cb = writer.getDirectContent();
			cb.saveState();
	        cb.beginText();
	        cb.setFontAndSize(BaseFont.createFont(Consts.PDF_FONT_PATH+"font/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED), 8);
	        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "说明：1. 本报告仅对监测样本负责，仅供临床参考，不做诊断证明之用    2. 请依据医生建议按时复诊检查自感异常随时复诊", 10, 10, 0);
	        cb.endText();
	        cb.restoreState();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(document != null){
				document.close();
			}
		}
		return filePath;
	}
	
	public static void main(String[] args) {
		VoRemoteHeart heart = new VoRemoteHeart();
		heart.setEndTime("2016-08-15 17:18:10");
		heart.setMinute("0小时2分34秒");
		heart.setTime("2016-08-15 16:50:00");
		heart.setData("[142,141,140,0,0,0,0,0,0,138,137,136,135,134,133,132,131,130,129,128,127,126,125,124,0,0,0,0,142,140,133,132,131,135,143,148,153,159,160,161,162,163,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,149,148,147,146,145,144,143,142,141,140,139,138,137,136,135,134,133,132,131,130,129,128,127,126,125,124,123,122,123,124,125,126,125,126,125,124,125,124,123,122,123,122,121,120,119,118,119,120,119,118,117,116,117,116,117,116,117,0,0,0,0,0,0,0,0]");
		heart.setUterusData("[]");
		VoRemoteData data = new VoRemoteData();
		data.setHeart(heart);
		data.setMobile("12077117711");
		data.setMonitorTimes(5);
		data.setOrderId(10);
		data.setPreganyDate("2016-11-10");
		data.setPreganyWeek("30");
		data.setRemoteType(RemoteType.实时监护.name());
		data.setUserAge(25);
		data.setUserName("王大花");
		data.setConsumerId(948);
		data.setUserId(11543);
		String filePath = generatePDF(null, 10, data, 3, "李小龙", "西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医院西北妇女儿童医童医", 0, heart.getData().split(",").length, null);
		System.out.println(filePath);
	}
	
}
