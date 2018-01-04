package chris.com.clistviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ut.requsetmanager.callback.DataRequestCallback;
import com.ut.requsetmanager.entity.ResponseStatus;
import com.ut.requsetmanager.entity.platformservice.PlatformServiceType;
import com.ut.requsetmanager.request.NetworkRequestImpl;

import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.UTListView;
import chris.com.clistviewdemo.entity.Item;

/**
 * Created by jianjianhong on 2017/12/20.
 */

public class ListViewActivity extends AppCompatActivity {

    private UTListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        listView = findViewById(R.id.listAct_lv);
        setData();
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
                            Toast.makeText(ListViewActivity.this, status.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
