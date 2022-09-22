# [入职培训]Hello World

## Context

最常用的**Activity，Service，Application**都是Context的子类,下图为Context体系结构图：

![image-20220801171129923](C:\Users\Tianyu.Fu\AppData\Roaming\Typora\typora-user-images\image-20220801171129923.png)

+ Context为抽象类，ContextImpl为Context实现类,ContextWrapper为包装类（装饰设计模式）。ContextWrapper的子类有Application,Service,ContextThemeWrapper（与主题相关）,ContextImpl才是最核心的

### ContextImpl

+ Context可以看作是接口文档，ContextImpl最重要的是 **ActivityThread中 mMainThread**这个全局变量，使用地方最多。getApplicationContext，startActivity，getMainLooper都是在这里实现，通过ActivityThread封装。

```java
@Override
 public Looper getMainLooper() {
     return mMainThread.getLooper();
 }

@Override
public Context getApplicationContext() {
    return (mPackageInfo != null) ?
            mPackageInfo.getApplication() : mMainThread.getApplication();
}

@Override
public void startActivity(Intent intent, Bundle options) {
    warnIfCallingFromSystemProcess();

    mMainThread.getInstrumentation().execStartActivity(
            getOuterContext(), mMainThread.getApplicationThread(), null,
            (Activity) null, intent, -1, options);
}

//差不多有10个类似方法
@Override
public void sendBroadcast(Intent intent, String receiverPermission, int appOp) {

        ActivityManager.getService().broadcastIntent(
                mMainThread.getApplicationThread(), intent, resolvedType, null,
                Activity.RESULT_OK, null, null, receiverPermissions, appOp, null, false, false,
                getUserId());

}
```
第二重要的是**LoadedApk中mPackageInfo** 这个变量，因为他被引用的次数是第二多的，要么通过mPackageInfo获取一些内容，要么通过他做判断。当然，这些功能也都是LoadedApk这个类封装的，通过他的名字也就知道他是干嘛的，这个类可以管理运行的.apk文件。


```java
  @Override
    public Context getApplicationContext() {
        return (mPackageInfo != null) ?
                mPackageInfo.getApplication() : mMainThread.getApplication();
    }
    @Override
    public String getPackageName() {
        if (mPackageInfo != null) {
            return mPackageInfo.getPackageName();
        }
        // No mPackageInfo means this is a Context for the system itself,
        // and this here is its name.
        return "android";
    }
    @Override
    public ApplicationInfo getApplicationInfo() {
        if (mPackageInfo != null) {
            return mPackageInfo.getApplicationInfo();
        }
        throw new RuntimeException("Not supported in system context");
    }

@Override
public String getPackageResourcePath() {
    if (mPackageInfo != null) {
        return mPackageInfo.getResDir();
    }
    throw new RuntimeException("Not supported in system context");
}

//差不多有10个
void sendOrderedBroadcast() {
           多处用mPackageInfo做判断
        }

}
```



## Context种类：

**Activity，Service，Application**都是继承ContextWrapper，也就是都是Context的子类。



## getContext、getBaseContext和getApplicationContext区别

***getContext() , getBaseContxet() , getApplicationContext区别***

1. **Activity,Service和Application**都有getBaseContxet(),getApplicationContext()这两个方法。但没有getContext方法。
2. **在Fragment中，才有getContext方法**。而Fragment的getContext方法获取的对象是他的**寄主对象**，也就是Activity。如果查看getContext源码，在一系列包装后，实际上获取的就是他所在的Activity对象。

## getApplication和getApplicationContext区别

**区别：**

**getApplication和getApplicationContext**区别：如果在Activity中打印这两个方法，获取的为同一个对象，但是使用范围不同。getApplication只有Activity和Service才能获取到Application，若想在其他地方获取Application只能通过getApplicationContext获取



## Application的使用

1. Application是全局单例。所以自己实现单例是多余的，通过上面的**getApplication**或者**getApplicationContext**获取就行了。
2. application里面调用context方法，**需要在attachBaseContext之后**。因为是在attachBaseContext完成context的赋值的。比如在构造器里面调用Context的方法就会奔溃，因为还没有赋值。
3. 在onCreate方法调用Context是可以的，但极限操作是在attachBaseContext方法里面。
   