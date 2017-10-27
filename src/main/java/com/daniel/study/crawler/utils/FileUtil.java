/**
 * @author    fengyu
 * @filename  2017年5月27日-FileUtil.java
 * @time      下午10:59:23
 * @classname FileUtil
 */
package com.daniel.study.crawler.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fengyu
 * @time 2017年5月27日::下午10:59:23
 *
 */
public class FileUtil
{
	private static final Logger LOGGER = LoggerFactory.getLogger( FileUtil.class );

	public static void writeFile(File parent, String fileName, String extensionName,
			String content)
	{
		File text = new File( parent, fileName + extensionName );
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try
		{
			fos = new FileOutputStream( text );
			bos = new BufferedOutputStream( fos );
			bos.write( content.replace( "<br>", "" ).getBytes() );
		}
		catch (Exception e)
		{
			LOGGER.error( "write file error:", e );
		}
		finally
		{
			try
			{
				if (fos != null)
				{
					fos.close();
				}
			}
			catch (IOException e)
			{
				LOGGER.error( "close file error:", e );
			}
			try
			{
				if (bos != null)
				{
					bos.close();
				}
			}
			catch (IOException e)
			{
				LOGGER.error( "close file error:", e );
			}
		}
	}
}
