package io.test.money_transfer;

import org.redisson.api.EvictionMode;
import org.redisson.api.ObjectListener;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.map.event.MapEntryListener;
import org.redisson.api.mapreduce.RMapReduce;
import org.redisson.client.codec.Codec;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TestRedisMap<K, V> extends HashMap<K, V> implements RMapCache<K, V> {

    @Override
    public void loadAll(boolean replaceExistingValues, int parallelism) {

    }

    @Override
    public void loadAll(Set<? extends K> keys, boolean replaceExistingValues, int parallelism) {

    }

    @Override
    public V putIfExists(K k, V v) {
        return null;
    }

    @Override
    public Set<K> randomKeys(int i) {
        return null;
    }

    @Override
    public Map<K, V> randomEntries(int i) {
        return null;
    }

    @Override
    public <KOut, VOut> RMapReduce<K, V, KOut, VOut> mapReduce() {
        return null;
    }

    @Override
    public RCountDownLatch getCountDownLatch(K key) {
        return null;
    }

    @Override
    public RPermitExpirableSemaphore getPermitExpirableSemaphore(K key) {
        return null;
    }

    @Override
    public RSemaphore getSemaphore(K key) {
        return null;
    }

    @Override
    public RLock getFairLock(K key) {
        return null;
    }

    @Override
    public RReadWriteLock getReadWriteLock(K key) {
        return null;
    }

    @Override
    public RLock getLock(K key) {
        return null;
    }

    @Override
    public int valueSize(K key) {
        return 0;
    }

    @Override
    public V addAndGet(K key, Number delta) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map, int batchSize) {

    }

    @Override
    public Map<K, V> getAll(Set<K> keys) {
        return null;
    }

    @SafeVarargs
    @Override
    public final long fastRemove(K... keys) {
        for (K key : keys) {
            remove(key);
        }
        return keys.length;
    }

    @Override
    public boolean fastPut(K key, V value) {
        put(key, value);
        return true;
    }

    @Override
    public boolean fastReplace(K key, V value) {
        return false;
    }

    @Override
    public boolean fastPutIfAbsent(K key, V value) {
        return false;
    }

    @Override
    public boolean fastPutIfExists(K k, V v) {
        return false;
    }

    @Override
    public Set<K> readAllKeySet() {
        return super.keySet();
    }

    @Override
    public Collection<V> readAllValues() {
        return super.values();
    }

    @Override
    public Set<Entry<K, V>> readAllEntrySet() {
        return super.entrySet();
    }

    @Override
    public Map<K, V> readAllMap() {
        return this;
    }

    @Override
    public Set<K> keySet(int count) {
        return null;
    }

    @Override
    public Set<K> keySet(String pattern, int count) {
        return null;
    }

    @Override
    public Set<K> keySet(String pattern) {
        return null;
    }

    @Override
    public Collection<V> values(String keyPattern) {
        return null;
    }

    @Override
    public Collection<V> values(String keyPattern, int count) {
        return null;
    }

    @Override
    public Collection<V> values(int count) {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet(String keyPattern) {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet(String keyPattern, int count) {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet(int count) {
        return null;
    }

    @Override
    public boolean expire(long timeToLive, TimeUnit timeUnit) {
        return false;
    }

    @Override
    public boolean expireAt(long timestamp) {
        return false;
    }

    @Override
    public boolean expireAt(Date timestamp) {
        return false;
    }

    @Override
    public boolean expire(Instant instant) {
        return false;
    }

    @Override
    public boolean clearExpire() {
        return false;
    }

    @Override
    public long remainTimeToLive() {
        return 0;
    }

    @Override
    public RFuture<V> mergeAsync(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction) {
        return null;
    }

    @Override
    public RFuture<V> computeAsync(K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
        return null;
    }

    @Override
    public RFuture<V> computeIfAbsentAsync(K k, Function<? super K, ? extends V> function) {
        return null;
    }

    @Override
    public RFuture<V> computeIfPresentAsync(K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
        return null;
    }

    @Override
    public RFuture<Void> loadAllAsync(boolean replaceExistingValues, int parallelism) {
        return null;
    }

    @Override
    public RFuture<Void> loadAllAsync(Set<? extends K> keys, boolean replaceExistingValues, int parallelism) {
        return null;
    }

    @Override
    public RFuture<Integer> valueSizeAsync(K key) {
        return null;
    }

    @Override
    public RFuture<Map<K, V>> getAllAsync(Set<K> keys) {
        return null;
    }

    @Override
    public RFuture<Void> putAllAsync(Map<? extends K, ? extends V> map) {
        return null;
    }

    @Override
    public RFuture<Void> putAllAsync(Map<? extends K, ? extends V> map, int batchSize) {
        return null;
    }

    @Override
    public RFuture<Set<K>> randomKeysAsync(int i) {
        return null;
    }

    @Override
    public RFuture<Map<K, V>> randomEntriesAsync(int i) {
        return null;
    }

    @Override
    public RFuture<V> addAndGetAsync(K key, Number delta) {
        return null;
    }

    @Override
    public RFuture<Boolean> containsValueAsync(Object value) {
        return null;
    }

    @Override
    public RFuture<Boolean> containsKeyAsync(Object key) {
        return null;
    }

    @Override
    public RFuture<Void> setMaxSizeAsync(int maxSize) {
        return null;
    }

    @Override
    public RFuture<Void> setMaxSizeAsync(int maxSize, EvictionMode mode) {
        return null;
    }

    @Override
    public RFuture<Boolean> trySetMaxSizeAsync(int maxSize) {
        return null;
    }

    @Override
    public RFuture<Boolean> trySetMaxSizeAsync(int maxSize, EvictionMode mode) {
        return null;
    }

    @Override
    public RFuture<V> putIfAbsentAsync(K key, V value, long ttl, TimeUnit unit) {
        return null;
    }

    @Override
    public RFuture<V> putIfAbsentAsync(K key, V value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit) {
        return null;
    }

    @Override
    public RFuture<V> putAsync(K key, V value, long ttl, TimeUnit unit) {
        return null;
    }

    @Override
    public RFuture<V> putAsync(K key, V value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit) {
        return null;
    }

    @Override
    public RFuture<Boolean> fastPutAsync(K key, V value, long ttl, TimeUnit unit) {
        return null;
    }

    @Override
    public RFuture<Boolean> fastPutAsync(K key, V value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit) {
        return null;
    }

    @Override
    public RFuture<Boolean> fastPutIfAbsentAsync(K key, V value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit) {
        return null;
    }

    @Override
    public RFuture<Boolean> updateEntryExpirationAsync(final K k, final long l, final TimeUnit timeUnit, final long l1, final TimeUnit timeUnit1) {
        return null;
    }

    @Override
    public RFuture<V> getWithTTLOnlyAsync(final K k) {
        return null;
    }

    @Override
    public RFuture<Integer> sizeAsync() {
        return null;
    }

    @Override
    public RFuture<Long> remainTimeToLiveAsync(K key) {
        return null;
    }

    @Override
    public RFuture<Long> fastRemoveAsync(K... keys) {
        return null;
    }

    @Override
    public RFuture<Boolean> fastPutAsync(K key, V value) {
        return null;
    }

    @Override
    public RFuture<Boolean> fastReplaceAsync(K key, V value) {
        return null;
    }

    @Override
    public RFuture<Boolean> fastPutIfAbsentAsync(K key, V value) {
        return null;
    }

    @Override
    public RFuture<Boolean> fastPutIfExistsAsync(K k, V v) {
        return null;
    }

    @Override
    public RFuture<Set<K>> readAllKeySetAsync() {
        return null;
    }

    @Override
    public RFuture<Collection<V>> readAllValuesAsync() {
        return null;
    }

    @Override
    public RFuture<Set<Entry<K, V>>> readAllEntrySetAsync() {
        return null;
    }

    @Override
    public RFuture<Map<K, V>> readAllMapAsync() {
        return null;
    }

    @Override
    public RFuture<V> getAsync(K key) {
        return null;
    }

    @Override
    public RFuture<V> putAsync(K key, V value) {
        return null;
    }

    @Override
    public RFuture<V> removeAsync(K key) {
        return null;
    }

    @Override
    public RFuture<V> replaceAsync(K key, V value) {
        return null;
    }

    @Override
    public RFuture<Boolean> replaceAsync(K key, V oldValue, V newValue) {
        return null;
    }

    @Override
    public RFuture<Boolean> removeAsync(Object key, Object value) {
        return null;
    }

    @Override
    public RFuture<V> putIfAbsentAsync(K key, V value) {
        return null;
    }

    @Override
    public RFuture<V> putIfExistsAsync(K k, V v) {
        return null;
    }

    @Override
    public RFuture<Boolean> expireAsync(long timeToLive, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public RFuture<Boolean> expireAtAsync(Date timestamp) {
        return null;
    }

    @Override
    public RFuture<Boolean> expireAtAsync(long timestamp) {
        return null;
    }

    @Override
    public RFuture<Boolean> expireAsync(Instant instant) {
        return null;
    }

    @Override
    public RFuture<Boolean> clearExpireAsync() {
        return null;
    }

    @Override
    public RFuture<Long> remainTimeToLiveAsync() {
        return null;
    }

    @Override
    public Long getIdleTime() {
        return null;
    }

    @Override
    public long sizeInMemory() {
        return 0;
    }

    @Override
    public void restore(byte[] state) {

    }

    @Override
    public void restore(byte[] state, long timeToLive, TimeUnit timeUnit) {

    }

    @Override
    public void restoreAndReplace(byte[] state) {

    }

    @Override
    public void restoreAndReplace(byte[] state, long timeToLive, TimeUnit timeUnit) {

    }

    @Override
    public byte[] dump() {
        return new byte[0];
    }

    @Override
    public boolean touch() {
        return false;
    }

    @Override
    public void migrate(String host, int port, int database, long timeout) {

    }

    @Override
    public void copy(String host, int port, int database, long timeout) {

    }

    @Override
    public boolean move(int database) {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean unlink() {
        return false;
    }

    @Override
    public void rename(String newName) {

    }

    @Override
    public boolean renamenx(String newName) {
        return false;
    }

    @Override
    public boolean isExists() {
        return false;
    }

    @Override
    public Codec getCodec() {
        return null;
    }

    @Override
    public int addListener(ObjectListener listener) {
        return 0;
    }

    @Override
    public void setMaxSize(int maxSize) {

    }

    @Override
    public void setMaxSize(int maxSize, EvictionMode mode) {

    }

    @Override
    public boolean trySetMaxSize(int maxSize) {
        return false;
    }

    @Override
    public boolean trySetMaxSize(int maxSize, EvictionMode mode) {
        return false;
    }

    @Override
    public V putIfAbsent(K key, V value, long ttl, TimeUnit ttlUnit) {
        return putIfAbsent(key, value);
    }

    @Override
    public V putIfAbsent(K key, V value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit) {
        return putIfAbsent(key, value);
    }

    @Override
    public V put(K key, V value, long ttl, TimeUnit unit) {
        return put(key, value);
    }

    @Override
    public V put(K key, V value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit) {
        return put(key, value);
    }

    @Override
    public boolean fastPut(K key, V value, long ttl, TimeUnit ttlUnit) {
        put(key, value);
        return true;
    }

    @Override
    public boolean fastPut(K key, V value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit) {
        put(key, value);
        return true;
    }

    @Override
    public boolean fastPutIfAbsent(K key, V value, long ttl, TimeUnit ttlUnit) {
        putIfAbsent(key, value);
        return true;
    }

    @Override
    public boolean fastPutIfAbsent(K key, V value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit) {
        putIfAbsent(key, value);
        return true;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map, long ttl, TimeUnit ttlUnit) {
        putAll(map);
    }

    @Override
    public boolean updateEntryExpiration(final K k, final long l, final TimeUnit timeUnit, final long l1, final TimeUnit timeUnit1) {
        return false;
    }

    @Override
    public V getWithTTLOnly(final K k) {
        return null;
    }

    @Override
    public RFuture<Void> putAllAsync(Map<? extends K, ? extends V> map, long ttl, TimeUnit ttlUnit) {
        return null;
    }

    @Override
    public int addListener(MapEntryListener listener) {
        return 0;
    }

    @Override
    public void removeListener(int listenerId) {

    }

    @Override
    public long remainTimeToLive(K key) {
        return 0;
    }

    @Override
    public RFuture<Long> getIdleTimeAsync() {
        return null;
    }

    @Override
    public RFuture<Long> sizeInMemoryAsync() {
        return null;
    }

    @Override
    public RFuture<Void> restoreAsync(byte[] state) {
        return null;
    }

    @Override
    public RFuture<Void> restoreAsync(byte[] state, long timeToLive, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public RFuture<Void> restoreAndReplaceAsync(byte[] state) {
        return null;
    }

    @Override
    public RFuture<Void> restoreAndReplaceAsync(byte[] state, long timeToLive, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public RFuture<byte[]> dumpAsync() {
        return null;
    }

    @Override
    public RFuture<Boolean> touchAsync() {
        return null;
    }

    @Override
    public RFuture<Void> migrateAsync(String host, int port, int database, long timeout) {
        return null;
    }

    @Override
    public RFuture<Void> copyAsync(String host, int port, int database, long timeout) {
        return null;
    }

    @Override
    public RFuture<Boolean> moveAsync(int database) {
        return null;
    }

    @Override
    public RFuture<Boolean> deleteAsync() {
        return null;
    }

    @Override
    public RFuture<Boolean> unlinkAsync() {
        return null;
    }

    @Override
    public RFuture<Void> renameAsync(String newName) {
        return null;
    }

    @Override
    public RFuture<Boolean> renamenxAsync(String newName) {
        return null;
    }

    @Override
    public RFuture<Boolean> isExistsAsync() {
        return null;
    }

    @Override
    public RFuture<Integer> addListenerAsync(ObjectListener listener) {
        return null;
    }

    @Override
    public RFuture<Void> removeListenerAsync(int listenerId) {
        return null;
    }

    @Override
    public void destroy() {

    }
}
