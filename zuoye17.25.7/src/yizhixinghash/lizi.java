package yizhixinghash;



import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by weicong on 17-7-26.
 */
public class lizi<T> {

    private TreeMap<Long, T> huan;
    private List<T> zhenshi;
    private final int huangeshu = 100;

    public lizi(List<T> z) {
        super();
        this.zhenshi = z;
        init();
    }

    private void init() {
        huan = new TreeMap<Long, T>();
        for (int i = 0; i != zhenshi.size(); ++i) {
            final T shardInfo = zhenshi.get(i);
            for (int a =0; a < huangeshu; a++)
                huan.put
               (hash("zhenshi-" + i + "-huan-" + a), shardInfo);

        }
    }

    public T gethuanInfo(String key) {
        SortedMap<Long, T> tail = huan.tailMap(hash(key));
        if (tail.size() == 0) {
            return huan.get(huan.firstKey());
        }
        return tail.get(tail.firstKey());
    }

    private Long hash(String key) {

        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);
        long z;

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;

            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }

}