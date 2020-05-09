package com.example.testapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.model.api.Time
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {
    companion object{
//        const val TEXT = "text"
        const val TIME = "time"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

//        var text = intent.getStringExtra("text")
//        textView_sub_content.text = text

        var time = intent.getParcelableExtra<Time>(TIME)
        if (time != null){
            var text = time.startTime + "\n" + time.endTime + "\n" + time.parameter.parameterName + time.parameter.parameterUnit
            textView_sub_content.text = text
        }
    }
}
