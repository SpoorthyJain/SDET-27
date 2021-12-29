package com.crm.autodesk.genericutility;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author Spoorthy
 *
 */
public class FileUtility 
{
	/**
	 * it's used to read the data from data.proprties File, based on Key which you pass as an argument
	 * @param key
	 * @return
	 * @throws Throwable 
	 * @throws IOException
	 */
	public String getPropertyKeyValue(String key) throws Throwable 
	{
		FileInputStream fis = new FileInputStream(IpathConstant.filePath);
		Properties pObj = new Properties();
		pObj.load(fis);
		String value = pObj.getProperty(key);
		return value;
	}
}