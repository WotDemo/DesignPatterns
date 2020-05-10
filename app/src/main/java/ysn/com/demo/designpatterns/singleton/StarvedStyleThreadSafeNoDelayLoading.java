package ysn.com.demo.designpatterns.singleton;

/**
 * @Author yangsanning
 * @ClassName StarvedChineseThreadSafeNoDelayLoading
 * @Description 饿汉式-线程安全-无延迟加载
 * 饿汉式就是 : 采取直接实例化 instance 的方式，这样就不会产生线程不安全问题。
 * 这种方式比较常用，但容易产生垃圾对象(丢失了延迟实例化(lazy loading)带来的节约资源的好处)。
 * 它基于 classloader机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，虽然导致类装载的原因有很多种，
 * 在单例模式中大多数都是调用 getInstance 方法， 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，
 * 这时候初始化 instance 显然没有达到 lazyloading 的效果。
 * @Date 2020/5/10
 */
public class StarvedStyleThreadSafeNoDelayLoading {

    /**
     * 急切的创建了instance, 所以叫饿汉式
     */
    private static StarvedStyleThreadSafeNoDelayLoading instance = new StarvedStyleThreadSafeNoDelayLoading();

    private StarvedStyleThreadSafeNoDelayLoading() {
    }

    public static StarvedStyleThreadSafeNoDelayLoading newInstance() {
        return instance;
    }

    /**
     * 如果我们只是要调用 StarvedStyleThreadSafeNoDelayLoading.getStr(...)，
     * 本来是不想要生成 StarvedStyleThreadSafeNoDelayLoading 实例的，不过没办法，已经生成了
     */
    public static String getStr(String str) {
        return "hello" + str;
    }
}
