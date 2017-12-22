package chris.com.clistviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.UTSectionListView;
import chris.com.clistviewdemo.entity.Course;
import chris.com.clistviewdemo.entity.User;

/**
 * Created by jianjianhong on 2017/12/21.
 */

public class SectionListViewActivity extends AppCompatActivity {

    private UTSectionListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_listview);

        listView = findViewById(R.id.sectionListAct_lv);

        listView.setData(initData(), "courseList");
    }

    private List initData() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513941392714&di=f2fd750aaf59467618382fc3e4423c73&imgtype=0&src=http%3A%2F%2Ftx.haiqq.com%2Fuploads%2Fallimg%2F150403%2F210P235H-3.jpg", "语文"));
        courseList.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513941417280&di=8cca2283d93d9ef2514008629b6abc7c&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D559118063%2C1910583601%26fm%3D214%26gp%3D0.jpg", "数学"));

        List<Course> courseList2 = new ArrayList<>();
        courseList2.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513941392714&di=f2fd750aaf59467618382fc3e4423c73&imgtype=0&src=http%3A%2F%2Ftx.haiqq.com%2Fuploads%2Fallimg%2F150403%2F210P235H-3.jpg", "Java"));
        courseList2.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513941417280&di=8cca2283d93d9ef2514008629b6abc7c&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D559118063%2C1910583601%26fm%3D214%26gp%3D0.jpg", "C#"));
        courseList2.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513941392711&di=276b3726d00eefcef4b385b5606ce990&imgtype=0&src=http%3A%2F%2Fimage.3761.com%2Fattachments%2Fimage%2F2015-07%2F20150730090715_39057.jpg", "Android"));
        courseList2.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1513931395&di=203774f4e4696a2777b577461257bded&src=http://www.ld12.com/upimg358/allimg/c150402/142O5W545F60-BF3.jpg", "语文"));
        courseList2.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1513931395&di=77308a936f879c7a5c2ac777f0045732&src=http://www.qqwangming.org/uploads/allimg/c141226/1419561116120-1146495.jpg", "数学"));

        List<Course> courseList3 = new ArrayList<>();
        courseList3.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513941392714&di=f2fd750aaf59467618382fc3e4423c73&imgtype=0&src=http%3A%2F%2Ftx.haiqq.com%2Fuploads%2Fallimg%2F150403%2F210P235H-3.jpg", "Java"));
        courseList3.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513941417280&di=8cca2283d93d9ef2514008629b6abc7c&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D559118063%2C1910583601%26fm%3D214%26gp%3D0.jpg", "C#"));
        courseList3.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513941392711&di=276b3726d00eefcef4b385b5606ce990&imgtype=0&src=http%3A%2F%2Fimage.3761.com%2Fattachments%2Fimage%2F2015-07%2F20150730090715_39057.jpg", "Android"));
        courseList3.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1513931395&di=203774f4e4696a2777b577461257bded&src=http://www.ld12.com/upimg358/allimg/c150402/142O5W545F60-BF3.jpg", "语文"));
        courseList3.add(new Course("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1513931395&di=77308a936f879c7a5c2ac777f0045732&src=http://www.qqwangming.org/uploads/allimg/c141226/1419561116120-1146495.jpg", "数学"));


        List<User> users = new ArrayList<>();
        users.add(new User("张三", courseList));
        users.add(new User("李四", courseList2));
        users.add(new User("王五", courseList3));
        return users;
    }
}
