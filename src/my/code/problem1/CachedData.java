package my.code.problem1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedData {
    public static void main(String[] args) {

    }
    //构造缓存对象
    private static Map<String, Object> cacheData = new HashMap<String, Object>();
    //构造读写锁
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object processCachedData(String key) {
        Object value = null;
        try {
            //当线程开始读时，首先开始加上读锁
            rwl.readLock().lock();
            //获取值
            value = cacheData.get(key);
            //判断是否存在值
            if (value == null) {
                try {
                    //在开始写之前，首先要释放读锁，否则写锁无法拿到
                    rwl.readLock().unlock();
                    //获取写锁开始写数据
                    rwl.writeLock().lock();


                    if (value == null) {
                        value = "mycode1";
                        cacheData.put(key, value);
                    }
                    //写完之后重入降级为读锁
                    rwl.readLock().lock();
                } finally {
                    //最后释放写锁
                    rwl.writeLock().unlock();
                }
            }
        } finally {
            //释放读锁
            rwl.readLock().unlock();
        }
        return value;
    }
}
