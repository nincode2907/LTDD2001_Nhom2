This is a project practice by LTDD2001_Nhom2

## Let's start

First, the way to clone the project:
```bash
git clone https://github.com/nincode2907/LTDD2001_Nhom2.git
```

# Technical
<h2>Mô tả kỹ thuật và nghiệp vụ</h2>
<p>Dự án được xây dựng theo kiến trúc <strong>MVVM (Model-View-ViewModel)</strong>: là một mô hình kiến trúc phần mềm thường được sử dụng trong phát triển ứng dụng di động và web để tách biệt phần dữ liệu <strong>(Model)</strong>, giao diện người dùng <strong>(View)</strong> và cung cấp một lớp trung gian <strong>(ViewModel)</strong> để quản lý sự tương tác giữa chúng.</p>
<p>Giao diện người dùng sử dụng <strong>Jetpack Compose</strong>: là một framework phát triển giao diện người dùng (UI) được <strong>Google</strong> phát triển cho ứng dụng di động và máy tính bảng chạy trên nền tảng Android. Nó cung cấp một cách mới để xây dựng giao diện người dùng trong ứng dụng Android bằng cách sử dụng cú pháp dựa trên Kotlin, thay vì XML như trước đây.</p>
<p>Logic ứng dụng được xây dựng bằng ngôn ngữ Kotlin, sử dụng <strong>Coroutine</strong> để xử lý các tác vụ bất đồng bộ.</p>
<p>Database sử dụng <strong>Firebase</strong> là để quản lý cơ sở dữ liệu trong ứng dụng, nó cung cấp một loạt các dịch vụ đám mây cho phát triển ứng dụng, bao gồm:</p>
<ul>
  <li><strong>Firebase Cloud Firestore:</strong> Firestore cũng là một cơ sở dữ liệu thời gian thực nhưng cung cấp một cấu trúc dữ liệu linh hoạt hơn, được tổ chức thành collection và document.</li>
  <li><strong>Xác thực người dùng:</strong> Firebase cung cấp dịch vụ xác thực người dùng bằng cách sử dụng Firebase Authentication. Điều này cho phép bạn quản lý danh sách người dùng, xác thực bằng email, số điện thoại, mạng xã hội và hỗ trợ các xác thực tùy chỉnh.</li>
</ul>
<ol>
  <li>
    <h3>Các thư viện sử dụng trong dự án</h3>
    <ul>
      <li>
        <strong>Dagger Hilt:</strong> cung cấp một cách tiếp cận tiện lợi và dễ
        sử dụng cho việc triển khai Dependency Injection.
      </li>
      <li>
        <strong>ExoPlayer:</strong> Là player của app để phát nội dung âm thanh
        và video.
      </li>
      <li>
        <strong>Coil-kt:</strong> Để tải và hiển thị hình ảnh một cách hiệu quả.
      </li>
      <li>
        <strong>Moshi JSON:</strong> Cho việc parse và serialize dữ liệu JSON.
      </li>
      <li>
        <strong>Youtube Player:</strong> Để tích hợp xem video từ YouTube.
      </li>
      <li>
        <strong>Facebook SDK:</strong> Cho tích hợp chia sẻ và kết nối với mạng
        xã hội Facebook.
      </li>
      <li>
        <strong>Firebase Auth KTX:</strong> Để quản lý xác thực người dùng
        Firebase dễ dàng hơn.
      </li>
      <li>
        <strong>Kotlin SDK:</strong> Sử dụng ngôn ngữ lập trình Kotlin cho ứng
        dụng.
      </li>
    </ul>
  </li>
  <li>
    <h3>Chức năng chuyển hướng giữa các màn hình (navigation)</h3>
    <p>
      Sử dụng thư viện Navigation của Android để dễ dàng điều hướng giữa các
      phần khác nhau của ứng dụng và truyền dữ liệu giữa các màn hình bằng
      Parcelable.
    </p>
    <h3>Chức năng login</h3>
    <p>
      <strong>Chú ý</strong>: muốn sử dụng chức năng login cần phải build app
      phiên bản release và cần 1 số cấu hình nhỏ
    </p>
    <ul>
      <li>Bước 1: Chọn build variants</li>
      <li>
        Bước 2: Nhấn vào ô bên dưới Active build variant thay đổi debug thành
        release
      </li>
      <li>Bước 3: Chọn build trên thanh công cụ và chọn clean project</li>
      <li>
        Bước 4: Chọn Build trên thanh công cụ và chọn
        <strong>Build Bundle/APK</strong> và chọn build APK
      </li>
      <li>
        Bước 5: Vào build gradle(Module app) thay đổi đường dẫn của storeFile
        file bằng cách vào thư mục lưu file project vào thư mục keystore và lấy
        đường dẫn đến tệp key.jks xong thay đổi đường dẫn như hình dưới
      </li>
      <li>Bước 6: Sync Now project, run app và có thể đăng nhập</li>
    </ul>
  </li>
  <li>
    <h3>Chức năng play video</h3>
    <p>
      Sử dụng thư viện <strong>media 3 ExoPlayer</strong> để phát video theo
      đường link Uri đã lưu ở database. Tụi em đã xử lý lưu lại vòng đời của
      Video ở lớp ViewModel nên video sẽ không bị ảnh hưởng đến vòng đời của ứng
      dụng.
    </p>
  </li>
  <li>
    <h3>Chức năng thêm phim vào danh sách yêu thích</h3>
    <p>
      Sử dụng userId trong <strong>authentication</strong> của
      <strong>Firebase</strong> làm tham số để lưu trữ trên Firebase, mỗi khi
      người dùng login thì danh sách sẽ được lấy xuống dựa trên id hiện tại của
      người dùng trên ứng dụng. Chức năng này cần có người dùng để lưu lại thông
      tin nên nếu chưa đăng nhập thì sẽ trả về trang đăng nhập để tiếp tục.
    </p>
  </li>
  <li>
    <h3>Chức năng yêu cầu kết nối mạng</h3>
    <p>
      Sử dụng <strong>ConnectivityManager</strong> để quản lý và kiểm tra xem
      ứng dụng có kết nối được với wifi hay dữ liệu di động không. Nếu không,
      nội dung sẽ không được tải xuống và yêu cầu kết nối mạng để tiếp tục.
    </p>
  </li>
  <li>
    <h3>Chức năng gửi thông báo</h3>
    <p>
      Sử dụng <strong>POST_NOTIFICATIONS</strong> để xin cấp quyền người dùng,
      khi ứng dụng được cấp quyền cho phép thông báo, tạo
      <strong>channel</strong> và dùng
      <strong>NotificationCompat.Builder</strong> để tạo thông báo gửi cho người
      dùng.
    </p>
  </li>
</ol>

# User manual
<h2>Hướng dẫn sử dụng</h2>
  <ol>
    <li>
        <h3>Đăng nhập & đăng ký tài khoản</h3>
        <p>
            Có 2 phương thức đăng nhập chính là <strong>Google</strong> và <strong>Facebook</strong>
            <ul>
                <li>
                    <strong>Google</strong>: Bạn cần phải build app theo realease variable và thay đổi đường dẫn thì mới có thể đăng nhập.
                </li>
                <li>
                    <strong>Facebook</strong>: Cần phải thêm tài khoản và đăng ký tài khoản trên facebook là developer thì mới được phép.
                </li>
            </ul>
            Trước khi đăng nhập thì bạn chỉ dưới vai trò khách, sau đăng nhập thành công thì mới thực hiện các tác vụ cá nhân hóa.
        </p>
    </li>
    <li>
        <h3>Trang chủ</h3>
        <p>Bạn có thể tìm thấy các phim nổi bật và theo từng thể loại được sắp xếp, các hiệu ứng <strong>Carousel</strong> khiến ứng dụng sinh động hơn.</p>
    </li>
    <li>
        <h3>Tìm kiếm</h3>
        <p>Bạn có thể tìm kiếm phim bạn muốn coi bằng cách nhập tên phim bạn cần tìm trong thanh công cụ tìm kiếm hoặc có thể tìm kiếm phim theo thể loại phim bạn thích trong phần thể loại.</p>
        <p>Với chức năng tìm kiếm, sẽ tìm được phim theo tên mà người dùng muốn tìm kiếm, có thể tìm kiếm cả phim tiếng Việt lẫn tiếng Anh.</p>
        <p>Khi chọn vào từng thể loại sẽ hiển thị ra các phim có cùng thể loại, cách chia như vậy rất tốt cho người không biết xem phim nào hoặc đang lựa chọn phim phù hợp với bản thân.</p>
    </li>
    <li>
        <h3>Sắp ra mắt</h3>
        <p>Ở đây bạn sẽ biết được lịch ra mắt những bộ phim sắp tới, bộ lọc phim dựa trên thời gian phim ra mắt so với thời gian hiện tại.</p>
    </li>
    <li>
        <h3>Xếp hạng</h3>
        <p>Đây là nơi xếp hạng danh sách phim đạt lượt xem cao trong tháng này và bạn có thể bấm vào icon thêm để thêm vào danh sách yêu thích.</p>
    </li>
    <li>
        <h3>Danh sách phim yêu thích</h3>
        <p>Việc lưu trữ lại những phim yêu thích hoặc phim chưa có thời gian xem nên muốn lưu lại là vô cùng cần thiết, vì thế tính năng này ra đời. Để sử dụng được chức năng này, bắt buộc bạn phải đăng nhập trước, cơ sở dữ liệu của chúng tôi sẽ lưu lại thông tin này để bạn có thể sử dụng ở lần đăng nhập sau hoặc tài khoản khác.</p>
    </li>
    <li>
        <h3>Xem phim</h3>
        <p><strong>Khi click vào một bộ phim bất kỳ</strong>, bạn sẽ được chuyển đến trang xem phim, với thông tin cơ bản của phim như tên, thể loại, quốc gia, thời lượng, và năm ra mắt. Mô tả phim được rút gọn để không chiếm quá nhiều diện tích, bạn có thể mở rộng để xem thêm.</p>
        <p>Ứng dụng cung cấp tính năng <strong>lưu phim vào danh sách yêu thích</strong> (đăng nhập cần thiết), <strong>chia sẻ phim</strong>, và <strong>đánh dấu yêu thích</strong>.</p>
        <p>Bạn cũng có thể <strong>chia sẻ phim</strong> với bạn bè và người thân. Tab "Phim liên quan" hiển thị các lựa chọn tương tự để bạn có thể xem tiếp khi muốn tìm các phim có cùng chủ đề.</p>
        <p>Đặc biệt, tab <strong>"Trailer"</strong> cho phép bạn xem trước <strong>trailer trên YouTube</strong> ngay trên ứng dụng, giúp bạn quyết định xem phim dựa trên sự hứng thú từ trailer.</p>
        <p>Ứng dụng cung cấp một số tùy chỉnh cho phép bạn điều chỉnh theo ý mình. Cuối cùng, bạn có một player với các chức năng cơ bản như tạm dừng, play, tua về trước/sau, tùy chỉnh tốc độ và chất lượng âm thanh, và chế độ xem toàn màn hình.</p>
    </li>
</ol>

# Member and work assignment
  Nhóm 2
  <table>
    <thead>
        <tr>
            <th>STT</th>
            <th>Họ và tên</th>
            <th>MSSV</th>
            <th>Công việc</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1</td>
            <td>Hoàng Quảng Ngà</td>
            <td>2051010201</td>
            <td>Xử lý logic, đăng nhập/đăng ký, trang tìm kiếm, trang user, player, firebase.</td>
        </tr>
        <tr>
            <td>2</td>
            <td>Phạm Đức Mạnh</td>
            <td>2051012061</td>
            <td>Trang xếp hạng, thông báo, viết báo cáo tiếng Anh.</td>
        </tr>
        <tr>
            <td>3</td>
            <td>Bùi Văn Nin</td>
            <td>2051012083</td>
            <td>Trang chủ, trang xem phim, danh sách phim yêu thích, kết nối mạng.</td>
        </tr>
        <tr>
            <td>4</td>
            <td>Lê Thị Ngọc Thư</td>
            <td>2051010304</td>
            <td>Trang sắp ra mắt, chức năng chia sẻ, database, viết báo cáo tiếng Anh.</td>
        </tr>
        <tr>
            <td>5</td>
            <td>Mai Nhật Thanh Tâm</td>
            <td>2051012105</td>
            <td>Splash screen, database, viết báo cáo tiếng Việt.</td>
        </tr>
    </tbody>
</table>

