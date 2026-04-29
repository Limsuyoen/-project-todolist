package com.example.todolist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ActivityTodoBinding

class MainActivity : AppCompatActivity() {
    //전역 변수로 바인딩 객체 선언
   private var mbinding: ActivityMainBinding? = null
    //매번 null 체크 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mbinding!!

    override fun onCreate(saveInstanceState: Bundle?){
        super.onCreate(saveInstanceState)
        //자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해
        //액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        mbinding = ActivityMainBinding.inflate(layoutInflater)

        //getRoot메서드로 레이아웃 내부의 최상위 위치 뷰의
        //인스턴스를 활용하여 생성된 뷰를 액티비티에 표시합니다
        setContentView(binding.root)

        //이제부터 binging바인딩 변수를 활용하여 마음 껏 xml 파일 내의 뷰 id접근 가능
        //뷰 id도 파스칼케이스 + 카멜케이스 네이밍 규칙 적용으로 tv_message -> tvMessage로 변환
        binding.calendarView.setOnDateChangeListener { view, year, month, day ->
            val intent = Intent(this, TodoActivity::class.java)
            intent.putExtra("year",year)
            intent.putExtra("month", month + 1)
            intent.putExtra("day", day)
            startActivity(intent)
        }
    }



}
