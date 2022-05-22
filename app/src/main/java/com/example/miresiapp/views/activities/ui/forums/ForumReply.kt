package com.example.miresiapp.views.activities.ui.forums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.miresiapp.R
import com.example.miresiapp.views.fragments.forum.CommentsFragment

class ForumReply : AppCompatActivity() {
    private var forumId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum_reply)

        forumId = intent?.extras?.getInt("forumId")
        forumId?.let {
            setFragment(it)
        }

    }

    private fun setFragment(forumId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.commentContainer, CommentsFragment().apply {
                arguments = Bundle().apply {
                    putInt("forumId", forumId)
                }
            })
            .commit()
    }
}