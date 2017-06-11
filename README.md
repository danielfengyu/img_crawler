# img_crawler
这是一个基于开源网络爬虫框架[WebCollector](https://github.com/CrawlScript/WebCollector)的网络图片爬虫，可以爬取指定网站的图片，并按网站的图片存放路径把图片存放到当前工程的根目录下。

WebCollector教程：[https://www.oschina.net/p/webcollector?fromerr=uguyLrsq](https://www.oschina.net/p/webcollector?fromerr=uguyLrsq)

# 获取
git clone https://github.com/danielfengyu/img_crawler.git

# 运行环境
- jdk8
- maven 3.2.5 及以上

# 应用配置
在启动之前需要先配置src/main/resource下的config.properties文件，在该文件配置一些爬虫所需的基本数据，打开该文件，按注释配置即可
启动

# 运行
1. 运行工程根目录下的mvn.bat脚本，编译工程
2. 运行startup.bat脚本，启动工程，开始爬取指定网站的图片

# License
使用该project你必须遵守所在地的法律，不能用其做违法之事；抱着学习、善意的态度使用和修改。