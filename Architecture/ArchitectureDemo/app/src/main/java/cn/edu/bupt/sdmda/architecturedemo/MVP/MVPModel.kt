package cn.edu.bupt.sdmda.architecturedemo.MVP

import androidx.annotation.StringRes
import cn.edu.bupt.sdmda.architecturedemo.R
import java.util.*
import kotlin.random.Random

class MVPModel(private var ctx: MVPActivity) {
    companion object {
        //        单例模式
//        在类中定义一个自己类型的静态实例（instance）
//        并定义一个静态函数（getInstance）
//        函数中判断instance是否为null
//        若为null则实例化，并返回
//        若不为空，则直接返回instance
//        这样就能确保返回的instance始终为同一个
        var instance: MVPModel? = null

        //        这里的单例模式和一般的不太一样
//        instance的ctx变量需要变化，指向当前activity
        @JvmStatic
        fun getInstance(act: MVPActivity): MVPModel? {
            if (instance == null)
                instance = MVPModel(act)
            else
//                apply函数和run类似
//                run函数执行Lambda表示式中的代码，并返回结果
//                apply函数执行Lambda表示式中的代码，并返回接收者，也就是this
//                apply主要用于集中修改某个实例中的属性
                instance?.apply {
                    ctx = act
                }
            return instance
        }
    }

    private val interval: Long = 1000

    private var count = 0
    private var answer = 0
    private var guess = ctx.resources.getInteger(R.integer.error_code)
    private var startTime = System.currentTimeMillis()
    private var timer: Timer? = null
    private var task: TimerTask? = null


    @StringRes
    private var rezult: Int = R.string.unknown

    // 构造函数
    init {
        reset()
        instance = this
    }

    fun reset() {
//        run函数接收一个Lambda表示式为参数
//        代码块中，this表示接收者,
//        执行Lambda表达式中的代码，并返回结果
//        这里使用run函数主要是想省略ctx.
//        如果不写run，需要:
//        ctx.resources.getInteger(R.integer.error_code)
//        ctx.setStatue(count, rezult, guess)
        ctx.run {
            answer = Random(System.currentTimeMillis()).nextInt(
                resources.getInteger(R.integer.min_number),
                resources.getInteger(R.integer.max_number)
            )
            count = 0
            guess = resources.getInteger(R.integer.error_code)
            rezult = R.string.unknown
            setStatue(count, rezult, guess)
        }
        resetTimer()

    }

    fun guess(g: Int) {
        ctx.run {
            when {
                count + 1 > resources.getInteger(R.integer.max_count) ->
                    rezult = R.string.exceeded
                g == resources.getInteger(R.integer.error_code) -> {
                    rezult = R.string.error
                    count -= 1
                }
                rezult == R.string.correct -> count -= 1
                else -> {
                    rezult = when {
                        g > answer -> R.string.larger
                        g < answer -> R.string.smaller
                        g == answer -> R.string.correct
                        else -> R.string.unknown
                    }
                }
            }
            count += 1
            guess = g
            setStatue(count, rezult, guess)
        }
    }

    fun cheat() {
        ctx.showToast(answer.toString())
    }

    private fun resetTimer() {
        startTime = System.currentTimeMillis()
        task?.cancel()
        timer?.cancel()
        timer = Timer(ctx.localClassName)
        task = object : TimerTask() {
            override fun run() {
                ctx.setTimer(((System.currentTimeMillis() - startTime)) / 1000)
            }
        }
        timer?.scheduleAtFixedRate(task, interval, interval)
    }

}