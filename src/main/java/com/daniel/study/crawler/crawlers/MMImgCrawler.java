/**
 * @author fengyu
 * @filename 2017年5月15日-MMImgCrawler1.java
 * @time 下午8:24:47
 * @classname MMImgCrawler1
 */
package com.daniel.study.crawler.crawlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 */
public class MMImgCrawler extends BreadthCrawler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MMImgCrawler.class);
    private File downloadDir;
    private List<String> resourceTypes;
    private List<String> travelContentTypes;
    private List<String> downloadContentTypes;
    private Pattern urlAttrPattern;

    /**
     * @param crawlPath
     * @param autoParse
     * @author fengyu
     * @time 2017年5月15日::下午8:25:07
     */
    public MMImgCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        downloadDir = new File(CrawlerConstant.COLLET_DATA_STORAGE_PATH);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }
        resourceTypes = Arrays.asList(CrawlerConstant.ELEMENT_TYPE.split(","));
		travelContentTypes=Arrays.asList(CrawlerConstant.TRAVEL_CONTENT_TYPE.split(","));
		downloadContentTypes=Arrays.asList(CrawlerConstant.DOWNLOAD_CONTENT_TYPE.split(","));
        urlAttrPattern = Pattern.compile("\\w+\\[(.+?)\\]");
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        String url = page.url();
        String contentType = page.contentType();
        if(contentType.indexOf(';')>0){
            contentType=contentType.split(";")[0];
        }
        if (travelContentTypes.contains(contentType)) {
            resourceTypes.forEach(type -> {
                Elements elements = page.select(type);
                for (Element ele : elements) {
                	String elementAttrName = extractAttrName(type);
                	//特殊Attribute
                    if ("data-original".equals(elementAttrName)) {
                        String imgSrc = "http:" + ele.attr("data-original");
                        LOGGER.info("add url：{}", imgSrc);
                        next.add(imgSrc);
                    } else {
                    	//正常Attribute
                        String imgSrc = ele.absUrl(elementAttrName);
                        LOGGER.info("add url：{}", imgSrc);
                        next.add(imgSrc);
                    }
                }
            });
        } else if (downloadContentTypes.contains(contentType)) {
            LOGGER.info("img url:{}", url);
            String fileName = generateFileName(url);
            downloadPageContentAsFile(page, fileName);
        }else{
        	LOGGER.info("UNKNOW CONTENT-TYPE：{},URL:{}",contentType,url);
		}
    }

    /**
     * 更加URL生成下载后的文件名称（含目录名称）
     *
     * @param url
     * @return
     */
    private String generateFileName(String url) {
        String[] urlParts = url.split("/");
        int urlPartsSize = urlParts.length;
        String fileName = "";
        for (int i = 0; i < urlPartsSize; i++) {
            if (i > 2) {
                fileName += "/";
                fileName += urlParts[i];
            }
        }
        return fileName;
    }

    /**
     * 把URL内容下载为指定名称的文件
     *
     * @param page
     * @param fileName
     */
    private void downloadPageContentAsFile(Page page, String fileName) {
        try {
            File img = new File(downloadDir, fileName);
            FileUtils.write(img, page.content());
            LOGGER.info("保存图片：{}到：{}", page.url(), img);
        } catch (FileNotFoundException e) {
            LOGGER.error("file not find error:", e);
        } catch (IOException e) {
            LOGGER.error("io error:", e);
        }
    }

    /**
     * 提取元素中含有URI的属性名称
     *
     * @param elementType
     * @return
     */
    private String extractAttrName(String elementType) {
        Matcher m = urlAttrPattern.matcher(elementType);
        String elementAttrName = "";
        while (m.find()) {
            elementAttrName = m.group(1);
            break;
        }
        return elementAttrName;
    }
}
