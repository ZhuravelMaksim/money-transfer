package io.test.money_transfer;

import org.redisson.api.ObjectListener;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RSet;
import org.redisson.api.SortOrder;
import org.redisson.api.mapreduce.RCollectionMapReduce;
import org.redisson.client.codec.Codec;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class TestRedisSet<V> extends HashSet<V> implements RSet<V> {

    @Override
    public RCountDownLatch getCountDownLatch(V value) {
        return null;
    }

    @Override
    public RPermitExpirableSemaphore getPermitExpirableSemaphore(V value) {
        return null;
    }

    @Override
    public RSemaphore getSemaphore(V value) {
        return null;
    }

    @Override
    public RLock getFairLock(V value) {
        return null;
    }

    @Override
    public RReadWriteLock getReadWriteLock(V value) {
        return null;
    }

    @Override
    public RLock getLock(V value) {
        return null;
    }

    @Override
    public Stream<V> stream(int count) {
        return super.stream();
    }

    @Override
    public Stream<V> stream(String pattern, int count) {
        return super.stream();
    }

    @Override
    public Stream<V> stream(String pattern) {
        return super.stream();
    }

    @Override
    public Iterator<V> iterator(int count) {
        return super.iterator();
    }

    @Override
    public Iterator<V> iterator(String pattern, int count) {
        return super.iterator();
    }

    @Override
    public Iterator<V> iterator(String pattern) {
        return super.iterator();
    }

    @Override
    public <KOut, VOut> RCollectionMapReduce<V, KOut, VOut> mapReduce() {
        return null;
    }

    @Override
    public Set<V> removeRandom(int amount) {
        return null;
    }

    @Override
    public V removeRandom() {
        return null;
    }

    @Override
    public V random() {
        return null;
    }

    @Override
    public Set<V> random(int count) {
        return null;
    }

    @Override
    public boolean move(String destination, V member) {
        return false;
    }

    @Override
    public Set<V> readAll() {
        return this;
    }

    @Override
    public int union(String... names) {
        return 0;
    }

    @Override
    public Set<V> readUnion(String... names) {
        return null;
    }

    @Override
    public int diff(String... names) {
        return 0;
    }

    @Override
    public Set<V> readDiff(String... names) {
        return null;
    }

    @Override
    public int intersection(String... names) {
        return 0;
    }

    @Override
    public Set<V> readIntersection(String... names) {
        return null;
    }

    @Override
    public boolean tryAdd(V... values) {
        return false;
    }

    @Override
    public RFuture<Boolean> retainAllAsync(Collection<?> c) {
        return null;
    }

    @Override
    public RFuture<Boolean> removeAllAsync(Collection<?> c) {
        return null;
    }

    @Override
    public RFuture<Boolean> containsAsync(Object o) {
        return null;
    }

    @Override
    public RFuture<Boolean> containsAllAsync(Collection<?> c) {
        return null;
    }

    @Override
    public RFuture<Boolean> removeAsync(Object o) {
        return null;
    }

    @Override
    public RFuture<Integer> sizeAsync() {
        return null;
    }

    @Override
    public RFuture<Boolean> addAsync(V e) {
        return null;
    }

    @Override
    public RFuture<Boolean> addAllAsync(Collection<? extends V> c) {
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
    public void removeListener(int listenerId) {

    }

    @Override
    public RFuture<Set<V>> removeRandomAsync(int amount) {
        return null;
    }

    @Override
    public RFuture<V> removeRandomAsync() {
        return null;
    }

    @Override
    public RFuture<V> randomAsync() {
        return null;
    }

    @Override
    public RFuture<Set<V>> randomAsync(int count) {
        return null;
    }

    @Override
    public RFuture<Boolean> moveAsync(String destination, V member) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readAllAsync() {
        return null;
    }

    @Override
    public RFuture<Integer> unionAsync(String... names) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readUnionAsync(String... names) {
        return null;
    }

    @Override
    public RFuture<Integer> diffAsync(String... names) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readDiffAsync(String... names) {
        return null;
    }

    @Override
    public RFuture<Integer> intersectionAsync(String... names) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readIntersectionAsync(String... names) {
        return null;
    }

    @Override
    public RFuture<Boolean> tryAddAsync(V... values) {
        return null;
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
    public Set<V> readSort(SortOrder order) {
        return null;
    }

    @Override
    public Set<V> readSort(SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public Set<V> readSort(String byPattern, SortOrder order) {
        return null;
    }

    @Override
    public Set<V> readSort(String byPattern, SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public <T> Collection<T> readSort(String byPattern, List<String> getPatterns, SortOrder order) {
        return null;
    }

    @Override
    public <T> Collection<T> readSort(String byPattern, List<String> getPatterns, SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public Set<V> readSortAlpha(SortOrder order) {
        return null;
    }

    @Override
    public Set<V> readSortAlpha(SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public Set<V> readSortAlpha(String byPattern, SortOrder order) {
        return null;
    }

    @Override
    public Set<V> readSortAlpha(String byPattern, SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public <T> Collection<T> readSortAlpha(String byPattern, List<String> getPatterns, SortOrder order) {
        return null;
    }

    @Override
    public <T> Collection<T> readSortAlpha(String byPattern, List<String> getPatterns, SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public int sortTo(String destName, SortOrder order) {
        return 0;
    }

    @Override
    public int sortTo(String destName, SortOrder order, int offset, int count) {
        return 0;
    }

    @Override
    public int sortTo(String destName, String byPattern, SortOrder order) {
        return 0;
    }

    @Override
    public int sortTo(String destName, String byPattern, SortOrder order, int offset, int count) {
        return 0;
    }

    @Override
    public int sortTo(String destName, String byPattern, List<String> getPatterns, SortOrder order) {
        return 0;
    }

    @Override
    public int sortTo(String destName, String byPattern, List<String> getPatterns, SortOrder order, int offset, int count) {
        return 0;
    }

    @Override
    public RFuture<Set<V>> readSortAsync(SortOrder order) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readSortAsync(SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readSortAsync(String byPattern, SortOrder order) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readSortAsync(String byPattern, SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public <T> RFuture<Collection<T>> readSortAsync(String byPattern, List<String> getPatterns, SortOrder order) {
        return null;
    }

    @Override
    public <T> RFuture<Collection<T>> readSortAsync(String byPattern, List<String> getPatterns, SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readSortAlphaAsync(SortOrder order) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readSortAlphaAsync(SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readSortAlphaAsync(String byPattern, SortOrder order) {
        return null;
    }

    @Override
    public RFuture<Set<V>> readSortAlphaAsync(String byPattern, SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public <T> RFuture<Collection<T>> readSortAlphaAsync(String byPattern, List<String> getPatterns, SortOrder order) {
        return null;
    }

    @Override
    public <T> RFuture<Collection<T>> readSortAlphaAsync(String byPattern, List<String> getPatterns, SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public RFuture<Integer> sortToAsync(String destName, SortOrder order) {
        return null;
    }

    @Override
    public RFuture<Integer> sortToAsync(String destName, SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public RFuture<Integer> sortToAsync(String destName, String byPattern, SortOrder order) {
        return null;
    }

    @Override
    public RFuture<Integer> sortToAsync(String destName, String byPattern, SortOrder order, int offset, int count) {
        return null;
    }

    @Override
    public RFuture<Integer> sortToAsync(String destName, String byPattern, List<String> getPatterns, SortOrder order) {
        return null;
    }

    @Override
    public RFuture<Integer> sortToAsync(String destName, String byPattern, List<String> getPatterns, SortOrder order, int offset, int count) {
        return null;
    }
}
