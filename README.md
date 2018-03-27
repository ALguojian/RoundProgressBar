# 自定义进度条


```
 <com.guojian.library.RoundProgressBar
        android:id="@+id/progress_bar_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:maxLong="60"
        app:numSize="16dp"
        app:num_color="#123456"
        app:round_Progress_color="#666"
        app:round_color="@color/colorAccent"
        app:round_width="3dp"
        app:showText="true"
        app:style="STROKE"
        app:textName="去招募"
        app:margeSize="10dp"
        app:textSize="16sp"
        app:text_name_color="#57caa1" />
```

属性定义如下：

```
  <!--圆环颜色-->
        <attr name="round_color" format="color" />

        <!--圆环进度颜色-->
        <attr name="round_Progress_color" format="color" />

        <!--圆环宽度-->
        <attr name="round_width" format="dimension" />

        <!--圆环内文字颜色-->
        <attr name="text_name_color" format="color" />

        <!--数字进度颜色-->
        <attr name="num_color" format="color" />

        <!--圆环内文字大小-->
        <attr name="textSize" format="dimension" />

        <!--数字以及文字距离中心点的距离-->
        <attr name="margeSize" format="dimension" />

        <!--数字大小-->
        <attr name="numSize" format="dimension" />

        <!--最大进度-->
        <attr name="maxLong" format="integer" />

        <!--文字-->
        <attr name="textName" format="string" />

        <!--是否展示文字以及数字-->
        <attr name="showText" format="boolean" />

        <!--进度圆环或者扇形圆环-->
        <attr name="style">

            <enum name="STROKE" value="0" />
            <enum name="FILL" value="1" />
        </attr>
```
