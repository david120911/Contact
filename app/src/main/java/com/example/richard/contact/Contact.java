package com.example.richard.contact;

public class Contact {
    private int id;
    private String name;
    private String[] pinyin;

    public String[] getPinyin() {
        return pinyin;
    }

    public void setPinyin(String[] pinyin) {
        this.pinyin = pinyin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
