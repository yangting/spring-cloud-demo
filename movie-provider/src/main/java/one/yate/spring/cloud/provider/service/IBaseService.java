package one.yate.spring.cloud.provider.service;


import java.util.List;

/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
public interface IBaseService<E, PK> {
    void add(final E e);

    void batchAdd(final List<E> list);

    int remove(final PK id);

    void batchRemove(final PK[] ids);

    int update(final E e);

    E getEntity(final PK id);

}