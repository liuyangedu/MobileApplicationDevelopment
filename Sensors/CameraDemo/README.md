# CameraDemo

## 1. 整体逻辑

本工程演示了Camera1和Camera2的基本用法。

本工程包含三个 Activity：

1. MainActivitiy。入口，用于不同功能的切换
2. Camera1Activity。演示Camera1.
3. Camera2Activity。演示Camera2.

## 2. Camera1

这部分演示了Camera1的基本使用，同时可以切换闪光灯和前后摄像头。

## 2.1 界面

1. 一个撑满整个界面的SurfaceView，用于实时显示摄像头捕获到的画面，即Preview
2. 中间有一个ImageView，用于显示捕获到的照片
3. 当预览时，显示SurfaceView，此时ImageView.Visibility=GONE。拍照时反过来，SurfaceView.Visibility=GONE，ImageView.Visibilite=Visible
4. 三个按钮，分别表示闪光灯状态、前后摄像头、拍照

## 2.2 获取相机信息

`getCameraInfo`函数获取当前设备所有相机，并保存所有相机的朝向，保存在`cameraInfos`中

## 2.3 开启相机并预览

1. `Camera.open(id)`负责打开相机，并返回Camera实例，为`currentCam`
2. `currentCam.getParameters()`函数返回`Camera.Parameters`，可以通过它进行相机的参数设置，比如闪光灯、对焦方式、白平衡等
3. 设置完毕后，通过`currentCam.setParameters()`函数使设置生效
4. `currentCam.setPreviewDisplay`函数设置预览时谁负责显示，需要将SurfaceView的SurfaceHolder传进去
5. 最后调用`currentCam.startPreview`开始预览

## 2.4 拍照

当拍照按钮被按下时：

1. 修改按钮的文本，需要注意按钮的文本，该按钮的行为由文本控制。若文本为Capture，则为拍照；若为Preview则为预览
2. 若文本为Capture，开始拍照
3. 这里假设使用自动对焦的方式
4. 使用`currentCam.autoFocus`函数设置自动对焦成功后的回调函数
5. 在`onAutoFocus`回调函数中调用`currentCam.tackPicture`函数进行拍照。
6. `currentCam.tackPicture`可以设置快门回调，Raw数据回调，JPEG数据回调
7. 我们使用JPEG回调，注意修改界面的surfaceView和ImageView的Visibility

## 2.5 修改闪光灯模式

1. 通过`currentCam.getParameters()`函数获取`Camera.Parameters`
2. 通过参数修改闪光灯模式，并通过`currentCam.setParameters()`使其生效

## 2.6 摄像头切换

1. 先关闭当前相机
2. 然后用同样的方法打开另外一个相机即可（id不一样）