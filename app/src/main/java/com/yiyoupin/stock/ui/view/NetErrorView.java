package com.yiyoupin.stock.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyoupin.stock.R;

/**
 * @author wangcc
 * @date 2018/4/26
 * @describe
 */
public class NetErrorView extends FrameLayout {
    private ImageView imageView;
    private TextView textView;
    public NetErrorView(@NonNull Context context) {
        this(context,null);
    }

    public NetErrorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NetErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_net_error,this);
        imageView=findViewById(R.id.image);
        textView=findViewById(R.id.txt);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);

        MarginLayoutParams params= (MarginLayoutParams) imageView.getLayoutParams();
        if (params.bottomMargin!=height*0.6f) {
            params.bottomMargin = (int) (height * 0.6f);
            imageView.setLayoutParams(params);
        }

        MarginLayoutParams txtParams= (MarginLayoutParams) textView.getLayoutParams();
        if (txtParams.bottomMargin!=height*0.5f) {
            txtParams.bottomMargin = (int) (height * 0.5f);
            textView.setLayoutParams(txtParams);
        }
    }

    public void setNetClick(OnClickListener listener){
        imageView.setOnClickListener(listener);
        textView.setOnClickListener(listener);
    }
}
