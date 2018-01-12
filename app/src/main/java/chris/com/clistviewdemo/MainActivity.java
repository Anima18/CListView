package chris.com.clistviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;


import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.UTListView;
import chris.com.clistviewdemo.entity.Item;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "CListView";

    private UTListView listView;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.mainAct_lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, items.get(i).getName());
                Intent intent = new Intent(MainActivity.this, items.get(i).getaClass());
                startActivity(intent);
            }
        });
        setData();
    }

    private void setData() {
        /*NetworkRequestImpl.create(this)
                .setUrl("http://192.168.60.104:44423/GetRDDataJson?a=&b=&c=getdtobjectdata&d=dtctsvr_objectcode=fdzhizhanglog;dtctsvr_projectversion=1;dtctsvr_projectcode=utdtpower")
                .setMethod("GET")
                .setProgressMessage("正在加载中，请稍后...")
                .setPlatformService(PlatformServiceType.DATA_CENTER_SERVICE)
                .send(new DataRequestCallback<List>() {
                    @Override
                    public void onResult(List data, ResponseStatus status) {
                        Log.i(TAG, status.toString());
                        listView.setData(data);
                    }
                });*/
        items = new ArrayList<>();
        items.add(new Item("ListView", ListViewActivity.class));
        items.add(new Item("StateListView", StateListViewActivity.class));
        items.add(new Item("SectionListView", SectionListViewActivity.class));
        listView.setData(items);
    }
}
