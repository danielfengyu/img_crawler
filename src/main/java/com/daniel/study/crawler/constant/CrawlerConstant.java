/**
 * @author    fengyu
 * @filename  2017年5月15日-CrawlerConstant.java
 * @time      下午9:01:51
 * @classname CrawlerConstant
 */
package com.daniel.study.crawler.constant;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.daniel.study.crawler.utils.PropertiesUtil;




/**
 * @author fengyu
 * @time 2017年5月15日::下午9:01:51
 *
 */
public class CrawlerConstant
{
	private static final Logger lg = LoggerFactory.getLogger(CrawlerConstant.class);
	// 种子路径
	public static String SEED_PATH;

	// 采集数据存放路径
	public static String COLLET_DATA_STORAGE_PATH;

	// 爬取的URL的正则表达式
	public static String URL_REGEX;

	// 过滤URL的条件，满足这些条件的URL加入CrawlDatums中。
	public static String FILTER_CONDITIONS;

	// 存储crawldatums的目录
	public static String CRAWLDATUMS_DIR;

	// 抓取线程数
	public static Integer THREAD_NUMBER;

	// 爬取深度
	public static Integer DEPTH;
	static
	{
		init();
	}

	/**
	 * @author fengyu
	 * @time 2017年5月15日::下午9:04:51
	 * @return void
	 */
	private static void init()
	{
		try
		{
			Properties properties = PropertiesUtil
					.getPropertiesByCurrentClassLoader( "config.properties" );
			SEED_PATH = properties.getProperty( "seed" );
			COLLET_DATA_STORAGE_PATH = properties.getProperty( "path" );
			URL_REGEX = properties.getProperty( "regex" );
			FILTER_CONDITIONS = properties.getProperty( "filter_conditions" );
			CRAWLDATUMS_DIR = properties.getProperty( "crawldatums_dir" );
			THREAD_NUMBER = Integer.parseInt( properties.getProperty( "thread_number" ).trim() );
			DEPTH = Integer.parseInt( properties.getProperty( "depth" ).trim() );
		}
		catch (IOException e)
		{
			lg.error("read config file failed:",e);
		}
	}
}
