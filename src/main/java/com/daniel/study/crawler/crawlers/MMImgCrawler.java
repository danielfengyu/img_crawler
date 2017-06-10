/**
 * @author    fengyu
 * @filename  2017年5月15日-MMImgCrawler1.java
 * @time      下午8:24:47
 * @classname MMImgCrawler1
 */
package com.daniel.study.crawler.crawlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;

import com.daniel.study.crawler.constant.CrawlerConstant;


/**
 * @author fengyu
 * @time 2017年5月15日::下午8:24:47
 *
 */
public class MMImgCrawler extends BreadthCrawler
{
	private static final Logger LOGGER = LoggerFactory.getLogger( MMImgCrawler.class );
	private File downloadDir;
	/**
	 * @author fengyu
	 * @time 2017年5月15日::下午8:25:07
	 * @param crawlPath
	 * @param autoParse
	 */
	public MMImgCrawler(String crawlPath, boolean autoParse)
	{
		super( crawlPath, autoParse );
		downloadDir = new File( CrawlerConstant.COLLET_DATA_STORAGE_PATH );
		if (!downloadDir.exists())
		{
			downloadDir.mkdirs();
		}
	}

	public void visit(Page page, CrawlDatums next)
	{
		String url = page.url();
		String contentType = page.contentType();
		if (contentType.contains( "html" ))
		{
			Elements imgs = page.select( "img[src]" );
			Elements htmlUrls = page.select( "a[href]" );
			for (Element img : imgs)
			{
				String imgSrc = img.absUrl( "src" );
				LOGGER.info( "图片：{}", imgSrc );
				next.add( imgSrc );
			}
			for (Element htmlUrl : htmlUrls)
			{
				String htmlSrc = htmlUrl.absUrl( "href" );
				LOGGER.info( "abs html:{}", htmlSrc );
				next.add( htmlSrc );
			}
		}
		else if (contentType.contains( "image" ))
		{
			LOGGER.info( "img url:{}", url );
			String[] urlParts = url.split( "/" );
			int urlPartsSize = urlParts.length;
			String fileName = "";
			for (int i = 0; i < urlPartsSize; i++)
			{
				if (i > 2)
				{
					fileName += "/";
					fileName += urlParts[i];
				}
			}
			try
			{
				File img = new File( downloadDir, fileName );
				FileUtils.write( img, page.content() );
				LOGGER.info( "保存图片：{}到：{}", url, img );
			}
			catch (FileNotFoundException e)
			{
				LOGGER.error( "file not find error:",e );
			}
			catch (IOException e)
			{
				LOGGER.error( "io error:", e );
			}
		}
	}
}
