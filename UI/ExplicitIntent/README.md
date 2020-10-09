# ExplicitIntent

## 1. Activity 跳转

首先构建显式 Intent：`Intent intent = new Intent(MainActivity.this, SecondActivity.class);`
                        
注意构造函数的第一个参数，是 `Context` 类型，一般就是当前 Activity 的实例。如果在匿名接口（比如`View.OnClickListener`）中时，要用`类名.this`。

第二个参数是要启动的 Activity

## 2. 参数传递

`Intent` 中有一个 `Bundle` 叫 `Extra`，通过函数`PutExtra`可以在里面保存数据。一般Key使用静态变量保存（SecondActivity.KEY_MSG）。注意字符串尽量不要硬编码（R.string.message)

1. 使用静态变量设置Key和字符串资源的使用

```java
intent.putExtra(SecondActivity.KEY_MSG,
                        getResources().getString(R.string.answer));
```


被打开的 Activity 通过`getIntent()`获取这个 Intent，并通过`getExtra`等函数从中取值。注意if的第二个条件：它先获取字符串，并赋值给data；然后赋值表达式的特点直接判断data是否为空。

```java
Intent intent = getIntent();
String data;
if (intent.getExtras()!=null && 
    (data = intent.getExtras().getString(KEY_MSG))!=null) {
	tv.setText(data);
}
```

# 3. 控件绑定

在XML文件中给需要绑定的控件增加id属性

```xml
<TextView
        android:id="@+id/textview1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

然后在Java代码中声明一个同类型的变量，并用`findViewById`函数根据id进行绑定。控件绑定一般在`onCreate`中完成。

```java
tv = findViewById(R.id.textview1);
```



# 4. 给控件增加响应

一种较常用的给控件增加响应的方法是在 Java 代码中设置监听器(Listener)。

```java
tv.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(
			MainActivity.this,
			SecondActivity.class
		);
		intent.putExtra(SecondActivity.KEY_MSG,
			getResources().getString(R.string.message));
		startActivity(intent);
	}
});
```

该函数需要一个`View.OnClickListener`类型的变量，上面的代码通过匿名接口的形式实现该接口。

# 5. 需要返回结果的Intent

假设从活动A跳转至活动B，并且希望B返回结果给A，关键步骤如下：
1. 将A中的`StartActivity`改成`StartActivityForResult`
2. 在A中重写`onActivityResult`函数
3. 在B中通过`setReslut`设置结果