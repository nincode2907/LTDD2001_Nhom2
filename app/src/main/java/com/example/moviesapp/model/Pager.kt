package com.example.myapplication.model

sealed class Pager(val tile: String) {
    object TrangChu : Pager("Trang Chủ")
    object PhimBo : Pager("Phim bộ")
    object PhimHanhDong : Pager("Phim Hành động")
    object PhimKinhDi : Pager("Phim kinh dị")

}