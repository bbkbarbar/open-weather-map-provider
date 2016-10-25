package hu.barbar.owm.api.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import hu.barbar.owm.api.util.Config;

public class ResourceDownloaderTest {

	String originalFileLocation = null;
	
	public void setUpForTest(){
		originalFileLocation = Config.FOLDER_TO_STORE_IMAGES;
		Config.FOLDER_TO_STORE_IMAGES = "target\\test-resources\\";
		createDirectory(Config.FOLDER_TO_STORE_IMAGES);
		System.out.println("Directory to store downloaded images modified for testing: " + Config.FOLDER_TO_STORE_IMAGES);
	}
	
	public void setBackToOriginal(){
		Config.FOLDER_TO_STORE_IMAGES = originalFileLocation;
		System.out.println("Directory to store downloaded images set back to original: " + Config.FOLDER_TO_STORE_IMAGES);
	}
	
	@Test
	public void getFileNameFromUrlTest() {
		
		String imageUrl = "http://openweathermap.org/img/w/02d.png";
		assertEquals("Filename does not as expected.", "02d.png", ResourceDownloader.getFileNameFromUrl(imageUrl));
		
		imageUrl = "02d.png";
		assertEquals("Filename does not as expected.", null, ResourceDownloader.getFileNameFromUrl(imageUrl));
		
	}
	
	@Test
	public void getExtensionFromFileNameNullTest() {
		assertNull("Without filename it should be null", ResourceDownloader.getExtensionFromFileName(null));
	}
	
	@Test
	public void getExtensionFromFileNameWithoutExtensionTest() {
		assertNull("Without filename it should be null without extension", ResourceDownloader.getExtensionFromFileName("filename"));
	}
	
	@Test
	public void getExtensionFromFileNamePositive1Test() {
		assertEquals("Extension does not as expected.", "png", ResourceDownloader.getExtensionFromFileName("02d.png"));
	}
	
	@Test
	public void getExtensionFromFileNamePositive2Test() {
		assertEquals("Extension does not as expected.", "jpg", ResourceDownloader.getExtensionFromFileName("file.jpg"));
	}
	
	@Test
	public void downloadAndSaveImagePositiveTest() {
		setUpForTest();
		String imageUrl = "http://openweathermap.org/img/w/02d.png";
		assertTrue("Can not download file.", ResourceDownloader.downloadAndSaveImage(imageUrl) );
		assertTrue("File does NOT exists.", fileExists(Config.FOLDER_TO_STORE_IMAGES + ResourceDownloader.getFileNameFromUrl(imageUrl)));
		setBackToOriginal();
	}
	
	@Test
	public void downloadAndSaveImageNegativeTest() {
		assertFalse("Should not able to download file", ResourceDownloader.downloadAndSaveImage("http://not_existing_image_url") );
	}

	@Test
	public void getImageAsPathTest(){
		setUpForTest();
		String imageUrl = "http://openweathermap.org/img/w/02d.png";
		assertEquals(Config.FOLDER_TO_STORE_IMAGES + ResourceDownloader.getFileNameFromUrl(imageUrl), ResourceDownloader.getImageAsPath(imageUrl));
		setBackToOriginal();
	}
	
	@Test
	public void getFolderPartFromPathTest(){
		assertEquals("res", ResourceDownloader.getFolderPartFromPath("res\\file.png"));
		assertEquals("base\\res", ResourceDownloader.getFolderPartFromPath("base\\res\\file.png"));
		assertEquals("d:\\base\\res", ResourceDownloader.getFolderPartFromPath("d:\\base\\res\\file.png"));
	}
	
	@Test
	public void pathContainsFolderTest(){
		assertTrue(ResourceDownloader.pathContainsFolder("res\\file.png"));
		assertTrue(ResourceDownloader.pathContainsFolder("base\\res\\file.png"));
		assertTrue(ResourceDownloader.pathContainsFolder("d:\\base\\res\\file.png"));
		
		assertFalse(ResourceDownloader.pathContainsFolder("file.png"));
	}
	
	
	private boolean fileExists(String filePathString) {
		File f = new File(filePathString);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}else{
			return false;
		}
	}
	
	private void createDirectory(String dirName){
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
