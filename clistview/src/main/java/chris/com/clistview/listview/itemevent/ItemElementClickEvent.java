package chris.com.clistview.listview.itemevent;

import android.content.Context;
import android.view.View;

import chris.com.clistview.listview.util.CommonUtil;


/**
 * Created by jianjianhong on 2016/11/11.
 */
public class ItemElementClickEvent implements ItemEventAble {
    private String id;
    private Context context;
    private OnClickListener listener;

    public interface OnClickListener {
        void onClick(View itemView, int position);
    }

    public ItemElementClickEvent(Context context, String id, OnClickListener listener) {
        this.context = context;
        this.id = id;
        this.listener = listener;
    }

    public void bindEvent(View view, final int position) {
        final View eView = CommonUtil.getView(context, view, id);
        eView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick(eView, position);
            }
        });
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OnClickListener getListener() {
        return listener;
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
