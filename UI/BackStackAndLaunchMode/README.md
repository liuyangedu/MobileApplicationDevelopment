# BackStackAndLaunchMode

## 1. BaseActivity

本项目所有的活动的界面和控件的行为都是一样的，只是注册时启动模式不一样，所以为了减少代码量，实现代码复用，将活动的代码都写在`BaseActivity`中，其余活动都继承`BaseActivity`即可。

和前面的例子比，`BaseActivity`额外重写了生命周期函数`onNewIntent`。

BaseActivity 可以不用注册。

## 2. onPause

当一个活动部分被遮挡时，该活动会进入Pause状态，安卓系统调用生命周期函数`onPause`，但是不会调用`onStop`。

通过弹出一个对话框即可实现上述效果。

新建一个活动，在注册时指定其主题为对话框即可。

# 3. 控件响应

`View.OnClickListener`是一个接口，另外一种常用的给控件添加响应的方法如下：

1. 活动所在的类实现接口`View.OnClickListener`，并实现其中的函数`onClick`

2. 需要响应点击事件的控件`a`通过代码`a.setsetOnClickListener(this)`即可让`a`响应点击事件，其中的`this`指活动所在的类

3. `onClick`函数中一般需要对参数`view`的`id`进行`switch`操作，来判断用户到底在界面上点击了哪个控件

# 4. 启动模式

项目中有5个不同启动模式的活动，通过在界面上点击即可启动对应的活动。

通过观察界面上的TextView的字符串和LogCat的输出可以测试不同启动模式下活动的行为