# 应用中心

## 项目介绍
  - fs-open-app-center-adapter
    - 用于与.net交互相关的接口承载。
	- 目前已经基本改造成rest接口了，原先的dubbo-fcp接口基本已经不再使用了。
	- 有问题可以直接找夏林锋。
  - fs-open-app-center-external
    - 外部的服务，目前主要的服务有拉取应用管理员的接口。用于北京方面的查询。
	- 外联服务号时添加了一个企业回复消息时.进行是否可回复验证.
  - fs-open-app-center-provider
    - 应用中心所有核心业务。
  - fs-open-app-center-web
    - 应用中心的所有web接口。
  - fs-open-app-manage
    - 开发者管理后台的后台接口支持.

## 模板
  - 生成模板的脚本 见 "初始化应用创建模板execute_json数据脚本.js"
  - 直接可以生成。

## 开发者管理后台
  - 线上环境部署在/opt/tengine
  - 配置文件为 /opt/tengine/conf/nginx.conf
  - 前端的代码已经接入了jenkins，是钟雷云接入的。
    - 逻辑为将前端代码发布到/opt/tengine/www/web 这个目录.
  - 管理后台权限配置地址：http://portal-pay.foneshare.cn/changeSystem?systemId=1
  - 具体的直接找郭明就可以了