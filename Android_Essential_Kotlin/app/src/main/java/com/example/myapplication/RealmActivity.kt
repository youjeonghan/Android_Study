package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityRealmBinding
import com.example.myapplication.databinding.ActivitySharedPreferenceBinding
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRealmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Realm.init(this@RealmActivity)
        // 램을 만들 수 있는 빌더를 가져와서 마이그레이션 필요한 경우 램을 지우겠다!는 옵션이다
        val config: RealmConfiguration =
            RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
        // realm을 얻는 것
        val realm = Realm.getDefaultInstance()

        binding.buttonSave.setOnClickListener {
            realm.executeTransaction {
                // 트랜잭션은 작업을 묶는것 데이터 손상을 막기위해서 (저장하는 도중 어떤 데이터가 값이 변하는것)
                // A테이블에서 데이터를 가져온다
                // B테이블에서 데이터를 가져온다
                // C테이블에서 데이터를 가져온다
                // 조합을 한다
                // D테이블에 저장을 한다
                with(it.createObject(School::class.java)) {
                    this.name = "어떤 대학교"
                    this.location = "서울"
                }
            }
        }
        binding.buttonLoad.setOnClickListener {
            realm.executeTransaction {
                val data = it.where(School::class.java).findFirst()
                Log.d("dataa", "data : " + data)
            }
        }
        binding.buttonDelete.setOnClickListener {
            realm.executeTransaction {
                // 테이블을 전부 지우는 것
                it.where(School::class.java).findAll().deleteAllFromRealm()
                // 하나만 지우는 것
//                it.where(School::class.java).findFirst().deleteFromRealm()

            }
        }


    }
}