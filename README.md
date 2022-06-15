# 本prj使用
## eclipse 2022 3 月版

## JAVA SE-17

## JAVAFX 

## openjfx-18.0.1
## 的jar檔案，都包在資料夾libforPanda
## 其內含有 本prj使用的library

## 方便又好用的	Gson 2.9.0
## 方便但難用的	SQLite jbdc
## 不方便又難用的	org.json
Gson真的簡單便利
SQLite則是資料視覺化的親民程度高。
orj.json...  應該叫orz，如果不是inteliji不支援Gson，我絕對不會用org.json...


## SQLite要先下載才能用	https://sqlite.org/download.html
## DB browser 是視覺化SQLite的好工具好朋友 https://sqlitebrowser.org/dl/

開檔案的教學在demo影片中喔!看影片比較方便!

不然 
請跟著下步驟，載入gson 2.9.0、SQLite jbdc、org.json
project->propertiy->buildpath->classpath->add external jar 

請跟著下步驟，載入javafx全部jar
project->propertiy->buildpath->classpath->add libriry->user library->system library->new->add  external jar


重要重要重重要
project->propertiy->run debug tool->new->java application->  search ->Choose Hellow aplication
project->propertiy->run debug tool->new->java application->  argument VM格子內填入下面文字
 --module-path  !這部分的路徑請根據自己電腦修改\test3\libforPanda\openjfx-18.0.1_windows-x64_bin-sdk\javafx-sdk-18.0.1\lib想辦法找到左邊這個lib資料夾!  --add-modules javafx.controls,javafx.fxml
