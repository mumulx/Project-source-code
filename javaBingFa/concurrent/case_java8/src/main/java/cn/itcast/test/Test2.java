package cn.itcast.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test2")
public class Test2 {
    public static void test02() throws ExecutionException, InterruptedException {

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running");
                Thread.sleep(1000);//线程停止
                return 100;
            }
        });
        Thread t = new Thread(task,"t1");
        t.start();

        log.debug("{}",task.get());//阻塞，直到task返回结果   {}是sl4j的占位符，多个参数就写多个{}





    }
    public static void main(String[] args) {
        Runnable r = () -> {
            log.debug("running");
        };

        Thread t = new Thread(r, "t2");
// 创建任务对象
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                log.debug("hello");
            }
        };
// 参数1 是任务对象; 参数2 是线程名字，推荐
        Thread t2 = new Thread(task2, "t2");
        t2.start();// 创建任务对象
    }
}
