package com.jumper.hospital.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
/**
 * fastdfs文件上传
 * @author rent
 * @date 2014-03-28
 */
public class FastdfsUpload {

	private static final Logger logger = Logger.getLogger(FastdfsUpload.class);
	
	public static String getConfigPath(){
		try {
			String classPath = new File(FastdfsUpload.class.getResource("/").getFile()).getCanonicalPath();
			String configFilePath = classPath + File.separator + "fdfs_client.conf";
			return configFilePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static TrackerClient getTracker(){
		try {
			String configFilePath = getConfigPath();
			if(StringUtils.isEmpty(configFilePath)){
				System.err.println("the fdfs_client.conf config file is not found!!!");
			}
			ClientGlobal.init(configFilePath);
			
			/**建立连接**/
			TrackerClient trackerClient = new TrackerClient();
		    return trackerClient;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 上传单个文件
	 * @param uploadFileName 文件名
	 * @param inputStream 输入流
	 * @return String(文件路径)
	 */
	public static String upoladFile(String uploadFileName, InputStream inputStream){
		String upPath = null;
		StorageClient1 client1 = null;
		try {
			byte[] file_buff = null;
		    if(inputStream != null){
		    	int len = inputStream.available();
		    	file_buff = new byte[len];
		    	inputStream.read(file_buff);
		    }
		    String fileExtName = uploadFileName.split("\\.")[1];
		    
			client1 = ConnectionPool.getPoolInstance().checkout(10);    
			upPath = client1.upload_file1(file_buff, fileExtName, null);    
			ConnectionPool.getPoolInstance().checkin(client1);
			inputStream.close();
		} catch (InterruptedException e) { 
			//确实没有空闲连接,并不需要删除与fastdfs连接    
		} catch (Exception e) { 
			//发生io异常等其它异常，默认删除这次连接重新申请 
			ConnectionPool.getPoolInstance().drop(client1);
			e.printStackTrace();
		}finally{
		if(null!=inputStream)
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		return "/"+upPath; 
	}
	
	/**
	 * 上传文件，该方法主要用于主从文件上传
	 * @param inputStream 输入流
	 * @param master_filename 主文件名
	 * @param prefixName 从文件扩展名
	 * @return String(从文件路径)
	 */
	public static String upoladPrefixFile(String fileName, InputStream inputStream, String master_filename, String prefixName){
		/**
		 * 从主文件名中获取group分组及remote_filename信息
		 */
		if(StringUtils.isEmpty(master_filename)){
			return "the resource path master_filename can`t be empty!";
		}
		String group_remote_file = master_filename.substring(1, master_filename.length());
		int index = group_remote_file.indexOf("/");
		String group_name = group_remote_file.substring(0, index);
		String remote_filename = group_remote_file.substring(index+1, group_remote_file.length());
		String file_ext_name = fileName.split("\\.")[1];
		
		try {
			/** 读取配置Fastdfs配置文件及初始化 **/
			String configFilePath = getConfigPath();
			if(StringUtils.isEmpty(configFilePath)){
				System.err.println("the fdfs_client.conf config file is not found!!!");
				return "";
			}
			ClientGlobal.init(configFilePath);
			
			/**建立连接**/
			TrackerClient trackerClient = new TrackerClient();
		    TrackerServer trackerServer = trackerClient.getConnection();
		    StorageServer storageServer = null;
		    StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		    
		    System.out.println("fileLength:"+String.valueOf(inputStream.available()/1000));
		    
		    byte[] file_buff = null;
		    if(inputStream != null){
		    	int len = inputStream.available();
		    	file_buff = new byte[len];
		    	inputStream.read(file_buff);
		    }
		    
		    StorageServer[] storageServers = trackerClient.getStoreStorages(trackerServer, group_name);
		    if (storageServers == null) {
		    	System.err.println("get store storage servers fail, error code: " + storageClient.getErrorCode());
		    }else{
		        for (int k = 0; k < storageServers.length; k++){
		        	System.out.println(storageServers[k].getInetSocketAddress().getAddress().getHostAddress() + ":" + storageServers[k].getInetSocketAddress().getPort());
		        }
		    }
		    
		    String[] results = storageClient.upload_file(group_name, remote_filename, prefixName, file_buff, file_ext_name, null);
		    
		    if (results == null){
		        System.err.println("upload file fail, error code: " + storageClient.getErrorCode());
		        return "";
		    }
		    
		    String result_group_name = results[0];
		    String result_remote_filename = results[1];
		    System.out.println("result_group_name: " + result_group_name + ", result_remote_filename: " + result_remote_filename);

		    ServerInfo[] servers = trackerClient.getFetchStorages(trackerServer, group_name, remote_filename);
		    if (servers == null){
		    	System.err.println("get storage servers fail, error code: " + trackerClient.getErrorCode());
		    }
		    return "/"+result_group_name+"/"+result_remote_filename;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void getFile(){
		String configFilePath = getConfigPath();
		if(StringUtils.isEmpty(configFilePath)){
			System.err.println("the fdfs_client.conf config file is not found!!!");
		}
		try {
			ClientGlobal.init(configFilePath);
			TrackerClient trackerClient = new TrackerClient();
		    TrackerServer trackerServer = trackerClient.getConnection();
		    StorageServer storageServer = null;
		    StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		    
		    String group_name = "group1";
		    String remote_filename = "M00/00/00/CgAEuFUWdKOAVtEhAABQs6PtSR4209.jpg";
		    FileInfo fi = storageClient.get_file_info(group_name, remote_filename);
		    String sourceIpAddr = fi.getSourceIpAddr();
		    long size = fi.getFileSize();
		    System.out.println("ip:" + sourceIpAddr + ",size:" + size);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean deleteFile(String filePath){
		String configFilePath = getConfigPath();
		if(StringUtils.isEmpty(configFilePath)){
			System.err.println("the fdfs_client.conf config file is not found!!!");
		}
		
		int flag = filePath.indexOf("/");
		String groupName = filePath.substring(0, flag);
		String fileName = filePath.substring(flag+1, filePath.length());
		
		if(StringUtils.isEmpty(groupName) || StringUtils.isEmpty(fileName)){
			logger.error("groupName or fileName is empty!!!");
			return false;
		}
		
		try {
			ClientGlobal.init(configFilePath);
			
			TrackerClient trackerClient = new TrackerClient();
		    TrackerServer trackerServer = trackerClient.getConnection();

		    StorageServer storageServer = null;
		    StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		    
		    storageClient.delete_file(groupName, fileName);
		    logger.info("delete file success!fileName is "+fileName);
		    return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		try {
			File file = new File("F:\\test.mp4");
			InputStream inputStream = new FileInputStream(file);
			String path = upoladFile("test_1.mp4", inputStream);
			System.out.println(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
