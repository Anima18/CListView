package chris.com.clistviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
                .setUrl("http://192.168.60.104:44423/GetRDDataJson?a=&b=&c=getdtobjectdata&d=dtctsvr_objectcode=fdzhizhanglog;dtctsvr_projectversion=1;dtctsvr_projectcode=utdtpower")
                .setMethod("GET")
                .setProgressMessage("正在加载中，请稍后...")
                .setPlatformService(PlatformServiceType.DATA_CENTER_SERVICE)
                .send(new DataRequestCallback<List>() {
                    @Override
                    public void onResult(List data, ResponseStatus status) {;
                        listView.setData(data);
                    }
                });
    }
}
