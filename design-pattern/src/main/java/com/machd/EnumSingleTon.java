package com.machd;

/**
 *  优点：因为枚举类型的底层是不能使用反射进行破坏的
 *  缺点：也是懒汉式的一种，可能会造成内存浪费
 * @author machd
 * @version 3.0
 * @date 2022/4/23
 */
public enum EnumSingleTon {
    INSTANCE;


    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleTon getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        EnumSingleTon instance = EnumSingleTon.getInstance();
        // 注入所需要的属性
        instance.setData(new Object());

    }
}
