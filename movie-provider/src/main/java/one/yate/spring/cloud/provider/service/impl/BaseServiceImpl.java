package one.yate.spring.cloud.provider.service.impl;

import one.yate.spring.cloud.provider.metadata.dao.IBaseMapperDao;
import one.yate.spring.cloud.provider.service.IBaseService;

import java.util.List;


/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
public abstract class BaseServiceImpl<E, PK> implements IBaseService<E, PK> {
    protected abstract IBaseMapperDao<E, PK> getMapperDao();

    public void add(E e) {
        this.getMapperDao().add(e);
    }

    public void batchAdd(List<E> list) {
        this.getMapperDao().batchAdd(list);
    }

    public int remove(PK id) {
        return this.getMapperDao().remove(id);
    }

    public void batchRemove(PK[] ids) {
        this.getMapperDao().batchRemove(ids);
    }

    public int update(E e) {
        return this.getMapperDao().update(e);
    }

    public E getEntity(PK id) {
        return this.getMapperDao().getEntity(id);
    }

}