package chris.com.clistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

import java.util.List;

import chris.com.clistview.listview.adapter.UTSectionAdapter;

/**
 * Created by jianjianhong on 2017/3/22.
 */

public class UTSectionListView extends ListView {

    private Context mContext;
    private int sectionLayout;
    private int itemLayout;

    private UTSectionAdapter adapter;

    public UTSectionListView(Context context) {
        this(context, null);
    }

    public UTSectionListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UTSectionListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.UTSectionListView);

        itemLayout = array.getResourceId(R.styleable.UTSectionListView_itemLayout, 0);
        sectionLayout = array.getResourceId(R.styleable.UTSectionListView_sectionLayout, 0);

        bindAdapter();
    }

    public void bindAdapter() {
        if(sectionLayout == 0) {
            throw new IllegalArgumentException("sectionLayout can't be null");
        }
        if(itemLayout == 0) {
            throw new IllegalArgumentException("itemLayout can't be null");
        }

        adapter = new UTSectionAdapter(mContext, sectionLayout, itemLayout);
        this.setAdapter(adapter);
    }

    public void setData(List sectionList, List<List> itemLists) {
        adapter.setData(sectionList, itemLists);
    }

    public void setData(List sectionData, String childProperty) {
        adapter.setData(sectionData, childProperty);
    }

    public void setOnSectionItemClickListener(UTSectionAdapter.OnSectionItemClickListener listener) {
        adapter.setOnSectionItemClickListener(listener);
    }
}
