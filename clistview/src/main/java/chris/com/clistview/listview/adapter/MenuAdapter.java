package chris.com.clistview.listview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import chris.com.clistview.R;


/**
 * Created by Admin on 2016/8/6.
 */
@SuppressLint("all")
public class MenuAdapter extends BaseAdapter {

    private Context context;
    private String[] menuList;

    public MenuAdapter(Context context, String[] menuList) {
        this.context = context;
        this.menuList = menuList;
    }
    @Override
    public int getCount() {
        return menuList.length;
    }

    @Override
    public Object getItem(int i) {
        return menuList[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_menu_item, viewGroup, false);
            TextView textView = view.findViewById(R.id.menuList_text);
            textView.setText(menuList[i]);
        }

        return view;
    }
}
