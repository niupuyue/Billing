package com.paulniu.billing.database.source

import com.paulniu.bill_base_lib.util.TimeUtil
import com.paulniu.bill_data_lib.bean.BillBean
import com.paulniu.bill_data_lib.dao.BillBeanDao
import com.paulniu.bill_data_lib.dao.TypeBeanDao.Properties.Id
import com.paulniu.billing.database.AppDataBase
import org.greenrobot.greendao.query.WhereCondition

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 6:39 PM
 * desc: 账单数据库操作实现类
 */
object BillSource {

    /**
     * 插入或更新账单信息
     */
    fun addOrUpdateBill(bill: BillBean):Long {
        return AppDataBase.daoSession.insertOrReplace(bill)
    }

    /**
     * 删除账单信息
     */
    fun deleteBill(bill: BillBean) {
        AppDataBase.daoSession.delete(bill)
    }

    /**
     * 查询所有的账单信息
     */
    fun queryAllBill(): List<BillBean> {
        return AppDataBase.daoSession.queryBuilder(BillBean::class.java).build().list()
    }

    /**
     * 根据id查询账单信息
     */
    fun queryByBillId(id: Long): BillBean {
        return AppDataBase.daoSession.queryBuilder(BillBean::class.java)
            .where(BillBeanDao.Properties.Time.eq(id)) as BillBean
    }

    /**
     * 根据类型查询账单信息 默认按照时间降序排列
     * @param typeId 账单类型
     * @param page 第几页
     * @param count 每页展示多少条数据
     */
    fun queryByBillType(typeId: Long, page: Int = 1, count: Int = 10): List<BillBean> {
        return AppDataBase.daoSession.queryBuilder(BillBean::class.java)
            .where(BillBeanDao.Properties.TypeId.eq(typeId)).offset(page * count).limit(count)
            .orderDesc(BillBeanDao.Properties.Time).build().list()
    }

    /**
     * 查询某一天的账单
     * @param time 需要查询的时间，如果不传，默认查询当前的
     */
    fun queryByDayBills(
        time: Long = System.currentTimeMillis(),
        page: Int = 1,
        count: Int = 10
    ): List<BillBean> {
        val dataArray = TimeUtil.getDayStartAndEnd(time)
        return AppDataBase.daoSession.queryBuilder(BillBean::class.java)
            .where(
                BillBeanDao.Properties.Time.ge(dataArray[0]),
                BillBeanDao.Properties.Time.le(dataArray[1])
            ).offset(page * count).limit(count).orderDesc(BillBeanDao.Properties.Time).build()
            .list()
    }

    /**
     * 查询某一月的账单
     */
    fun queryByMonthBills(
        time: Long = System.currentTimeMillis(),
        page: Int = 1,
        count: Int = 10
    ): List<BillBean> {
        val dateArray = TimeUtil.getMonthStartAndEnd(time)
        return AppDataBase.daoSession.queryBuilder(BillBean::class.java)
            .where(
                BillBeanDao.Properties.Time.ge(dateArray[0]),
                BillBeanDao.Properties.Time.le(dateArray[1])
            ).offset(page * count).limit(count)
            .orderDesc(BillBeanDao.Properties.Time).build().list()
    }

    /**
     * 根据原始SQL语句查询
     * 有些语句在GreenDao中实现比较复杂，使用默认SQL语句比较方便
     */
    fun queryBySql(sql: String): List<BillBean> {
        val conditions = WhereCondition.StringCondition(sql)
        return AppDataBase.daoSession.queryBuilder(BillBean::class.java)
            .where(conditions).build().list()
    }

}