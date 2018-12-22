# ServiceDemo

## 1. 整体逻辑

本工程简单演示一个简单的音乐播放器。

共有两个界面一模一样的 Activity，它可以控制音乐的播放、暂停；可以停止服务，开始服务、绑定服务

解绑服务。

## 2. 服务

MusicService 为服务类，里面又定义了一个MyBinder类。

MyBinder 类用于实现其他组件和 Service 的通信。在 Service 的`onBind` 函数中，会返回一个 MyBinder 的实例，并能被其他组件通过`ServiceConnection`拿到。因此MyBinder类中一般定义一些用于控制服务的方法。本例中定义了音乐的播放和暂停函数。

媒体资源的播放使用安卓自带的 MediaPlayer 完成，它自动开启一个线程播放音乐。

## 3. 动态注册

通过`Context.registerReceiver`函数实现广播接收器的动态注册。

通过动态注册的接收器一定要记得在合适的时机调用`Context.unregisterReceiver`函数完成反注册。

动态注册的广播接收器必须要打开应用，并且执行完`Context.registerReceiver`函数才会生效。

## 4. 生命周期

和 Activity 类似，服务也有自己的生命周期。

Started 服务和 Bound 服务的生命周期顺序略有不同。一个服务可以即被 Start 也被 Bind。

1. `onCreate`函数当第一次启动（包括 start 和 bind）服务时调用，而且只会调用一次
2. `onStartCommand`函数每 start 一次就会调用一次
3. `onBind`第一次 bind 时调用
4. `onUnbind`函数当没有其他组件 bind 至本服务时调用
5. `onDestroy`函数当没有其他组件 bind 和 start 本服务时调用

## 5. 注意事项

1. 在 Activity 中连续两次调用`unbindService` 函数会报错，因此建议额外设置一个布尔型变量，用于标记是否已经和服务解绑
2. 注意资源的释放。在 Activity 的`onDestroy` 函数中解绑服务；在Service 的`onDestroy`函数中释放播放器资源