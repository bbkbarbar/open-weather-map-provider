package hu.barbar.owm.api.util;

public class Config {

	public static String DATE_FORMAT = "yyyy.MM.dd HH:mm"; //"yyyy.MM.dd HH:mm Z"
	
	public static String FOLDER_TO_STORE_IMAGES = "res\\";

	
	public static void setDateFormat(String formatPattern){
		Config.DATE_FORMAT = formatPattern;
	}
	
	public static void setFolderToStoreImages(String path){
		Config.FOLDER_TO_STORE_IMAGES = path;
	}
	
}
