package ysn.com.demo.designpatterns.singleton;

/**
 * @Author yangsanning
 * @ClassName LazyMoreThreadSafePoorPerformance
 * @Description 懒汉式-线程安全-性能不好
 * 为了解决{@link LazyMoreThreadUnsafe}的问题，我们可以直接在newInstance()方法上面直接加上一把 synchronized 同步锁。
 * 那么在一个时间点只能有一个线程能够进入该方法，从而避免了实例化多次 instance。
 * 但是当一个线程进入该方法之后，其它试图进入该方法的线程都必须等待，即使 instance 已经被实例化了。
 * 这会让线程阻塞时间过长，因此该方法有性能问题，不推荐使用。
 * @Date 2020/5/10
 */
public class LazyMoreThreadSafePoorPerformance {

    private static LazyMoreThreadSafePoorPerformance instance;

    /**
     * 方法在{@link LazyMoreThreadUnsafe}的基础上加了synchronized
     */
    public static synchronized LazyMoreThreadSafePoorPerformance newInstance() {
        if (instance == null) {
            instance = new LazyMoreThreadSafePoorPerformance();
        }
        return instance;
    }
}
