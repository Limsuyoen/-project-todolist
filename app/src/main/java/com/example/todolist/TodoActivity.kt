package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ActivityTodoBinding

class TodoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTodoBinding//xml 레이아웃 기반으로 자동 생성 클래스
    //kotlin은

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        //자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해
        //액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        binding = ActivityTodoBinding.inflate(layoutInflater)

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

        binding.inputBtn.setOnClickListener {//java와 달리 onclick()함수를 안씀
           val str = binding.inputText.getText().toString()
            //1. 데이터 만들기
            val list = mutableListOf(
                TodoItem(str, false),
                TodoItem("공부하기", false),
                TodoItem("운동하기", false)
            )
            //2. Adapter 생성
            val adapter = TodoAdapter(list)
            //3. RecyclerView 생성(중요)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adapter
        }
    }
}