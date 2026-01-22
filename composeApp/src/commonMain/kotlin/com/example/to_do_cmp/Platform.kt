package com.example.to_do_cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform