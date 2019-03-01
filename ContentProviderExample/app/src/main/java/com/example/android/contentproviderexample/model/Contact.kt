package com.example.android.contentproviderexample.model

class Contact(var builder: Builder) {
    class Builder {
        lateinit var name: String
        lateinit var phone: String

        fun setName(name: String): Builder {
            this.name = name
            return this
        }

        fun setPhone(phone: String): Builder {
            this.phone = phone
            return this
        }

        fun build(): Contact {
            validate()
            return Contact(this)
        }

        fun validate() {}

    }
}
