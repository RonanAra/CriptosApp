package com.example.coinbase.common.mapper

interface Mapper<F, T> {
    fun mapFrom(from: F): T
}