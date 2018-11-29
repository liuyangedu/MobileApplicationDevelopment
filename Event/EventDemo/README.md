# EventDemo

## 1. 工程结构

工程包含四个 Activity。

- MainActiviy 实现不同界面的跳转
- PathActivity主要演示KeyEvent和 MotionEvent的传播路径和响应
- GestureActivity 演示安卓自带手势检测的使用
- CustomGestureActivity 演示自定义手势识别

本工程重写了六个控件，都在controls包中，主要重写了和事件相关的函数，并输出日志

## 2. PathActivity

界面布局层次如下：

- PathActivity
  - MyFrameLayout
    - MyLineaerLayout
      - MyTextView

      - MyButton

      - MyEdit

      - MyEdit

## 3. KeyEvent的传播

点击 ShowPath 按钮，然后按下音量下键，然后查看 LogCat 的输出。因为输出信息较多，最好设置一下 LogCat 的过滤器。

从 LogCat 的日志能明显看出来事件是从 Activity 开始传递，顺序为自顶向下，依次传给 MyFrameLayout, MyLinearLayout, MyEditText。

有几点需要注意：

1. 如果去掉布局中的两个 EditText 再试，会发现只有 Activity 有输出，而MyFrameLayout和MyLinearLayout不会输出日志。因为事件传递时会先判断当前的焦点是否位于该 ViewGroup 下，即事件只会传递给当前为焦点的控件。安卓这样设计的好处是事件在碰到复杂界面时，只会沿着一个分支传递，大大提高效率。所以如果我们去掉两个 EditText，此时布局中没有 Focusable 的控件，因此事件不会再向下传递
2. 对上面描述的机制，从另外一个角度可以得到证实。我们的布局中有两个 EditText，但是从 LogCat 中看，只有一个 EditText 的dispatchKeyEvent函数被调用了。因为另外一个 EditText 没有焦点，所以事件不会传给它。

## 4. KeyEvent的处理

KeyEvent 事件可以通过重写onKeyDown/onKeyUp 或者 设置监听器setOnKeyListener来处理。

前者为回调式，后者为监听式。回调式是只有在能修改类代码的时候才能使用，监听式则不需要，可以给一个已经写好的控件自由设置不同的响应。

我的工程中，在 PathActivity 里通过监听式给met1增加了一个 KeyEvent 响应，而 MyEditText 中本身也有回调式的函数定义。

从安卓源码来说，它会先尝试监听式，如果监听式没有处理事件，则再尝试回调函数。

不管是哪种方式处理事件，只要返回 `true` 则表示消费事件，即该事件已经被处理，不需要再处理了。

我的工程中，PathActivity会消费音量上按钮的 Down点击事件，MyEditText会消费音量下按钮的 Down点击事件。

建议测试流程：

1. 按音量下，查看 LogCat，会发现 MyEditText 再分发事件后，会先出发监听式的响应函数，再触发回调式响应函数。可以修改代码，在监听式响应函数中返回true，再试试，会发现回调式响应函数不会触发了，因为监听式函数中已经处理该事件了。
2. 按音量下，查看 LogCat，会发现在 Action_Down事件中，MyEditText 处理了该事件，导致 PathActivity 的 onKeyDown没有被调用，因为此事件传递到这里时已经被消费了。不过Action_Up 事件在传递到 PathActivity 时，调用了onKeyDown函数。
3. 需要注意一点，我在 MyFrameLayout 和 MyLinearLayout 中都重写了 onKeyDown，onKeyUp函数，但是上述演示过程中始终都没有输出。这是因为这两个控件是不能 Focusable 的，所以默认无法处理 KeyEvent。



## 5. ViewGroup 对 MotionEvent 的拦截

在 PathActivity下，点击任意一个 EditText，再查看日志。首先会发现 MotionEvent 的传递路径和 KeyEvent 是类似的，自顶向下。不过MyFrameLayout和MyLinearLayout作为 ViewGroup，中间多了一个`onInterceptTouchEvent`的函数。

所有 ViewGroup 都有这个函数，它在分发事件之前会先调用该函数决定是否拦截这个 MotionEvent，如果不拦截，则正常向下分发事件，如果拦截，则直接调用自己的处理函数试图处理事件，并不再向下传递。

可以在MyFrameLayout或MyLinearLayout中任意一个的`onInterceptTouchEvent`函数里返回 true，再重复一下上述过程即可看到效果。



## 6. MotionEvent的组合

MotionEvent 是很多触屏事件的基础，比如单击事件(onClick)，长按事件(onLongClick)等。

比如单击事件就是由一个Action_Down和一个 Action_Up 组合而成。

单击事件触发的判定一般在控件自己的onTouchEvent函数中。所以如果中断这个过程会导致单击事件无法触发。

工程中，上面那个 EditText 有单击事件响应，通过在界面点击一下该控件应该能在 LogCat 看到对应的输出。

下面演示两个方法来打断该过程：

1. 通过监听式增加 Touch 的响应，并且直接返回`true`。此时 onClick 将不会响应。请思考为什么。

2. 在 MyEditText 代码中，重写`onTouchEvent`，不要调用`super.onTouchEvent(event)`，直接返回`true`或`false`。

注意上述第二点里返回`true`或`false`时，LogCat 的输出不一样。

如果返回 `false`,会发现 Action_Up不会再传递给这个 EditText。

因为 MotionEvent 很『智能』地具有记忆功能，如果上一个事件你没处理，它默认你不能处理 MotionEvent，所以不会再传递给你。



## 7. 自带手势检测

类`GestureDetector`为安卓自带的手势检测类。

基本的使用方法为：首先实例化一个`GestureDetector`，然后重写 Activity 的 `onTouchEvent`函数，并调用`GestureDetector`的`onTouchEvent`即可。

触发手势后的响应通过实现若干接口后的函数来实现。

`OnGestureListener` 为构造`GestureDetector`时的第二个参数，里面可以检测单击，滚动，长按，滑动等基本手势。

同时可以通过`GestureDetector`的`setOnDoubleTapListener`函数增加双击的响应。

工程中为了完整性，展示了代码比较多的版本。实际开发中，建议使用`SimpleOnGestureListener`，用于减少代码量。具体用法见工程中的注释代码。

`onClick`和手势中的单击事件会冲突，所以如果要同时响应单击和双击，请不要用 onClick,而是在手势接口中处理。具体可以打开工程中相关的代码查看效果。



## 8.自定义手势检测

有两种方法。

第一种是想前面在屏幕两边上下移动的例子一样，完全手动处理，需要自己想办法完成手势的识别和检测。

第二种则较简单，需要借助安卓模拟器中自带的软件：GestureBuilder。

首先在GestureBuilder中定义好手势，然后导出手势文件；

在工程中将手势文件作为 Raw 资源导入；

在需要识别手势的区域放入控件：GestureOverlayView，并用GestureLibrary 导入 Raw 资源里的手势文件；

在 Java 代码中利用 id 获取GestureOverlayView对象，并通过函数`addOnGesturePerformedListener`增加响应；

在响应中通过GestureLibrary的recognize函数尝试识别手势，根据手势的名字和得分来判断是否触发某手势，并执行相应操作。

