package com.kwdz.blog.svc.common.service;


import com.kwdz.blog.api.common.error.DataException;
import com.kwdz.blog.api.common.page.PageInfo;
import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.api.common.util.FastCopy;
import com.kwdz.blog.api.common.util.RedisUtil;
import com.kwdz.blog.svc.common.dao.CommonDao;
import com.kwdz.blog.svc.common.dao.FastPage;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.internal.QueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;
import javax.persistence.*;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用Service实现类
 *
 * @param <V> 实体Vo
 * @param <E> 实体
 */
@Slf4j
public class CommonService4RedisImpl<V, E> implements CommonService<V, E> {

    private Class<V> entityVoClass;//实体类模型

    private Class<E> entityClass;//实体类

    @Autowired
    private CommonDao<E> commonDao;//注入实体类仓库

    private String key;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RedisUtil redisUtil;

    @SuppressWarnings("unchecked")
    public CommonService4RedisImpl() {
        Type[] types = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        this.entityVoClass = (Class<V>) types[0];
        this.entityClass = (Class<E>) types[1];
    }

    /**
     * 获取分页结果PageInfo
     */
    @Override
    public ResultModel<PageInfo<V>> page(V entity) {
        //1.获取分页对象(Page<Entity>)
        Page<E> page = commonDao.findAll(FastPage.getSpecification(entity, entityClass), FastPage.getPageable(entity));
        //2.获取分页对象(PageInfo<EntityVo>)
        PageInfo<V> pageInfo = FastPage.getPageInfo(page, entityVoClass);
        //3.获取组织(Organization)
        return ResultModel.of(pageInfo);
    }

    /**
     * 获取自定义分页结果PageInfo
     */
    public ResultModel<PageInfo<V>> readPage(V entity, Query query) {
        //设置countQuerySQL语句
        Query countQuery = em.createNativeQuery("select count(1) from ( " + ((QueryImpl) query).getHibernateQuery().getQueryString() + " )");
        //设置countQuerySQL参数
        query.getParameters().forEach(parameter -> countQuery.setParameter(parameter.getName(), query.getParameterValue(parameter.getName())));
        //获取分页结果PageInfo
        return readPage(entity, query, countQuery);
    }

    /**
     * 获取自定义分页结果PageInfo
     */
    public ResultModel<PageInfo<V>> readPage(V entity, Query query, Query countQuery) {
        //1.获取分页对象(Page<Entity>/Page<Object[]>)
        Page page = FastPage.readPage(query, FastPage.getPageable(entity), countQuery);
        //2.获取分页对象(PageInfo<EntityVo>)
        PageInfo<V> pageInfo = FastPage.getPageInfo(page, entityVoClass);
        //3.获取组织(Organization)
        return ResultModel.of(pageInfo);
    }


    /**
     * 获取分页集合List
     */
    @Override
    public ResultModel<List<V>> pageList(V entity) {
        List<E> entityList = commonDao.findAll(FastPage.getSpecification(entity, entityClass));
        List<V> entityVoList = FastCopy.copyList(entityList, entityVoClass);
        return ResultModel.of(entityVoList);
    }

    /**
     * 获取普通List
     */
    @Override
    public ResultModel<List<V>> list(V entity) {
        List<E> entityList = commonDao.findAll(Example.of(FastCopy.copy(entity, entityClass)));
        List<V> entityVoList = FastCopy.copyList(entityList, entityVoClass);
        return ResultModel.of(entityVoList);
    }

    /**
     * 获取单个实体
     */
    @Override
    public ResultModel<V> get(String id) {
        // 从缓存中获取城市信息
        try {
            Object obj = Class.forName(this.entityClass.getName()).newInstance();
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.set(obj, id);
                    break;
                }
            }
            this.key = createKey(getId((E) obj));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {

        }
        // 缓存存在
        boolean hasKey = redisUtil.hasKey(this.key);
        if (hasKey) {
            V v = (V) redisUtil.get(this.key);

            log.info(this.entityVoClass.getSimpleName() + ".ServiceImpl.get() : 从缓存中获取了数据 >> " + v.toString());
            return ResultModel.of(v);
        }


        E entity = commonDao.findOne(id);
        if (entity == null) {
            throw new RuntimeException("实体ID不存在：" + id + "(" + entityClass + ")");
        }
        V entityVo = FastCopy.copy(entity, entityVoClass);

        // 插入缓存,60秒缓存
        redisUtil.set(this.key, entityVo, 60);
        log.info(this.entityVoClass.getSimpleName() + ".ServiceImpl.get() : 数据插入缓存 >> " + entityVo.toString());

        return ResultModel.of(entityVo);
    }

    /**
     * 获取单个实体且非空
     */
    @Override
    public ResultModel<V> getShow(String id) {
        E entity = commonDao.findOne(id);
        if (entity == null) {
            try {
                return ResultModel.of(entityVoClass.newInstance());
            } catch (Exception e) {
                return ResultModel.of(null);
            }
        }
        V entityVo = FastCopy.copy(entity, entityVoClass);
        return ResultModel.of(entityVo);
    }

    /**
     * 新增/保存单个实体
     */
    @Override
    public ResultModel<V> save(V entity) {
        try {
            E e = commonDao.save(FastCopy.copy(entity, entityClass));

//            Object obj = Class.forName(this.entityVoClass.getName()).newInstance();
            Class c = e.getClass();
            //  取得所有类成员变量
            Field[] fields = c.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                try {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Id.class)) {
                        //  缓存存在，删除缓存
                        this.key = createKey(getId(e));
                        boolean hasKey = redisUtil.hasKey(this.key);
                        if (hasKey) {
                            redisUtil.del(key);
                            log.info(this.entityVoClass.getSimpleName() + ".ServiceImpl.save() : 从缓存中删除  >> " + this.key + e.toString());
                        }
                        break;
                    }

                    if ("userId".equalsIgnoreCase(field.getName())) {
                        //  缓存存在，删除缓存
                        this.key = createKey(getId(e));
                        boolean hasKey = redisUtil.hasKey(this.key);
                        if (hasKey) {
                            redisUtil.del(key);
                            log.info(this.entityVoClass.getSimpleName() + ".ServiceImpl.save() : 从缓存中删除  >> " + this.key + e.toString());
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


            return ResultModel.of(FastCopy.copy(e, entityVoClass));
        } catch (Throwable e) {
            //  打印SQL最底层异常
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * 新增/保存批量实体
     */
    @Override
    public ResultModel<List<V>> saveList(List<V> entityList) {
        List<V> list = new ArrayList<>();
        if (entityList != null) {
            entityList.forEach((entity) -> list.add(FastCopy.copy(commonDao.save(FastCopy.copy(entity, entityClass)), entityVoClass)));
        }
        return ResultModel.of(list);
    }

    /**
     * 批量删除
     */
    @Override
    public ResultModel<List<String>> deleteBatch(List<String> ids) {
        if (ids != null && ids.size() > 0) {
            for (String id : ids) {
                commonDao.delete(id);
                try {
                    Object obj = Class.forName(this.entityClass.getName()).newInstance();
                    for (Field field : obj.getClass().getDeclaredFields()) {
                        if (field.isAnnotationPresent(Id.class)) {
                            field.set(obj, id);
                            break;
                        }
                    }
                    this.key = createKey(getId((E) obj));
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {

                }
                // 缓存存在
                boolean hasKey = redisUtil.hasKey(this.key);
                if (hasKey) {
                    V v = (V) redisUtil.get(this.key);
                    redisUtil.del(this.key);
                    log.info(this.entityVoClass.getSimpleName() + ".ServiceImpl.delete() : 从缓存中删除  >> " + v.toString());
                }
            }
            return ResultModel.of(ids);
        }
        return ResultModel.of(new ArrayList<>());
    }


    public String getId(E e) {
        String id = "";
        try {
            Class<? extends Object> tClass = e.getClass();

            //得到所有属性
            Field[] fields = tClass.getDeclaredFields();

            /**
             * 这里只需要 id 这个属性，所以直接取 fields[0] 这
             * 一个，如果id不是排在第一位，自己取相应的位置，
             * 如果有需要，可以写成for循环，遍历全部属性
             */
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldValue = (String) field.get(e);
                if (field.isAnnotationPresent(Id.class)) {
                    id = field.getName() + ":" + fieldValue;
                    break;
                }
            }
            return id;
        } catch (Exception ex) {
            log.info("没有这个属性");
            return null;
        }
    }

    private String createKey(String key) {
        return this.entityVoClass.getSimpleName() + ":" + key;
    }
}
