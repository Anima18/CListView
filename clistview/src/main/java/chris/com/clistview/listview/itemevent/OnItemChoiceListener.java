package chris.com.clistview.listview.itemevent;

import java.util.List;

/**
 * Created by jianjianhong on 2017/3/13.
 */

public interface OnItemChoiceListener {
    void onClick(String menuName, List<Integer> positionList, List dataList);
}
