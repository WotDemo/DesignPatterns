package ysn.com.demo.designpatterns.singleton;

/**
 * @Author yangsanning
 * @ClassName StaticInnerClassImplementation
 * @Description 静态内部类实现
 * 当 StaticInnerClassImplementation 类加载时，静态内部类 Holder 没有被加载进内存。
 * 只有当调用 newInstance() 方法从而触发 Holder.instance 时 Holder 才会被加载，此时初始化 instance 实例，并且 JVM 能确保 instance 只被实例化一次。
 * 这种方式不仅具有延迟初始化的好处，而且由 JVM 提供了对线程安全的支持。
 * @Date 2020/5/10
 */
public class StaticInnerClassImplementation {

    private StaticInnerClassImplementation() {
    }

    /**
     * 主要是使用了 嵌套类可以访问外部类的静态属性和静态方法 的特性
     * 很多人都会把这个嵌套类说成是静态内部类，严格地说，内部类和嵌套类是不一样的，它们能访问的外部类权限也是不一样的。
     */
    private static class Holder {
        private static final StaticInnerClassImplementation instance = new StaticInnerClassImplementation();
    }

    public static StaticInnerClassImplementation newInstance() {
        return Holder.instance;
    }
}
