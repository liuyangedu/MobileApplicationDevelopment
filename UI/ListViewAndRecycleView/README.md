# ListView and RecyclerView

## 1. 工程结构
adapter目录下包含3个Adapter：

1. MyAdapter。在CustomListViewActivity上使用的Adapter
2. MyRecycleAdapter。在RecycleViewActivity中使用的Adapter
3. StaggerAdapter。在StaggerRecyclerViewActivity中使用的Adapter

activity 目录下包含6个activity：

1. MainActivity 为入口
2. ArrayListViewActivity 为使用ArrayAdapter+ListView的Activity
3. SimpleListViewActivity为使用SimpleAdapter+ListView的Activity
4. CustomListViewActivity为使用自定义的MyAdapter+ListView的Activity
5. RecycleViewActivity 为使用MyRecycleAdapter+RecyclerView的Activity
6. StaggerRecyclerViewActivity 为展示瀑布流的 RecyclerView 的Activity


## 2. 其他注意事项
- MyAdapter.java中有两个`getView`函数，一个为使用ViewHolder模式的，一个为没有使用ViewHolder模式的。
- ArrayListViewActivity中有静态函数`getData`，负责生成字符串列表
- SimpleListViewActivity中有静态函数`getData`, 负责生成`List<Map<String, Object>>`类型的数据
- 把所有的响应都写在`onBindViewHolder`函数中的一个主要原因是需要使用`viewHolder.getAdapterPosition()`获取当前项的序号，而变量`viewHolder`必须在`onBindViewHolder`中访问。
- RecyclerViewActivity中为Item的插入和删除增加了默认动画，要想让它生效，则需要在修改数据后调用`notifyItemInserted(int)`或`notifyItemRemoved(int)`函数，而不是`notifyDataSetChanged()`
- Java中，内部匿名类（比如各种Listener）在使用其外部变量时，要求变量是final的，这是为了避免该变量被修改。一个非final变量要在内部匿名类中使用，请重新定义一个同类型的finla变量，并使用这个final的变量。
