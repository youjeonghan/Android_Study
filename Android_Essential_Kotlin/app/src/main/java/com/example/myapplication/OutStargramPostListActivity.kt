package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.myapplication.databinding.ActivityOutStargramPostListBinding
import com.example.myapplication.databinding.OutstargramItemViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OutStargramPostListActivity : AppCompatActivity() {

    lateinit var glide: RequestManager

    private lateinit var binding: ActivityOutStargramPostListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStargramPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        glide = Glide.with(this@OutStargramPostListActivity)

        (application as MasterApplication).service.getAllPosts().enqueue(
            object : Callback<ArrayList<Post>> {
                override fun onResponse(
                    call: Call<ArrayList<Post>>,
                    response: Response<ArrayList<Post>>
                ) {
                    if (response.isSuccessful) {
                        val postList = response.body()
                        val ada
                    }
                }

                override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {

                }
            }
        )
    }
}

class PostAdapter(
    var postList: ArrayList<Post>,
    val glide: RequestManager
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    inner class ViewHolder(itemViewBinding: OutstargramItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        val postOwner: TextView
        val postImage: ImageView
        val postContent: TextView

        init {
            postOwner = itemViewBinding.postOwner
            postImage = itemViewBinding.postImg
            postContent = itemViewBinding.postContent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.outstargram_item_view, parent, false)
        LayoutInflater.from(parent.context).inflate(R.layout.phonebook_item, parent, false)
        return ViewHolder(OutstargramItemViewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postOwner.setText(postList.get(position).owner)
        holder.postContent.setText(postList.get(position).content)
        glide.load(postList.get(position).image).into(holder.postImage)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}