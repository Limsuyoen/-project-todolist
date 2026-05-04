package com.example.todolist

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ActivityTodoBinding

class TodoActivity : AppCompatActivity() {

    //private 이 클래스에서만 사용함
    //activitytodobinding은 todoactivity기반으로 자동 생성된 클래스
    // -> xml의 view들을 자동으로 연결해주는 클래스, inflate시점에 이루어짐
    //var binding: ActivityTodoBinding 과 ActivityTodoBinding binding; 은같은 의미
    //String name;(타입 변수명) == var name: String (추후수정가능여부 변수명: 타입)
    private lateinit var binding : ActivityTodoBinding//xml 레이아웃 기반으로 자동 생성 클래스
    //1. binding변수 생성(사용범위, 초기화여부, 추후수정여부,타입 지정)
    //kotlin은 NPE(NullPointerException:null인 객체/변수 호출시 오류 발생)를 방지하기 위해
    //lateinit: activity의 생명주기 떄문에 사용함
    // binding을 선언하는 시점에는 activity가 생성되지 않음(onCreate내에서 생성)
    //-> 어차피 binding 사용(ui연결)을 on create안에서만 할 수 있어서 나중에 초기화한다고 선언필요

    lateinit var adapter: TodoAdapter
    val list = mutableListOf<TodoItem>()
    //mutablelist는 kotlin에서 제공하는 함수로 변경 가능한 리스트 생성

    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        //2. 바인딩 클래스의 인스턴스 생성
        //자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해
        //액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        binding = ActivityTodoBinding.inflate(layoutInflater)
        //xml레이아웃을 실제 view객체로 바꿀때 inflate()를 사용함
        //내부에서 findviewbyid 비슷한 작업으로 view찾아서 연결

        //3. 레이아웃 화면 연결
        setContentView(binding.root)
        //setContentView: activity에서 화면 채우는 대표 메서드, xml을 inflation함
        //root는 xml에서 만들어진 최상위 view ( ex) linearlayout)
        //-> xml의 모든 요소를 root라는 박스로 binding.root로 불러오는거임

        // Adapter 생성
        val adapter = TodoAdapter(list)
        //3. RecyclerView 생성(중요)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        //MainActivity에서 intent로 넘긴 값 받아옴
        val year = intent.getIntExtra("year", 0)
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)
        //화면에 있는 요소 변경
        binding.yearText.text = year.toString()
        binding.monthText.text = month.toString()
        binding.dayText.text = day.toString()
        //intent종료는 backbtn으로
        binding.backBtn.setOnClickListener{
            finish()
        }

        list.add(TodoItem("", false, true,true))
        adapter.notifyItemInserted(list.size - 1)

//        binding.inputBtn.setOnClickListener {//java와 달리 onclick()함수를 안씀
//           val str = binding.inputText.getText().toString()
//
//            if(str.isNotEmpty()){
//                list.add(TodoItem(str,false,false))
//                //맨 마지막 하나 추가됐다고 알려줌 마지막만 갱신
//                adapter.notifyItemInserted(list.size - 1)
//                binding.inputText.text.clear()
//            }
//        }
    }
}