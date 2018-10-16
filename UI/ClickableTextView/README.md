# ClickableTextview

## 1. 增加控件的响应

在 res/layout 下，打开对应的布局文件，在要增加响应的控件元素中增加属性`onClick`，其值为 Java 类中的一个函数名（假设为fun1)。

然后在对应的 Java 类中实现函数 `public void fun1(View view)`

## 2. Java 变量和控件的绑定

在 res/layout 下，打开对应的布局文件，在要绑定的控件元素中增加属性`id`，其值为`@+id/xxx`，其中`xxx`为自定义的 id。

在对应的 Java 类中定义与控件同类型的变量，并用`findViewById()`实现绑定。绑定后就可以通过该变量控制控件。

注意：

- 一般Java 变量和控件的绑定都在onCreate 函数中完成
- 一般整个过程都放在一个函数(initView)中，便于阅读代码
