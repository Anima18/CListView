package chris.com.clistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListPopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

import chris.com.clistview.listview.adapter.MenuAdapter;
import chris.com.clistview.listview.util.CommonUtil;

/**
 * Created by jianjianhong on 2016/11/18.
 * 自定义UTToolBar，遵循ToolBar的设计规范。包含了5个元素：NavButtonView,TitleView,Spinner,RightButtonView,MoreButtonView
 * NavButtonView是导航图标，比如常用的返回菜单
 * TitleView是标题视图，居中显示
 * RightButtonView是右侧图标，比如常用的菜单
 * MoreButtonView是更多的菜单，隐藏在三点按钮中
 * Spinner是下拉菜单，如果设置了Spinner，RightButtonView和MoreButtonView不显示
 */
public class UTToolbar extends RelativeLayout {
    private static final String TAG = "UTToolbar";
    private Context mContext;
    private View root;
    private ImageButton mNavButtonView;
    private ImageButton mRightButtonView;
    private ImageButton mMoreButtonView;
    private TextView mTitleView;
    private ListPopupWindow listPopupWindow;
    //private UTSpinner spinner;

    public interface OnItemClickListener {
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    public UTToolbar(Context context) {
        this(context, null);
    }

    public UTToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UTToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        initView(context);
        initViewData(attrs);
    }

    private void initView(Context context) {
        Log.i(TAG, "before load layout");
        root = View.inflate(context, R.layout.view_uttoolbar, this);
        Log.i(TAG, "after load layout");
        mNavButtonView = (ImageButton) root.findViewById(R.id.imageButton_toolbar_navigation);
        mRightButtonView = (ImageButton) root.findViewById(R.id.imageButton_toolbar_rightIcon);
        mMoreButtonView = (ImageButton) root.findViewById(R.id.imageButton_toolbar_more);
        mTitleView = (TextView) root.findViewById(R.id.textView_toolbar_title);
        //spinner = (UTSpinner) root.findViewById(R.id.spanner_toolbar_text);
        Log.i(TAG, "after init view");
    }

    private void initViewData(AttributeSet attrs) {
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.UTToolbar);

        int backgroundColor = array.getColor(R.styleable.UTToolbar_backgroundColor, 0);
        if(backgroundColor != 0) {
            root.setBackgroundColor(backgroundColor);
        }

        final Drawable navigationIcon = array.getDrawable(R.styleable.UTToolbar_navigationMenu);
        setNavigationView(navigationIcon);

        final Drawable rightIcon = array.getDrawable(R.styleable.UTToolbar_rightMenu);
        setRightIconView(rightIcon);

        int mTitleColor = array.getColor(R.styleable.UTToolbar_textColor, 0);
        final String title = array.getString(R.styleable.UTToolbar_text);
        mTitleView.setText(title);
        if(mTitleColor != 0) {
            mTitleView.setTextColor(mTitleColor);

        }

        final String moreMenuStr = array.getString(R.styleable.UTToolbar_moreMenu);
        if(!TextUtils.isEmpty(moreMenuStr)) {
            setMoreMenu(moreMenuStr);
        }

        array.recycle();
    }

    public void setNavigationView(Drawable drawable) {
        if (mNavButtonView != null) {
            mNavButtonView.setVisibility(VISIBLE);
            mNavButtonView.setImageDrawable(drawable);
        }else {
            mNavButtonView.setVisibility(GONE);
        }
    }

    public void setRightIconView(Drawable drawable) {
        if(mRightButtonView != null/* && spinner.getVisibility() == GONE*/) {
            mRightButtonView.setVisibility(VISIBLE);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            mRightButtonView.setLayoutParams(params);
            mRightButtonView.setImageDrawable(drawable);
        }else {
            mRightButtonView.setVisibility(GONE);
        }
    }

    public void setTitle(String title) {
        if(mTitleView != null) {
            mTitleView.setText(title);
        }
    }

    public void setTitleColor(int color) {
        if(mTitleView != null) {
            mTitleView.setTextColor(color);
        }
    }

    public void setBackground(int backgroundColor) {
        if(root != null) {
            root.setBackgroundColor(backgroundColor);
        }
    }

    public void setMoreMenu(String moreMenuStr) {
        if(moreMenuStr == null || "".equals(moreMenuStr.trim()) /*||  spinner.getVisibility() == VISIBLE*/) {
            mMoreButtonView.setVisibility(GONE);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            mRightButtonView.setLayoutParams(params);
        }else {
            mMoreButtonView.setVisibility(VISIBLE);
            String[] menuArray = moreMenuStr.split(",");
            listPopupWindow = new ListPopupWindow(mContext);
            MenuAdapter adapter = new MenuAdapter(mContext, menuArray);
            listPopupWindow.setAdapter(adapter);

            mMoreButtonView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listPopupWindow.setWidth(mContext.getResources().getDimensionPixelSize(R.dimen.menuAct_menu_width));
                    listPopupWindow.setAnchorView(mMoreButtonView);
                    listPopupWindow.setHorizontalOffset(-CommonUtil.dipToPixels(mContext, 122));
                    listPopupWindow.setVerticalOffset(-CommonUtil.dipToPixels(mContext, 36));

                    listPopupWindow.setModal(true);
                    listPopupWindow.show();
                }
            });

            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.LEFT_OF, R.id.imageButton_toolbar_more);
            params.rightMargin = -CommonUtil.dipToPixels(mContext, 16);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            mRightButtonView.setLayoutParams(params);
        }
    }

    /*public void setSpinnerItems(String items) {
        spinner.setVisibility(VISIBLE);
        checkForMenuVisibility();
        spinner.setItems(items);
    }

    public void setSpinnerItems(List data, String itemField) {
        spinner.setVisibility(VISIBLE);
        checkForMenuVisibility();
        spinner.setItems(data, itemField);
    }*/

   /* public UTSpinner getSpinner() {
        return spinner;
    }

    public void setSpinnerShowable(boolean showable) {
        if(showable) {
            spinner.setVisibility(VISIBLE);
        }else {
            spinner.setVisibility(GONE);
        }
    }

    private void checkForMenuVisibility() {
        if(spinner.getVisibility() == VISIBLE) {
            mRightButtonView.setVisibility(GONE);
            mMoreButtonView.setVisibility(GONE);
        }
    }*/

    public void setNavigationOnClickListener(OnClickListener listener) {
        mNavButtonView.setOnClickListener(listener);
    }

    public void setRightIconOnClickListener(OnClickListener listener) {
        mRightButtonView.setOnClickListener(listener);
    }

    /*public void setMenuItemOnClickListener(AdapterView.OnItemClickListener listener) {
        if(listPopupWindow != null) {
            listPopupWindow.setOnItemClickListener(listener);
            listPopupWindow.dismiss();
        }
    }*/

    public void setMoreMenuItemClickListener(final OnItemClickListener moreMenuItemClickListener) {
        if(listPopupWindow != null) {
            listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    moreMenuItemClickListener.onItemClick(parent, view, position, id);
                    listPopupWindow.dismiss();
                }
            });
        }
    }

    /*public void setSpinnerItemClickListener(final AdapterView.OnItemSelectedListener listener) {
        if(spinner != null) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    listener.onItemSelected(parent, view, position, id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    listener.onNothingSelected(parent);
                }
            });
        }
    }*/

    public void closeMenuDialog() {
        if(listPopupWindow != null && listPopupWindow.isShowing()) {
            listPopupWindow.dismiss();
        }
    }
}
