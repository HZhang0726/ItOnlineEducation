该项目为前后端分离项目，在用户登录之后可进行课程预览以及详情查看，选择自己喜欢的课程进行下单，以及下单列表的查看等功能。

封装自定义JsonData工具类，来进行请求格式的响应；

使用json wen token进行登录校验；

引入Guava对轮播图、课程列表等接口进行本地缓存；

使用JMeter对加入缓存的接口进行压测，得到在加入缓存前TPS(1900)->加入缓存后TPS(11300)；



###该代码为后端接口代码