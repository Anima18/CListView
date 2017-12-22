package chris.com.clistview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chris.com.clistview.listview.util.ListViewUtil;

/**
 * Created by jianjianhong on 2017/3/17.
 */

public class UTSpinner extends AppCompatSpinner {

    private ArrayAdapter<String> adapter;
    private List<String> itemList = new ArrayList<>();

    private Context mContext;
    private String itemField;

    public UTSpinner(Context context) {
        this(context, null);
    }

    public UTSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.spinnerStyle);
    }

    public UTSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.UTSpinner);

        String items = array.getString(R.styleable.UTSpinner_items);
        setItems(items);

        String itemField = array.getString(R.styleable.UTSpinner_itemField);
        setItemField(itemField);

        array.recycle();
        bindAdapter();
    }

    public void bindAdapter() {
        if(adapter == null) {
            adapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item, itemList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            this.setAdapter(adapter);
        }
    }

    public void setItems(String items) {
        if(TextUtils.isEmpty(items)) {
            return;
        }
        String[] itemArray = items.split(",");
        this.itemList.clear();
        this.itemList.addAll(Arrays.asList(itemArray));
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void setItems(List data) {
        if(TextUtils.isEmpty(itemField)) {
            throw new IllegalArgumentException("itemField不能为空");
        }

        itemList.clear();
        for(Object o : data) {
            try {
                String value = ListViewUtil.getPropertyValue(o, itemField).toString();
                itemList.add(value);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void setItems(List data, String itemField) {
        this.itemField = itemField;
        setItems(data);
    }

    public void setItemField(String itemField) {
        this.itemField = itemField;
    }

    public void setItems(String[] items) {
        if(items == null) {
            throw new IllegalArgumentException("items 不能为空");
        }
        this.itemList.clear();
        this.itemList.addAll(Arrays.asList(items));
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

}
