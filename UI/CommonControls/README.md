# CommonControls



## 1. 本工程包含的主要内容

1. Toast的基本使用
2. 三种AlertDialog的创建：简单、单选、多选
3. Toolbar的使用(包括OptionMenu和搜索)
4. ContextMenu
5. 发送通知
6. 图像资源相关(包括selector)
7. ImageView的使用



## 2. 工程结构

工程中共有三个Activity，`MainActivity`为App入口，包含6个按钮，对应六种不同的功能：

1. Toast
2. 简单模式的AlertDialog
3. 单选模式的AlertDialog
4. 多选模式的AlertDialog
5. 发送通知
6. 展示9-patch图像

`FromNotificationAct`是点击顶端通知栏后会跳转到的Activity

`NinePatchAct`为展示9-patch图像的Activity，里包含两个ImageView，分别显示了一张普通图像和一张9-patch图像。



## 3. 字符串列表资源

1. values/string.xml中演示了在资源中定义一个字符串列表的方法。里面定义的字符串将由于创建单选和多选模式的AlertDialog。
2. values/string.xml中有一个name=clicked的字符串资源。在字符串中使用了占位符%d。
