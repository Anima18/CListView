package chris.com.clistview.listview.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chris.com.clistview.R;
import chris.com.clistview.listview.ItemView;
import chris.com.clistview.listview.util.ListViewUtil;

/**
 * Created by jianjianhong on 2017/3/21.
 */

public class UTSectionAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private SparseIntArray typeArray;
    private SparseArray<Object> dataArray;
    private List sectionList;
    private List<List> itemLists;

    private Context mContext;
    private int sectionLayout;
    private int itemLayout;
    private LayoutInflater li;

    private OnSectionItemClickListener listener;

    public interface OnSectionItemClickListener {
        void onSectionItemClick(View itemView, int sectionPosition, int itemPosition);
    }

    public void setOnSectionItemClickListener(OnSectionItemClickListener listener) {
        this.listener = listener;
    }

    public UTSectionAdapter(Context context, int sectionLayout, int itemLayout) {
        this.mContext = context;
        this.sectionLayout = sectionLayout;
        this.itemLayout = itemLayout;
        this.typeArray = new SparseIntArray();
        this.dataArray = new SparseArray<>();
        this.li = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return typeArray.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return dataArray.size();
    }

    @Override
    public Object getItem(int position) {
        return dataArray.get(position);
    }

    private int getSectionPosition(int viewPosition) {
        int position = -1;
        for(int i = 0; i < viewPosition; i++) {
            if(TYPE_SEPARATOR == typeArray.get(i)) {
                position++;
            }
        }
        return position;
    }

    private int getItemPosition(int sectionPosition, Object item) {
        return itemLists.get(sectionPosition).indexOf(item);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        List<View> itemViews = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            ItemView itemView = new ItemView(parent.getContext());
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = itemView.inflate(li, parent, itemLayout);
                    convertView.setBackground(mContext.getResources().getDrawable(R.drawable.selectable_item_background));
                    break;
                case TYPE_SEPARATOR:
                    convertView = itemView.inflate(li, parent, sectionLayout);
                    convertView.setBackgroundColor(mContext.getResources().getColor(R.color.backgroundColor));
                    break;
            }
            if(convertView instanceof ViewGroup) {
                ViewGroup rootView = (ViewGroup) convertView;
                itemViews = ListViewUtil.traverseViewGroup(li, rootView, new ArrayList<View>(), null);
                convertView.setTag(itemViews);
            }
        } else {
            itemViews = (List<View>) convertView.getTag();
        }

        final Object t = getItem(position);
        for(View v : itemViews) {
            ListViewUtil.showItemViewValue(li, mContext, v, t);
        }

        if(TYPE_ITEM == rowType && listener != null) {
            final int itemPosition = position;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int sectionPosition = getSectionPosition(itemPosition);
                    listener.onSectionItemClick(v, sectionPosition, getItemPosition(sectionPosition, t));
                }
            });
        }
        return convertView;
    }

    public void setData(List sectionList, List<List> itemLists) {
        if(sectionList == null || itemLists == null) {
            throw new IllegalArgumentException("sectionList and itemLists can't be null");
        }

        if(sectionList.size() != itemLists.size()) {
            throw new IllegalArgumentException("SectionList and itemLists length must be the same");
        }

        this.sectionList = sectionList;
        this.itemLists = itemLists;

        typeArray.clear();
        dataArray.clear();
        int cursor = 0;

        for(int i = 0; i <sectionList.size(); i++) {
            typeArray.append(cursor, TYPE_SEPARATOR);
            dataArray.append(cursor, sectionList.get(i));
            cursor++;
            List itemList = itemLists.get(i);
            for(int j = 0; j < itemList.size(); j++) {
                typeArray.append(cursor, TYPE_ITEM);
                dataArray.append(cursor, itemList.get(j));
                cursor++;
            }
        }

        notifyDataSetChanged();
    }

    public void setData(List dataList, String childProperty) {
        if(dataList == null || TextUtils.isEmpty(childProperty)) {
            throw new IllegalArgumentException("sectionList and childProperty can't be null");
        }

        List<List> itemLists = new ArrayList<>(dataList.size());
        try {
            for(Object section : dataList) {
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

        setData(dataList, itemLists);
    }
}