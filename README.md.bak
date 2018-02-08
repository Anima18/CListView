# CListView
CListView是集成数据的通用列表组件，使用者只需要提供数据源和绑定数据项，就能实现列表填充。

CListView的优势：
1. 填充ListView不在需要编写Adapter，只需要设置数据源，大大减少开发工作量
2. 填充的数据源不一定要是Java bean对象，可以是Map
3. 结合通用网络组件事半功倍，[RequestManager](https://github.com/Anima18/RequestManager "RequestManager")

ClistView类型：
1. UTListView
2. UTSectionListView
3. UTStateListView

## 使用
### 安装
```
implementation 'com.anima:CListView:1.0.4'
```

### 使用步骤
1. 设置ListView布局
2. 设置ListView item布局，为元素绑定属性
3. 获取网络数据
4. 为ListView设置数据源

## 示例
### 请求API  

```
//github获取users接口
https://api.github.com/users?since=135
```
返回结果格式：

```
[
  {
    "login": "octocat",
    "id": 1,
    "avatar_url": "https://github.com/images/error/octocat_happy.gif",
    "gravatar_id": "",
    "url": "https://api.github.com/users/octocat",
    "html_url": "https://github.com/octocat",
    "followers_url": "https://api.github.com/users/octocat/followers",
    "following_url": "https://api.github.com/users/octocat/following{/other_user}",
    "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
    "organizations_url": "https://api.github.com/users/octocat/orgs",
    "repos_url": "https://api.github.com/users/octocat/repos",
    "events_url": "https://api.github.com/users/octocat/events{/privacy}",
    "received_events_url": "https://api.github.com/users/octocat/received_events",
    "type": "User",
    "site_admin": false
  }
]
```




### CListView使用
1. CListView使用
app:itemLayout 是ListView item的布局

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <chris.com.clistview.CListView使用
        android:id="@+id/listAct_lv"
        android:layout_height="match_parent"
        android:layout_width="match_parent"
        android:divider="#CCCCCC"
        android:dividerHeight="1px"
        app:itemLayout="@layout/listview_list_item"/>

</android.support.constraint.ConstraintLayout>
```

2. item布局  
android:contentDescription用来绑定数据对象的属性，比如说：  
ImageView绑定了avatar_url属性，用来显示user头像图片；  
TextView绑定了login属性，用来显示user名称。
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="72dp"
    android:gravity="center_vertical">

    <ImageView
        android:layout_width="56dp"
        android:layout_height="56dp"
        android:layout_marginLeft="16dp"
        android:contentDescription="avatar_url"/>

    <TextView
        android:id="@+id/mainList_title_tv"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_weight="1"
        android:paddingLeft="16dp"
        android:paddingRight="16dp"
        android:gravity="center_vertical"
        android:textSize="16sp"
        android:textColor="#212121"
        android:text="hello world"
        android:contentDescription="login" />
</LinearLayout>

```


3. 填充数据

使用[RequestManager](https://github.com/Anima18/RequestManager "RequestManager")请求服务数据，ListView通过setData(data)设置数据源。获取服务数据并渲染成列表就完成了。
```
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
```

4. 效果图  

![image](https://raw.githubusercontent.com/Anima18/CListView/master/images/S80104-19193302.jpg)