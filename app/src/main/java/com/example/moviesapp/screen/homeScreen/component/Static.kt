package com.example.moviesapp.screen.homeScreen.component

import android.graphics.Color.parseColor
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import codes.andreirozov.bottombaranimation.ui.theme.fontFamilyBody

object StyleStatic {
    val darkColor = "#1C1E1D";
    val whiteColor = "#F8F8F8";
    val whiteBlurColor = "#8C8C8C";
    val primaryTextColor: Color = Color(parseColor(whiteColor))
    var primaryModeColor: Color = Color(parseColor(darkColor));
    var blurTextWhiteColor: Color = Color(parseColor(whiteBlurColor));

    var textCommonStyle = TextStyle(
        fontSize = 13.sp,
        color = primaryTextColor,
        fontFamily = fontFamilyBody
    )

    var modifierFilmInListSize = Modifier
        .width(120.dp)
        .height(185.dp)
}

data class FilmInfo(
    val id: String,
    val name : String,
    val description : String,
    val trailer : String,
    val poster : String,
    val yearRelease : Int,
    val episodeTotal : String,
    val time : String,
    val country : String,
    val categories : String
)

val listFilms = listOf<FilmInfo>(
    FilmInfo(
        id = "OPfRdHT",
        name = "One Piece Film: Red - Đảo Hải Tặc",
        description = "One Piece Film: Red là bộ phim hoạt hình anime của Nhật Bản thuộc thể loại kỳ ảo, hành động - phiêu lưu được sản xuất bởi Toei Animation. Đây là phần phim thứ mười lăm trong loạt phim điện ảnh của One Piece, dựa trên bộ truyện manga nổi tiếng cùng tên của tác giả Eiichiro Oda. Phim được công bố lần đầu tiên vào ngày 21 tháng 11, 2021 để kỷ niệm sự ra mắt của tập phim thứ 1000 của bộ anime One Piece và sau khi tập phim này được phát sóng, đoạn quảng cáo và áp phích chính thức của phim cũng chính thức được công bố. Phim dự kiến sẽ phát hành vào ngày 6 tháng 8 năm 2022. Bộ phim được giới thiệu sẽ là hành trình xoay quanh một nhân vật nữ mới cùng với Shanks \"Tóc Đỏ\".",
        trailer = "AmTUaFdTETc",
        poster = "https://www.animationmagazine.net/wordpress/wp-content/uploads/One-Piece-RED.jpg",
        yearRelease = 2022,
        episodeTotal = "Trọn Bộ",
        time = "115 phút",
        country = "Nhật Bản",
        categories = "Anime lẻ, Phim chiếu rạp, Shounen, Super Power, Fantasy, Drama, Comedy, Adventure, Action"
    ),
    FilmInfo(
        id = "SnT",
        name = "Suzume no Tojimari",
        description =  "Câu chuyện Suzume no Tojimari kể về Suzume, một cô gái 17 tuổi đến từ một thị trấn Kyushu yên tĩnh gặp một chàng trai trẻ đang tìm kiếm một cánh cửa. Họ tìm thấy một cánh cửa trong đống đổ nát trên núi, và Suzume đã mở nó. Chẳng bao lâu, nhiều cánh cửa hơn bắt đầu mở ra trên khắp Nhật Bản, kéo theo những thảm họa từ phía bên kia. Bộ phim mô tả sự giải phóng và trưởng thành của Suzume, khi cô đóng những cánh cửa đang gây ra thảm họa.",
        trailer = "5pTcio2hTSw",
        poster = "https://wallpaperaccess.com/full/8887832.jpg",
        yearRelease = 2022,
        episodeTotal = "Trọn Bộ",
        time = "121 phút",
        country = "Nhật Bản",
        categories = "Phiêu Lưu, Anime, Movie & OVA, Giả Tưởng"
    ),
    FilmInfo(
        id = "Hlce",
        name = "Hôn lễ của em",
        description = "Hôn Lễ Của Em kể về câu chuyện tình yêu kéo dài 15 năm giữa Châu Tiêu Tề - một nam sinh có năng khiếu bơi lội, và Vưu Vịnh Từ - một nữ sinh chuyển trường. Trong thời gian học cấp 3, Châu Tiêu Tề ( Hứa Quang Hán đóng) gặp phải tình yêu sét đánh với Vưu Vịnh Từ ( Chương Nhược Nam đóng), tình yêu thời thanh xuân ngây thơ và thuần khiết, cậu không ngừng âm thầm bảo vệ cô, nhưng cô lại rời đi không từ mà biệt. Từ đó về sau, chính là một cuộc tình kéo dài tận 15 năm. Hôn lễ của em, cũng chính là lễ trưởng thành của anh.",
        trailer = "v3Hx-hTdlko",
        poster = "https://kenh14cdn.com/thumb_w/660/203336854389633024/2021/5/4/20210425c3705a73j00qs33xf01rlc0018g01ywc-16201143584772017878023.jpg",
        yearRelease = 2021,
        episodeTotal = "Trọn bộ",
        time = "115 phút",
        country = "Trung Quốc",
        categories = "Tâm Lý - Tình Cảm"
    ),
    FilmInfo(
        id = "JK0cthc",
        name = "Jujutsu Kaisen 0: Chú Thuật Hồi Chiến",
        description = "Cho những ai chưa biết, Jujutsu Kaisen 0 là là tập truyện ngắn ra mắt trước khi Chú Thuật Hồi Chiến được sáng tác. Trọng tâm câu chuyện xoay quanh nguyền sư đặc cấp Yuta Okkotsu trong thời gian anh ta mới vào học viện chú thuật.Theo nhiều dự đoán, Jujutsu Kaisen 0 được chuyển thể anime movie là \"bước mở đường\" cho những dự án Jujutsu Kaisen tiếp theo. Phần phim này sẽ giúp giải thích về các nhân vật chính, đặc biệt là những nguyền sư đặc cấp hiện tại.",
        trailer = "UPRqnFnnrr8",
        poster = "https://images2.thanhnien.vn/Uploaded/sangdt/2022_03_16/jujutsukaisen0-293.jpg",
        yearRelease = 2022,
        episodeTotal = "Trọn Bộ",
        time = "121 phút",
        country = "Nhật Bản",
        categories = "Phiêu Lưu, Anime, Movie & OVA, Giả Tưởng"
    ),
    FilmInfo(
        id = "TTldc25NDH",
        name = "Thám Tử Lừng Danh Conan 25: Nàng Dâu Halloween",
        description = "Bối cảnh lần này diễn ra tại Shibuya, Tokyo đang nhộn nhịp mùa Halloween.Một hôn lễ được tổ chức ở Shibuya Hikarie. Tại đó thanh tra Satou của tổ điều tra số 1 sở cảnh sát Tokyo xuất hiện với hình dáng cô dâu đang mặc váy cưới. Trong lúc đang theo dõi các vị khách mời là nhóm Conan thì một tên côn đồ bất ngờ xông vào tấn công, vì trung sĩ Takagi cố gắng bảo vệ nên đã bị thương. Takagi không bị nguy hiểm đến tính mạng và tình trạng đã ổn định, nhưng trong đôi mắt của Satou hiện lên hình ảnh của thần chết mà mình từng nhìn thấy ngay trước khi thanh tra Matsuda, người mà cô để ý hy sinh trong một vụ đánh bom liên hoàn 3 năm trước.Cùng thời điểm, tên tội phạm của vụ đánh bom liên hoàn đó đã vượt ngục. Rei Furuya (Tooru Amuro) thuộc cảnh sát an ninh bắt đầu truy lùng kẻ gây ra cái chết cho người bạn cùng khóa là thanh tra Matsuda, nhưng một kẻ hóa trang bí ẩn xuất hiện ở đó và gắn một quả bom loại vòng cổ.Conan đến một hầm trú ẩn dưới lòng đất, nơi Amuro đang ẩn náu để phá giải quả bom vòng cổ đó và nghe kể về vụ án Amuro và các thành viên cùng khóa thời học viện cảnh sát mà bây giờ đã qua đời gặp phải rằng họ bắt gặp \"Plamya\"- một kẻ đánh bom hóa trang chưa rõ danh tính ở Shibuya vào 3 năm trước.*\"Plamya\" - ( пламя - tiếng Nga nghĩa là ngọn lửa)Cuối cùng, một bóng đen đáng lo ngại nhắm vào nhóm Conan đang tiến hành cuộc điều tra....“Cuộc tranh giành vận mệnh” được ngọn lửa thổi bùng. Giờ đây, lễ hội Halloween biến dạng thành một chuyến đi đêm điên cuồng...",
        trailer = "5DcWtjrYcFI",
        poster = "https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/r/s/rsz_conan_movie_2022-_vnese_poster_1_.jpg",
        yearRelease = 2022,
        episodeTotal = "Trọn Bộ",
        time = "110 phút",
        country = "Nhật Bản",
        categories = "Anime lẻ, Shounen, Romance, Police, Drama, Comedy, Mystery, Action"
    ),
    FilmInfo(
        id = "DNvccvtth",
        name = "Doraemon: Nobita Và Cuộc Chiến Vũ Trụ Tí Hon",
        description = "Nobita tình cờ gặp được người ngoài hành tinh tí hon Papi, vốn là Tổng thống của hành tinh Pirika, chạy trốn tới Trái Đất để thoát khỏi những kẻ nổi loạn nơi quê nhà. Doraemon, Nobita và hội bạn thân dùng bảo bối đèn pin thu nhỏ biến đổi theo kích cỡ giống Papi để chơi cùng cậu bé. Thế nhưng, một tàu chiến không gian tấn công cả nhóm. Cảm thấy có trách nhiệm vì liên lụy mọi người, Papi quyết định một mình đương đầu với quân phiến loạn tàn ác. Doraemon và các bạn lên đường đến hành tinh Pirika, sát cánh bên người bạn của mình.",
        trailer = "dd_R1GQwKlY",
        poster = "https://static.wikia.nocookie.net/dorepedia/images/8/8d/Doraemon_the_Movie_41_poster.jpg/revision/latest?cb=20220506110934&path-prefix=vi",
        yearRelease = 2022,
        episodeTotal = "Trọn Bộ",
        time = "108 phút",
        country = "Nhật Bản",
        categories = "Anime lẻ, Phim chiếu rạp, Sci-Fi, Comedy, Adventure"
    ),
    FilmInfo(
        id = "BCmtnk",
        name = "Black Clover: Mahou Tei no Ken",
        description = "Movie của Black Clover nè!",
        trailer = "PrgxJ1_sUcs",
        poster = "https://cdn.myanimelist.net/images/anime/1499/131676l.jpg",
        yearRelease = 2023,
        episodeTotal = "Trọn bộ",
        time = "113 phút",
        country = "Nhật Bản",
        categories = "Anime lẻ, Shounen, Fantasy, Comedy, Magic, Action"
    ),
    FilmInfo(
        id = "TGDQctvt",
        name = "Thanh Gươm Diệt Quỷ: Chuyến Tàu Vô Tận",
        description = "Kamado Tanjiro và những người bạn từ quân đoàn Diệt quỷ đi cùng với Viêm trụ Rengoku Kyōjurō, để điều tra một loạt những vụ mất tích bí ẩn xảy ra bên trong một chuyến tàu. Họ không biết rằng Enmu, Hạ huyền Nhất thuộc Thập nhị Nguyệt quỷ, cũng ở trên tàu và đã sắp đặt sẵn một cái bẫy. Sau khi lên chuyến tàu mang tên \"Vô Tận\" để điều tra về các vụ mất tích, Tanjirō cùng những người bạn của và mình gặp Viêm Trụ Rengoku Kyōjurō. Tanjirō đã gặp Viêm Trụ để hỏi về Điệu Múa Của Hoả Thần (Hoả Thần Thần Lạc) của cha mình. Nhưng cậu chỉ nhận được câu trả lời từ Viêm Trụ là: \"Anh chẳng biết gì hết! Hãy trở thành người kế tục của anh! Anh sẽ dạy cậu thật tốt!\". Tuy nhiên, khi người soát vé bấm vé, Huyết Quỷ Thuật từ con quỷ Enmu đã được kích hoạt và họ bị chìm vào giấc mộng. Để giải thoát cho mình, họ cần phải đốt tấm vé tàu hoặc tự sát. Những người hành khách bị mất tích đã theo lời con quỷ xâm nhập vào giấc mơ của Tanjirō và bạn bè của cậu để phá hủy lõi linh hồn và giúp con quỷ giết họ dễ hơn. Họ làm vậy chỉ để nhận được \"những giấc mộng đẹp\". Tanjirō mơ về cuộc sống của gia đình cậu trước khi bị Muzan sát hại, Zenitsu mơ về sự hạnh phúc của mình và Nezuko, Inosuke mơ về đội thám hiểm hang động còn Viêm Trụ thì mơ về cuộc nói chện giữa anh và cha mình sau khi trở thành trụ cột. Tanjirō đã tự thoát khỏi giấc mơ bằng cách tự sát, Zenitsu và Inosuke thì có ý chí mạnh nên bản thể trong mơ của hai cậu có thể ở trong vùng vô thức nên những người kia không thể phá hủy lõi được, Viêm Trụ thì đã ngăn cản cô gái giết mình bằng cách khống chế cô gái ngoài đời thực bằng bản năng. Nezuko đã cứu họ khỏi giấc mơ bằng cách đốt các tấm vé tàu. Sau khi thoát khỏi giấc mơ, Tanjirō lên nóc tàu và tìm thấy Enmu. Cậu đã chém cổ nhưng vì hắn đã hợp thể với đoàn tàu nên cậu phải chém cổ hắn ở toa đầu tàu để tiêu diệt hắn. Tanjirō và Inosuke có nhiệm vụ tiêu diệt Enmu. Viêm Trụ, Nezuko và Zenitsu thì bảo vệ hành khách. Khi tới toa đầu tàu, Inosuke đã chém phần sàn để lộ ra cổ của con quỷ. Tên lái tàu vì muốn có giấc mộng đẹp nên đã lấy kim đâm vào bụng Tanjirō. Con quỷ đã làm Tanjirō chìm vào giấc mộng nhiều lần và suýt tử ngoài đời thực nhưng Inosuke đã kịp ngăn cậu lại. Cuối cùng Tanjirō đã tiêu diệt con quỷ bằng cách sử dụng \"Thiên Thanh\", một điệu trong \"Hoả Thần Thần Lạc\" và chém đứt cổ Hạ huyền Nhất Enmu. Cơn ác mộng kết thúc tại đây. Viêm Trụ Rengoku Kyōjurō đã chỉ cậu cách cầm máu bằng hơi thở nhưng cậu không thể di chuyển được. Tuy nhiên, sau một cơn ác mộng thì lại có cơn ác mộng khác còn kinh khủng hơn kéo tới. Đó là sự xuất hiện của Thượng huyền Tam Akaza. Rengoku Kyōjurō đã bảo vệ Tanjirō và chiến đấu với Thượng huyền Tam. Akaza đã nhiều lần mời gọi Viêm Trụ trở thành quỷ để anh được bất tử, trở nên mạnh hơn và có thể chiến đấu với hắn mãi mãi. Nhưng Kyōjurō đã trả lời: \"Già đi và chết đi mới là vẻ đẹp của sinh vật phù du mang tên con người. Bởi vì họ sẽ già đi, bởi vì họ sẽ chết đi, nên họ mới đẹp đẽ và quý giá.\". Trận chiến đã kết thúc khi Akaza giết Kyōjurō bằng chiêu thức \"Phá hoạt sát: Diệt thức\" và đấm xuyên bụng anh. Rengoku vẫn có giữ tay hắn trong bụng để kìm chân hắn đến khi trời sáng. Lúc trời bắt đầu bừng sáng thì Akaza tự cắt tay mình để bỏ chạy. Trong lúc hắn chạy, Tanjirō đã ném kiếm xuyên đầu hắn và hét: \"Anh Rengoku mới là người chiến thắng!\". Điều này đã khiến Akaza rất tức giận. Trước khi chết, Viêm Trụ đã nói với Tanjirō vài điều về điệu múa của cha cậu. Câu đặc biệt nhất của anh chính là: \"Hãy giữ con tim mình rực lửa!\". Bộ phim kết thúc với phân cảnh chúa công Ubuyashiki nói: \"Sớm thôi, ta sẽ gặp lại cậu và mọi người ở đó.\" và cảnh Tanjirō khóc khi lặp lại tên Viêm Trụ Rengoku Kyōjurō.\n",
        trailer = "ATJYac_dORw",
        poster = "https://ecdn.game4v.com/g4v-content/uploads/2020/04/Kimetsu-no-Yaiba-the-Movie-trailer-1-game4v.jpg",
        yearRelease = 2020,
        episodeTotal = "Trọn bộ",
        time = "1 giờ 57 phút",
        country = "Nhật Bản",
        categories = "Anime lẻ, Shounen, Supernatural, Demons, Historical, Action"
    ),
    FilmInfo(
        id = "Vdmtt",
        name = "Venom: Đối Mặt Tử Thù",
        description = "Venom: Đối Mặt Tử Thù hứa hẹn trận chiến khốc liệt nhất giữa Venom và kẻ thù truyền kiếp, Carnage",
        trailer = "-FmWuCgJmxo",
        poster = "https://images2.thanhnien.vn/uploaded/sangdt/2021_05_11/venomposter_TDET.jpg?width=500",
        yearRelease = 2021,
        episodeTotal = "Trọn bộ",
        time = "97 phút",
        country = "Âu - Mỹ",
        categories = "Phiêu Lưu - Hành Động, Khoa Học - Viễn Tưởng, Phim Chiếu Rạp"
    ),
    FilmInfo(
        id = "Bg",
        name = "Bố già",
        description = "Trấn Thành vào vai ông Tư – một tài xế xe ôm quần quật làm việc qua ngày để chăm lo cho gia đình của mình. Mặc dù khá bảo thủ, nóng nảy, thường xuyên quát tháo nhưng thực chất ông Tư lại là một người rất giàu lòng yêu thương – không chỉ với người thân mà còn có hàng xóm, bạn bè xung quanh và thậm chí là cả những người xa lạ.",
        trailer = "jluSu8Rw6YE",
        poster = "https://cdn.tuoitrethudo.com.vn/stores/news_dataimages/ngokhucquanganh/062021/14/08/2509_Poster_1.jpg?rt=20210614082516",
        yearRelease = 2021,
        episodeTotal = "Trọn bộ",
        time = "128 phút",
        country = "Việt Nam",
        categories = "Hài, Gia Đình"
    ),
    FilmInfo(
        id = "TQ",
        name = "Trạng Quỳnh",
        description = "Trạng Quỳnh kể về hành trình giải cứu người thầy của Trạng Quỳnh cùng \"đồng bọn\" là cô gái xinh đẹp Điềm và anh bạn tinh quái Xẩm. Mặc dù chỉ là những người \"thấp cổ bé họng\" trong xã hội nhưng họ không hề ngần ngại đối đầu với bọn cường hào ác bá, tiêu biểu là Trịnh Bá - công tử con quan lớn và Ả Liễu - người phụ nữ vô cùng mưu mô, xảo quyệt.",
        trailer = "ikXMNocYUeY",
        poster = "https://m.media-amazon.com/images/M/MV5BNzI0ZWFhYTctODQ1ZC00ZGM2LThhOTgtMmM5OGRiMDNlZGIxXkEyXkFqcGdeQXVyNDc0Njc1NTY@._V1_.jpg",
        yearRelease = 2019,
        episodeTotal = "Trọn Bộ",
        time = "95 phút",
        country = "Việt Nam",
        categories = "Hài Hước, Phim Chiếu Rạp"
    ),
    FilmInfo(
        id = "Qnqnh10",
        name = "Quá Nhanh Quá Nguy Hiểm 10",
        description = "Quá Nhanh Quá Nguy Hiểm 10 kể về Dom Toretto và gia đình của anh ấy bị trở thành mục tiêu của người con trai đầy thù hận của ông trùm ma túy Hernan Reyes.",
        trailer = "qxUFonjqQj8",
        poster = "https://m.media-amazon.com/images/M/MV5BNzZmOTU1ZTEtYzVhNi00NzQxLWI5ZjAtNWNhNjEwY2E3YmZjXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg",
        yearRelease = 2023,
        episodeTotal = "Trọn bộ",
        time = "141 phút",
        country = "Âu - Mỹ",
        categories = "Phiêu Lưu - Hành Động, Phim Chiếu Rạp"
    ),
    FilmInfo(
        id = "Adccn",
        name = "Avatar: Dòng Chảy Của Nước",
        description = "Lấy bối cảnh 10 năm sau những sự kiện xảy ra ở phần đầu tiên. Phim kể câu chuyện về gia đình mới của Jake Sully (Sam Worthington thủ vai) cùng những rắc rối theo sau và bi kịch họ phải chịu đựng khi phe loài người xâm lược hành tinh Pandora.",
        trailer = "d9MyW72ELq0",
        poster = "https://i.etsystatic.com/34708433/r/il/15fb83/4498029997/il_570xN.4498029997_cjib.jpg",
        yearRelease = 2022,
        episodeTotal = "Trọn bộ",
        time = "192 phút",
        country = "Âu - Mỹ",
        categories = "Tâm Lý - Tình Cảm, Khoa Học - Viễn Tưởng, Phim Chiếu Rạp"
    ),
    FilmInfo(
        id = "NbN",
        name = "Nhà Bà Nữ",
        description = "Xoay quanh gia đình của bà Nữ (nghệ sĩ Lê Giang đảm nhận) - người làm nghề bán bánh canh. Truyện phim khắc họa mối quan hệ phức tạp, đa chiều xảy ra với các thành viên trong gia đình. Câu tagline (thông điệp) chính \"Mỗi gia đình đều có những bí mật\" chứa nhiều ẩn ý về nội dung bộ phim muốn gửi gắm.",
        trailer = "IkaP0KJWTsQ",
        poster = "https://nguoinoitieng.tv/images/nnt/106/0/bjpo.jpg",
        yearRelease = 2023,
        episodeTotal = "Trọn bộ",
        time = "102 phút",
        country = "Việt Nam",
        categories = "Tâm Lý - Tình Cảm, Hài Hước, Gia Đình - Học Đường, Phim Chiếu Rạp"
    ),
    FilmInfo(
        id = "NTC",
        name = "Nàng Tiên Cá",
        description = "Câu chuyện được yêu thích về Ariel - một nàng tiên cá trẻ xinh đẹp và mạnh mẽ với khát khao phiêu lưu. Ariel là con gái út của Vua Triton và cũng là người ngang ngạnh nhất, nàng khao khát khám phá về thế giới bên kia đại dương. Trong một lần ghé thăm đất liền, nàng đã phải lòng Hoàng tử Eric bảnh bao. Trong khi tiên cá bị cấm tiếp xúc với con người, Ariel đã làm theo trái tim mình. Nàng đã thỏa thuận với phù thủy biển Ursula hung ác để cơ hội sống cuộc sống trên đất liền. Nhưng cuối cùng việc này lại đe dọa tới mạng sống của Ariel và vương miện của cha nàng.",
        trailer = "kpGo2_d3oYE",
        poster = "https://upload.wikimedia.org/wikipedia/vi/9/97/N%C3%A0ng_ti%C3%AAn_c%C3%A1_2023_-_Vietnam_poster.jpg",
        yearRelease = 2023,
        episodeTotal = "Trọn bộ",
        time = "135 phút",
        country = "Âu - Mỹ",
        categories = "Phiêu Lưu - Hành Động, Phim Chiếu Rạp, Bí Ẩn - Siêu Nhiên"
    ),
    FilmInfo(
        id = "CMB3nst",
        name = "Chị Mười Ba: 3 Ngày Sinh Tử",
        description = "Câu chuyện kể về Chị Mười Ba đưa Kẽm Gai, tay đàn em cũ vừa mới ra tù, lên Đà Lạt để làm việc cho tiệm Gara của mình. Tại đây, Kẽm Gai dường như đã tìm lại được sự bình yên và hạnh phúc. Tuy vậy, anh sớm trở thành đối tượng bị tình nghi giết hại Đức Mát - em trai của đại ca Thắng Khùng khét tiếng đất Đà Lạt - và phải trốn chạy. Với thời hạn chỉ ba ngày, liệu Chị Mười Ba có minh oan được cho Kẽm Gai và cứu anh em An Cư Nghĩa Đoàn khỏi mối đe doạ mới? Liệu có bí mật khủng khiếp nào khác đang được che giấu? Tất cả sẽ được hé lộ trong phim.",
        trailer = "Ncwkodt5dA4",
        poster = "https://i.imgur.com/dmhE6qm.jpg",
        yearRelease = 2021,
        episodeTotal = "Trọn bộ",
        time = "96 phút",
        country = "Việt Nam",
        categories = "Phiêu Lưu - Hành Động, Hài Hước, Phim Chiếu Rạp"
    ),
    FilmInfo(
        id = "Tgnhd",
        name = "Tiếng Gọi Nơi Hoang Dã",
        description = "Dựa trên tiểu thuyết huyền thoại của nhiều thế hệ của nhà văn Jack London - chính thức xuất hiện trên màn ảnh rộng với những thước phim chân thực nhất. Bộ phim xoay quanh câu chuyện về chú chó Buck trong chuyến hành trình tìm vàng đầy cam go khi bị bắt rời khỏi cuộc sống bình yên và tới vùng Alaska đầy lạnh giá. Từ đây, bản năng của Buck tại môi trường thiên nhiên hoang dã được đánh thức, liệu chuyến phiêu lưu với nhiều khó khăn sẽ kết thúc như thế nào?",
        trailer = "ASIJBR4zJ5M",
        poster = "https://assets2.htv.com.vn/Images/TAP%20CHI%20HTV/HAU%20TRUONG%20TRUYEN%20HINH/DUONG%202020/CALL%20OF%20WILD/1%20tieng%20goi%20poster.jpg",
        yearRelease = 2020,
        episodeTotal = "Trọn bộ",
        time = "100 phút",
        country = "Âu - Mỹ",
        categories = "Phiêu Lưu - Hành Động, Gia Đình - Học Đường, Phim Chiếu Rạp"
    )
)

