# ImplicitIntent

## 1. 给控件增加响应的第三种方法

依然是通过setXXXListener 函数来增加响应。

该函数需要传入一个 XXXListener 的实例，所以可以选择新建一个匿名类，实现 XXXListener 接口，更常见的做法是直接让当前的 Activity 实现 XXXListener 接口。

因此设置监听器的函数只要传入 `this` 为参数即可。

在响应函数中一般用 swtich 对控件的 id 进行筛选，执行对应的操作。

## 2. Dial & Web & Alipay

点击不同的按钮后打开对应的界面。

标准的隐式 Intent 用法。指定 Action，Category，Data 即可。

注意`startActivity`要确保`intent`能匹配到至少一个活动

## 3. 分享

 该工程增加了一个`ShareActivity`，安装了该工程后，在任意图片界面点击分享，即能看到这活动
