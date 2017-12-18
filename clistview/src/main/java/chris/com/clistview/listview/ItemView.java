package chris.com.clistview.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import chris.com.clistview.R;
import chris.com.clistview.UTListView;
import chris.com.clistview.listview.util.CommonUtil;


public class ItemView{
	private static final String TAG = "ItemView";
	private Context mContext;
	private View root;
	private int marginValue;
	
	public ItemView(Context context) {
		mContext = context;
		marginValue = CommonUtil.dipToPixels(mContext, 16);
	}

	public View inflate(LayoutInflater li, ViewGroup parent, int xmlFile) {
		root =  li.inflate(xmlFile, parent, false);
		return root;
	}
	
	public View inflate(LayoutInflater li, ViewGroup parent, int xmlFile, String mode) {
        root =  li.inflate(xmlFile, parent, false);

		if(UTListView.MODE_MULTICHOICE.equals(mode)) {
			addCheckbox(parent);
		}

        return root;
    }

	private void addCheckbox(ViewGroup parent) {
		CheckBox checkBox = (CheckBox) android.view.LayoutInflater.from(mContext).inflate(R.layout.listview_checkbox, parent, false);
		checkBox.setVisibility(View.GONE);
		checkBox.setClickable(false);

		if(root instanceof LinearLayout) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
			params.leftMargin = marginValue;
			params.rightMargin = marginValue;

			checkBox.setLayoutParams(params);
			((LinearLayout)root).addView(checkBox);
		}else if(root instanceof RelativeLayout) {
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
			params.leftMargin = marginValue;
			params.rightMargin = marginValue;
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			checkBox.setLayoutParams(params);
			((RelativeLayout)root).addView(checkBox);
		}
	}
}
