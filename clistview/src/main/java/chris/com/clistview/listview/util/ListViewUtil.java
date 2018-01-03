package chris.com.clistview.listview.util;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import chris.com.clistview.R;
import chris.com.clistview.UTListView;

/**
 * Created by jianjianhong on 2017/3/9.
 */

public class ListViewUtil {

    public static List<View> traverseViewGroup(LayoutInflater li, ViewGroup parent, List<View> itemViewList, String mode) {
        if(itemViewList == null) {
            throw new IllegalArgumentException("itemViewList 不能为null");
        }
        for(int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            String variable = getVariable(li, child);
            if(child instanceof ViewGroup) {
                traverseViewGroup(li, (ViewGroup)child, itemViewList, mode);
            } else if(!TextUtils.isEmpty(variable)) {
                itemViewList.add(child);
            } else if(UTListView.MODE_MULTICHOICE.equals(mode) && TextUtils.isEmpty(variable)
                    && (child.getClass() == CheckBox.class || child.getClass() == AppCompatCheckBox.class)) {
                itemViewList.add(child);
            }
        }
        return itemViewList;
    }

    public static Object getPropertyValue(Object o, String propertyStr) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int indexOf = propertyStr.indexOf(".");
        if(indexOf == -1) {
            Field field = o.getClass().getDeclaredField(propertyStr);
            field.setAccessible(true);
            return field.get(o);
        }else {
            String currentProperty = propertyStr.substring(0, indexOf);
            String subProperty = propertyStr.substring(indexOf+1);

            Field field = o.getClass().getDeclaredField(currentProperty);
            field.setAccessible(true);
            Object subObject = field.get(o);
            return getPropertyValue(subObject, subProperty);
        }
    }

    public static Object getPropertyValueByMap(Map<String, Object> m, String propertyStr) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int indexOf = propertyStr.indexOf(".");
        if(indexOf == -1) {
            return m.get(propertyStr);
        }else {
            String currentProperty = propertyStr.substring(0, indexOf);
            String subProperty = propertyStr.substring(indexOf+1);
            return getPropertyValueByMap(((Map)m.get(currentProperty)), subProperty);
        }
    }

    public static void showItemViewValue(LayoutInflater li, Context context, View view, Object t) {
        String variable = getVariable(li, view);

        if(TextUtils.isEmpty(variable)) {
            return;
        }

        try {
            String value = "";
            if(t instanceof Map) {
                try {
                    value = ListViewUtil.getPropertyValueByMap((Map)t, variable).toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                value = ListViewUtil.getPropertyValue(t, variable).toString();
            }

            //Class viewClass = view.getClass();
            if(view instanceof TextView) {
                TextView textView = (TextView)view;
                textView.setText(value);
            } else if(view instanceof ImageView) {
                ImageView iv = (ImageView) view;
                String url = value;
                if(url.contains("file:///android_asset/")) {
                    Picasso.with(context).load(url)/*.memoryPolicy(MemoryPolicy.NO_CACHE)*/.fit().placeholder(R.drawable.image_default).error(R.drawable.image_not_found).centerCrop().into(iv);
                }else if(url.contains("http://") || url.contains("https://")) {
                    Picasso.with(context).load(url).fit().centerCrop().placeholder(R.drawable.image_default).error(R.drawable.image_not_found).into(iv);
                }else {
                    Picasso.with(context).load(new File(context.getFilesDir().getPath() +  File.separator + url)).fit().centerCrop().placeholder(R.drawable.image_default).error(R.drawable.image_not_found).into(iv);
                }
            } else if(view instanceof CheckBox) {
                CheckBox checkBox = (CheckBox)view;
                checkBox.setChecked(Boolean.valueOf(value));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static String getVariable(LayoutInflater li, View view) {

        return view.getContentDescription() == null ? null : view.getContentDescription().toString();
    }

}
