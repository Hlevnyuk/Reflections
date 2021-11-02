package com.luxsoft.reflection.main;
import java.io.Serializable;
import java.util.EventListener;
public class Human implements Serializable, EventListener, Cloneable{
    private int id;
    private String name;
    private int age;
    private boolean gender;
    public Human(){
        setId(10);
        setName("Tolik");
        setAge(26);
        setGender(true);
    }
    public Human(int id, String name, int age, boolean gender){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    private int getId(){
        return id;
    }
    private void setId(int id){
        this.id = id;
    }
    private String getName(){
        return name;
    }
    private void setName(String name){
        this.name = name;
    }
    private boolean getGender(){
        return gender;
    }
    private void setGender(boolean gender){
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    final public void firstMethod() {
        System.out.println("The first final method");
    }
    final public void secondMethod() {
        System.out.println("Second final method");
    }
    final public void thirdMethod(){
        System.out.println("Third final method");
    }
    @Override
    public String toString() {
        return "Human:" +
                " id=" + id +
                ", name=" + name +
                ", age=" + age +
                ", gender=" + gender;
    }
}
