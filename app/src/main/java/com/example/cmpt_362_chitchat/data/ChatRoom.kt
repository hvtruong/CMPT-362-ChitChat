package com.example.cmpt_362_chitchat.data

class ChatRoom() {
    var chatRoomId: String = ""
    var chatRoomType: String = ""

    constructor(chatRoomId: String, chatRoomType: String): this() {
        this.chatRoomId = chatRoomId
        this.chatRoomType = chatRoomType
    }
}