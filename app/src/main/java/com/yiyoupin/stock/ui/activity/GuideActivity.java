package com.yiyoupin.stock.ui.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyoupin.stock.R;
import com.yiyoupin.stock.ui.base.BaseStockActivity;
import com.yiyoupin.stock.ui.util.UiUtils;

/**
 * @author wangcc
 * @date 2018/4/24
 * @describe
 */
public class GuideActivity extends BaseStockActivity {
    protected ViewPager viewPager;
    private int[] resId=new int[]{
            R.mipmap.splash_one,
            R.mipmap.splash_two
    };

    private Adapter adapter=new Adapter();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);

    }

    @Override
    public void initAction() {
        viewPager.setAdapter(adapter);
    }

    class Adapter extends PagerAdapter{

        @Override
        public int getCount() {
            return resId.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=getLayoutInflater().inflate(R.layout.item_guide,null);
            ImageView imageView=view.findViewById(R.id.image);
            TextView tiyan=view.findViewById(R.id.tiyan);
            if (position!=resId.length-1){
                tiyan.setVisibility(View.GONE);
            }else {
                tiyan.setVisibility(View.VISIBLE);
            }
            tiyan.setOnClickListener(v -> {
                UiUtils.goHomeActivity(mContext);
            });
            imageView.setImageResource(resId[position]);
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
