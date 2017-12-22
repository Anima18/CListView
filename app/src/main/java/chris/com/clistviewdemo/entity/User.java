package chris.com.clistviewdemo.entity;

import java.util.List;

/**
 * Created by jianjianhong on 2017/3/8.
 */

public class User {
    public String name;
    private String avatar;
    private boolean isSelected;
    private Address address;
    private List<Course> courseList;

    public User(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public User(String name, String avatar, boolean isSelected, Address address) {
        this.name = name;
        this.avatar = avatar;
        this.isSelected = isSelected;
        this.address = address;
    }

    public User(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
