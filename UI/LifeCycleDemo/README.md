# LifeCycleDemo

## 1. 生命周期演示


在活动中重写六个生命周期函数，并输出日志。

界面上的`TextView`可以点击，点击后将销毁整个活动

需要注意的几点：

1. `onDestroy`,`onStop`,`onPause`这几个涉及资源释放的函数，最好把对父类函数的调用放在最后。因为如果放在前面的话，可能`super.onDestroy()`已经销毁整个活动了，一些变量就会失效，进而导致运行错误。
2. 直接在`onCreate`中调用`finish`的话，将不调用`onStop`和`onPause`，直接调用`onDestroy`
3. 正常调用`finish`函数，活动将依照生命周期正常销毁。