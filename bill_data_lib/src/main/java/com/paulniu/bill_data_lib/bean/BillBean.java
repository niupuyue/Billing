package com.paulniu.bill_data_lib.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import com.paulniu.bill_data_lib.dao.DaoSession;
import com.paulniu.bill_data_lib.dao.TypeBeanDao;
import com.paulniu.bill_data_lib.dao.BillBeanDao;

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 8:06 PM
 * desc:
 */
@Entity
public class BillBean {
    @Id(autoincrement = true)
    public Long id = null;
    public String title;
    public float money;
    public Long typeId;
    // 单项关联表
    @ToOne(joinProperty = "typeId")
    public TypeBean typeBean;
    public long time;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1884953332)
    private transient BillBeanDao myDao;
    @Generated(hash = 1863371532)
    public BillBean(Long id, String title, float money, Long typeId, long time) {
        this.id = id;
        this.title = title;
        this.money = money;
        this.typeId = typeId;
        this.time = time;
    }
    @Generated(hash = 562884989)
    public BillBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public float getMoney() {
        return this.money;
    }
    public void setMoney(float money) {
        this.money = money;
    }
    public Long getTypeId() {
        return this.typeId;
    }
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    @Generated(hash = 638861748)
    private transient Long typeBean__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1261509980)
    public TypeBean getTypeBean() {
        Long __key = this.typeId;
        if (typeBean__resolvedKey == null || !typeBean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TypeBeanDao targetDao = daoSession.getTypeBeanDao();
            TypeBean typeBeanNew = targetDao.load(__key);
            synchronized (this) {
                typeBean = typeBeanNew;
                typeBean__resolvedKey = __key;
            }
        }
        return typeBean;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1987593041)
    public void setTypeBean(TypeBean typeBean) {
        synchronized (this) {
            this.typeBean = typeBean;
            typeId = typeBean == null ? null : typeBean.getId();
            typeBean__resolvedKey = typeId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1186420397)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBillBeanDao() : null;
    }
}
