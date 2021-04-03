package com.mobile.activitycontractssample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.mobile.activitycontractssample.databinding.ActivityMainBinding
import java.util.jar.Manifest
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val TAG : String = "ACTIVITY CONTRACT"
    private val requestPermission =  registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){map ->

        for (entry in map.entries)
        {
            Log.e("Result  == ", "${entry.key} = ${entry.value}")
        }



    }

    private val customContract = registerForActivityResult(SecondActivity.ContractClass()) { output ->

        output.let {
            Log.e("Got result = ", it.outputString)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestPermission.launch(arrayOf(android.Manifest.permission.CAMERA,
        android.Manifest.permission.ACCESS_FINE_LOCATION))

        binding.btnLaunch.setOnClickListener {

            customContract.launch(Input(id = "1", name = "DEvu"))

        }


    }


}