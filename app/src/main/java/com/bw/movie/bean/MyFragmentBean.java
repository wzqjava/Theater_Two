package com.bw.movie.bean;

/**
 * date:2019/1/14
 * author:赵豪轩(xuan)
 * function:
 */
public class MyFragmentBean {
    String name;
    String name1;
    int img;
    int img1;

    public MyFragmentBean(String name, String name1, int img, int img1) {
        this.name = name;
        this.name1 = name1;
        this.img = img;
        this.img1 = img1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }
}
