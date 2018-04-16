package com.yiyoupin.stock.ui.util;

import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.fragment.HomeFrgament;
import com.yiyoupin.stock.ui.fragment.LoginFragment;
import com.yiyoupin.stock.ui.fragment.MainFragment;
import com.yiyoupin.stock.ui.fragment.MyFrgament;
import com.yiyoupin.stock.ui.fragment.OptionalFrgament;
import com.yiyoupin.stock.ui.fragment.QuotesFrgament;

/**
 * @author wangcc
 * @date 2018/4/16
 * @describe
 */
public class MainFragmentUtil {

    private static int TYPE_LOGIN = 0;
    private static int TYPE_MAIN = 1;

    public static BaseStockFragment getInstance(int index) {
        BaseStockFragment fragment = null;
        if (index == TYPE_LOGIN) {
            fragment = LoginFragment.getInstance();
        } else if (index == TYPE_MAIN) {
            fragment = MainFragment.getInstance(null);
        }

        return fragment;
    }
}
