package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ActivityTodoBinding

class TodoActivity : AppCompatActivity() {

    //private 이 클래스에서만 사용함
    //activitytodobinding은 todoactivity기반으로 자동 생성된 클래스
    // -> xml의 view들이 자동 findviewByid해서 객체로 묶어있는 형태로 생성됨
    //var binding: ActivityTodoBinding 과 ActivityTodoBinding binding; 은같은 의미
    //String name;(타입 변수명) == var name: String (추후수정가능여부 변수명: 타입)
    private lateinit var binding : ActivityTodoBinding//xml 레이아웃 기반으로 자동 생성 클래스
    //1. binding변수 생성(사용범위, 초기화여부, 추후수정여부,타입 지정)
    //kotlin은 NPE(NullPointerException:null인 객체/변수 호출시 오류 발생)를 방지하기 위해
    //lateinit: activity의 생명주기 떄문에 사용함
    // binding을 선언하는 시점에는 activity가 생성되지 않음(onCreate내에서 생성)
    //-> 어차피 binding 사용(ui연결)을 on create안에서만 할 수 있어서 나중에 초기화함

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