# ExplicitIntent

## 1. Activity 跳转

首先构建显式 Intent：`Intent intent = new Intent(MainActivity.this,
                        SecondActivity.class);`
                        
注意构造函数的第一个参数，是 `Context` 类型，一般就是当前 Activity 的实例。如果在匿名接口（比如`View.OnClickListener`）中时，要用`类名.this`。

第二个参数是要启动的 Activity

## 2. 参数传递

`Intent` 中有一个 `Bundle` 叫 `Extra`，通过函数`PutExtra`可以在里面保存数据（注意静态变量和字符串资源的使用）

```
intent.putExtra(SecondActivity.KEY_MSG,
                        getResources().getString(R.string.answer));
```


被打开的 Activity 通过`getIntent()`获取这个 Intent，并通过`getExtra`等函数从中取值。（注意处理 null）

```
Intent intent = getIntent();
    if(intent.getExtras()!=null) {
        String msg = intent.getExtras().getString(KEY_MSG);
        tv.setText(msg);
    }
```

# 3. 给控件增加响应
一种较常用的给控件增加响应的方法是在 Java 代码中设置监听器(Listener)。

```
btnS.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,
                    SecondActivity.class);
            intent.putExtra(SecondActivity.KEY_MSG,
                    getResources().getString(R.string.answer));
            startActivity(intent);
        }
});
```

该函数需要一个`View.OnClickListener`类型的变量，上面的代码通过匿名接口的形式实现该接口。