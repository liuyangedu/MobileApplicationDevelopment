# ReadValueFromSensors

## 1. 整体逻辑

本工程演示了读取传感器的通用方法。

本工程只有一个 Activity。Activity 界面上面为一个 TextView，用于显示当前选择的传感器的值。

下面为一个 ListView，列出当前设备所有传感器的名字和类型

## 2. 获取传感器

利用SensorManager可以管理传感器。

1. 首先通过`context.getSystemService(Context.SENSOR_SERVICE)`获取SensorManager实例
2. 然后调用它的`getSensorList`函数，参数为要获取的传感器类型，如果是全部传感器则是`Sensor.TYPE_ALL`

## 3. 读取传感器的值

大部分传感器的读取方法是统一的。

调用`SensorManager.registerListener`函数。

1. 该函数的第一个参数是一个`SensorEventListener`，它是个接口，关键是其中的`onSensorChanged`函数。当传感器的值有变化时则会自动调用这个函数。`onSensorChanged`函数参数`SensorEvent`中的`values`变量就包含了当前传感器的所有值，它是一个长度不定的 float 数组。通过查阅相关的文档可以知道数组中每一个值的具体含义。
2. 该函数的第二个参数是要读取值的传感器变量。
3. 该函数的第三个参数是读取值的频率，频率设太高会比较耗电。