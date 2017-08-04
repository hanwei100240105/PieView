package mypieview.bupt.com.mypieview;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private PieData pada,pada1,pada2,pada3;
    private ArrayList<PieData> mData;
    private PieView pie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PieView view=new PieView(this);
        setContentView(view);

        pada = new PieData("甲",20);
        pada1=new PieData("乙",50);
        pada2=new PieData("丙",10);
        pada3=new PieData("丁",20);
        mData=new ArrayList<PieData>();
        mData.add(pada);
        mData.add(pada1);
        mData.add(pada2);
        mData.add(pada3);

        view.setData(mData);
//        //尝试用Map集合去写。

    }
}
