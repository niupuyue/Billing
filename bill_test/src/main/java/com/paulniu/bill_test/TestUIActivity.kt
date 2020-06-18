package com.paulniu.bill_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import com.paulniu.bill_base_lib.listener.IDateSetListener
import com.paulniu.bill_base_lib.widget.DatePickerDialog
import com.paulniu.bill_data_lib.bean.BaseType
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.bill_data_lib.source.BaseTypeSource
import com.paulniu.bill_data_lib.source.TypeSource
import kotlinx.android.synthetic.main.activity_test_u_i.*
import java.util.*

class TestUIActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_u_i)


        btn01.setOnClickListener {
            BaseTypeSource.addOrUpdate(BaseType(0, "收入"))
            BaseTypeSource.addOrUpdate(BaseType(1, "支出"))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.ic_launcher, "餐饮", 1, BaseType(1, "支出")))
            TypeSource.addOrUpdate(TypeInfo(0, R.mipmap.ic_launcher, "书籍", 1, BaseType(1, "支出")))

        }

        btn02.setOnClickListener {
            val baseTypes = BaseTypeSource.queryBaseTypes()
            val types = TypeSource.queryTypeInfosByBaseType(1)
            Log.e("NPL", "测试")
        }

        btn03.setOnClickListener {
            val c = Calendar.getInstance()

            val dialog = DatePickerDialog(this, 0, object : IDateSetListener {
                override fun onDateSet(
                    startDatePicker: DatePicker,
                    startYear: Int,
                    startMonth: Int,
                    startDay: Int
                ) {
                    val textString = String.format(
                        "选择年月：%d-%d\n", startYear,
                        startMonth + 1
                    );
                    Log.e("NPL", "msg=${textString}")
                }
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))
            dialog.show()
        }

    }

}
