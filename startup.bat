@echo off
copy .\target\img_crawler-0.0.1-SNAPSHOT.jar . 
java -jar -Dfile.encoding=gbk img_crawler-0.0.1-SNAPSHOT.jar
@pause