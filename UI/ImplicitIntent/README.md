# ImplicitIntent

## 1. 给控件增加响应的方法

依然是通过setXXXListener 函数来增加响应。

该函数需要传入一个 XXXListener 的实例，所以可以选择新建一个类，该类实现 XXXListener 接口，更常见的做法是直接让当前的 Activity 实现 XXXListener 接口。

因此设置监听器的函数只要传入 `this` 为参数即可。

在响应函数中一般用 swtich 对控件的 id 进行筛选，执行对应的操作。

## 2. Dial

点击按钮后打开系统的拨号界面。

标准的隐式 Intent 用法。指定 Action，Category，Data 即可。

## 3. Surf

点击按钮后会打开网页`www.bupt.edu.cn`。同样是隐式 Intent 用法。

不过在 `startActivity`之前通过`resolveActivity`函数检查了是否匹配到 Activity，避免报错。
