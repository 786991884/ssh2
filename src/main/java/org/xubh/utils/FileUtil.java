package org.xubh.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件相关操作辅助类。
 * 
 */
public class FileUtil {
	private static final String FOLDER_SEPARATOR = "/";
	private static final char EXTENSION_SEPARATOR = '.';

	/**
	 * 功能：复制文件或者文件夹。
	 * 
	 * @param inputFile
	 *            源文件
	 * @param outputFile
	 *            目的文件
	 * @param isOverWrite
	 *            是否覆盖(只针对文件)
	 * @throws IOException
	 */
	public static void copy(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
		if (!inputFile.exists()) {
			throw new RuntimeException(inputFile.getPath() + "源目录不存在!");
		}
		copyPri(inputFile, outputFile, isOverWrite);
	}

	/**
	 * 功能：为copy 做递归使用。
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @param isOverWrite
	 * @throws IOException
	 */
	private static void copyPri(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
		// 是个文件。
		if (inputFile.isFile()) {
			copySimpleFile(inputFile, outputFile, isOverWrite);
		} else {
			// 文件夹
			if (!outputFile.exists()) {
				outputFile.mkdir();
			}
			// 循环子文件夹
			for (File child : inputFile.listFiles()) {
				copy(child, new File(outputFile.getPath() + "/" + child.getName()), isOverWrite);
			}
		}
	}

	/**
	 * 功能：copy单个文件
	 * 
	 * @param inputFile
	 *            源文件
	 * @param outputFile
	 *            目标文件
	 * @param isOverWrite
	 *            是否允许覆盖
	 * @throws IOException
	 */
	private static void copySimpleFile(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
		// 目标文件已经存在
		if (outputFile.exists()) {
			if (isOverWrite) {
				if (!outputFile.delete()) {
					throw new RuntimeException(outputFile.getPath() + "无法覆盖！");
				}
			} else {
				// 不允许覆盖
				return;
			}
		}
		InputStream in = new FileInputStream(inputFile);
		OutputStream out = new FileOutputStream(outputFile);
		byte[] buffer = new byte[1024];
		int read = 0;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
		in.close();
		out.close();
	}

	/**
	 * 功能：删除文件
	 * 
	 * @param file
	 *            文件
	 */
	public static void delete(File file) {
		deleteFile(file);
	}

	/**
	 * 功能：删除文件，内部递归使用
	 * 
	 * @param file
	 *            文件
	 * @return boolean true 删除成功，false 删除失败。
	 */
	private static void deleteFile(File file) {
		if (file == null || !file.exists()) {
			return;
		}
		// 单文件
		if (!file.isDirectory()) {
			boolean delFlag = file.delete();
			if (!delFlag) {
				throw new RuntimeException(file.getPath() + "删除失败！");
			} else {
				return;
			}
		}
		// 删除子目录
		for (File child : file.listFiles()) {
			deleteFile(child);
		}
		// 删除自己
		file.delete();
	}

	/**
	 * 从文件路径中抽取文件的扩展名, 例如. "mypath/myfile.txt" -> "txt". *
	 * 
	 * @param 文件路径
	 * @return 如果path为null，直接返回null。
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return null;
		}
		int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return null;
		}
		return path.substring(extIndex + 1);
	}

	/**
	 * 从文件路径中抽取文件名, 例如： "mypath/myfile.txt" -> "myfile.txt"。
	 * 
	 * @param path
	 *            文件路径。
	 * @return 抽取出来的文件名, 如果path为null，直接返回null。
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
	}

	/**
	 * 功能：保存文件。
	 * 
	 * @param content
	 *            字节
	 * @param file
	 *            保存到的文件
	 * @throws IOException
	 */
	public static void save(byte[] content, File file) throws IOException {
		if (file == null) {
			throw new RuntimeException("保存文件不能为空");
		}
		if (content == null) {
			throw new RuntimeException("文件流不能为空");
		}
		InputStream is = new ByteArrayInputStream(content);
		save(is, file);
	}

	/**
	 * 功能：保存文件
	 * 
	 * @param streamIn
	 *            文件流
	 * @param file
	 *            保存到的文件
	 * @throws IOException
	 */
	public static void save(InputStream streamIn, File file) throws IOException {
		if (file == null) {
			throw new RuntimeException("保存文件不能为空");
		}
		if (streamIn == null) {
			throw new RuntimeException("文件流不能为空");
		}
		// 输出流
		OutputStream streamOut = null;
		// 文件夹不存在就创建。
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		streamOut = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
			streamOut.write(buffer, 0, bytesRead);
		}
		streamOut.close();
		streamIn.close();
	}

	/**
	 * 列出某文件夹及其子文件夹下面的文件，并可根据扩展名过滤
	 * 
	 * @param path
	 */
	public static void list(File path) {
		if (!path.exists()) {
			System.out.println("文件名称不存在!");
		} else {
			if (path.isFile()) {
				if (path.getName().toLowerCase().endsWith(".pdf") || path.getName().toLowerCase().endsWith(".doc") || path.getName().toLowerCase().endsWith(".html") || path.getName().toLowerCase().endsWith(".htm")) {
					System.out.println(path);
					System.out.println(path.getName());
				}
			} else {
				File[] files = path.listFiles();
				for (int i = 0; i < files.length; i++) {
					list(files[i]);
				}
			}
		}
	}

	/**
	 * 拷贝一个目录或者文件到指定路径下
	 * 
	 * @param source
	 * @param target
	 */
	public static void copy(File source, File target) {
		File tarpath = new File(target, source.getName());
		if (source.isDirectory()) {
			tarpath.mkdir();
			File[] dir = source.listFiles();
			for (int i = 0; i < dir.length; i++) {
				copy(dir[i], tarpath);
			}
		} else {
			try {
				InputStream is = new FileInputStream(source);
				OutputStream os = new FileOutputStream(tarpath);
				byte[] buf = new byte[1024];
				int len = 0;
				while ((len = is.read(buf)) != -1) {
					os.write(buf, 0, len);
				}
				is.close();
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 上传 文件
	 * <p>
	 * Title: upload
	 * </p>
	 * 
	 * @param @param logo 文件
	 * @param @param path 目标路径
	 * @param @param newFileName 修改后的文件名
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static int upload(File logo, String newFileName, String path) {
		int count = 0;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(logo);
			String newPath = path + "/" + newFileName;
			fos = new FileOutputStream(newPath);
			byte[] buffer = new byte[10240];

			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}

			count++;

			if (fos != null)
				fos.close();
			if (fis != null)
				fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			return count;
		}
		return count;
	}

	/**
	 * 获取批量上传图片名称
	 * <p>
	 * Title: addImages
	 * </p>
	 * 
	 * @param @param logo 文件数组
	 * @param @param logoFileName 文件名称数组
	 * @param @param path 目标路名
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static int batchUploadFiles(File logo[], String[] logoFileName, String path) {
		int count = 0;
		for (int i = 0; i < logo.length; i++) {
			String newFileName = replacingFileName(logoFileName[i]);
			if (upload(logo[i], newFileName, path) > 0) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 判断 上传 文件 后缀格式
	 * <p>
	 * Title: isFileNameType
	 * </p>
	 * 
	 * @param @param logoFileName
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isFileNameType(String logoFileName) {
		String fileName = getFileNameType(logoFileName);
		if (((fileName.equals(".jpge") || fileName.equals(".JPGE")) || (fileName.equals(".peng") || fileName.equals(".PENG")) || (fileName.equals(".gif") || fileName.equals(".GIF")) || (fileName.equals(".jpg") || fileName.equals(".JPG")) || (fileName.equals(".bmp") || fileName.equals(".BMP"))) || ((fileName.equals(".png") || fileName.equals(".PNG")))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 创建 文件 信息
	 * <p>
	 * Title: newFileInfo
	 * </p>
	 * 
	 * @param @param filePathName
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean newFileInfo(String filePathName) {
		try {
			File file = new File(filePathName);
			if (!file.isFile()) {
				file.mkdirs();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 删除 原有 文件
	 * <p>
	 * Title: deleteFile
	 * </p>
	 * 
	 * @param @param filePathName 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void deleteFile(String filePathName) {
		File file = new File(filePathName);
		if (file.isFile()) {
			file.delete();
		}
	}

	/**
	 * 取得 上传 文件 格式名称
	 * <p>
	 * Title: getFileNameType
	 * </p>
	 * 
	 * @param @param logoFileName
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	private static String getFileNameType(String logoFileName) {
		String fileName = logoFileName.substring(logoFileName.lastIndexOf('.'));
		return fileName;
	}

	/**
	 * 获取随机数
	 * <p>
	 * Title: getRandomNumber
	 * </p>
	 * 
	 * @param @param median
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	private static String getRandomNumber(int median) {
		String randomNumber = "";
		String[] markString = { "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		for (int i = 0; i < median; i++) {
			int position = (int) (Math.random() * 57);
			randomNumber += markString[position];
		}
		return randomNumber;
	}

	/**
	 * 更换文件名称
	 * <p>
	 * Title: replacingFileName
	 * </p>
	 * 
	 * @param @param logoFileName
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	private static String replacingFileName(String logoFileName) {
		return getRandomNumber(5) + "_" + logoFileName;
	}
}