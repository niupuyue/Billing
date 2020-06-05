package com.paulniu.bill_data_lib.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/5/31 8:07 PM
 * desc:
 */
@Entity
public class TypeBean {
    @Id(autoincrement = true)
    public Long id;
    public String title;
    @Generated(hash = 709890700)
    public TypeBean(Long id, String title) {
        this.id = id;
        this.title = title;
    }
    @Generated(hash = 119682038)
    public TypeBean() {
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
}
