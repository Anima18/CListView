package chris.com.clistview.listview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.R;
import chris.com.clistview.listview.ItemView;
import chris.com.clistview.listview.ListViewEvent;
import chris.com.clistview.listview.itemevent.ItemElementClickEvent;
import chris.com.clistview.listview.itemevent.ItemEventAble;
import chris.com.clistview.listview.itemevent.OnItemChoiceListener;
import chris.com.clistview.listview.util.ListViewUtil;

/**
 * Created by jianjianhong on 2017/3/9.
 */

public class UTBaseAdapter extends BaseAdapter implements ListViewEvent {

    /**
     * listView item layout
     */
    protected int itemLayoutFile;

    protected Context mContext;

    /**
     * listView showType: normal，checkbox
     */
    protected String mode;

    /**
     * listView进入编辑状态时，需要显示的自定义菜单列表
     */
    protected String[] menuNameArray;

    /**
     * listView itemView event
     */
    protected List<ItemEventAble> itemElementEventList = new ArrayList<>();

    protected OnItemChoiceListener itemChoiceListener;

    /**
     * listView data
     */
    protected List list;

    protected LayoutInflater li;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            ItemView itemView = new ItemView(mContext);
            convertView = itemView.inflate(li, parent, itemLayoutFile, mode);
            if(convertView instanceof ViewGroup) {
                ViewGroup rootView = (ViewGroup) convertView;
                List<View> itemViews = ListViewUtil.traverseViewGroup(li, rootView, new ArrayList<View>(), mode);
                convertView.setTag(itemViews);
                convertView.setBackground(mContext.getResources().getDrawable(R.drawable.selectable_item_background));
            }
        }

        return convertView;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public void setOnItemChoiceListener(OnItemChoiceListener listener) {
        this.itemChoiceListener = listener;
    }

    @Override
    public void setOnItemElementClickListener(String id, ItemElementClickEvent.OnClickListener listener) {
        itemElementEventList.add(new ItemElementClickEvent(mContext, id, listener));
    }

    public void setMenuNameArray(String[] menuNameArray) {
        this.menuNameArray = menuNameArray;
    }

    public void setData(List list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public List getData() {
        return this.list;
    }

    protected void showItemViewValue(View view, Object t) {
        ListViewUtil.showItemViewValue(li, mContext, view, t);
    }
}
