package com.rawtooth.demoapp

import kotlin.random.Random
var id=0;
data class DetailModel(
    var id:Int=getAutoId(),
    var name:String=" ",
    var email:String=" "

) {
    companion object {
        fun getAutoId(): Int {
           id++
            return id
        }
    }
}