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

## 2. ArrayAdapter和SimpleAdapter

ArrayAdapter和SimpleAdapter是安卓系统自带的适配器。

ArrayAdapter主要用于显示一个字符串序列。它的构造函数需要传入布局文件id以及一个字符串数组。

SimpleAdapter可以显示一个自定义布局，在构造函数中需要一个`List<Map<String, Object>>`类型作为数据，同时传入一个字符串数组和整型数组，分别对应`Map<String, Object>中`的Key和布局上对应的控件id



## 3. 自定义适配器

上述两种安卓自带的适配器无法自由控制ListView中的控件，所以一般实际开发中都会选择通过继承`BaseAdapter`来实现自己的适配器。

在自己的适配器中可以根据实际需要定义构造函数。一般至少需要传入布局id和数据。

自己实现的适配器中需要重写几个关键函数：

1. `getCount()`。用于返回当前适配器中的数据数量。
2. `getItem(int)`。返回给定位置的数据。
3. `getVIew()`。最关键的函数，需要通过它来构建每个Item的视图。首先可以使用第二个参数来提高效率，它是专门用于循环使用的一个View。其次为了避免频繁调用findViewById，最好使用ViewHolder设计模式，即在适配器中定义一个ViewHolder类，里面包含了一个Item中的所有控件；并且在第一次`inflate`视图时，将ViewHolder中的变量和视图中的控件绑定，并通过`setTag`函数将其存入视图中。在后续使用时，直接从视图中`getTag`拿到ViewHolder。



## 4. RecyclerView

RecyclerView是后推出的“高级”的ListView，它更加灵活，但是有些地方在使用时比ListView更麻烦。

1. 安卓没有提供默认的适配器实现，需要直接实现
2. 适配器中强制采用ViewHolder模式。
3. 关键函数有：onCreateViewHolder，onBindViewHolder，getItemCount
4. onCreateViewHolder。类似ListView的getView函数，需要构建每一个Item的视图，并将其和一个VIewHolder联系起来
5. onBindViewHolder。在ViewHolder和控件绑定后调用。大部分控件的响应都在此设置
6. getItemCount。类似ListView的getCount函数。

适配器中一般会根据需要定义若干接口，通过接口设置一些控件的响应。下面以Button为例详细解释。

1. 首先适配器中定义名为`OnViewInItemClickListener`的接口，并定义这个类型的变量`_viewInItemClickListener`
2. 在`onBindViewHolder`函数中，给`Button`设置点击响应。在点击响应中，首先判断`_viewInItemClickListener`是否为空，如果为空，则表示没有响应。如果不为空，则调用`_viewInItemClickListener.onClick`进行响应。
3. 在使用适配器时，通过`setViewInItemClickListener`设置控件的响应。
4. 这种写法可以实现控件的响应和适配器内部逻辑分离。可以自由地在适配器外部定义控件的响应。




## 5. 其他注意事项
- MyAdapter.java中有两个`getView`函数，一个为使用ViewHolder模式的，一个为没有使用ViewHolder模式的。
- ArrayListViewActivity中有静态函数`getData`，负责生成字符串列表
- SimpleListViewActivity中有静态函数`getData`, 负责生成`List<Map<String, Object>>`类型的数据
- 把所有的响应都写在`onBindViewHolder`函数中的一个主要原因是需要使用`viewHolder.getAdapterPosition()`获取当前项的序号，而变量`viewHolder`必须在`onBindViewHolder`中访问。
- RecyclerViewActivity中为Item的插入和删除增加了默认动画，要想让它生效，则需要在修改数据后调用`notifyItemInserted(int)`或`notifyItemRemoved(int)`函数，而不是`notifyDataSetChanged()`
- Java中，内部匿名类（比如各种Listener）在使用其外部变量时，要求变量是final的，这是为了避免该变量被修改。一个非final变量要在内部匿名类中使用，请重新定义一个同类型的finla变量，并使用这个final的变量。
- drawable中有一个divider.xml。是用xml定义图形的方法，具体参考:[Drawable Android Docs](https://developer.android.com/guide/topics/resources/drawable-resource#Shape)



