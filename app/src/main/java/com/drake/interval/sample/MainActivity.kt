package com.drake.interval.sample

import android.view.Menu
import android.view.MenuItem
import com.drake.engine.base.EngineActivity
import com.drake.interval.Interval
import com.drake.interval.sample.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : EngineActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var interval: Interval // 轮询器

    override fun initView() {
        interval = Interval(0, 1, TimeUnit.SECONDS, 20).life(this) // 自定义计数器个数的轮询器, 当[start]]比[end]值大, 且end不等于-1时, 即为倒计时
        // interval = Interval(1, TimeUnit.SECONDS) // 每秒回调一次, 不会自动结束
        interval.subscribe {
            binding.tvFragment.text = it.toString()
        }.finish {
            binding.tvFragment.text = "计时完成"
        }.start()
    }

    override fun initData() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_interval, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.start -> interval.start()
            R.id.pause -> interval.pause()
            R.id.resume -> interval.resume()
            R.id.reset -> interval.reset()
            R.id.switch_interval -> interval.switch()
            R.id.stop -> interval.stop()
            R.id.cancel -> interval.cancel()
        }
        return super.onOptionsItemSelected(item)
    }
}