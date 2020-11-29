package cn.edu.bupt.sdmda.architecturedemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.edu.bupt.sdmda.architecturedemo.MVP.MVPActivity
import cn.edu.bupt.sdmda.architecturedemo.normal.NormalActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
//        Version 1
//        和Java中几乎一样的写法
//        btn_normal.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                val normal = Intent(this@MainActivity, NormalActivity::class.java)
//                startActivity(normal)
//            }
//        })

//        Version 2
//        函数最后一个参数为接口类型，并且接口中只有一个函数，可以将其移出函数
//        btn_normal.setOnClickListener() {
//            object : View.OnClickListener {
//                override fun onClick(v: View?) {
//                    val normal = Intent(this@MainActivity, NormalActivity::class.java)
//                    startActivity(normal)
//                }
//            }
//        }

//        Version 3
//        如果移出最后一个参数后，括号内没有其他参数，可以省略括号
//        btn_normal.setOnClickListener{
//            object : View.OnClickListener {
//                override fun onClick(v: View?) {
//                    val normal = Intent(this@MainActivity, NormalActivity::class.java)
//                    startActivity(normal)
//                }
//            }
//        }

//        Version 4
////      如果接口中只有一个函数，则可以省略函数名那一行，直接写函数体
//        btn_normal.setOnClickListener {
//            View.OnClickListener { v ->
//                val normal = Intent(this, NormalActivity::class.java)
//                startActivity(normal)
//            }
//        }

//        Version 5
//        可以进一步省略接口类型，直接用Lambda表达式

//        注意Intent构造函数的第一个参数
//        如果显式说明用的是匿名类（Version 1,2,3），则this表示匿名类
//        否则this表示外层实例（MainActivity）
        btn_normal.setOnClickListener {
            val normal = Intent(this, NormalActivity::class.java)
            startActivity(normal)
        }

        btn_mvp.setOnClickListener {
            val mvc = Intent(this, MVPActivity::class.java)
            startActivity(mvc)
        }

    }

//    定义接口类型的匿名类
//    val listener = object : View.OnClickListener {
//        override fun onClick(v: View?) {
//            when (v?.id) {
//                R.id.btn_guess -> return
//                R.id.btn_reset -> return
//            }
//        }
//    }


}