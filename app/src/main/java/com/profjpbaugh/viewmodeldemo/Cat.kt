package com.profjpbaugh.viewmodeldemo

class Cat (name: String, temperament : String, origin : String, url: String){

    var name = name
        get() = field
        set(value){
            field = name
        }

    var temperament = name
        get() = field
        set(value){
            field = temperament
        }

    var origin = name
        get() = field
        set(value){
            field = origin
        }

    var url = name
        get() = field
        set(value){
            field = url
        }
}