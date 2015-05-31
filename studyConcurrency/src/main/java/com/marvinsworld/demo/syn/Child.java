package com.marvinsworld.demo.syn;

/**
 * 测试synchronized重入锁,没有父类实例,只有子类实例.
 * 其实不需要用父子类来做例子,完全可以使用一个类的两个不同的synchronized方法.
 */
public class Child extends Father {
    public static void main(String[] args) {
        Child child = new Child();
        child.doSomething();
    }
 
    public synchronized void doSomething() {
        System.out.println("child.doSomething()");
        doAnotherThing(); // 调用自己类中其他的synchronized方法
         
    }
 
    private synchronized void doAnotherThing() {
        super.doSomething(); // 调用父类的synchronized方法
        System.out.println("child.doAnotherThing()");
    }
}
 
class Father {
    public synchronized void doSomething() {
        System.out.println("father.doSomething()");
    }
}