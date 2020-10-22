# FragmentDemo

## 1.  工程结构

本工程演示了`Fragment`的基本使用。

工程中包括3个`Activity`类和3个`Fragment`类。

activity文件夹中包括：

1. `MainActivity`：入口活动，其中包括2个按钮，分别跳转到能演示两种操作碎片方式的活动。
2. `ReplaceFragmentActivity`：演示使用`replace`的方法动态增添碎片的方式。活动有两种布局，横屏一种，竖屏一种。竖屏的布局中包含一个`FrameLayout`，用于显示碎片。横屏的布局包含左右两个`FrameLayout`，左边显示`ListFragment`，即列表，右边显示`ETFragment`或`TVFragment`。
3. `HideAndShowFragmentActivity`：演示使用`hide`和`show`的方法动态增添碎片的方式。布局和上面的活动一模一样
4. `IChangeFragment`：接口，包含两个切换碎片的函。`ReplaceFragmentActivity`和`HideAndShowFragmentActivity`需要实现这个接口，确保在碎片中可以将活动转换为`IChangeFragment`类型，并调用其中的两个函数。

fragment文件夹中包括：

1. `ListFragment`：列表碎片，模拟新闻列表。其中包含两个按钮。对应不同的内容。
2. `ETFragment`：内容碎片1。里面包含一个`EditText`和`Button`。点击其中的`Button`能将`EditText`中的文本传递给活动，并修改`TVFragment`中`TextView`的字符串
3. `TVFragment`：内容碎片2。里面包含一个`TextView`。点击`TextView`能将其显示的文本传递给活动，并修改`ETFragment`中`EditText`的字符串。





## 2. 带参数的构建碎片

构建碎片时，难免需要传递参数。但是使用传统的增加**带参数的构造函数**的方法的话，当屏幕翻转时会报错。

因为屏幕翻转时，活动需要销毁并重新生成，碎片依附于活动，因此也需要销毁并重新生成。

安卓系统自动生成碎片时，需要调用它的**默认构造函数（就是没有参数的那个）**。

Java会给所有没有写构造函数的类自动添加一个空的默认构造函数，但是如果类有其他构造函数，则不会自动生成。

所以给碎片类增加带参数的构造函数会导致碎片类没有默认构造函数，因此报错。



## 3. 替换碎片

如果碎片类型动态变化（比如新闻内容），则适合使用替换的方式切换碎片。

每次都构造新的碎片实例，并通过`FragmentTransaction`的`replace`函数进行替换即可

建议：替换前可以使用`FragmentManager`的`FindFragmentById`获取当前显示的碎片，并判断类型来决定是否需要替换，避免重复替换。

`FragmentTransaction`的`addToBackStack()`函数可以将碎片的切换动作加入返回栈



## 4. 隐藏和显示碎片

如果碎片类型固定（比如微信的“聊天”、“通讯录”、“发现”，“我“四个界面），则适合使用隐藏和显示的方法切换碎片。

先把所有的碎片都实例化，并`add`到`FragmentManager`中，然后将其余的通过`hide`函数隐藏，只保留需要显示的。

切换时，只需要隐藏当前碎片，并将需要显示的碎片`show`出来即可。



## 5. 判断横屏竖屏

为横屏和竖屏写两套布局，竖屏的布局中只有`R.id.left_container`，横屏的布局中包含`R.id.left_container`和`R.id.right_container`。

所以只需要判断`findViewById(R.id.right_container)`是否返回空即可判断是横屏还是竖屏



## 6. 碎片向活动发送消息

碎片中可以调用函数`getActivity()`得到它依附的活动实例。

通过该实例即可方便地发送消息



## 7. 活动向碎片发送消息

活动通过`FragmentManager`管理碎片。`FragmentManager`的函数`findFragmentById()`可以获取布局中显示的活动实例。

通过该实例即可方便地发送消息

