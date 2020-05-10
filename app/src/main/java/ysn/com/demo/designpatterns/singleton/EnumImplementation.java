package ysn.com.demo.designpatterns.singleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author yangsanning
 * @ClassName EnumImplementation
 * @Description 枚举实现
 * 这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
 * 该实现在多次序列化再进行反序列化之后，不会得到多个实例。而其它实现需要使用 transient 修饰所有字段，并且实现序列化和反序列化的方法。
 * @Date 2020/5/10
 */
public class EnumImplementation {

    public static int clientTotal = 1000;

    public static int threadTotal = 200;

    private EnumImplementation() {

    }

    public static EnumImplementation newInstance() {
        return Sing.INSTANCE.newInstance();
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        /**
         * 注意set也要加锁
         */
        Set<EnumImplementation> set = Collections.synchronizedSet(new HashSet<>());

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    set.add(EnumImplementation.newInstance());
                    semaphore.release();
                } catch (Exception e) {

                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();

        // 1
        System.out.println(set.size());
    }

    private enum Sing {

        /**
         * 实例
         */
        INSTANCE;

        private EnumImplementation instance;

        /**
         * jvm保证只运行一次
         */
        Sing() {
            instance = new EnumImplementation();
        }

        public EnumImplementation newInstance() {
            return instance;
        }
    }
}
