# BroadcastReceiverDemo

## 1. 整体逻辑

本工程简单演示了广播接收器的使用。

act 目录下有四个 Activity：

1. MainActivity为入口
2. DynamicActivity展示了如何动态注册广播接收器，并自动读取短信中验证码的方法
3. SendActivity展示了如何发送广播
4. LocalActivity 展示了如何发送本地广播，并利用本地广播代替消息队列的方法。

receiver目录下为一个广播接收器，用于展示静态注册

utils目录下为一个类，包含了整个工程中常用的函数：发送广播和读取短信内容



## 2. 静态注册

广播接收器为四大组件之一，因此需要注册。

静态注册是像 Activity 一样，在 Manifests 文件中注册一个广播接收器，并通过 Intent-Filter指明它要接受的广播类型。

静态注册的广播接收器只要安装就会生效，无需打开应用。

## 3. 动态注册

通过`Context.registerReceiver`函数实现广播接收器的动态注册。

通过动态注册的接收器一定要记得在合适的时机调用`Context.unregisterReceiver`函数完成反注册。

动态注册的广播接收器必须要打开应用，并且执行完`Context.registerReceiver`函数才会生效。

## 4. 本地广播接收器

如果广播接收器的注册是用`LocalBroadcastManager.registerReceiver`完成，则它为本地广播接收器，只会接受本地广播。

通过`LocalBroadcastManager.sendBroadcast`可以发送本地广播。

发送本地广播—>接收广播 的机制可以部分代替消息队列的功能。

