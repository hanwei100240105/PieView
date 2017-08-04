package mypieview.bupt.com.mypieview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by HanWei on 2017/8/3.
 */

public class PieView extends View {
    private Paint mPaint;
    private String TAG = "text";
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private ArrayList<PieData> mData;
    // 宽高
    private int width, height;

    public PieView(Context context) {
        this(context, null);

    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.v(TAG, "进入了这个构造函数");
        init();
    }

    public void init() {
        Log.v(TAG, "进入了init函数");
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.v(TAG, "进入了onDraw函数");
        if (null == mData)
            return;     //从当前的onDraw()方法退出
        float currentStartAngle = mStartAngle;
        width = getWidth();
        height = getHeight();// 当前起始角度
        canvas.translate(width / 2, height / 2);
        // 将画布坐标原点（0，0）移动到屏幕中心位置作为新的坐标原点
        //canvas.translate(10, 10);
        // 把当前画布的原点移到(10,10),后面的操作都以(10,10)作为参照点，默认原点为(0,0)
        float r = (float) (Math.min(width, height) / 2 * 0.8);  // 饼状图半径
        canvas.drawColor(Color.WHITE);
        Log.v(TAG, "width=" + width + ",hight=" + height);

        Log.v(TAG, "r=" + r);


        RectF rect = new RectF(-r, -r, r, r);

        Log.v(TAG, "mData集合长度是" + mData.size());
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rect, currentStartAngle, pie.getAngle(), true, mPaint);

            currentStartAngle += pie.getAngle();
            //canvas.save();

        }

    }


    // 设置数据
    public void setData(ArrayList<PieData> mData) {
        Log.v(TAG, "进入了setData函数");
        this.mData = mData;
        initData(mData);
        invalidate();   // 刷新
    }

    // 初始化数据
    private void initData(ArrayList<PieData> mData) {
        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;//从当前方法中退出。
        Log.v(TAG, "数组mColor长度是" + mColors.length);
        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            sumValue += pie.getValue();//计算4个数值和
            int j = i % mColors.length;       //设置颜色
            pie.setColor(mColors[j]);
            Log.v(TAG, "取出颜色" + pie.getColor());
        }
        Log.v(TAG, "四个数值总和为" + sumValue);

        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);                   //获得每一个PieData对象

            float percentage = pie.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;// 对应的角度

            pie.setPercentage(percentage);                  // 记录百分比
            pie.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;
            Log.v(TAG, pie.getName() + "对应的百分比是" + pie.getPercentage() * 100 + "%" +
                    ",对应的角度是" + pie.getAngle());

        }
        Log.v(TAG, "总角度是" + sumAngle + "°");
    }
}
