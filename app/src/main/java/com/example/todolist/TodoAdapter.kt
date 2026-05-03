package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.TodoItem

class TodoAdapter(
    private val dataSet: MutableList<TodoItem>
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    //1. ViewHolder 클래스
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.todo_text)
        val checkbox: CheckBox = view.findViewById(R.id.check_box)
    }


    //2. view 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)

        return ViewHolder(view)
    }

    //3.데이터 바인딩
    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        val item = dataSet[position]
        //1. 텍스트 바인딩
        holder.textView.text = item.text
        //2. 체크 상태 바인딩
        holder.checkbox.setOnCheckedChangeListener(null)
        holder.checkbox.isChecked = item.isChecked
        //3. 체크 이벤트 -> 데이터 업데이트
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
        }

    }

    override fun getItemCount(): Int  = dataSet.size
}


