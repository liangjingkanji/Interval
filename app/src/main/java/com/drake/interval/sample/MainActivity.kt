package com.drake.interval.sample

import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import com.drake.engine.base.EngineActivity
import com.drake.interval.Interval
import com.drake.interval.sample.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit


class MainActivity : EngineActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var interval: Interval // 轮询器

    override fun initView() {
        // interval = Interval(0, 1, TimeUnit.MILLISECONDS, 1000 * 60 * 2).life(this) // 自定义计数器个数的轮询器, 当[start]]比[end]值大, 且end不等于-1时, 即为倒计时

        // interval = Interval(0, 1, TimeUnit.SECONDS, 5).life(this) // 自定义计数器个数的轮询器, 当[start]]比[end]值大, 且end不等于-1时, 即为倒计时
        interval = Interval(20, 1, TimeUnit.MILLISECONDS, 0).life(this) // 自定义计数器个数的轮询器, 当[start]]比[end]值大, 且end不等于-1时, 即为倒计时


        // interval = Interval(10, 1, TimeUnit.SECONDS, 0).life(this) // 自定义计数器个数的轮询器, 当[start]]比[end]值大, 且end不等于-1时, 即为倒计时
        // interval = Interval(10, 1, TimeUnit.SECONDS, 30).life(this) // 自定义计数器个数的轮询器, 当[start]]比[end]值大, 且end不等于-1时, 即为倒计时
        // interval = Interval(1, TimeUnit.SECONDS) // 每秒回调一次, 不会自动结束
        interval.subscribe {
            binding.tvFragment.text = it.toString()
        }.finish {
            binding.tvFragment.text = "计时完成"
        }
        interval.start()

        // dev {
        //     function {
        //         interval.start()
        //     }
        // }

        val l = 10 * 1000L
        val timer = object : CountDownTimer(l, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // 每秒钟触发一次，更新 UI
                // val seconds = millisUntilFinished / 1000
                // val timeLeft = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60)
                // 在这里更新倒计时的 UI，例如显示在 TextView 上
                // binding.tvFragment2.text = ((l - millisUntilFinished) / 1000).toString()
                binding.tvFragment2.text = ((millisUntilFinished + 20) / 1000).toString()
            }

            override fun onFinish() {
                // 倒计时结束时触发的操作
                binding.tvFragment2.text = "计时完成"
                // 可以在这里执行相应的操作，例如触发某个事件或跳转到下一个界面等
            }
        }
        timer.start()

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