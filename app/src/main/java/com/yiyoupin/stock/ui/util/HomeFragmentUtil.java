package com.yiyoupin.stock.ui.util;

import com.yiyoupin.stock.ui.base.BaseStockFragment;
import com.yiyoupin.stock.ui.fragment.HomeFrgament;
import com.yiyoupin.stock.ui.fragment.MyFrgament;
import com.yiyoupin.stock.ui.fragment.OptionalFrgament;
import com.yiyoupin.stock.ui.fragment.QuotesFrgament;

/**
 * @author zhaoyapeng
 * @version create time:15/10/30下午2:52
 * @Email zyp@jusfoun.com
 * @Description $ fragemnt 工具类
 */
public class HomeFragmentUtil {
    private static int TYPE_HOME = 0;
    private static int TYPE_QUOTES = 1;
    private static int TYPE_OPTIONSAL = 2;
    private static int TYPE_PERSONAL = 3;

    public static BaseStockFragment getInstance(int index) {
        BaseStockFragment fragment = null;
        if (index == TYPE_HOME) {
            fragment = HomeFrgament.getInstance();
        } else if (index == TYPE_QUOTES) {
            fragment = QuotesFrgament.getInstance();
        } else if (index == TYPE_OPTIONSAL) {
            fragment = OptionalFrgament.getInstance();
        } else if (index == TYPE_PERSONAL) {
            fragment = MyFrgament.getInstance();
        }

        return fragment;
    }
}
