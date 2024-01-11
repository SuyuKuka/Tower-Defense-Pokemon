package model;

import java.util.ArrayList;

public class Hero {
    private String name;
    private int buff;
    private int debuff;
    private String image;

    private String desc;

    private static ArrayList<Hero> list=new ArrayList<>();


    public Hero(String name, int buff, int debuff, String image, String desc) {
        this.name=name;
        this.buff=buff;
        this.debuff=debuff;
        this.image=image;
        this.desc=desc;

        list.add(this);
    }

    public Hero(){

    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ArrayList<Hero> getList() {
        return list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getBuff() {
        return buff;
    }

    public void setBuff(int buff) {
        this.buff = buff;
    }

    public int getDebuff() {
        return debuff;
    }

    public void setDebuff(int debuff) {
        this.debuff = debuff;
    }
}
