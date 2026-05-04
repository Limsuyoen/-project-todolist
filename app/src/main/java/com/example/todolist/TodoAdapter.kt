package com.example.todolist

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.EditText
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

        val editText: EditText = view.findViewById(R.id.todo_edt)
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
        val isLast = position == dataSet.size - 1

        //isEditing 상태에 따른 뷰 변경
        if(item.isEditing){
            holder.editText.visibility = View.VISIBLE
            holder.textView.visibility = View.GONE
            holder.checkbox.visibility = View.GONE
            holder.editText.setText(item.text)

            //신규 입력일때만 포커스
            if(item.isNew){
                holder.editText.post {
                    holder.editText.requestFocus()
                }
            }

        }else{
            holder.editText.visibility = View.GONE
            holder.textView.visibility = View.VISIBLE
            holder.textView.text = item.text
            holder.checkbox.visibility = View.VISIBLE
        }

        //5.
        holder.textView.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                val item = dataSet[pos]
                item.isEditing = true
                item.isNew = false //수정 시
                notifyItemChanged(pos)
            }
        }

        //6. enter누르면 저장
        holder.editText.setOnKeyListener(null)

        holder.editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE &&
                (event == null || event.action == KeyEvent.ACTION_DOWN)
            ) {

                val pos = holder.adapterPosition
                if (pos != RecyclerView.NO_POSITION) {

                    val item = dataSet[pos]
                    val text = holder.editText.text.toString()

                    if (text.isNotEmpty()) {
                        item.text = text
                        item.isEditing = false

                        if(item.isNew) {
                            dataSet.add(TodoItem("", false, true,true))
                            notifyItemInserted(dataSet.size - 1)
                        }
                        item.isNew = false
                        notifyItemChanged(pos)

                    }
                }else{
                    if(!isLast){
                        dataSet.removeAt(pos)
                        notifyItemRemoved(pos)
                    }else{}
                }
                //done눌렀을때
                v.clearFocus()
                // 키보드 내리기
                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                true
            } else false
        }
    }

    override fun getItemCount(): Int  = dataSet.size
}


