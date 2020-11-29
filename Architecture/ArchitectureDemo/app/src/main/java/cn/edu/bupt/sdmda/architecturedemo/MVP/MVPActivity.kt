package cn.edu.bupt.sdmda.architecturedemo.MVP

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import cn.edu.bupt.sdmda.architecturedemo.R
import kotlinx.android.synthetic.main.activity_game.*

//Activity扮演Controller
//处理业务逻辑，并连接View和Model
class MVPActivity : AppCompatActivity() {
    private var model: MVPModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initView()
//        这里使用单例模式，确保数据不会丢失
        model = MVPModel.getInstance(this)
    }

    private fun initView() {
        btn_reset.setOnClickListener {
            model?.reset()
        }
        // 业务逻辑示例1：
        // 在Controller中判断数据格式是否有效，然后进行不同的调用
        btn_guess.setOnClickListener {
            try {
                model?.guess(et_number.text.toString().toInt())
            } catch (e: NumberFormatException) {
                model?.guess(resources.getInteger(R.integer.error_code))
            }
        }
        // 业务逻辑示例2：
        // 竖屏时无法作弊
        tv_label_guessed.isLongClickable = true
        tv_label_guessed.setOnLongClickListener {
            if (windowManager.defaultDisplay.rotation != 0)
                model?.cheat()
            true
        }
        setStatue(0, R.string.unknown, resources.getInteger(R.integer.error_code))
    }

    //    没有private修饰符，默认为public
//    函数showToast和setStatue是设计给Model调用的
    fun showToast(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show()
    }

    fun setStatue(count: Int, @StringRes rezult: Int, guess: Int) {
        tv_count.text = count.toString()
        tv_result.text = resources.getString(rezult)
        tv_guessed.text = guess.toString()
    }

    fun setTimer(elapsed: Long) {
        tv_timer.text = elapsed.toString()
    }
}