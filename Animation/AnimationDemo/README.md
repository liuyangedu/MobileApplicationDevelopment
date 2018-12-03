# AnimationDemo

## 1. 整体逻辑

共五个 Activity。MainActivity 为入口，各界面的跳转。

FrameAnimationActivity 中演示了帧动画的相关内容

TweenAnimationActivity 中演示了补间动画的相关内容

PropertyAnimationActivity 中演示了属性动画的相关内容

LayoutTransitionActivity 中演示了布局中动画的相关内容

## 2. 帧动画

帧动画就是一系列图片的快速播放。

首先把各帧导入工程。然后在drawable下新建资源文件，根元素为`animatio-list`，里面可以设置动画的各帧和持续时间等属性。

Java 代码部分需要通过ImageView的getDrawable函数得到对应的 Drawable，并转为AnimationDrawable 类型。转换后的变量即可操作动画，可以实现动画的播放和停止。

## 3. 补间动画

补间动画也叫关键帧动画，只需要设置好关键帧，和插值方式，系统会自动将中间部分补全，并形成动画。

补间动画只能改变控件的部分属性，一般需要在资源文件夹下新建目录`anim`，在里面定义具体的动画。anim资源文件中可以设为根元素的元素名即为可以修改的属性名。

Java 代码中通过`AnimationUtils.loadAnimation`从 XML 文件中读取动画资源，并设置各种属性（包括持续时间，是否重复，重复模式，插值器，动画状态改变时的响应等）

可以通过自定义插值器实现不同的效果，具体见工程中的`TweenAnimationActivity.interpolator`。

设置好动画后，调用控件的`startAnimation`函数即可开始动画。

控件的`cleaerAnimation`或动画变量的`cancel`函数可以停止动画

补间动画无法真正改变控件的位置。

## 4. 属性动画

属性动画是 Android 3.0后提出的新的动画形式，目的是取代补间动画。它几乎可以修改控件的任意属性。

它分两种：`ValueAnimator`和`ObjectAnimator`。前者是后者的父类，所以使用更灵活，功能更强大；而后者使用更方便。

- ValueAnimator的核心思想是根据我们给定的起止值和插值器来不断产生新的值。为了实现动画，一般需要设置它的`UpdateListener`,在监听器中拿到当前的值，并对需要做动画的属性进行变化。
- ObjectAnimator 对 ValueAnimator 进行封装，可以方便的对一个控件的某些属性进行变化。
- Animator（安卓中Animator就是属性动画）比 Animation（安卓中Animation就是补间动画）比，它能真正改变控件的位置，它能暂停。
- Android 后来的更新中，给 View 增加了`animate`函数，可以更方便地给控件增加动画，具体参加工程中的代码。

## 5. 布局动画

布局动画主要用于控件出现和消失。ViewGroup的`setLayoutTransition`函数可以设置该动画。

它一共有四种动画可以设置:

- Appearing，控件出现时
- Change_appearing，因为别的控件出现导致的变化
- Disappearing，控件消失时
- Change_disappearing，因为别的控件消失导致的变化

可以用前面的 Animator 设置动画。



