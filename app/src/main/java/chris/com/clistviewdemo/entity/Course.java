package chris.com.clistviewdemo.entity;

/**
 * Created by jianjianhong on 2017/3/14.
 */

public class Course {
    private String avatar;
    private String course;

    public Course(String course) {
        this.course = course;
    }

    public Course(String avatar, String course) {
        this.avatar = avatar;
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
