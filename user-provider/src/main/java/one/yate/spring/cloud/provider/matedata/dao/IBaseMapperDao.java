package one.yate.spring.cloud.provider.matedata.dao;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
public interface IBaseMapperDao<E, PK> {
    /**
     * @param e
     * @description 通过实体进行添加
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(final E e);

    /**
     * @param list
     * @description 通过集合进行批量添加
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void batchAdd(final List<E> list);

    /**
     * @param e
     * @description 通过主键进行删除
     */
    Integer remove(@Param(value = "id") final PK e);

    /**
     * @param ids
     * @description 通过主键进行批量删除
     */
    void batchRemove(final PK[] ids);

    /**
     * @param e
     * @description 通过实体更新
     */
    Integer update(final E e);

    /**
     * @param id
     * @return
     * @throws Exception
     * @description 通过主键查询
     */
    E getEntity(@Param(value = "id") final PK id);
}