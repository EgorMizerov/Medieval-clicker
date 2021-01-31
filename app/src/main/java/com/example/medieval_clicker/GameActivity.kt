package com.example.medieval_clicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.Math.abs

var counter = 0;
var clickPower = 1;

var counterView: TextView? = null
var powerView: TextView? = null

class GameActivity : AppCompatActivity(), GestureDetector.OnGestureListener {
    lateinit var gestureDetector: GestureDetector
    var x2: Float = 0.0f
    var x1: Float = 0.0f
    var y2: Float = 0.0f
    var y1: Float = 0.0f

    companion object{
        const val  MIN_DISTANCE = 150
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_game)

        gestureDetector = GestureDetector(this, this)
        counterView = findViewById(R.id.count)
        powerView = findViewById(R.id.power)

        // Тэги
    }

    override fun onPause() {
        super.onPause()
        counter = 0;
        clickPower = 1;
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)


        when (event?.action) {
            0->{
                x1 = event.x
                y1 = event.y
            }

            1->{
                x2 = event.x
                y2 = event.y

                val valueX: Float = x2 - x1
                val valueY: Float = y2 - y1

                // Обработка вертикальных свайпов
                if (abs(valueY) > MIN_DISTANCE) {
                    val shopping_menu: ScrollView = findViewById(R.id.shopping_menu)
                    val params: ViewGroup.LayoutParams? = shopping_menu.layoutParams

                    // Свайп вниз
                    if (y2 > y1) {
                        params?.height = resources.getDimension(R.dimen.shopping_menu_close).toInt()
                        shopping_menu.layoutParams = params
                    }
                    // Свайп вверх
                    else {
                        params?.height = resources.getDimension(R.dimen.shopping_menu_open).toInt()
                        shopping_menu.layoutParams = params
                    }
                } else if (abs(valueX) > MIN_DISTANCE) {
                    if (x2 > x1) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent);
                    }
                }
                // Обработка нажатия
                else {
                    counter += clickPower
                    counterView?.setText(counter.toString())
                }
            }
        }

        return super.onTouchEvent(event)
    }

    // Вернутся в галвнео меню
    fun moveToStartActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent);
    }

    // Улучшение нажатия
    fun buySword(view: View) {
        var v: Button = findViewById(view.id)
        var id: String = v.text.toString().substringAfter("x")
        clickPower *= id.toInt()
        powerView?.setText(clickPower.toString())
        Toast.makeText(this, "Сила удара увеличена в ${id} раза", Toast.LENGTH_SHORT).show()
    }

    override fun onDown(e: MotionEvent?): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onShowPress(e: MotionEvent?) {
//        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
//        TODO("Not yet implemented")
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}