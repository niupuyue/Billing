package com.paulniu.bill_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.paulniu.bill_data_lib.bean.BaseType
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.bill_data_lib.source.BaseTypeSource
import com.paulniu.bill_data_lib.source.TypeSource
import kotlinx.android.synthetic.main.activity_test_u_i.*

class TestUIActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_u_i)


        btn01.setOnClickListener {
            BaseTypeSource.addOrUpdate(BaseType(0, "收入"))
            BaseTypeSource.addOrUpdate(BaseType(1, "支出"))
            TypeSource.addOrUpdate(TypeInfo(0,R.mipmap.ic_launcher, "餐饮", 1, BaseType(1, "支出")))
            TypeSource.addOrUpdate(TypeInfo(0,R.mipmap.ic_launcher, "书籍", 1, BaseType(1, "支出")))

        }

        btn02.setOnClickListener {
            val baseTypes = BaseTypeSource.queryBaseTypes()
            val types = TypeSource.queryTypeInfosByBaseType(1)
            Log.e("NPL", "测试")
        }

    }

}
