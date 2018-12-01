# HandlerDemo

## 1. 整体逻辑

本工程简单演示了 Handler 的使用。

只有一个 Activity。界面上为两个按钮，一个 TextView。

两个按钮对应不同的向 Handler 发送消息的方法。

## 2. Handler

`Handler`是安卓中的消息队列处理器。它可以根据收到的消息类型执行对应操作。

- 构建 Handler。一般通过制定消息队列来构建 Handler。`getMainLooper`函数返回主进程的消息队列。如果构造函数中不写参数，则默认绑定当前线程的消息队列。
- `handleMessage`函数。处理消息的函数。一般通过`switch`对不同类型的`Message`进行处理

## 3. 发送消息

- 通过`Message.obtain`函数获取空白消息。
- 设置what属性来决定消息的类型。
- 通过setData函数可以向消息中增加额外的数据
- 通过`handler`的`sendMessage`函数发送消息，这种消息会根据`what`的不同得到不同的处理。
- 通过`handler`的`post`函数发送一个`Runnable`。因为发送的是一个`Runnable`，所以 Handler 会直接调用`Runnable`中的代码。