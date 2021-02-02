package com.example.myapplication

import android.app.Activity
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.myapplication.databinding.ActivityMelonBinding
import com.example.myapplication.databinding.SongItemViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MelonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMelonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMelonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as MasterApplication).service.getSongList().enqueue(
            object : Callback<ArrayList<Song>> {
                override fun onResponse(
                    call: Call<ArrayList<Song>>,
                    response: Response<ArrayList<Song>>
                ) {

                    if (response.isSuccessful) {
                        Log.d("test", "ok")
                        val songList = response.body()

                        val adapter = MelonAdapter(
                            songList!!,
                            Glide.with(this@MelonActivity),
                            this@MelonActivity
                        )
                        binding.songList.adapter = adapter

                    }
                }

                override fun onFailure(call: Call<ArrayList<Song>>, t: Throwable) {
                    Log.d("test", "fail")
                }
            }
        )
    }


    inner class MelonAdapter(
        var songList: ArrayList<Song>,
        val glide: RequestManager,
        val activity: Activity
    ) : RecyclerView.Adapter<MelonAdapter.ViewHolder>() {
        inner class ViewHolder(itemViewBinding: SongItemViewBinding) :
            RecyclerView.ViewHolder(itemViewBinding.root) {
            val thumbnail: ImageView
            val title: TextView
            val play: ImageView

            init {
                thumbnail = itemViewBinding.songImg
                title = itemViewBinding.songTitle
                play = itemViewBinding.songPlay

                itemViewBinding.songPlay.setOnClickListener {
                    val position: Int = adapterPosition
                    val path = songList.get(position).song
                    val mediaPlayer = MediaPlayer.create(
                        this@MelonActivity,
                        Uri.parse(path)
                    )
                    mediaPlayer.start()

                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.song_item_view, parent, false)
            return ViewHolder(SongItemViewBinding.bind(view))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title.setText(songList.get(position).title)
            glide.load(songList.get(position).song).into(holder.play)
            glide.load(songList.get(position).thumbnail).into(holder.thumbnail)
        }

        override fun getItemCount(): Int {
            return songList.size
        }
    }
}