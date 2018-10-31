package com.lizhi.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

/**
 * 取第四个 为当前项目所在的工程路径
 * 
 * @author lx
 *
 */
public class FileUtil {
	private static String[] urls = new String[6];
	static {
		System.out.println(1);

		// 第一种：获取类加载的根路径 D:\git\daotie\daotie\target\classes
		File f = new File(FileUtil.class.getResource("/").getPath());
		urls[0] = f.toString();

		// 获取当前类的所在工程路径; 如果不加“/” 获取当前类的加载目录 D:\git\daotie\daotie\target\classes\my
		File f2 = new File(FileUtil.class.getResource("").getPath());
		urls[1] = f2.toString();

		// 第二种：获取项目路径 D:\git\daotie\daotie
		File directory = new File("");// 参数为空
		String courseFile;
		try {
			courseFile = directory.getCanonicalPath();
			urls[2] = courseFile;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 第三种： file:/D:/git/daotie/daotie/target/classes/
		URL xmlpath = FileUtil.class.getClassLoader().getResource("");
		urls[3] = xmlpath.toString();

		// 第四种： D:\git\daotie\daotie
		urls[4] = System.getProperty("user.dir");
		/*
		 * 结果： C:\Documents and Settings\Administrator\workspace\projectName 获取当前工程路径
		 */

		// 第五种： 获取所有的类路径 包括jar包的路径
		urls[5] = System.getProperty("java.class.path");

	}

	// public static void main(String[] args) {
	// for(String str : urls) {
	// System.out.println(str);
	//
	// }
	// }
	public static String getUrl(int index) {
		return urls[index];
	}

	/**
	 * MultipartFile获取File式的inputStream 2018年01月13日 15:47:30 阅读数：237
	 * 从springmvc获取到的文件是MultipartFile类型的，有的时候不能直接用从这种类型获取到的inputstream操作一些事情，比如从中初始化poi的Workbook，这时候要怎么获取到File式的流呢？
	 * 有一个方法就是把读到的MultipartFile转存到本地，然后再从本地读取这个转存的这个临时文件，从中获取文件流。这么写的话可以达到目的但是听起来就很繁琐对不对。还有一个方法，是从csdn论坛上看到的一部分获取的灵感，在这里分享给大家：
	 * 其中mFile就是MultipartFile类型的。
	 * 要导入 uploadfile的包
	 * 
	 * @throws IOException
	 */
	public static InputStream multipartFiletoFile(MultipartFile mFile) throws IOException {
//		CommonsMultipartFile cFile = (CommonsMultipartFile) mFile;
//		DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
//		InputStream inputStream = fileItem.getInputStream();
		byte[] bytes = mFile.getBytes();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		return byteArrayInputStream;
	}

	public static void main(String[] args) {
		new FileUtil();
		String s = Arrays.toString(urls);
		System.out.println(s);
	}
}
