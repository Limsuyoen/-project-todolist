package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ActivityTodoBinding

class TodoActivity : AppCompatActivity() {
    private var mbinding: ActivityTodoBinding? = null
    //매번 null 체크 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mbinding!!

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        //자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해
        //액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        mbinding = ActivityTodoBinding.inflate(layoutInflater)

        //getRoot메서드로 레이아웃 내부의 최상위 위치 뷰의
        //인스턴스를 활용하여 생성된 뷰를 액티비티에 표시합니다
        setContentView(binding.root)

        val year = intent.getIntExtra("year", 0)
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)

        binding.yearText.text = year.toString()
        binding.monthText.text = month.toString()
        binding.dayText.text = day.toString()

        binding.backBtn.setOnClickListener{
            finish()
        }


    }
}