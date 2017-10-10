package com.jumper.hospital.draw;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.draw.DrawInterface;

public class ChartDrawInterface implements DrawInterface {

	protected int[] dataArray;
	
	protected int graphType;
	
	protected int[] fetalMoveArray;
	
	protected int offset;
	
	protected ChartDrawInterface(int[] dataArray, int graphType, int[] fetalMoveArray, int offset) {
		this.dataArray = dataArray;
		this.graphType = graphType;
		this.fetalMoveArray = fetalMoveArray;
		this.offset = offset;
	}

	@Override
	public void draw(PdfContentByte canvas, float left, float bottom, float right, float top, float oy) {
		/** 设置画布区域大小，这里设置为A4纸张的大小 **/
		Rectangle rect = PageSize.A4.rotate();
		Graphics2D graph = canvas.createGraphics(rect.getWidth(), rect.getHeight());
		/** 去掉线条毛边 **/
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		DrawChart dc = new DrawChart();
		dc.drawMethod(graph, dataArray, graphType, fetalMoveArray, offset);
	}

}
