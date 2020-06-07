package com.paulniu.billing.business

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.paulniu.bill_data_lib.bean.BillBean
import com.paulniu.billing.R
import com.paulniu.billing.database.source.BillSource
import com.paulniu.billing.widget.AddBillDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_activity_bar_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initListener()
        initData()
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

    private fun initData(){

    }


//    private val mAddBillDialog by lazy {
//        AddBillDialog(this, this)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        btn_add.setOnClickListener {
//            if (!mAddBillDialog.isShowing) {
//                mAddBillDialog.show()
//            }
//        }
//
//        btn_query.setOnClickListener {
//            val bills = BillSource.queryAllBill()
//            Toast.makeText(this, bills.toString(), Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun onAddBill(billBean: BillBean) {
//        // 添加账单
//        val count = BillSource.addOrUpdateBill(billBean)
//        if (count > 0) {
//            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show()
//        }
//    }
}
