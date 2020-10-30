# ListViewDemo

## 1. 工程结构
项目中包含4个活动。包data中包含数据生成的类`DataGenerator`和新闻实体类`News`。

1. `MainActivity`。入口活动，主要负责界面跳转
2. `ArrayAdapterActivity`。演示`ArrayAdapter`使用的活动
3. `SimpleAdapterActivity`。演示`SimpleAdapterActivity`使用的活动
4. `CustomBaseAdapterActivity`。演示如何通过继承`BaseAdapter`实现自定义适配器的活动

所有演示ListView的活动均使用布局`R.layout.activity_listview`。该布局上面大部分为`ListView`，紧跟着一个分割线，然后是两个按钮，分别对应在`ListView`中增加和删除数据



