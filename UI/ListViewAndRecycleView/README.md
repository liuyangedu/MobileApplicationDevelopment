# ListView and RecyclerView

## 1. 工程结构
adapter目录下包含两个Adapter：

1. MyAdapter。在CustomListViewActivity上使用的Adapter
2. MyRecycleAdapter。在RecycleViewActivity中使用的Adapter

activity 目录下包含5个activity：

1. MainActivity 为入口

2. ArrayListViewActivity 为使用ArrayAdapter+ListView的Activity

3. SimpleListViewActivity为使用SimpleAdapter+ListView的Activity

4. CustomListViewActivity为使用自定义的MyAdapter+ListView的Activity

5. RecycleViewActivity 为使用MyRecycleAdapter+ListView的Activity


## 2. 其他注意事项
- MyAdapter.java中有两个getView函数，一个为使用ViewHolder模式的，一个为没有使用ViewHolder模式的。
- ArrayListViewActivity中有静态函数getData，负责生成字符串列表
- SimpleListViewActivity中有静态函数getData, 负责生产List<Map<String, Object>>数据