package com.yiyoupin.stock.ui.util;

import android.support.v4.view.ViewPager;

/**
 * @author wangcc
 * @date 2018/4/16
 * @describe
 */
public class TransformUtil {

    public static void reverse(ViewPager viewPager, ViewPager.PageTransformer transformer) {
        if (viewPager != null) {
            viewPager.setPageTransformer(true, transformer);
        }
    }

    public static void forward(ViewPager viewPager, ViewPager.PageTransformer transformer) {
        if (viewPager != null) {
            viewPager.setPageTransformer(false, transformer);
        }
    }
}
