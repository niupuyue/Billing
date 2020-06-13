package com.paulniu.billing.business

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
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
class AnalysisActivity : AppCompatActivity(), OnChartValueSelectedListener, IAnalysisItemListener {

    private val pieDatas = ArrayList<PieEntry>()
    private val billDatas = ArrayList<BillInfo>()
    private val typesData = ArrayList<TypeInfo>()
    private val sortMoneyDatas = ArrayList<Float>()

    // 在规定时间内的金额总数
    private var totalMoney: Float? = null

    // 开始的时间
    private var startTime: Long = TimeUtil.getMonthStartAndEnd()[0]

    // 结束的时间
    private var endTime: Long = TimeUtil.getMonthStartAndEnd()[1]

    private var mAnalysisAdapter: AnalysisAdapter? = null

    private var mAnalysisListData = ArrayList<TypeInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis)

        initView()
        initData()
        initListener()
    }

    private fun initView() {
        setSupportActionBar(analysis_activity_toolbar)
        // 设置toolbar的自定义样式
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setBackgroundDrawable(null)
        val analysis_toolbar_layout = layoutInflater.inflate(R.layout.view_analysis_toolbar, null)
        val analysis_toolbar_params = ActionBar.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        supportActionBar?.setCustomView(analysis_toolbar_layout, analysis_toolbar_params)
        analysis_toolbar_layout.analysis_toolbar_back_iv.setOnClickListener {
            onBackPressed()
        }

        // 使用百分比数据
        analysis_activity_piechart.setUsePercentValues(true)
        // 是否启用详细描述
        analysis_activity_piechart.description.isEnabled = false
        // 设置上下左右的偏移量
//        analysis_activity_piechart.setExtraOffsets(5f,5f,10f,10f)
        // 设置摩擦系数
        analysis_activity_piechart.dragDecelerationFrictionCoef = 0.95f
        // 设置饼状图上文字的样式
//        analysis_activity_piechart.setCenterTextTypeface()
        // 设置饼状图中心圆样式可见
        analysis_activity_piechart.isDrawHoleEnabled = true
        // 设置中心圆背景颜色为白色
        analysis_activity_piechart.setHoleColor(Color.WHITE)
        // 设置分割线颜色为白色
        analysis_activity_piechart.setTransparentCircleColor(Color.WHITE)
        analysis_activity_piechart.setTransparentCircleAlpha(110)
        // 设置中心圆的半径
        analysis_activity_piechart.holeRadius = 30f
        // 设置外部半径
        analysis_activity_piechart.transparentCircleRadius = 51f
        // 设置中心文字可见
        analysis_activity_piechart.setDrawCenterText(true)
        // 设置旋转角度
        analysis_activity_piechart.rotationAngle = 0f
        // 设置是否可以进行旋转操作
        analysis_activity_piechart.isRotationEnabled = false
        // 设置高亮的图标是否可以点击
        analysis_activity_piechart.isHighlightPerTapEnabled = true
        // 添加监听事件
        analysis_activity_piechart.setOnChartValueSelectedListener(this)
        // 设置Y轴动画
        analysis_activity_piechart.animateY(1400, Easing.EaseInOutQuad)

        val l: Legend = analysis_activity_piechart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = false
    }

    /**
     * 初始化数据，默认显示当前月份的统计数据
     */
    private fun initData() {
        mAnalysisListData.clear()
        billDatas.clear()
        billDatas.addAll(BillSource.queryBillByMonth())
        // 获取当前月份的总金额
        totalMoney = BillCalculateSource.sumMoneyByTimes(startTime, endTime)
        // 获取所有的类型集合
        typesData.clear()
        typesData.addAll(TypeSource.queryTypes()!!)
        // 计算每个类型所对应的金额
        typesData.forEachIndexed { _, typeInfo ->
            sortMoneyDatas.add(
                BillCalculateSource.sumMoneyByTimesType(
                    startTime,
                    endTime,
                    typeInfo.id
                ) ?: 0.0f
            )
        }
        // 初始化pieData
        if (typesData.size == sortMoneyDatas.size) {
            typesData.forEachIndexed { index, typeInfo ->
                if (sortMoneyDatas[index] > 0) {
                    pieDatas.add(PieEntry(sortMoneyDatas[index], typeInfo.title + " ${sortMoneyDatas[index]}"))
                    typesData[index].totalMoney = sortMoneyDatas[index]
                    typesData[index].precent = sortMoneyDatas[index] / totalMoney!!
                    mAnalysisListData.add(typesData[index])
                }
            }
            val pieDataSet = PieDataSet(pieDatas, "账单统计")
            pieDataSet.sliceSpace = 3f
            pieDataSet.selectionShift = 5f
            pieDataSet.colors = setPieDataClolrs()

            pieDataSet.valueLinePart1OffsetPercentage = 80f
            pieDataSet.valueLinePart1Length = 0.2f
            pieDataSet.valueLinePart2Length = 0.4f
            pieDataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

            // 设置横杠指示不显示
            pieDataSet.setDrawValues(false)

            val data = PieData(pieDataSet)
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(11f)
            data.setValueTextColor(Color.BLACK)
            analysis_activity_piechart.data = data
            analysis_activity_piechart.highlightValues(null)
            analysis_activity_piechart.invalidate()

            analysis_activity_recyclerview.layoutManager = LinearLayoutManager(this)
            mAnalysisAdapter = AnalysisAdapter(this, mAnalysisListData, this)
            analysis_activity_recyclerview.adapter = mAnalysisAdapter
        } else {
            Toast.makeText(this, "数据错误，暂时无法执行统计操作", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListener() {

    }

    /**
     * 设置颜色集合
     */
    private fun setPieDataClolrs(): List<Int> {

        // add a lot of colors
        val colors = java.util.ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        return colors
    }

    override fun onNothingSelected() {
        // 没有选中
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        //选中了某一个对象
    }

    override fun onTypeItemClick(type: TypeInfo) {
        // 选择具体的某一个类型，点击进入类型统计页面

    }

}