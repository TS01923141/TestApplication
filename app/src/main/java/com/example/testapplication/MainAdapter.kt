package com.example.testapplication

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.model.api.Time

class MainAdapter(timeList: MutableList<Time>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = MainAdapter::class.simpleName
    private var timeList = timeList
    private lateinit var context: Context

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView_data: TextView = itemView.findViewById(R.id.textView_data)
        var constraintLayout_data : ConstraintLayout = itemView.findViewById(R.id.constraintLayout_data)
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!this::context.isInitialized) context = parent.context

        if (viewType % 2 == 0) {
            return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false))
        } else {
            return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        if (timeList.size == 0) return 0
        return (timeList.size * 2) - 1
    }

    override fun onBindViewHolder(h: RecyclerView.ViewHolder, p: Int) {
        if (p % 2 == 0) {
            var position = p / 2
            var holder: DataViewHolder = h as DataViewHolder
            var text = timeList[position].startTime + "\n" + timeList[position].endTime + "\n" + timeList[position].parameter.parameterName + timeList[position].parameter.parameterUnit
            holder.textView_data.text = text

            holder.constraintLayout_data.setOnClickListener {
                var intent = Intent(context, SubActivity::class.java)
//                intent.putExtra(SubActivity.TEXT, text)
                intent.putExtra(SubActivity.TIME, timeList[position])
                context.startActivity(intent)
            }
        }
    }
}