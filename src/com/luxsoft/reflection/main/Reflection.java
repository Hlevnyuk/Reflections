package com.luxsoft.reflection.main;
import com.luxsoft.reflection.main.Human;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.*;
import java.util.Arrays;

public class Reflection{
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException {
        System.out.println("Метод принимает класс и возвращает созданный объект этого класса");
        System.out.println();
        Human human = new Human(1, "Artem", 19, true);
        System.out.println(generateInstance(Human.class));
        System.out.println();
        System.out.println("Метод принимает object и вызывает у него все методы без параметров");
        System.out.println();
        callMethodsWithoutParameters(human);
        System.out.println();
        System.out.println("Метод принимает object и выводит на экран все сигнатуры методов в который есть final");
        System.out.println();
        showSignaturesOfAllFinalMethods(human);
        System.out.println();
        System.out.println("Метод принимает Class и выводит все не публичные методы этого класса");
        System.out.println();
        showAllNonPublicMethods(Human.class);
        System.out.println();
        System.out.println("Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует");
        System.out.println();
        showAllSuperClassesAndInterfaces(Human.class);
        System.out.println();
        System.out.println("Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)");
        System.out.println();
        System.out.println(human);
        changeFieldsOfTheObjectToDefault(human);
        System.out.println(human);
    }
    public static Object generateInstance(Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors){
            if(constructor.getParameterCount() == 0){
                return constructor.newInstance();
            }
        }
        return "See in method generateInstance, you have problem";
    }
    public static void callMethodsWithoutParameters(Object object) throws InvocationTargetException, IllegalAccessException, NullPointerException {
        Class c = object.getClass();
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterCount() == 0) {
                if (!method.canAccess(object)) {
                    method.trySetAccessible();
                }
                try {
                    method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void showSignaturesOfAllFinalMethods(Object object) {
        Class c = object.getClass();
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (Modifier.isFinal(method.getModifiers())) {
                System.out.println(method);
            }
        }
    }
    public static void showAllNonPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            if(!Modifier.isPublic(method.getModifiers())){
                System.out.println(method);
            }
        }
    }
    public static void showAllSuperClassesAndInterfaces(Class clazz) {
        while (clazz != null){
            System.out.println(clazz.getName() + " " + Arrays.toString(clazz.getInterfaces()));
            clazz = clazz.getSuperclass();
        }
    }
    public static void changeFieldsOfTheObjectToDefault(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if(Modifier.isPrivate(field.getModifiers())){
                field.setAccessible(true);
                if(field.getType() == String.class){
                    field.set(object, null);
                }
                else if(field.getType() == int.class){
                    field.setInt(object, 0);
                }
                else if(field.getType() == boolean.class){
                    field.setBoolean(object, false);
                }
            }
        }
    }
}