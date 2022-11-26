package com.minjaeheo.todolistapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.minjaeheo.todolistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // layout 파일과 상호작용 할 수 있게 바인딩함 gradle에서 먼저 키고 써야됨. lateinit 늦은 초기화, 코틀린에선 최초로 값을 할당을 초기화라고 함
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // 초기화
        setContentView(binding.root) // 뷰 바인딩 결합 xml과 합체 fb 구문 안 써도 됨 (보일러 플레이트)

        // 카멜케이스 fb처럼 앞에 binding만 쓰면 된다.
        binding.tvName.setText("안녕하세요 연습중")
        binding.tvTest.setText("연습중2")

        // log 찍는 방법
        // Log.d("!@", "처음 호출될 때  ${setContentView(R.layout.activity_main)}")

        // 액티비티가 생성될 때 호출되며 사용자 인터페이스 초기화 할 때 이 곳에 구현
        println("minjaeheo onCreate")
    }

    override fun onStart() {
        super.onStart()
        // 액티비티가 사용자에게 보여지기 직전에 호출 됨
        println("minjaeheo onStart")
    }

    override fun onResume() {
        super.onResume()
        // 액티비티가 사용자랑 상호작용 하기 직전에 호출 됨 (시작 or 재개 상태)
        println("minjaeheo onResume")

    }

    override fun onPause() {
        super.onPause()
        // 다른 액티비티가 보여지게 될 때 호출 됨 (중지 상태)
        println("minjaeheo onPause")
    }

    override fun onStop() {
        super.onStop()
        // 액티비티가 사용자에게 완전히 보여지지 않을 때 호출 됨
        println("minjaeheo onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        // 액티비티가 소멸(제거)될 때 호출 됨
        println("minjaeheo onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        // 액티비티가 멈췄다가 다시 시작되기 전에 실행됨
        println("minjaeheo onRestart")
    }
}