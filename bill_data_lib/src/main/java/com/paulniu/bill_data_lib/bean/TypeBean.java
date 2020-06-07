package com.paulniu.bill_data_lib.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Transient;

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
    public int iconRes;
    public String title;
    public int baseType;

    @Transient
    public boolean isSelected;

    public int getBaseType() {
        return baseType;
    }

    public void setBaseType(int baseType) {
        this.baseType = baseType;
    }

    @Keep
    @Generated(hash = 709890700)
    public TypeBean(Long id, int iconRes, String title, int baseType) {
        this.id = id;
        this.iconRes = iconRes;
        this.title = title;
        this.baseType = baseType;
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

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

}
