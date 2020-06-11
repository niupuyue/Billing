package com.paulniu.billing.business

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulniu.bill_base_lib.constant.TimeConstant
import com.paulniu.bill_base_lib.event.AddBillSuccessEvent
import com.paulniu.bill_base_lib.event.ChangeUserNameEvent
import com.paulniu.bill_base_lib.util.SPUtil
import com.paulniu.bill_base_lib.util.TimeUtil
import com.paulniu.bill_data_lib.bean.BillInfo
import com.paulniu.bill_data_lib.source.BillCalculateSource
import com.paulniu.billing.Constant
import com.paulniu.billing.R
import com.paulniu.billing.adapter.MainBillListAdapter
import com.paulniu.billing.database.BillSource
import com.paulniu.billing.listener.IMainBillListDeleteListener
import com.paulniu.billing.listener.IMainBillListListener
import com.paulniu.billing.widget.dialog.MainBillListDeleteDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main_activity_bar_main.*
import kotlinx.android.synthetic.main.view_main_mytoolbar.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity(), IMainBillListListener {

    private var mBillList = ArrayList<BillInfo>()

    private val eventBus by lazy {
        EventBus.getDefault()
    }

    private var mBillListAdapter: MainBillListAdapter? = null

    private var mSelectedTime = System.currentTimeMillis()
    private var mYear = TimeUtil.getYear(mSelectedTime)
    private var mMonth = TimeUtil.getMonth(mSelectedTime)
    private var mDay = TimeUtil.getDay(mSelectedTime)

    private var mNavHeaderContainer: LinearLayout? = null
    private var mNavHeaderAvator: ImageView? = null
    private var mNavHeaderName: TextView? = null
    private var mNavHeaderMotto: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        eventBus.register(this)
        initView()
        initListener()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        eventBus.removeStickyEvent(AddBillSuccessEvent::class.java)
        eventBus.unregister(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 引入右上角菜单
        menuInflater.inflate(R.menu.activity_main_top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 右上角菜单item点击事件
        when (item.itemId) {
            R.id.action_settings -> {
                // 跳转到搜索页面
                startActivity(Intent(this, SearchActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        // 需要处理侧滑窗口
        super.onBackPressed()
    }

    override fun onClick(position: Int) {
        // 点击其中的一个，跳转到可以编辑和修改页面
    }

    override fun onLongClick(position: Int) {
        // 长恩显示删除弹窗
        val deleteDialog =
            MainBillListDeleteDialog(
                this,
                object : IMainBillListDeleteListener {
                    override fun onDelete() {
                        BillSource.deleteBillById(mBillList[position].id)
                        // 删除成功
                        Toast.makeText(this@MainActivity, "删除成功！", Toast.LENGTH_SHORT).show()
                        mBillList.removeAt(position)
                        mBillListAdapter?.notifyDataSetChanged()
                    }
                })
        if (!deleteDialog.isShowing) {
            deleteDialog.show()
        }
    }

    private fun initView() {
        setSupportActionBar(main_activity_toolbar)
        // 设置侧滑窗口和toolbar的联动
        val toggel = ActionBarDrawerToggle(
            this,
            main_activity_drawer_layout,
            main_activity_toolbar,
            R.string.main_activity_nav_open,
            R.string.main_activity_nav_close
        )
        main_activity_drawer_layout.addDrawerListener(toggel)
        toggel.syncState()
        // 设置toolbar的自定义样式
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setBackgroundDrawable(null)
        val main_toolbar_layout = layoutInflater.inflate(R.layout.view_main_mytoolbar, null)
        val main_toolbar_params = ActionBar.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        supportActionBar?.setCustomView(main_toolbar_layout, main_toolbar_params)
        main_toolbar_layout.main_activity_toolbar_date_tv.setOnClickListener {
            // 重新选择月份
            val datePickerDialog =
                DatePickerDialog(
                    this, R.style.AppThemeDatePicker,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        mYear = year
                        mMonth = month
                        mDay = dayOfMonth
                        mSelectedTime = TimeUtil.getCurrentTimeByDay(year, month, dayOfMonth)
                        // 重新加载数据
                        initData()
                        main_toolbar_layout.main_activity_toolbar_date_tv.text =
                            TimeUtil.formatTimeToString(
                                mSelectedTime,
                                TimeConstant.TYPE_YEAR_MONTH_XIEGANG
                            )
                    }, mYear, mMonth, mDay
                )
            datePickerDialog.show()
        }
        main_toolbar_layout.main_activity_toolbar_date_tv.text =
            TimeUtil.formatTimeToString(mSelectedTime, TimeConstant.TYPE_YEAR_MONTH_XIEGANG)

        val mNavHeader = main_activity_nav_view.getHeaderView(0)
        mNavHeaderContainer = mNavHeader.findViewById(R.id.main_activity_nav_header_container_ll)
        mNavHeaderAvator = mNavHeader.findViewById(R.id.main_activity_nav_header_avator_iv)
        mNavHeaderName = mNavHeader.findViewById(R.id.main_activity_nav_header_name_tv)
        mNavHeaderMotto = mNavHeader.findViewById(R.id.main_activity_nav_header_motto_tv)
    }

    private fun initListener() {
        // float按钮点击事件
        main_activity_fab.setOnClickListener {
            // 跳转到添加账单的页面
            startActivity(Intent(this, AddBillingActivity::class.java))
        }
        // 设置侧滑菜单item点击事件
        main_activity_nav_view.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // 跳转到主页面，此处可能需要刷新数据
                }
                R.id.nav_account -> {
                    // 跳转到个人页面
                    startActivity(Intent(this@MainActivity, PersonActivity::class.java))
                }
                R.id.nav_report -> {
                    // 跳转到报表页面
                    startActivity(Intent(this@MainActivity, AnalysisActivity::class.java))
                }
                R.id.nav_setting -> {
                    // 跳转到设置页面
                    startActivity(Intent(this@MainActivity, SettingActivity::class.java))
                }
            }
            // 点击侧滑菜单item之后关闭侧滑菜单到初始位置
            main_activity_drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

        mNavHeaderContainer?.setOnClickListener {
            // 跳转到个人页面
            startActivity(Intent(this@MainActivity, PersonActivity::class.java))
        }
    }

    private fun initData() {
        // 从数据库中获取数据
        mBillList = BillSource.queryBillByMonth(mSelectedTime) as ArrayList<BillInfo>
        formatBillData()
        mBillListAdapter = MainBillListAdapter(this, mBillList, this)
        main_activity_recyclerview.adapter = mBillListAdapter
        main_activity_recyclerview.layoutManager = LinearLayoutManager(this)
        // 计算这个月一共花了多少钱
        main_activity_analysis_simple_card_balance_tv.text = BillCalculateSource.sumMoneyByTimes(
            TimeUtil.getMonthStartAndEnd(mSelectedTime)[0],
            TimeUtil.getMonthStartAndEnd(mSelectedTime)[1]
        ).toString()
        // 设置用户昵称和座右铭
        mNavHeaderName?.text = SPUtil.getInstance(Constant.SP_APP_BASE_FILENAME)
            ?.getString(Constant.SP_KEY_USER_BASE_NAME, "牛爱英")
        mNavHeaderMotto?.text = SPUtil.getInstance(Constant.SP_APP_BASE_FILENAME)
            ?.getString(Constant.SP_KEY_USER_MOTTO, "好懒啊，什么都没有写~")
        // 设置用户的头像

    }

    /**
     * 将账单信息根据日期进行格式化
     */
    private fun formatBillData() {
        if (mBillList.isEmpty()) {
            return
        }
        var insertArray = ArrayList<Int>()
        // 主要思路：判断当前日期和下一个日期是否是同一天，如果是同一天，则不添加，否则就添加
        var index = 0
        insertArray.add(index)
        while (index < mBillList.size) {
            val xBillInfo = mBillList[index]
            val xBillTimes = TimeUtil.getYearMonthDayByTime(xBillInfo.time)
            var indey = index + 1
            while (indey < mBillList.size) {
                val yBillInfo = mBillList[indey]
                val yBillTimes = TimeUtil.getYearMonthDayByTime(yBillInfo.time)
                if (xBillTimes[0] != yBillTimes[0] || xBillTimes[1] != yBillTimes[1] || xBillTimes[2] != yBillTimes[2]) {
                    insertArray.add(indey)
                    index = indey
                    break
                }
                indey++
                if (indey == mBillList.size) {
                    index = indey
                    break
                }
            }
            if (index == mBillList.size - 1) {
                break
            }
        }
        var position = 0
        while (position < insertArray.size) {
            val time = mBillList[position + insertArray[position]].time
            mBillList.add(
                position + insertArray[position],
                BillInfo(
                    -1, TimeUtil.formatTimeToString(time, TimeConstant.TYPE_YEAR_MONTH_DAY_XIEGANG),
                    BillCalculateSource.sumMoneyByTimes(
                        TimeUtil.getDayStartAndEnd(time)[0],
                        TimeUtil.getDayStartAndEnd(time)[1]
                    ), -1, null, time
                )
            )
            position++
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun addBillSuccess(event: AddBillSuccessEvent) {
        initData()
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun changeUserName(event: ChangeUserNameEvent) {
        // TODO 这种方式不行
        mNavHeaderName?.text = SPUtil.getInstance(Constant.SP_APP_BASE_FILENAME)
            ?.getString(Constant.SP_KEY_USER_BASE_NAME, "牛爱英")
        mNavHeaderMotto?.text = SPUtil.getInstance(Constant.SP_APP_BASE_FILENAME)
            ?.getString(Constant.SP_KEY_USER_MOTTO, "好懒啊，什么都没有写~")
    }

}
