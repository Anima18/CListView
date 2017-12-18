package chris.com.clistview.listview.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Admin on 2016/7/24.
 */
public class CommonUtil {

    public static int dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static View getView(Context context, View parentView, String id) {
        int view_id = context.getResources().getIdentifier(id, "id", "chris.com.clistviewdemo");
        View view  = parentView.findViewById(view_id);
        return view;
    }

    public static boolean isEmpty(final Object obj) {
        if (null != obj && !"".equals(obj)) {
            return false;
        }
        return true;
    }

    /**
     * check Object obj is not null
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(final Object obj) {
        return !isEmpty(obj);
    }
}
