package com.example.rank.data

import com.example.moviesapp.R
import com.example.myapplication.model.NavigationItem



object DataProvider {
    val ranking =
        Ranking(
            id = "1",
            title = "One Piece",
            category = "Phiêu lưu/ Hành động",
            age = 14,
            description = "Luffy và phi hành đoàn đi đến hòn đảo Asuka để tìm kiếm một than" +
                    "h kiếm huyền thoại đắt giá nhất hành tinh mặc dù nó chứa một lời nguyền chết người và" +
                    " một kho báu giá trị. Khi trở lại, cả nhóm phát hiện ra Zoro – " +
                    "người được giao nhiệm vụ trông tàu đã mất tích và bất ngờ bị lính thủy tấn công. ",
            puppyImageId = R.drawable.demonslayer
        )
    val rankingList = listOf(
        ranking,
        Ranking(
            id = "2",
            title = "Naruto",
            category = "Hành động/ Kịch tính",
            age = 14,
            description = "Câu chuyện mở đầu với trận chiến tại một bờ biển hoang vắng vào ban đêm " +
                    "giữa ninja làng Cát và những tên khổng lồ mặc chiếc áo giáp nâu. Những ninja làng" +
                    " Cát đang hoàn toàn bị thất thế trước những tên to lớn này. Đúng lúc đó Gaara và Kankuro" +
                    " xuất hiện để bảo vệ họ. Do lá chắn cát của Gaara quá mạnh nên những tên to lớn kia buộc phải " +
                    "rút lui. Họ nhìn thấy những tên ấy chạy ra một con tàu chiến giữa biển. 2 ninja quyết chèo thuyền" +
                    " ra để tìm hiểu rõ về con tàu ấy, mặc cho người khác khuyên ngăn, khi bóng họ và tàu khuất dần," +
                    " tất cả chỉ nghe thấy tiếng kêu thất thanh và không thấy họ quay lại nữa. ",
            puppyImageId = R.drawable.demonslayer
        ),
        Ranking(
            id = "3",
            title = "Sword art Online",
            category = "Game/ Hành động",
            age = 14,
            description = "Con đường sống duy nhất là đánh bại mọi kẻ thù. Cái chết trong game đồng nghĩa với cái chết ngoài " +
                    "đời thực---- Bằng Nerve Gear, mười ngàn con người lao đầu vào một trò chơi bí ẩn 'Sword Art Online'," +
                    " để rồi bị giam cầm trong đó, buộc phải dấn thân vào một đấu trường sinh tử. ",
            puppyImageId = R.drawable.demonslayer
        ),
        Ranking(
            id = "4",
            title = "Slime datta ken",
            category = "Huyền ảo/ Hư cấu",
            age = 14,
            description = "Câu chuyện sẽ xoay quanh Razha, một quốc gia nằm ở phía Tây. Rimuru sẽ thấy mình" +
                    " bị cuốn vào một âm mưu xung quanh nữ hoàng." +
                    " Người ta nói rằng cô ấy sở hữu sức mạnh vô danh.  ",
            puppyImageId = R.drawable.demonslayer
        ),
        Ranking(
            id = "5",
            title = "Bleach",
            category = "Hành động/ Giả tưởng/ Phiêu lưu",
            age = 14,
            description = "Ichigo Kurosaki là một thiếu niên hung hăng, hay gây rắc rối và cậu có được" +
                    " một năng lực đặc bệt là có thể nhìn thấy linh hồn. Câu chuyện bắt đầu với sự " +
                    "xuất hiện bất ngờ của một người lạ ngay trong phòng ngủ của Ichigo. ",
            puppyImageId = R.drawable.demonslayer
        ),
        Ranking(
            id = "6",
            title = "My Happy Marriage",
            category = "Tình cảm/ Lịch sử/ Giả tưởng",
            age = 14,
            description = "phim hoạt hình chuyển thể từ bộ tiểu thuyết giả tưởng và mở đầu bằng lời tuyên bố tuyệt vọng" +
                    " của Miyo Saimori rằng cô ấy không được mong muốn và vô dụng do thiếu sức mạnh siêu nhiên.  ",
            puppyImageId = R.drawable.demonslayer
        ),
        Ranking(
            id = "7",
            title = "Undead Murder Farce",
            category = "Lịch sử/ Siêu nhiên/ Ma cà rồng",
            age = 14,
            description = "Cuối thế kỷ 19—vợ của một ma cà rồng bị sát hại, và thám tử được gọi là người sử dụng lồng được gọi đến để phá án." +
                    " Nhưng còn nhiều điều nữa về vị thám tử và chiếc lồng chim có rèm mà anh ta mang theo... ",
            puppyImageId = R.drawable.demonslayer
        ),
        Ranking(
            id = "8",
            title = "Cô Gái Tôi Thích Quên Mang Kính",
            category = "Hài hước/ Tình cảm/ Học đường",
            age = 14,
            description = "Komura thương thầm cô bạn Mie cùng lớp ngồi ngay bàn bên. Tuy trong đầu luôn nghĩ đến cô bạn đeo kính" +
                    " này nhưng cậu lại quá nhút nhát để dám bắt chuyện. Cho đến khi cô quên mang kính... ",
            puppyImageId = R.drawable.demonslayer
        ),
        Ranking(
            id = "9",
            title = "Jujutsu Kaisen",
            category = "Anime/ Hành động/ Kinh dị",
            age = 14,
            description = "Tại thế giới nơi ác quỷ ăn thịt loài người, những mảnh vỡ của con quỷ huyền thoại Ryoumen Sukuna" +
                    " bị thất lạc và rải rác khắp nơi. Bất kì con quỷ nào hấp thụ được mảnh cơ thể của Sukuna sẽ có được sức mạnh hủy" +
                    "diệt toàn bộ thế giới. May mắn thay, " +
                    "tồn tại ngôi trường bí ẩn đang cố bảo vệ thế giới mong manh khỏi lũ quỷ. ",
            puppyImageId = R.drawable.demonslayer
        ),
        Ranking(
            id = "10",
            title = "Làng Thợ Rèn",
            category = "Hành động/ Giả tưởng/ Shounen",
            age = 14,
            description = "Mùa thứ ba của Kimetsu no Yaiba, diễn ra ngay sau Kỹ Viện Trấn. ",
            puppyImageId = R.drawable.demonslayer
        )
    )


    val banner = Banner(
        id = "1",
        bannerImage = R.drawable.demonslayer
    )
    val bannerList = listOf(
        banner,
        Banner(
            id = "2",
            bannerImage = R.drawable.demonslayer
        ),
        Banner(
            id = "3",
            bannerImage = R.drawable.demonslayer
        ),
        Banner(
            id = "4",
            bannerImage = R.drawable.demonslayer
        )
    )
}