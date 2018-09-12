package com.lizhi.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

/**
 * 关于利用腾讯云对象桶储存文件,一个简单的类 后续会利用工厂模式实现不同
 * 
 * @author lx
 *
 */
public class FileUtilTX {
	private static COSClient cosclient;
	private static String bucketName = "lizhixiong-1256835525";// bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
	private static String bucketArea = "ap-guangzhou";// https://cloud.tencent.com/document/product/436/6224
	private static String secretId = "AKIDkNp593yOq6CLWfhemmOHF5jCWjlsHiOt";// https://console.cloud.tencent.com/cam/capi
	private static String secretKey = "Ws32sZN9pMMGza0GMGyUoy1tZ6RaXHqH";

	/**
	 * 快速入门 初始化客户端 cosclient 设置用户的身份信息， appid， bucket 所在的区域以及 bucket 名
	 * 
	 * @param args
	 */
	public static void instanceCosClient(String secretId, String secretKey, String bucketName, String bucketArea) {
		FileUtilTX.bucketName = bucketName;
		FileUtilTX.bucketArea = bucketArea;
		// 1 初始化用户身份信息(secretId, secretKey)
		COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

		// 2 设置bucket的区域, COS地域的简称请参照
		ClientConfig clientConfig = new ClientConfig(new Region(bucketArea));// 默认为广州

		// 3 生成cos客户端
		cosclient = new COSClient(cred, clientConfig);

	}

	/**
	 * 上传文件 将本地文件或者已知长度的输入流内容上传到 COS. 适用于图片类小文件(20M以下)上传，最大支持 5 GB(含), 5 GB
	 * 以上必须使用分块上传或高级 API 上传。如果本地文件大部分为20M以上, 建议参考使用接口文档的高级API. 如果COS上已存在对象, 则会进行覆盖.
	 * 另外因为对象存储中不存在目录，如果要创建目录对象, 请参考接口文档的FAQ. 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名
	 * bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为
	 * doc1/pic1.jpg, 详情参考
	 * [对象键](https://cloud.tencent.com/document/product/436/13324)
	 * 
	 * @param file
	 */
	public static void upload(String key, File file) {
		if (cosclient == null) {
			throw new RuntimeException("cosclient没有设定初始");
		}

		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
		PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
	}

	/**
	 * @param file
	 * @throws IOException
	 */
	public static void upload(String key, InputStream inputStream, long size) {
		if (cosclient == null) {
			throw new RuntimeException("cosclient没有设定初始");
		}
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(size);

		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
		PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
	}
	
	/**
	 * @param file
	 * @throws IOException
	 */
	public static void upload(String key, byte[] bytes) {
		try {
			if (cosclient == null) {
				throw new RuntimeException("cosclient没有设定初始");
			}
			

			// 创建一个文件
			File file = new File("cacheTest");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			out.write(bytes);
			out.close();

			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
			PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
			
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 批量上传
	 * 
	 * @param listFile
	 */
	public static void upload(Map<String, File> listFile) {
		if (cosclient == null) {
			throw new RuntimeException("cosclient没有设定初始");
		}

		Set<Entry<String, File>> entrySet = listFile.entrySet();
		for (Entry<String, File> entry : entrySet) {
			upload(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * @param key
	 *            要下载文件的的路径 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名
	 *            bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg`
	 *            中，对象键为 doc1/pic1.jpg, 详情参考
	 * @param downFile
	 *            指定要下载到的本地路径
	 */
	public static void download(String key, File downFile) {
		if (cosclient == null) {
			throw new RuntimeException("cosclient没有设定初始");
		}
		// 指定要下载的文件所在的 bucket 和对象键
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
		ObjectMetadata downObjectMeta = cosclient.getObject(getObjectRequest, downFile);
	}

	public static void delete(String key) {
		if (cosclient == null) {
			throw new RuntimeException("cosclient没有设定初始");
		}
		// 指定要删除的 bucket 和对象键
		cosclient.deleteObject(bucketName, key);
	}

	public static void shutdown() {
		if (cosclient != null) {
			// 关闭客户端(关闭后台线程)
			FileUtilTX.cosclient.shutdown();
		}
	}

	// public static void main(String[] args) {
	// FileUtilTX.instanceCosClient(secretId, secretKey, bucketName, bucketArea);
	// File file = new File("src/test/resources/mydown.jpg");
	// InputStream resourceAsStream =
	// FileUtilTX.class.getResourceAsStream("mydown.jpg");
	// System.out.println(resourceAsStream);
	// FileUtilTX.upload("test.jpg", resourceAsStream);
	// FileUtilTX.shutdown();
	// }
}
