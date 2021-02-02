package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.myapplication.databinding.ActivityMyTubeBinding
import com.example.myapplication.databinding.YoutubeItemViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTubeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyTubeBinding
    lateinit var glide: RequestManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTubeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as MasterApplication).service.getYoutubeList()
            .enqueue(object : Callback<ArrayList<Youtube>> {
                override fun onResponse(
                    call: Call<ArrayList<Youtube>>,
                    response: Response<ArrayList<Youtube>>
                ) {
                    if (response.isSuccessful) {
                        glide = Glide.with(this@MyTubeActivity)
                        val youtubeList = response.body()
                        Log.d("test", "" + youtubeList)
                        val adapter = MytubeAdapter(youtubeList!!, glide, this@MyTubeActivity)
                        binding.youtubeListRecycler.adapter = adapter
                        binding.youtubeListRecycler.layoutManager =
                            LinearLayoutManager(this@MyTubeActivity)
                    }
                }

                override fun onFailure(call: Call<ArrayList<Youtube>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })


    }
}

class MytubeAdapter(
    var youtubeList: ArrayList<Youtube>,
    val glide: RequestManager,
    val activity: Activity
) : RecyclerView.Adapter<MytubeAdapter.ViewHolder>() {
    inner class ViewHolder(itemViewBinding: YoutubeItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        val thumbnail: ImageView
        val title: TextView
        val content: TextView

        init {
            thumbnail = itemViewBinding.youtubeThumbnail
            title = itemViewBinding.youtubeTitle
            content = itemViewBinding.youtubeContent

            itemViewBinding.root.setOnClickListener {
                val position: Int = adapterPosition
                val intent = Intent(activity, MytubeDetailActivity::class.java)
                intent.putExtra("video_url", youtubeList.get(position).video)
                activity.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.youtube_item_view, parent, false)
        LayoutInflater.from(parent.context).inflate(R.layout.phonebook_item, parent, false)
        return ViewHolder(YoutubeItemViewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(youtubeList.get(position).title)
        holder.content.setText(youtubeList.get(position).content)
        glide.load(youtubeList.get(position).thumbnail).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return youtubeList.size
    }
}
