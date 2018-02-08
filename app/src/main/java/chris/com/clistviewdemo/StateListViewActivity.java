package chris.com.clistviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ut.requsetmanager.callback.DataRequestCallback;
import com.ut.requsetmanager.entity.ResponseStatus;
import com.ut.requsetmanager.request.NetworkRequestImpl;

import java.util.List;

import chris.com.clistview.CListView;
import chris.com.clistview.CToolbar;
import chris.com.clistview.listview.itemevent.OnItemChoiceListener;

/**
 * Created by jianjianhong on 2017/12/20.
 */

public class StateListViewActivity extends AppCompatActivity {
    private static final String TAG = "StateListView";
    private CListView listView;
    private CToolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_listview);

        listView = findViewById(R.id.listAct_lv);
        listView.setOnItemChoiceListener(new OnItemChoiceListener() {
            @Override
            public void onClick(String menuName, List<Integer> positionList, List dataList) {
                Log.i(TAG, menuName + "selected : " + positionList.toString());
            }
        });
        setData();

        toolbar = findViewById(R.id.state_toolbar);
        //设置返回点击监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateListViewActivity.this.finish();
            }
        });

        //设置右边菜单点击监听
        toolbar.setRightIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StateListViewActivity.this, "rightIcon click", Toast.LENGTH_SHORT).show();
            }
        });

        //设置更多菜单点击监听
        toolbar.setMoreMenuItemClickListener(new CToolbar.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StateListViewActivity.this, "menu "+position + " click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        NetworkRequestImpl.create(this)
                .setUrl("https://api.github.com/users?since=135")
                .setMethod("GET")
                .setProgressMessage("正在加载中，请稍后...")
                .send(new DataRequestCallback<List>() {
                    @Override
                    public void onResult(List data, ResponseStatus status) {
                        if(status.getCode() == 200) {
                            listView.setData(data);
                        }else {
                            Toast.makeText(StateListViewActivity.this, status.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
