package JavaTest;

import java.util.LinkedList;

/**
 * person 类
 */
public class Person {

    public String height;
    public int age;
    public String sex;
    public boolean yes;
    public String name;
    public LinkedList list;

    public boolean isYes() {
        return yes;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setYes(boolean yes) {
        this.yes = yes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList getList() {
        return list;
    }

    public void setList(LinkedList list) {
        this.list = list;
    }

    public Person(String height, int age) {
        this.height = height;
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getheight() {
        return height;
    }

    public void setheight(String height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return ((Person) obj).getHeight().equals(this.getHeight()) && ((Person) obj).getAge() == this.getAge();
    }

    public String toString() {
        return "[height: " + this.height + ", age: " + this.age + "]";
    }
}

