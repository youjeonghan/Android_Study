package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivitySharedPreferenceBinding
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySharedPreferenceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Realm.init(this@RealmActivity)
        // 램을 만들 수 있는 빌더를 가져와서 마이그레이션 필요한 경우 램을 지우겠다!는 옵션이다
        val config: RealmConfiguration =
            RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
        // realm을 얻는 것
        val ream = Realm.getDefaultInstance()

    }
}