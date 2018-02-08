package chris.com.clistview.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import chris.com.clistview.CListView;
import chris.com.clistview.R;
import chris.com.clistview.listview.util.CommonUtil;


public class ItemView {
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

		if(CListView.MODE_MULTICHOICE.equals(mode)) {
			root = addCheckbox(parent);
		}

        return root;
    }

	private View addCheckbox(ViewGroup parent) {
		CheckBox checkBox = (CheckBox) LayoutInflater.from(mContext).inflate(R.layout.listview_checkbox, parent, false);
		checkBox.setVisibility(View.GONE);
		checkBox.setClickable(false);

		if(root instanceof LinearLayout) {
			if(((LinearLayout)root).getOrientation() == LinearLayout.VERTICAL) {
				return createLinearLineVerticalView(checkBox);
			}else {
				return createLinearLineHorizontalView(checkBox);
			}
		}else if(root instanceof RelativeLayout) {
			return createRelativeLayoutView(checkBox);
		}else {
			return null;
		}
	}

	private View createLinearLineHorizontalView(CheckBox checkBox) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
		params.leftMargin = marginValue;
		params.rightMargin = marginValue;

		checkBox.setLayoutParams(params);
		((LinearLayout)root).addView(checkBox);
		return root;
	}

	private View createLinearLineVerticalView(CheckBox checkBox) {
		LinearLayout linearLayout = new LinearLayout(mContext);
		LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		linearLayout.setLayoutParams(params1);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, root.getLayoutParams().height, 1);
		root.setLayoutParams(params2);
		linearLayout.addView(root);

		LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
		params3.leftMargin = marginValue;
		params3.rightMargin = marginValue;
		checkBox.setLayoutParams(params3);
		linearLayout.addView(checkBox);

		return linearLayout;
	}

	private View createRelativeLayoutView(CheckBox checkBox) {
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		params.leftMargin = marginValue;
		params.rightMargin = marginValue;
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		checkBox.setLayoutParams(params);
		((RelativeLayout)root).addView(checkBox);
		return root;
	}
}
