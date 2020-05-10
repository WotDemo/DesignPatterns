package ysn.com.demo.designpatterns.singleton;

/**
 * @Author yangsanning
 * @ClassName LazyMore
 * @Description 懒汉式: 线程不安全。
 * 有延迟加载: 不是在类加载的时候就创建了，而是在调用 newStance() 的时候才会创建。
 * @Date 2020/5/10
 */
public class LazyMoreThreadUnsafe {

    /**
     * 私有静态变量 instance 被延迟实例化，这样做的好处是：如果没有用到该类，那么就不会实例化 instance，从而节约资源。
     */
    private static LazyMoreThreadUnsafe instance;

    private LazyMoreThreadUnsafe() {

    }

    /**
     * 这个实现在多线程环境下是不安全的，如果多个线程能够同时进入 if (instance == null)，
     * 并且此时 instance == null，那么会有多个线程执行 instance = new LazyMore(); 语句，这将导致实例化多次 instance。
     *
     * @return
     */
    public static LazyMoreThreadUnsafe newInstance() {
        if (instance == null) {
            instance = new LazyMoreThreadUnsafe();
        }
        return instance;
    }
}
