package com.jumper.hospital.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
/**
 * 连接池
 * 
 * @author rent
 */
public class ConnectionPool {

	// 限制连接数
	private int size = 5;
	// 繁忙连接实例
	private ConcurrentHashMap<StorageClient1, Object> busyConnectionPool = null;
	// 空闲连接实例
	private ArrayBlockingQueue<StorageClient1> idleConnectionPool = null;
	
	private Object obj = new Object();

	private ConnectionPool() {
		busyConnectionPool = new ConcurrentHashMap<StorageClient1, Object>();
		idleConnectionPool = new ArrayBlockingQueue<StorageClient1>(size);
		init(size);
	};

	private static ConnectionPool instance = new ConnectionPool();

	/** 获取连接池实例 **/
	public static ConnectionPool getPoolInstance() {
		return instance;
	}

	/** 初始化方法 **/
	private void init(int size) {
		initClientGlobal();
		TrackerServer trackerServer = null;
		try {
			TrackerClient trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			for (int i = 0; i < size; i++) {
				StorageServer storageServer = null;
				StorageClient1 client1 = new StorageClient1(trackerServer,
						storageServer);
				idleConnectionPool.add(client1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (trackerServer != null) {
				try {
					trackerServer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 从空闲实例中取出一个连接 
	 * 把连接放到工作连接中 
	 * 如果当前没有可用连接则等待waitTimes时间，再次检查
	 * @param waitTimes 等待时间
	 * @return StorageClient1
	 * @throws InterruptedException
	 */
	public StorageClient1 checkout(int waitTimes) throws InterruptedException {
		StorageClient1 client1 = idleConnectionPool.poll(waitTimes, TimeUnit.SECONDS);
		busyConnectionPool.put(client1, obj);
		return client1;
	}

	/**
	 * 将连接从工作连接中取出，放入空闲连接
	 * @param client1
	 */
	public void checkin(StorageClient1 client1) {
		if (busyConnectionPool.remove(client1) != null) {
			idleConnectionPool.add(client1);
		}
	}
	
	/**
	 * 如果连接死掉，移除该连接并创建新连接
	 * @param client1
	 */
	public void drop(StorageClient1 client1) {
		if (busyConnectionPool.remove(client1) != null) {
			TrackerServer trackerServer = null;
			try {
				TrackerClient trackerClient = new TrackerClient();
				// 此处有内存泄露，因为trackerServer没有关闭连接
				trackerServer = trackerClient.getConnection();
				StorageServer storageServer = null;
				StorageClient1 newClient1 = new StorageClient1(trackerServer, storageServer);
				idleConnectionPool.add(newClient1);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (trackerServer != null) {
					try {
						trackerServer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void initClientGlobal() {   
		InetSocketAddress[] trackerServers = new InetSocketAddress[1];
		trackerServers[0] = new InetSocketAddress(Consts.FASTDFS_ADDRESS, Integer.valueOf(Consts.FASTDFS_PORT));
		//trackerServers[0] = new InetSocketAddress("112.74.74.133", 22122);
		ClientGlobal.setG_tracker_group(new TrackerGroup(trackerServers)); 
		// 连接超时的时限，单位为毫秒 
		ClientGlobal.setG_connect_timeout(2000);   // 网络超时的时限，单位为毫秒 
		ClientGlobal.setG_network_timeout(30000);   
		ClientGlobal.setG_anti_steal_token(false);   // 字符集 
		ClientGlobal.setG_charset("UTF-8");   
		ClientGlobal.setG_secret_key(null); 
	 }

}
