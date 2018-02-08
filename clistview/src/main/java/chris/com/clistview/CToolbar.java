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

public class CToolbar extends RelativeLayout {
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

    public CToolbar(Context context) {
        this(context, null);
    }

    public CToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
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
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.CToolbar);

        int backgroundColor = array.getColor(R.styleable.CToolbar_backgroundColor, 0);
        if(backgroundColor != 0) {
            root.setBackgroundColor(backgroundColor);
        }

        final Drawable navigationIcon = array.getDrawable(R.styleable.CToolbar_navigationMenu);
        setNavigationView(navigationIcon);

        final Drawable rightIcon = array.getDrawable(R.styleable.CToolbar_rightMenu);
        setRightIconView(rightIcon);

        int mTitleColor = array.getColor(R.styleable.CToolbar_textColor, 0);
        final String title = array.getString(R.styleable.CToolbar_text);
        mTitleView.setText(title);
        if(mTitleColor != 0) {
            mTitleView.setTextColor(mTitleColor);

        }

        final String moreMenuStr = array.getString(R.styleable.CToolbar_moreMenu);
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

    public void setMoreMenu(List<String> moreMenuList) {
        if(moreMenuList == null || moreMenuList.size() == 0) {
            mMoreButtonView.setVisibility(GONE);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            mRightButtonView.setLayoutParams(params);
        }else {
            mMoreButtonView.setVisibility(VISIBLE);
            String[] array = new String[moreMenuList.size()];
            String[] menuArray = moreMenuList.toArray(array);
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

    public void setMoreMenu(String moreMenuStr) {
        if(TextUtils.isEmpty(moreMenuStr)) {
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


    public void setNavigationOnClickListener(OnClickListener listener) {
        mNavButtonView.setOnClickListener(listener);
    }

    public void setRightIconOnClickListener(OnClickListener listener) {
        mRightButtonView.setOnClickListener(listener);
    }

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

    public void closeMenuDialog() {
        if(listPopupWindow != null && listPopupWindow.isShowing()) {
            listPopupWindow.dismiss();
        }
    }
}
