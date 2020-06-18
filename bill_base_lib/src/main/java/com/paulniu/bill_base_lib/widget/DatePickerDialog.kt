package com.paulniu.bill_base_lib.widget

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import androidx.annotation.StyleRes
import com.paulniu.bill_base_lib.R
import com.paulniu.bill_base_lib.listener.IDateSetListener
import java.lang.reflect.Field

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/15 5:22 PM
 * desc: 根据需要显示不同的格式  只显示年份，只显示月份，显示年月日，显示星期
 */
class DatePickerDialog(
    context: Context,
    @StyleRes theme: Int,
    listener: IDateSetListener,
    year: Int,
    month: Int,
    day: Int
) : AlertDialog(context, theme), DatePicker.OnDateChangedListener, DialogInterface.OnClickListener {

    companion object {
        private const val START_YEAR = "start_year"
        private const val START_MONTH = "start_month"
        private const val START_DAY = "start_day"
    }

    private lateinit var mDatePickerStart: DatePicker

    private var mListener: IDateSetListener = listener

    init {
        val themeContext = getContext()
        setButton(DialogInterface.BUTTON_POSITIVE, "确定", this)
        setButton(DialogInterface.BUTTON_NEGATIVE, "取消", this)
        setIcon(0)

        val inflater: LayoutInflater =
            themeContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.view_date_picker_dialog, null)
        mDatePickerStart = view.findViewById(R.id.datePickerStart)
        mDatePickerStart.init(year, month, day, this)

        hideDay(mDatePickerStart)
    }

    override fun onSaveInstanceState(): Bundle {
        val state = super.onSaveInstanceState()
        state.putInt(START_YEAR, mDatePickerStart.getYear())
        state.putInt(START_MONTH, mDatePickerStart.getMonth())
        state.putInt(START_DAY, mDatePickerStart.getDayOfMonth())
        return state
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val start_year = savedInstanceState.getInt(START_YEAR)
        val start_month = savedInstanceState.getInt(START_MONTH)
        val start_day = savedInstanceState.getInt(START_DAY)
        mDatePickerStart.init(start_year, start_month, start_day, this)
    }

    override fun onDateChanged(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        if (view?.id == R.id.datePickerStart)
            mDatePickerStart.init(year, monthOfYear, dayOfMonth, this)
    }

    public fun getDatePickerStart(): DatePicker {
        return mDatePickerStart;
    }


    public fun updateStartDate(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        mDatePickerStart.updateDate(year, monthOfYear, dayOfMonth);
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        if (which == DialogInterface.BUTTON_POSITIVE) tryNotifyDateSet()
    }

    private fun tryNotifyDateSet() {
        if (mListener != null) {
            mDatePickerStart.clearFocus();
            mListener.onDateSet(
                mDatePickerStart, mDatePickerStart.year, mDatePickerStart.month,
                mDatePickerStart.dayOfMonth
            );
        }
    }

    private fun hideDay(mDatePicker: DatePicker) {
        try {
            /* 处理android5.0以上的特殊情况 */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android");
                if (daySpinnerId != 0) {
                    val daySpinner = mDatePicker.findViewById<View>(daySpinnerId)
                    if (daySpinner != null) {
                        daySpinner.visibility = View.GONE
                    }
                }
            } else {
                val datePickerfFields = mDatePicker::class.java.declaredFields
                for (datePickerField: Field in datePickerfFields) {
                    if ("mDaySpinner" == datePickerField.name || ("mDayPicker") == datePickerField.name
                    ) {
                        datePickerField.isAccessible = true
                        var dayPicker: Any? = null
                        try {
                            dayPicker = datePickerField.get(mDatePicker)
                        } catch (e: IllegalAccessException) {
                            e.printStackTrace()
                        } catch (e: IllegalArgumentException) {
                            e.printStackTrace()
                        }
                        (dayPicker as View).visibility = View.GONE
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}