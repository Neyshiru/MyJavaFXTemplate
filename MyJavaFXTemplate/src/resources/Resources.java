package resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.image.Image;

/**
 * Resources class which takes care of files.
 * 
 * @author Lhomme Lucien
 */
public abstract class Resources {
	
	/**
	 * Get the right bundle in the folder "resources/bundles".
	 * @param name String
	 * @return ResourceBundle
	 */
	public static ResourceBundle getBundle(String name) {
		return ResourceBundle.getBundle(String.format("resources/bundles.%s", name));
	}

	/**
	 * Get the right bundle in the folder "resources/bundles" for a specific language.
	 * @param name String
	 * @param locale Locale
	 * @return ResourceBundle
	 */
	public static ResourceBundle getBundle(String name, Locale locale) {
		return ResourceBundle.getBundle(String.format("resources/bundles.%s", name), locale);
	}
	
	/**
	 * Get the right image in the folder "resources/images".
	 * @param name String
	 * @return Image
	 */
	public static Image getImage(String name) {
		return new Image(Resources.class.getResourceAsStream(String.format("images/%s", name)));
	}
	
	/**
	 * Get the url of the path name.
	 * @param name String
	 * @return URL
	 */
	public static URL getURL(String name) {
		return Resources.class.getResource(name);
	}

	/**
	 * Check if the file has the good extension.
	 * @param file File
	 * @param ext String
	 * @return boolean
	 */
	public static boolean hasGoodExtension(File file, String ext) {
		return hasGoodExtension(file.getName(), ext);
	}

	/**
	 * Check if the file name has the good extension.
	 * @param fileName String
	 * @param ext String
	 * @return boolean
	 */
	public static boolean hasGoodExtension(String fileName, String ext) {
		return ext.equalsIgnoreCase(fileName.substring(fileName.lastIndexOf('.')));
	}
	
	public static String chargeStringFile(File file) {
		StringBuilder lines = new StringBuilder();
		if (file.isFile()) {
			if (file.canRead()) {
				try (BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(file), "UTF8"))) {
					String line;
					while ((line = br.readLine()) != null) {
						lines.append(line + "\n");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lines.toString();
	}
	
	public static void saveFile(File file, String text) {
		try {
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(text);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
