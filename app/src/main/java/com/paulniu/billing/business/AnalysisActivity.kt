package com.paulniu.billing.business

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.paulniu.bill_base_lib.constant.TimeConstant
import com.paulniu.bill_base_lib.util.DensityUtil
import com.paulniu.bill_base_lib.util.ResourceUtil
import com.paulniu.bill_base_lib.util.TimeUtil
import com.paulniu.bill_data_lib.bean.BillInfo
import com.paulniu.bill_data_lib.bean.TypeInfo
import com.paulniu.bill_data_lib.source.BillCalculateSource
import com.paulniu.bill_data_lib.source.TypeSource
import com.paulniu.billing.R
import com.paulniu.billing.adapter.AnalysisAdapter
import com.paulniu.billing.database.BillSource
import com.paulniu.billing.listener.IAnalysisItemListener
import kotlinx.android.synthetic.main.activity_analysis.*
import kotlinx.android.synthetic.main.view_analysis_toolbar.view.*

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:2020/6/7 6:11 PM
 * desc: 报表分析页面
 */
class AnalysisActivity : AppCompatActivity(), IAnalysisItemListener {

    private val billDatas = ArrayList<BillInfo>()
    private val sortMoneyDatas = ArrayList<Float>()
    private val mTypeInfos = ArrayList<TypeInfo>()
    private val mBarChartList = ArrayList<BarEntry>()

    // 在规定时间内的金额总数
    private var totalMoney: Float? = null

    private var mSelectedCurrentTime = System.currentTimeMillis()

    // 开始的时间
    private var startTime: Long = TimeUtil.getMonthStartAndEnd(mSelectedCurrentTime)[0]

    // 结束的时间
    private var endTime: Long = TimeUtil.getMonthStartAndEnd(mSelectedCurrentTime)[1]

    private var mAnalysisAdapter: AnalysisAdapter? = null

    private var mAnalysisListData = ArrayList<TypeInfo>()

    private var mYear = TimeUtil.getYearMonthDayByTime()[0]
    private var mMonth = TimeUtil.getYearMonthDayByTime()[1]
    private var mDay = TimeUtil.getYearMonthDayByTime()[2]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis)
        initView()
        initData()
        initBarChart()
        formatBarData()
    }

    @SuppressLint("InflateParams")
    private fun initView() {
        setSupportActionBar(analysis_activity_toolbar)
        // 设置toolbar的自定义样式
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setBackgroundDrawable(null)
        val analysisToolbarLayout = layoutInflater.inflate(R.layout.view_analysis_toolbar, null)
        val analysisToolbarParams = ActionBar.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        supportActionBar?.setCustomView(analysisToolbarLayout, analysisToolbarParams)
        // 点击返回按钮
        analysisToolbarLayout.analysis_toolbar_back_iv.setOnClickListener {
            onBackPressed()
        }
        // 点击切换月份
        analysisToolbarLayout.analysis_toolbar_month.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                R.style.AppThemeDatePicker,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    mYear = year
                    mMonth = month
                    mDay = dayOfMonth
                    mSelectedCurrentTime = TimeUtil.getCurrentTimeByDay(year, month, dayOfMonth)
                    // 重新计算开始时间和结束时间
                    startTime = TimeUtil.getMonthStartAndEnd(mSelectedCurrentTime)[0]
                    endTime = TimeUtil.getMonthStartAndEnd(mSelectedCurrentTime)[1]
                    // 重新加载数据
                    initData()
                    initBarChart()
                    formatBarData()
                    // 重新显示右上角月份
                    analysisToolbarLayout.analysis_toolbar_month.text = formatMonthText()
                },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }

        // 如果当前的时间在当前月份里，则显示本月，其他时候显示年份和月份
        analysisToolbarLayout.analysis_toolbar_month.text = formatMonthText()
    }

    /**
     * 初始化数据，默认显示当前月份的统计数据
     */
    private fun initData() {
        sortMoneyDatas.clear()
        mAnalysisListData.clear()
        billDatas.clear()
        billDatas.addAll(BillSource.queryBillByMonth(mSelectedCurrentTime))
        if (billDatas.isEmpty()) {
            analysis_activity_empty_rl.visibility = View.VISIBLE
            analysis_activity_container_ll.visibility = View.GONE
        } else {
            analysis_activity_empty_rl.visibility = View.GONE
            analysis_activity_container_ll.visibility = View.VISIBLE
            // 获取当前月份的总金额
            totalMoney = BillCalculateSource.sumMoneyByTimes(startTime, endTime)
            // 获取所有的类型集合
            mTypeInfos.clear()
            mTypeInfos.addAll(TypeSource.queryTypes())
            // 计算每个类型所对应的金额
            mTypeInfos.forEach { typeInfo ->
                val sortBean =
                    BillCalculateSource.sumMoneyByTimesType(startTime, endTime, typeInfo.id)
                sortMoneyDatas.add(sortBean ?: 0f)
            }
            if (mTypeInfos.size == sortMoneyDatas.size) {
                // 如果类型的数量和计算出来的类型数量相等，则开始封装barChart数据
                mTypeInfos.forEachIndexed { index, value ->
                    val typeTotalMoney = sortMoneyDatas[index]
                    value.totalMoney = typeTotalMoney
                    value.precent = typeTotalMoney / (totalMoney ?: 1f)
                    // 填充recyclerview的数据
                    if (typeTotalMoney != 0f) {
                        mAnalysisListData.add(value)
                    }
                }
                // 循环完成之后，typesData就是所有类型和对应类型的钱数
                analysis_activity_empty_rl.visibility = View.GONE
                analysis_activity_container_ll.visibility = View.VISIBLE
            } else {
                analysis_activity_empty_rl.visibility = View.VISIBLE
                analysis_activity_container_ll.visibility = View.GONE
            }
        }
    }

    private fun initBarChart() {
        analysis_activity_barchart.clear()
        analysis_activity_barchart.setTouchEnabled(false)
        analysis_activity_barchart.isDragEnabled = false
        analysis_activity_barchart.isScaleXEnabled = false
        analysis_activity_barchart.description.isEnabled = false

        // 设置legend
        val legend = analysis_activity_barchart.legend
        legend.textColor = ResourceUtil.getColor(R.color.color_A2A6B8)
        // 设置排列方法
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        // 设置大小
        legend.formSize = DensityUtil.dp2px(3f).toFloat()

        // 不需要显示标题
        analysis_activity_barchart.description.isEnabled = false

        analysis_activity_barchart.extraTopOffset = 15f

        analysis_activity_barchart.setFitBars(true)

        // 设置x轴
        val x = analysis_activity_barchart.xAxis
        x.position = XAxis.XAxisPosition.BOTTOM
        // 绘制靠近x轴第一条线的颜色
        x.axisLineColor = ResourceUtil.getColor(R.color.color_D8D8D8)
        // 设置x轴字体大小和颜色
        x.textColor = ResourceUtil.getColor(R.color.color_A2A6B8)
        // 设置x轴文字居中显示
//        x.setCenterAxisLabels(true)
        x.setDrawGridLines(false)
        x.granularity = 1f
        // 设置x轴中数据格式化
        x.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val curIndex = value.toInt()
                return if (curIndex >= 0 && curIndex < mAnalysisListData.size) {
                    mAnalysisListData[(value.toInt())].title ?: "未知类型"
                } else {
                    ""
                }
            }
        }

        // 设置y轴
        val yl = analysis_activity_barchart.axisLeft
        val yr = analysis_activity_barchart.axisRight
        yr.isEnabled = false
        // 是否绘制最上面的标签
        yl.setDrawTopYLabelEntry(true)
        // 设置左侧y轴的最大值和最小值
        yl.axisMinimum = 0f
        yl.axisMaximum = ((calculMaxData() / 100).toInt() + 1) * 100f
        // 设置左侧y轴标签设置的位置
        yl.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        // 设置是否显示左侧y轴最近的一条线
        yl.setDrawAxisLine(false)
        // 设置是否绘制横向网格
        yl.setDrawGridLines(true)
        // 设置绘制横向网格的颜色
        yl.gridColor = ResourceUtil.getColor(R.color.color_F2F2F2)
        // 设置绘制横向网格的宽度
        yl.gridLineWidth = DensityUtil.dp2px(0.5f).toFloat()
        yl.granularity = ((calculMaxData() / 5).toInt() / 100) * 100f
    }

    private fun formatBarData() {
        mBarChartList.clear()
        mAnalysisListData.forEachIndexed { index, typeInfo ->
            val money = typeInfo.totalMoney
            val barEntry = BarEntry(index.toFloat(), money ?: 0f)
            mBarChartList.add(barEntry)
        }
        val barDataSet: BarDataSet?
        if (analysis_activity_barchart.data != null && analysis_activity_barchart.data.dataSetCount > 0) {
            barDataSet = analysis_activity_barchart.data.getDataSetByIndex(0) as BarDataSet?
            barDataSet?.values = mBarChartList
            analysis_activity_barchart.data.notifyDataChanged()
            analysis_activity_barchart.notifyDataSetChanged()
        } else {
            barDataSet = BarDataSet(mBarChartList, "月份统计")
            barDataSet.setDrawIcons(false)

            val startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
            val startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light)
            val startColor3 = ContextCompat.getColor(this, android.R.color.holo_purple)
            val startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light)
            val startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light)

            val colorsLists = ArrayList<Int>()
            colorsLists.add(startColor1)
            colorsLists.add(startColor2)
            colorsLists.add(startColor3)
            colorsLists.add(startColor4)
            colorsLists.add(startColor5)

            barDataSet.colors = colorsLists
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(barDataSet)
            val barData = BarData(dataSets)
            barData.setValueTextSize(10f)
            barData.barWidth = 0.9f
            analysis_activity_barchart.data = barData
        }

        // 显示recyclerview
        analysis_activity_recyclerview.layoutManager = LinearLayoutManager(this)
        mAnalysisAdapter = AnalysisAdapter(this, mAnalysisListData, this)
        analysis_activity_recyclerview.adapter = mAnalysisAdapter

    }

    /**
     * 计算类型中的最大值
     */
    private fun calculMaxData(): Float {
        var maxMoney = 0f
        mAnalysisListData.forEach { typeInfo ->
            if (maxMoney < (typeInfo.totalMoney ?: 0f)) {
                maxMoney = typeInfo.totalMoney ?: 0f
            }
        }
        return maxMoney
    }

    private fun formatMonthText(): String? {
        return if (TimeUtil.formatTimeToCurrentMonth(mSelectedCurrentTime)) {
            getString(R.string.analysis_activity_top_current_monty)
        } else {
            TimeUtil.formatTimeToString(
                mSelectedCurrentTime,
                TimeConstant.TYPE_YEAR_MONTH_XIEGANG
            )
        }
    }

    override fun onTypeItemClick(type: TypeInfo?) {

    }

}