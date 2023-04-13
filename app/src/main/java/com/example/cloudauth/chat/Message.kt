package com.example.cloudauth.chat

data class Message(
    val text: String = "",
    val senderId: String = "",
    val receiverId :String = "",
    val timestamp: Long = 0

)