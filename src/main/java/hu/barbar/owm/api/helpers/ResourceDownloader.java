package hu.barbar.owm.api.helpers;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import hu.barbar.owm.api.util.Config;

public class ResourceDownloader {

	//TODO: change it to works fine on a linux-based system too..
	private static final char PATH_SEPARATOR = '\\';

	static Image downloadImage(String imageUrl) {
		Image image = null;
		try {
			URL url = new URL(imageUrl);
			image = ImageIO.read(url);
			return image;
		} catch (IOException e) {
			System.out.println(e.toString());
			return null;
		}
	}

	static byte[] downloadImageAsBytes(String imageUrl) {
		URL url;
		try {
			url = new URL(imageUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		InputStream in;
		try {
			in = new BufferedInputStream(url.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1 != (n = in.read(buf))) {
				out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();
			return response;
		} catch (UnknownHostException e){
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static String getFileNameFromUrl(String imageUrl) {
		if(imageUrl == null || !(imageUrl.contains("/")) ){
			return null;
		}
		String[] parts = imageUrl.split("/");
		if(parts.length == 0){
			return null;
		}
		return parts[parts.length-1];
	}
	
	static String getExtensionFromFileName(String fileName) {
		if(fileName == null || !(fileName.contains(".")) ){
			return null;
		}
		return fileName.substring(fileName.lastIndexOf('.')+1);
	}

	static boolean saveImage(byte[] input, String filePath) {
		if(input == null || filePath == null){
			return false;
		}
		
		if(pathContainsFolder(filePath)){
			String folderPart = getFolderPartFromPath(filePath);
			if(!folderExists(folderPart)){
				createDirectory(folderPart);
			}
		}
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			fos.write(input);
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try{
				fos.close();
				return true;
			}catch(Exception e){}
		}
		return false;
		
	}
	
	static String getFolderPartFromPath(String filePath) {
		if(filePath == null){
			return null;
		}
		return filePath.substring(0, filePath.lastIndexOf(PATH_SEPARATOR));
	}

	static boolean pathContainsFolder(String filePath) {
		return filePath.contains(PATH_SEPARATOR + "");
	}

	static boolean downloadAndSaveImage(String imageUrl) {
		return saveImage(downloadImageAsBytes(imageUrl), Config.FOLDER_TO_STORE_IMAGES + getFileNameFromUrl(imageUrl) );
	}

	
	/**
	 * Download icon (if it is not downloaded yet) and <br>
	 * Get local path for it.
	 * @param imageUrl
	 * @return
	 */
	public static String getImageAsPath(String imageUrl) {
		
		if(imageUrl == null){
			return null;
		}
		
		if(fileExists(Config.FOLDER_TO_STORE_IMAGES + getFileNameFromUrl(imageUrl))){
			// get the path for image if it has been downloaded earlier..
			return Config.FOLDER_TO_STORE_IMAGES + getFileNameFromUrl(imageUrl);
		}else{
			// download image if it isn't present locally yet..
			if( downloadAndSaveImage(imageUrl) ){
				return Config.FOLDER_TO_STORE_IMAGES + getFileNameFromUrl(imageUrl);
			}else{
				return null;
			}
		}
		
	}
	
	
 	private static boolean fileExists(String filePathString) {
		File f = new File(filePathString);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}else{
			return false;
		}
	}
 	
 	private static boolean folderExists(String folderPath) {
 		File f = new File(folderPath);
		if(f.exists() && f.isDirectory()) { 
		    return true;
		}else{
			return false;
		}
	}
 	
 	private static void createDirectory(String dirName){
		File theDir = new File(dirName);
		if (!theDir.exists()) {
		    try{
		        theDir.mkdir();
		    } 
		    catch(SecurityException se){
		    }        
		}
	}

}
