package com.paulniu.billing.business

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulniu.bill_base_lib.constant.TimeConstant
import com.paulniu.bill_base_lib.event.AddBillSuccessEvent
import com.paulniu.bill_base_lib.util.TimeUtil
import com.paulniu.bill_data_lib.bean.BillInfo
import com.paulniu.billing.R
import com.paulniu.billing.adapter.MainBillListAdapter
import com.paulniu.billing.database.BillSource
import com.paulniu.billing.listener.IMainBillListListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main_activity_bar_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity(), IMainBillListListener {

    private var mBillList = ArrayList<BillInfo>()

    private val eventBus by lazy {
        EventBus.getDefault()
    }

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
        supportActionBar?.setDisplayShowCustomEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(false);
        supportActionBar?.setBackgroundDrawable(null);
        val main_toolbar_layout = layoutInflater.inflate(R.layout.view_main_mytoolbar, null)
        val main_toolbar_params = ActionBar.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        supportActionBar?.setCustomView(main_toolbar_layout, main_toolbar_params)
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
    }

    private fun initData() {
        // 从数据库中获取数据
        mBillList = BillSource.queryBillByMonth() as ArrayList<BillInfo>
        formatBillData()
        main_activity_recyclerview.adapter = MainBillListAdapter(this, mBillList, this)
        main_activity_recyclerview.layoutManager = LinearLayoutManager(this)
    }

    private fun formatAnalysisCard() {
        main_activity_analysis_simple_card.visibility = View.VISIBLE
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
                if (indey == mBillList.size){
                    index = indey
                    break
                }
            }
            if (index ==mBillList.size - 1){
                break
            }
        }
        var position = 0
        while (position < insertArray.size){
            mBillList.add(position+insertArray[position],
                BillInfo(-1,TimeUtil.formatTimeToString(mBillList[position+insertArray[position]].time,TimeConstant.TYPE_YEAR_MONTH_DAY_XIEGANG),
                    0f,-1,null,mBillList[position+insertArray[position]].time))
            position++
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun addBillSuccess(event: AddBillSuccessEvent) {
        if (null != event) {
            initData()
        }
    }

}
