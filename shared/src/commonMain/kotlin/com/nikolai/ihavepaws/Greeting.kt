package com.nikolai.ihavepaws

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}