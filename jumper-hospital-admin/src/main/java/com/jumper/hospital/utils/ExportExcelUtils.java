package com.jumper.hospital.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
/**
 * 导出Excel工具 
 * @author win
 *
 * @param <T>
 */
public class ExportExcelUtils<T> {

	public void exportExcel(String title, Collection<T> dataset, OutputStream out) {
		exportExcel(title, null, dataset, out, "yyyy-MM-dd HH:mm:ss");
	}
	
	public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out) {
		exportExcel(title, headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * @param title 表格标题名
	 * @param headers 表格属性列名数组
	 * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd HH:mm:ss"
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
		/** 声明一个工作簿 **/
		HSSFWorkbook workbook = new HSSFWorkbook();
		/** 声明一个表格 **/
		HSSFSheet sheet = workbook.createSheet(title);
		/** 设置表格默认列宽度为15个字节 **/
		sheet.setDefaultColumnWidth((int) 16);
		sheet.autoSizeColumn(1);
		/** 生成样式，用于表格标题行 **/
		HSSFCellStyle style = workbook.createCellStyle();
		/** 设置样式 **/
		style.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    /** 生成字体 **/
	    HSSFFont font = workbook.createFont();
	    font.setColor(HSSFColor.VIOLET.index);
	    font.setFontHeightInPoints((short) 12);
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    /** 将字体应用到样式中 **/
	    style.setFont(font);
	    
	    /** 样式二，用于表格内容行 **/
	    HSSFCellStyle style2 = workbook.createCellStyle();
	    style2.setFillForegroundColor(HSSFColor.WHITE.index);
	    style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	    style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
      	/** 字体二 **/
	    HSSFFont font2 = workbook.createFont();
	    font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	    font2.setColor(HSSFColor.BLACK.index);
	    style2.setFont(font2);
	    /** 声明画图顶级管理器 **/
	    HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
	    
	    /** 设置表格标题行 **/
	    HSSFRow row = sheet.createRow(0);
	    for (int i = 0; i < headers.length; i++) {
	       HSSFCell cell = row.createCell(i);
	       cell.setCellStyle(style);
	       HSSFRichTextString text = new HSSFRichTextString(headers[i]);
	       cell.setCellValue(text);
	    }
	    
	    /** 以下是数据内容 **/
	    Iterator<T> it = dataset.iterator();
	    int index = 0;
	    while(it.hasNext() && it != null){
	    	index++;
	    	row = sheet.createRow(index);
	    	T t = (T) it.next();
	    	/** 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值 **/
	    	Field[] fields = t.getClass().getDeclaredFields();
	    	for(int i = 0;i < fields.length;i++){
	    		HSSFCell cell = row.createCell(i);
	            
	            Field field = fields[i];
	            String fieldName = field.getName();
	            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	            
	            try {
	                Class tCls = t.getClass();
	                Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
	                Object value = getMethod.invoke(t, new Object[] {});
	                /** 判断值的类型后进行强制类型转换 **/
	                String textValue = null;
	                if (value instanceof Boolean) {
	                	boolean bValue = (Boolean) value;
	                	textValue = "是";
	                	if (!bValue) {
	                		textValue ="否";
	                	}
	                } else if (value instanceof Date) {
	                	Date date = (Date) value;
	                	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	                    textValue = sdf.format(date);
	                } else if (value instanceof byte[]) {
	                	/** 有图片时，设置行高为60px **/
	                	row.setHeightInPoints(60);
	                	/** 设置图片所在列宽度为80px,注意这里单位的一个换算 **/
	                	sheet.setColumnWidth(i, (short) (35.7 * 80));
	                	byte[] bsValue = (byte[]) value;
	                	HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6, index);
	                 	anchor.setAnchorType(2);
	                	patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
	                } else if(value instanceof Calendar){
	                	Calendar cale = Calendar.getInstance();  
	                	Date tasktime = cale.getTime();  
	                	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                	textValue = df.format(tasktime);
	                } else{
	                	/** 其它数据类型都当作字符串简单处理 **/
	                	if(value != null){
	                		textValue = value.toString();
	                	}
	                }
	                /** 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成 **/
	                if(textValue!=null){
	                	Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
	                	Matcher matcher = p.matcher(textValue);
	                	if(matcher.matches()){
	                		/** 是数字当作double处理 **/
	                		cell.setCellValue(Double.parseDouble(textValue));
	                   }else{
	                	   HSSFRichTextString richString = new HSSFRichTextString(textValue);
	                	   HSSFFont font3 = workbook.createFont();
	                	   font3.setColor(HSSFColor.BLACK.index);
	                	   richString.applyFont(font3);
	                	   cell.setCellValue(richString);
	                   }
	                }else{
	                	cell.setCellValue("");
	                }
	            } catch (SecurityException e) {
	                e.printStackTrace();
	            } catch (NoSuchMethodException e) {
	                e.printStackTrace();
	            } catch (IllegalArgumentException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (InvocationTargetException e) {
	                e.printStackTrace();
	            } finally {
	                //清理资源
	            }
	    	}
	    }
	    
	    try {
	    	workbook.write(out);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	
	public static void main(String[] args) {
		Timestamp time = TimeUtils.getCurrentTime();
		if(time instanceof Date){
			System.out.println("时间类型");
		}
	}
}
