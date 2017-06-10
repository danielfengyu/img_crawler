/**
 * @author    fengyu
 * @filename  2017年6月10日-BootStrap.java
 * @time      下午3:27:57
 * @classname BootStrap
 */
package com.daniel.study.crawler.main;

import cn.edu.hfut.dmic.webcollector.util.Config;

import com.daniel.study.crawler.constant.CrawlerConstant;
import com.daniel.study.crawler.crawlers.MMImgCrawler;

/**
 * @author fengyu
 * @time 2017年6月10日::下午3:27:57
 *
 */
public class BootStrap
{
	public static void main(String[] args) throws Exception
	{
		MMImgCrawler crawler = new MMImgCrawler( CrawlerConstant.CRAWLDATUMS_DIR, true );
		Config.MAX_RECEIVE_SIZE = 10000 * 10000 * 10;
		crawler.addSeed( CrawlerConstant.SEED_PATH, true );
		crawler.addRegex( CrawlerConstant.URL_REGEX );
		crawler.setResumable( true );
		crawler.setThreads( CrawlerConstant.THREAD_NUMBER );
		crawler.start( CrawlerConstant.DEPTH );
	}
}
