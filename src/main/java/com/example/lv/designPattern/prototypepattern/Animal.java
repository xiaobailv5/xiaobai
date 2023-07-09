package com.example.lv.designPattern.prototypepattern;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 动物
 * @date 2023/7/7 14:45:53
 */
public abstract class Animal implements Cloneable{

    private String id;
    protected String type;

    /**
     * 动物
     */
    abstract void produce();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
