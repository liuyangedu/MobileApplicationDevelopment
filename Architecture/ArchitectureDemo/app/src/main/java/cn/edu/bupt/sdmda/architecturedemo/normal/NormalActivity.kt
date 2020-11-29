package cn.edu.bupt.sdmda.architecturedemo.normal

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import cn.edu.bupt.sdmda.architecturedemo.R
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import kotlin.random.Random

class NormalActivity : AppCompatActivity() {

    private val interval: Long = 1000

    private var count: Int? = null
    private var answer: Int? = null
    private var guess: Int? = null
    private var startTime: Long? = null

    //    这里不能用result做变量名
//    如果使用result作为变量名，Kotlin会自动为它增加setter，也就是setResult
//    该函数和Activity中的setResult函数冲突
//    Kotlin中public的常量会自动添加getter
//    Kotlin中public的变量会自动添加getter和setter
    @StringRes
    private var rezult: Int = R.string.unknown

    private val kCOUNT = "count"
    private val kANSWER = "answer"
    private val kGUESS = "guess"
    private val kREZULT = "result"
    private val kSTART = "start"

    private var timer: Timer? = null
    var task: TimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initView()
        reset()
//        ?.表示只有当变量非空才执行
//        T.let表示以当前T的实例为it，执行代码块中的代码
        savedInstanceState?.let {
            count = it.getInt(kCOUNT)
            answer = it.getInt(kANSWER)
            guess = it.getInt(kGUESS)
            rezult = it.getInt(kREZULT)
            startTime = it.getLong(kSTART)
            setStatus()
        }

    }

    //    用于保存数据，保存的数据会在onCreate时尝试读取
    override fun onSaveInstanceState(outState: Bundle) {
        count?.let { outState.putInt(kCOUNT, it) }
        answer?.let { outState.putInt(kANSWER, it) }
        guess?.let { outState.putInt(kGUESS, it) }
        startTime?.let { outState.putLong(kSTART, it) }
        outState.putInt(kREZULT, rezult)
        super.onSaveInstanceState(outState)
    }


    private fun initView() {
        tv_label_guessed.isLongClickable = true
        btn_reset.setOnClickListener {
            reset()
            setStatus()
        }
        btn_guess.setOnClickListener {
            doGuess()
            setStatus()
        }
//        注意 OnLongClickListener 接口中的唯一的函数原型为：
//        boolean onLongClick(View v);
//        所以可以使用Lambda表达式
//        Lambda表达式如果有返回值，最后一行为返回值
        tv_label_guessed.setOnLongClickListener {
            if (windowManager.defaultDisplay.rotation != 0)
                Toast.makeText(this, answer.toString(), Toast.LENGTH_LONG).show()
            true
        }
    }


    private fun reset() {
        answer = Random(System.currentTimeMillis()).nextInt(
            resources.getInteger(R.integer.min_number),
            resources.getInteger(R.integer.max_number)
        )
        count = 0
        startTime = System.currentTimeMillis()
        resumeTimer()
        setStatus()
    }

    private fun doGuess() {
        if (rezult == R.string.correct)
            return
        try {
            guess = et_number.text.toString().toInt()
        } catch (e: NumberFormatException) {
            guess = resources.getInteger(R.integer.error_code)
            return
        }
        count = count?.plus(1)
//        let中默认it表示接收者(即调用let的实例）
//        let嵌套时，可以为每一个let指定接收者的变量名
        count?.let { c ->
            answer?.let { a ->
                guess?.let { g ->
                    rezult = when {
                        c > resources.getInteger(R.integer.max_count) ->
                            R.string.exceeded
                        g == resources.getInteger(R.integer.error_code) ->
                            R.string.error
                        g > a ->
                            R.string.larger
                        g < a ->
                            R.string.smaller
                        else ->
                            R.string.correct
                    }
                } ?: let { R.string.unknown }
            } ?: let { R.string.unknown }
        } ?: let { R.string.unknown }

    }


    private fun setStatus() {
        tv_count.text = count.toString()
        tv_result.text = resources.getString(rezult)
        tv_guessed.text = guess.toString()
    }

    private fun resumeTimer() {
        task?.cancel()
        timer?.cancel()
        timer = Timer(resources.getString(R.string.app_name))
        task = object : TimerTask() {
            override fun run() {
                startTime?.let { t ->
                    tv_timer.text =
                        ((System.currentTimeMillis() - t) / 1000).toString()
                }
            }
        }
        timer?.scheduleAtFixedRate(task, interval, interval)
    }
}