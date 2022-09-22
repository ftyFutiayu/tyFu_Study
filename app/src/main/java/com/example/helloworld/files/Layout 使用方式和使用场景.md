# Layout 使用方式和使用场景

https://juejin.cn/post/6949186887609221133#heading-14

https://www.jianshu.com/p/17ec9bd6ca8a

## 1.LinearLayout线性布局

### 1.1 场景

LinearLayout将控件按照水平或者垂直排列，且都排列到一条线上。

![img](https://img-blog.csdnimg.cn/30aa8abb5bab45b89450eb72d0f7a680.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAaHV3ZWkwODE0,size_20,color_FFFFFF,t_70,g_se,x_16)



### 1.2 使用方式

**1.2.1 LinearLayout常用属性**：

+ **android : orientation** 属性，设置控件的水平 **horizontal** 或者垂直 **vertical** 排列
+ **anroid:gravity** 设置**控件以及其子元素的对齐方式**，可添加不同组合，比如 anroid:gravity="left|bottom"
+ android:layout_gravity**设置控件在父布局中对齐方式**，
+ **layout_weight 权重** android:layout_weight="1"
+ android:weightSum:权重数量，如果不设置这个值就是各控件权重的总和
+ 官方建议layout_width使用0dp，使用weight权重进行填充



## 3.ConstraintLayout约束布局

### 3.1 场景

约束布局是使用相对定位的方式确定控件位置和大小的布局，为了解决页面嵌套过多，导致绘制页面时间增加，影响用户体验的问题。

### 3.2 使用方式

#### 3.2.1 位置约束

约束布局使用方向约束对控件进行定位，**至少水平和垂直方向至少有一个约束才能确定控件的位置**

#### 3.2.2 基本方向约束

1. **layout_constraintStart_toStartOf="[parent]"**  控件开始方向与父布局开始方向对齐
2. 同样**layout_constraintEnd_toStartOf="[parent]"**  控件结束方向与父布局开始方向对齐
3. 记住 **该控件的(什么方向)在(哪个控件)的(什么方向)** 实现位置约束

例如：

```xml
<!-- 基本方向约束 --> 
<!-- 我的什么位置在谁的什么位置 --> 
app:layout_constraintTop_toTopOf=""           我的顶部和谁的顶部对齐 app:layout_constraintBottom_toBottomOf=""     我的底部和谁的底部对齐 app:layout_constraintLeft_toLeftOf=""         我的左边和谁的左边对齐 app:layout_constraintRight_toRightOf=""       我的右边和谁的右边对齐 app:layout_constraintStart_toStartOf=""       我的开始位置和谁的开始位置对齐 
app:layout_constraintEnd_toEndOf=""           我的结束位置和谁的结束位置对齐 
app:layout_constraintTop_toBottomOf=""        我的顶部位置在谁的底部位置 app:layout_constraintStart_toEndOf=""         我的开始位置在谁的结束为止
<!-- ...以此类推 -->
```

#### 3.2.3 基线对齐

**layout_constraintBaseline_toBaselineOf**  实现控件与某个基线对齐

实例代码：

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".activity.layout.LayoutDefaultActivity">

    <TextView
        android:id="@+id/tv1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="20"
        android:textSize="50sp"
        android:textColor="@color/black"
        android:textStyle="bold"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


    <TextView
        android:id="@+id/tv2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="%"
        android:textSize="20sp"
        app:layout_constraintBaseline_toBaselineOf="@+id/tv1"
        app:layout_constraintStart_toEndOf="@id/tv1" />
</androidx.constraintlayout.widget.ConstraintLayout>
```



#### 3.2.4 角度约束

**app:layout_constraintCircle=""** 目标控件的id  将本控件设置在目标控件某个角度

**app:layout_constraintCircleAngle=""** 与目标的角度

**app.layout_constraintCircleRadius=""** 与目标中心的距离

实例：

```xml
<TextView
    android:id="@+id/tv1"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="20"
    android:textSize="50sp"
    android:textColor="@color/black"
    android:textStyle="bold"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />


<TextView
    android:id="@+id/tv2"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="%"
    android:textSize="20sp"
    app:layout_constraintCircle="@id/tv1"
    app:layout_constraintCircleAngle="90"
    app:layout_constraintCircleRadius="40dp"
    app:layout_constraintStart_toEndOf="parent"
    app:layout_constraintTop_toTopOf="parent"/>
```



#### 3.2.5 百分比偏移

让控件位于父布局水平或垂直方向百分之多少的位置：

**app:layout_constraintHorizontal_bias=""   水平偏移 取值范围是0-1的小数 app:layout_constraintVertical_bias=""     垂直偏移 取值范围是0-1的小数**



#### 3.2.6 控件内外间距、GONE Margin

```xml
<!--  外边距  -->
android:layout_margin="0dp" 
android:layout_marginStart="0dp" 
android:layout_marginLeft="0dp" 
android:layout_marginTop="0dp" 
android:layout_marginEnd="0dp" 
android:layout_marginRight="0dp" 
android:layout_marginBottom="0dp" 
<!--  内边距  --> 
android:padding="0dp" 
android:paddingStart="0dp" 
android:paddingLeft="0dp" 
android:paddingTop="0dp" 
android:paddingEnd="0dp" 
android:paddingRight="0dp" 
android:paddingBottom="0dp" 
```



**GONE Margin**，当依赖的目标`view`隐藏时会生效的属性，**仅会在隐藏时生效，目标控件显示时不会生效**

**例如B被A依赖约束，当B隐藏时B会缩成一个点，自身的`margin`效果失效，A设置的GONE Margin 就会生效**，属性如下：

```xml
<!--  GONE Margin  --> 
app:layout_goneMarginBottom="0dp" 
app:layout_goneMarginEnd="0dp" 
app:layout_goneMarginLeft="0dp"
app:layout_goneMarginRight="0dp" 
app:layout_goneMarginStart="0dp" 
app:layout_goneMarginTop="0dp"
```



#### 3.2.7 控件尺寸

**尺寸限制**

constraintLayout提供限制最大最小尺寸的属性，但仅在wrap_content时生效

比如宽度设置为wrap_content，可以增加android:minWidth=""

**0dp**

view的大小不仅可以设置为warp_content、match_parent、指定尺寸，还能设置为 0dp 。

0dp作用因为设置类型不同有不同作用，设置类型有**layout_constraintWidth_default和layout_constrainHeight_default**  可以设置的值有：**spread、percent、wrap**

例如：

app:layout_constraintWidth_default="spread|percent|wrap" app:layout_constraintHeight_default="spread|percent|wrap"

+ **spread：占用所有符合约束的空间** ： 自身view会充满所有可以配置剩余空间。由于是默认值，所以可以省略app:layout_constraintWidth_default="spread" 
+ percent：按照父布局百分比进行设置： 自身view是父布局的一定比例，percent效果通过**layout_constraintWidth_default="percent"**实现，比例参数通过  **app:layout_constraintWidth_percent="0.5"** 设置，但设置了percent数值后第一个defalut="percent"就不需要写了
+ wrap 匹配内容但但不超过约束限制



#### 3.2.8 比例宽高

constraint layout可以**对宽高进行设置比例 至少有一个约束维度设置为0dp才能生效**，两种方法：

+ 浮点值：宽与高的比例
+ 宽度:高度 也是表示比例

app:layout_constraintDimensionRatio=""  宽高比例

#### 3.2.9 Chains链

将许多控件在水平或垂直方向形成一条链，

要求：**链中控件在水平或垂直方向，首尾互相约束**