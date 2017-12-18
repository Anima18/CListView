package chris.com.clistview.listview.itemevent;

import android.view.View;

import com.ut.xmlparser.parser.ViewUtil;

/**
 * Created by jianjianhong on 2016/11/11.
 */
public class ItemElementClickEvent implements ItemEventAble {
    private String id;
    private OnClickListener listener;

    public interface OnClickListener {
        void onClick(View itemView, int position);
    }

    public ItemElementClickEvent(String id, OnClickListener listener) {
        this.id = id;
        this.listener = listener;
    }

    public void bindEvent(View view, final int position) {
        final View eView = ViewUtil.findViewById(view, id);
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
