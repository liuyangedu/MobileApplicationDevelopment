# GPSDemo

## 1. 整体逻辑

本工程简单演示GPS 信息的获取。

本工程只有一个 Activity。Activity 界面上面有三个按钮和一个文本框。三个按钮分别用于

1. 获取所有的定位方法
2. 根据一定的标准获取定位方法
3. 从 GPS 中获取定位信息

## 2. 获取定位信息的方法

安卓支持三种方法

1. Passive。被动的方式，从其他 App或服务获取定位信息
2. Network。利用移动网络获取定位。基本原理是根据当前设备周围的若干基站位置进行定位，基站位置越多，定位越准确。不过它的精度还是比不上 GPS
3. GPS。利用 GPS 卫星信号定位

安卓提供了`LocationManager`用于管理定位信息。

获取定位首先需要确保手机打开了定位服务。本工程中利用函数`isGPSEnable`和`openGPS`来判断是否打开定位服务，以及打开相应的设置，让用户去打开定位。

定位信息属于敏感信息，所以需要现在 Manifests中申请权限：

1. `<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>`
2. `<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>`

然后在 Java 代码中再申请一次。其中`android.permission.ACCESS_FINE_LOCATION`包含了权限`android.permission.ACCESS_COARSE_LOCATION`

## 3. 读取定位信息

1. 通过`LocationManager.getLastKnownLocation`函数可以获取最近的位置信息。它的参数是定位方法（Passive,Network,GPS）
2. 通过`LocationManager.requestLocationUpdates`可以实现实时更新定位信息。第一个参数是定位方法（Passive,Network,GPS）；第二个参数是最短更新间隔，单位是 ms；第三个参数是最短更新距离，单位是 m；第四个参数是`LocationListener`接口，该接口中的`onLocationChanged`函数将会在定位信息变化时被调用

不要忘记`removeUpdates`。