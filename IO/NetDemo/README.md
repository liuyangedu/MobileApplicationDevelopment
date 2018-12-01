# NetDemo

## 1. 整体逻辑

两个 Activity。MainActivity 为入口，用于输入网址、读取信息、跳转到 ViewActivity。ViewActivity 中演示了如何使用 `WebView` 来浏览网页

## 2. 网络数据读取

读取网络数据必须要在单独的线程中执行，本工程采用 `AsyncTask`实现。

AsyncTask是安卓自带的类，可以方便的启动后台线程。

- 它是一个泛型类，有三个类型参数。第一个参数为输入的类型，第二个参数为任务进度的类型，第三个参数为输出的类型。本工程中它们分别为`String`,`Integer`,`String`；分别对应网址、下载的字节数、网址返回的字符串。
- 它如果要引用界面上的元素最好采用 `WeakReference` 的形式，避免内存泄漏
- 函数`doInBackground`函数为关键函数，它里面的代码会在线程中执行。
- 其他生命周期函数的具体使用参见工程

## 3. WebView

WebView 为安卓自带控件，用于显示网页。在使用时有以下注意事项

- 一般都会通过`setWebViewClient`函数设置新的 WebClient。如果不这么做的话，在 WebView 中点击其他链接，安卓系统会启动它默认的浏览器打开，而不是使用当前 WebView 打开
- 通过`getSettings`函数可以修改一些重要的设置
- 一般需要重写它所在 Activity 的`onKeyDown`函数，判断一下如果WebView可以回退的话，则把BACK的 KeyEvent 交给WebView 去处理。

