package chris.com.clistview.listview.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;

import com.ut.R;
import com.ut.xmlparser.inflat.LayoutInflater;
import com.ut.xmlparser.listview.ItemView;
import com.ut.xmlparser.listview.util.ListViewUtil;
import com.ut.xmlparser.parser.ViewUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jianjianhong on 2017/3/14.
 */

public class UTExpandableAdapter extends BaseExpandableListAdapter {

    private List groupList;
    private List<List> childList;

    private String expandIndicator;
    private String collapseIndicator;

    private Context mContext;
    private String groupItemLayout;
    private String childItemLayout;

    private LayoutInflater li;

    public UTExpandableAdapter(Context context, String groupItemLayout, String childItemLayout) {
        this.mContext = context;
        this.groupItemLayout = groupItemLayout;
        this.childItemLayout = childItemLayout;
        this.groupList = new ArrayList();
        this.childList = new ArrayList<>();
        this.li = new LayoutInflater(mContext);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        List<View> itemViews = null;
        if(null == convertView){
            Log.i("UTBaseAdapter", "getView");
            ItemView itemView = new ItemView(parent.getContext());
            convertView = itemView.inflate(li, parent, groupItemLayout);
            if(convertView instanceof ViewGroup) {
                ViewGroup rootView = (ViewGroup) convertView;
                itemViews = ListViewUtil.traverseViewGroup(li, rootView, new ArrayList<View>(), null);
                convertView.setTag(itemViews);
                convertView.setBackground(mContext.getResources().getDrawable(R.drawable.selectable_item_background));
            }
        }else {
            itemViews = (List<View>)convertView.getTag();
        }

        Object t = groupList.get(groupPosition);
        for(View v : itemViews) {
            ListViewUtil.showItemViewValue(li, mContext, v, t);
        }

        ImageView indicator = (ImageView)ViewUtil.findViewById(convertView, "group_indicator");
        if(indicator != null) {
            if(isExpanded) {
                indicator.setImageDrawable(ViewUtil.getDrawable(mContext, expandIndicator));
            }else {
                indicator.setImageDrawable(ViewUtil.getDrawable(mContext, collapseIndicator));
            }
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        List<View> itemViews = null;
        if(null == convertView){
            Log.i("UTBaseAdapter", "getView");
            ItemView itemView = new ItemView(parent.getContext());
            convertView = itemView.inflate(li, parent, childItemLayout);
            if(convertView instanceof ViewGroup) {
                ViewGroup rootView = (ViewGroup) convertView;
                itemViews = ListViewUtil.traverseViewGroup(li, rootView, new ArrayList<View>(), null);
                convertView.setTag(itemViews);
                convertView.setBackground(mContext.getResources().getDrawable(R.drawable.selectable_item_background));
            }
        }else {
            itemViews = (List<View>)convertView.getTag();
        }

        Object t = getChild(groupPosition, childPosition);

        for(View v : itemViews) {
            ListViewUtil.showItemViewValue(li, mContext, v, t);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setData(List groupList, String childProperty) {
        if(groupList == null || TextUtils.isEmpty(childProperty)) {
            throw new IllegalArgumentException("groupList and childProperty can't be null");
        }

        List<List> itemLists = new ArrayList<>(groupList.size());
        try {
            for(Object section : groupList) {
                List data = null;
                if(section instanceof Map) {
                    data = (List)ListViewUtil.getPropertyValueByMap((Map)section, childProperty);
                }else {
                    data = (List)ListViewUtil.getPropertyValue(section, childProperty);
                }
                itemLists.add(data);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        setData(groupList, itemLists);
    }

    public void setData(List groupList, List<List> childList) {
        this.groupList.clear();
        this.groupList.addAll(groupList);
        this.childList.clear();
        this.childList.addAll(childList);
        notifyDataSetChanged();
    }

    public String getExpandIndicator() {
        return expandIndicator;
    }

    public void setExpandIndicator(String expandIndicator) {
        this.expandIndicator = expandIndicator;
    }

    public String getCollapseIndicator() {
        return collapseIndicator;
    }

    public void setCollapseIndicator(String collapseIndicator) {
        this.collapseIndicator = collapseIndicator;
    }
}
