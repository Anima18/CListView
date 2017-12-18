package chris.com.clistview.listview;


import chris.com.clistview.listview.itemevent.ItemElementClickEvent;
import chris.com.clistview.listview.itemevent.OnItemChoiceListener;

/**
 * Created by jianjianhong on 2017/3/7.
 */

public interface ListViewEvent {
    void setOnItemElementClickListener(String id, ItemElementClickEvent.OnClickListener listener);
    void setOnItemChoiceListener(OnItemChoiceListener listener);
}
