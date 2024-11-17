package com.example.lv.es;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.es
 * @className: UserTest
 * @author: dus
 * @description:
 * @date: 2024/11/13 17:24
 * @version: 1.0
 */
public class UserTest implements Cloneable {

    private String name;

    private Integer age;

    private String address;

    public UserTest(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public UserTest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
