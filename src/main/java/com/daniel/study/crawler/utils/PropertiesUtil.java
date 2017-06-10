/**
 * @author    fengyu
 * @filename  2017年5月15日-PropertiesUtil.java
 * @time      下午8:53:47
 * @classname PropertiesUtil
 */
package com.daniel.study.crawler.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author fengyu
 * @time 2017年5月15日::下午8:53:47
 *
 */
public class PropertiesUtil
{
	public static InputStream getPropertiesAsStream(String path) throws FileNotFoundException
	{
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream( path );
		return in;
	}

	public static Properties getPropertiesByCurrentClassLoader(String path) throws IOException
	{
		Properties properties = new Properties();
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream( path );
		properties.load( in );
		if (in != null)
		{
			in.close();
		}
		return properties;
	}
	
	public static Properties getPropertiesByFile(String path)
	{
		Properties properties = new Properties();
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream( new File( path ) );
			properties.load( fis );

		}
		catch (Exception e)
		{

		}
		finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return properties;
	}

	public static Properties getPropertiesByClassLoader(String path) throws IOException
	{
		Properties properties = new Properties();
		InputStream in = ClassLoader.getSystemResourceAsStream( path );
		properties.load( in );
		if (in != null)
		{
			in.close();
		}
		return properties;
	}

	public static Properties getPropertiesByURL(String path) throws IOException
	{
		Properties properties = new Properties();
		URL url = new URL( path );
		InputStream in = url.openStream();
		properties.load( in );
		if (in != null)
		{
			in.close();
		}
		return properties;
	}

	public static ResourceBundle getResourceByBundle(String baseName)
	{
		ResourceBundle resource = ResourceBundle.getBundle( baseName );
		return resource;
	}

	public static ResourceBundle getResourceByStream(String path) throws IOException
	{
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream( path );
		ResourceBundle resource = new PropertyResourceBundle( in );
		if (in != null)
		{
			in.close();
		}
		return resource;
	}
}
