package com.paulniu.billing.business

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulniu.billing.R

/**
 * @author:Niu Puyue
 * e-mail:niupuyue@aliyun.com
 * time:1/24/21 11:38 AM
 * desc: 数据分析详情页面
 */
class AnalysisDetailActivity : AppCompatActivity() {

    companion object {

        private const val BILL_TYPE = "BILL_TYPE"
        private const val BILL_TIME = "BILL_TIME"

        fun newInstance(context: Context?, type_id: Int?, time: Long?): Intent {
            val intent = Intent(context, AnalysisDetailActivity::class.java)
            intent.putExtra(BILL_TYPE, type_id)
            intent.putExtra(BILL_TIME, time)
            return intent
        }
    }

    private var mTypeId:Int? = null
    private var mBillTime:Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis_detail)
        initExtraData()
        initBaseView()
        initBaseListener()
    }

    private fun initExtraData(){
        mTypeId = intent.getIntExtra(BILL_TYPE,0)
        mBillTime = intent.getLongExtra(BILL_TIME,System.currentTimeMillis())
    }

    private fun initBaseView(){

    }

    private fun initBaseListener(){

    }

}