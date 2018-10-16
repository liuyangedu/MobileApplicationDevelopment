# LifeCycleDemo

## 1. 增加类 BaseActivity
新建 Activity 的默认父类为 `AppCompatActivity`。

我们在项目中新加一个类`BaseActivity`，它继承自`AppCompatActivity`。同时重写(Override)六个生命周期函数：`onCreate`，`onDestroy`，`onStart`，`onStop`，`onResume`，`onPause`。在重写的函数中通过`Log`输出日志

然后修改 `MainActivity` 的父类为 `BaseActivity`
