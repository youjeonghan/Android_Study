package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.myapplication.databinding.ActivityOutStargramMyPostListBinding
import com.example.myapplication.databinding.OutstargramItemViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OutStargramMyPostListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOutStargramMyPostListBinding
    lateinit var myPostRecyclerView: RecyclerView
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStargramMyPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myPostRecyclerView = binding.myPostRecyclerview
        glide = Glide.with(this@OutStargramMyPostListActivity)
        createList()



        binding.allList.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramMyPostListActivity,
                    OutStargramPostListActivity::class.java
                )
            )
        }
        binding.userInfo.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramMyPostListActivity,
                    OutstargramUserinfo::class.java
                )
            )
        }
        binding.upload.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramMyPostListActivity,
                    OutStargramUploadActivity::class.java
                )
            )
        }
        binding.myList.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramMyPostListActivity,
                    OutStargramMyPostListActivity::class.java
                )
            )
        }
    }

    fun createList() {
        (application as MasterApplication).service.getUserPostList().enqueue(
            object : Callback<ArrayList<Post>> {
                override fun onResponse(
                    call: Call<ArrayList<Post>>,
                    response: Response<ArrayList<Post>>
                ) {
                    if (response.isSuccessful) {
                        val myPostList = response.body()
                        val adapter = PostAdapter(myPostList!!, glide)
                        binding.myPostRecyclerview.adapter = adapter
                        binding.myPostRecyclerview.layoutManager =
                            LinearLayoutManager(this@OutStargramMyPostListActivity)
                    }
                }

                override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}

//class PostAdapter(
//    var postList: ArrayList<Post>,
//    val glide: RequestManager
//) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
//    inner class ViewHolder(itemViewBinding: OutstargramItemViewBinding) :
//        RecyclerView.ViewHolder(itemViewBinding.root) {
//        val postOwner: TextView
//        val postImage: ImageView
//        val postContent: TextView
//
//        init {
//            postOwner = itemViewBinding.postOwner
//            postImage = itemViewBinding.postImg
//            postContent = itemViewBinding.postContent
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.outstargram_item_view, parent, false)
//        LayoutInflater.from(parent.context).inflate(R.layout.phonebook_item, parent, false)
//        return ViewHolder(OutstargramItemViewBinding.bind(view))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.postOwner.setText(postList.get(position).owner)
//        holder.postContent.setText(postList.get(position).content)
//        glide.load(postList.get(position).image).into(holder.postImage)
//    }
//
//    override fun getItemCount(): Int {
//        return postList.size
//    }
//}