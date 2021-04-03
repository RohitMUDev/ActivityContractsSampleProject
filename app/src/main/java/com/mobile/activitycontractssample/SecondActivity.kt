package com.mobile.activitycontractssample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.mobile.activitycontractssample.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name =   intent?.getStringExtra("name")
        val id =   intent?.getStringExtra("id")
        binding.btnSecond.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra("output", "got data and worked"))
            finish()
        }
    }


    class ContractClass : ActivityResultContract<Input, Output>() {
        override fun createIntent(context: Context, input: Input?): Intent {
            return  Intent(context, SecondActivity::class.java)
                .putExtra("name", input?.name)
                .putExtra("id", input?.id)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Output? {

            if(resultCode == RESULT_OK){

                val data = intent?.getStringExtra("output")
                return  data?.let { Output(outputString = it) }
            }
            return  Output("")
        }
    }


}