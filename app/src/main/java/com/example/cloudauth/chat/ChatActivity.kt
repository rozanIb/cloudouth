package com.example.cloudauth.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudauth.R
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {
    private lateinit var messagesRecyclerView: RecyclerView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var senderUid: String
    private lateinit var receiverUid: String
    private lateinit var messagesRef: DatabaseReference
    private lateinit var messagesAdapter: MessagesAdapter
    private val messagesList = mutableListOf<Message>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        receiverUid = "KeJ87E7124OC5qxcaM52HMkCZk92"
        senderUid = "H5HjQD7SdachQ70q266FTFCOy2w1"
        messagesRecyclerView = findViewById(R.id.messages_recycler_view)
        messageEditText = findViewById(R.id.message_input)
        sendButton = findViewById(R.id.send_button)

        messagesRef = FirebaseDatabase.getInstance().getReference("chat")

        messagesAdapter = MessagesAdapter(this, messagesList,
            senderUid)
        messagesRecyclerView.layoutManager = LinearLayoutManager(this)
        messagesRecyclerView.adapter = messagesAdapter

        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString().trim()
            Log.e("ola",messageText.toString())
            if (messageText.isNotEmpty()) {
                sendMessage(messageText)
                messageEditText.setText("")

            }
        }

        messagesRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(Message::class.java)
                if (message != null) {
                    messagesList.add(message)

                    messagesAdapter.
                    notifyItemInserted(messagesList.size - 1)

                    messagesRecyclerView.
                    scrollToPosition(messagesList.size - 1)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun sendMessage(messageText: String) {
        val timestamp = System.currentTimeMillis()
        val message = Message(messageText, senderUid, receiverUid, timestamp)
        Log.e("roza","1")

        FirebaseDatabase.getInstance().reference.child("chat").push().setValue(message)

        Log.e("roza","2")

//        if(messagesRef.push().setValue(message).isSuccessful){
//
//            Log.e("ola","done")
//        }else{
//            Log.e("ola","not")
//        }
    }
}