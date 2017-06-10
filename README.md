# img_crawler
这是一个基于开源爬虫框架WebCollector的网络图片爬虫，可以爬取指定网站的图片，并按网站的图片存放路径把图片存放到当前工程的根目录下。
#运行环境
- jdk8
- maven 3.2.5

#应用配置
在启动之前需要先配置src/main/resource下的config.properties文件，在该文件配置一些爬虫所需的基本数据，打开该文件，按注释配置即可
启动

#运行
1. 运行工程根目录下的mvn.bat脚本，编译工程
2. 运行startup.bat脚本，启动工程，开始爬取指定网站的图片