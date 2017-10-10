package com.jumper.hospital.utils;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

import com.jumper.hospital.base.Progress;

public class FileUploadProgressListener implements ProgressListener {
	
	private HttpSession session;
	
	public FileUploadProgressListener() {

	}
	
    public FileUploadProgressListener(HttpSession session) {
        this.session = session;
        Progress status = new Progress();
        session.setAttribute(Consts.PROGRESS, status);
    }  
	
	/**
	 * pBytesRead 到目前为止读取文件的比特数 pContentLength 文件总大小 pItems 目前正在读取第几个文件
	 */
	public void update(long pBytesRead, long pContentLength, int pItems) {
		Progress status = (Progress) session.getAttribute(Consts.PROGRESS);
		status.setBytesRead(pBytesRead);
		status.setContentLength(pContentLength);
		status.setItems(pItems);
		session.setAttribute(Consts.PROGRESS, status);
	}
}