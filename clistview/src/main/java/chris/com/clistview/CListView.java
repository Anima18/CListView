package chris.com.clistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;


import java.util.List;

import chris.com.clistview.listview.ListViewEvent;
import chris.com.clistview.listview.adapter.CArrayAdapter;
import chris.com.clistview.listview.adapter.CBaseAdapter;
import chris.com.clistview.listview.adapter.CABAdapter;
import chris.com.clistview.listview.itemevent.ItemElementClickEvent;
import chris.com.clistview.listview.itemevent.OnItemChoiceListener;


/**
 * Created by jianjianhong on 2017/3/7.
 */

public class CListView extends ListView implements ListViewEvent {
    private static final String TAG = "UTListView";
    private Context mContext;
    private int itemLayout;
    private String mode;
    private String actionMenus;
    private CBaseAdapter adapter;

    public static final String MODE_NORMAL = "Normal";
    public static final String MODE_MULTICHOICE = "MultiChoice";
    public static final String MODE_SINGLECHOICE = "SingleChoice";

    public CListView(Context context) {
        this(context, null);
    }

    public CListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CListView);

        itemLayout = array.getResourceId(R.styleable.CListView_itemLayout, 0);
        mode = array.getString(R.styleable.CListView_mode);
        actionMenus = array.getString(R.styleable.CListView_actionMenus);

        if(itemLayout != 0) {
            bindAdapter();
        }

        array.recycle();
    }

    public void bindAdapter() {
        Log.i(TAG, "bindAdapter");

        if(TextUtils.isEmpty(mode)) {
            mode = MODE_NORMAL;
        }

        if(MODE_NORMAL.equals(mode)) {
            adapter = new CArrayAdapter(mContext, itemLayout);
            adapter.setMode(mode);
            this.setAdapter(adapter);

        }else if(MODE_MULTICHOICE.equals(mode)) {
            adapter = new CABAdapter(mContext, itemLayout, this);
            adapter.setMode(mode);

            if(!TextUtils.isEmpty(actionMenus)) {
                adapter.setMenuNameArray(actionMenus.split(","));
            }

            this.setAdapter(adapter);
            this.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            this.setMultiChoiceModeListener(((CABAdapter)adapter).listener);
        }
    }

    public void setData(List dataList) {
        adapter.setData(dataList);
    }

    public List getData() {
        return adapter.getData();
    }

    public int getItemLayout() {
        return itemLayout;
    }

    @Override
    public void setOnItemChoiceListener(OnItemChoiceListener listener) {
        adapter.setOnItemChoiceListener(listener);
    }

    @Override
    public void setOnItemElementClickListener(String id, ItemElementClickEvent.OnClickListener listener) {
        adapter.setOnItemElementClickListener(id, listener);
    }
}
