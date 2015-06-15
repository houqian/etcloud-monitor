package test03;
 
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;
 
/**
 * һ����Hash�㷨
 * �㷨��⣺http://blog.csdn.net/sparkliang/article/details/5279393
 * �㷨ʵ�֣�https://weblogs.java.net/blog/2007/11/27/consistent-hashing
 * @author xiaoleilu
 *
 * @param <T> �ڵ�����
 */
public class ConsistentHash<T> {
    /** Hash������������Զ���hash�㷨 */
    HashFunc hashFunc;
    /** ���ƵĽڵ���� */
    private final int numberOfReplicas;
    /** һ����Hash�� */
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();
     
    /**
     * ���죬ʹ��JavaĬ�ϵ�Hash�㷨
     * @param numberOfReplicas ���ƵĽڵ����������ÿ���ڵ�ĸ��ƽڵ������ڸ��ؾ���
     * @param nodes �ڵ����
     */
    public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        this.hashFunc = new HashFunc() {
             
            @Override
            public Integer hash(Object key) {
                String data = key.toString();
                //Ĭ��ʹ��FNV1hash�㷨
                final int p = 16777619;
                int hash = (int) 2166136261L;
                for (int i = 0; i < data.length(); i++)
                    hash = (hash ^ data.charAt(i)) * p;
                hash += hash << 13;
                hash ^= hash >> 7;
                hash += hash << 3;
                hash ^= hash >> 17;
                hash += hash << 5;
                return hash;
            }
        };
        //��ʼ���ڵ�
        for (T node : nodes) {
            add(node);
        }
    }
 
    /**
     * ����
     * @param hashFunc hash�㷨����
     * @param numberOfReplicas ���ƵĽڵ����������ÿ���ڵ�ĸ��ƽڵ������ڸ��ؾ���
     * @param nodes �ڵ����
     */
    public ConsistentHash(HashFunc hashFunc, int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        this.hashFunc = hashFunc;
        //��ʼ���ڵ�
        for (T node : nodes) {
            add(node);
        }
    }
 
    /**
     * ���ӽڵ�<br>
     * ÿ����һ���ڵ㣬�ͻ��ڱջ������Ӹ������ƽڵ���<br>
     * ���縴�ƽڵ�����2����ÿ���ô˷���һ�Σ�������������ڵ㣬�������ڵ�ָ��ͬһNode
     * ����hash�㷨�����node��toString�������ʰ���toStringȥ��
     * @param node �ڵ����
     */
    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunc.hash(node.toString() + i), node);
        }
    }
 
    /**
     * �Ƴ��ڵ��ͬʱ�Ƴ���Ӧ������ڵ�
     * @param node �ڵ����
     */
    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunc.hash(node.toString() + i));
        }
    }
 
    /**
     * ���һ�������˳ʱ��ڵ�
     * @param key Ϊ������ȡHash��ȡ��˳ʱ�뷽���������һ������ڵ��Ӧ��ʵ�ʽڵ�
     * @return �ڵ����
     */
    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = hashFunc.hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle.tailMap(hash); //���ش�ӳ��Ĳ�����ͼ��������ڵ��� hash
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        //��������
        return circle.get(hash);
    }
 
    /**
     * Hash�㷨���������Զ���hash�㷨
     * @author xiaoleilu
     *
     */
    public interface HashFunc {
        public Integer hash(Object key);
    }
}