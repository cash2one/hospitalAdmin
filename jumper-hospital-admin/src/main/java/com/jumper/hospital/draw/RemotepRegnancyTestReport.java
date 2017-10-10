package com.jumper.hospital.draw;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationResult;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BaseFont;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 家庭医生  院外远程孕检报告单
 * @author Administrator
 *
 */
public class RemotepRegnancyTestReport  {
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public static String draw(HttpServletRequest request,VOFamilyExaminationResult voFamilyExaminationResult) {
		 Document doc = null;
		 OutputStream os=null;
		 String filePath = request.getSession().getServletContext().getRealPath("/")+"temp"+File.separator+TimeUtils.randomOrderId()+".pdf";
		 try{
			 /* 创建配置 */
		        Configuration cfg = new Configuration();
		        /* 指定模板存放的路径*/
		        cfg.setDirectoryForTemplateLoading(new File(request.getSession().getServletContext().getRealPath("/")+"temp"+File.separator));
		       // cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
		        cfg.setObjectWrapper(new DefaultObjectWrapper());
		        /* 从上面指定的模板目录中加载对应的模板文件*/
		        Template temp = cfg.getTemplate("spsShouYe.ftl","utf-8"); 
				 os = new FileOutputStream(filePath);
				 /* 创建数据模型 */
			     Map root = new HashMap();
			     root.put("e", voFamilyExaminationResult);
				
				Writer tempWriter = new CharArrayWriter();
				temp.process(root, tempWriter);
				
				ITextRenderer renderer = new ITextRenderer();
				//System.out.println(tempWriter.toString());
				renderer.setDocumentFromString(tempWriter.toString());
				
				//解决中文问题
				ITextFontResolver fontResolver = renderer.getFontResolver();
				fontResolver.addFont(Consts.PDF_FONT_PATH+"font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
				renderer.layout();
				renderer.createPDF(os);
			
			 return filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}finally{
			if(null!=os){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=doc)doc.close(); 
		}
	 }
	
	}