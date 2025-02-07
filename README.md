# Holland Quiz

## Tổng Quan
Holland Quiz là ứng dụng Android giúp người dùng đánh giá kiểu tính cách của mình dựa trên lý thuyết 6 nhóm tính cách của John Holland. Ứng dụng sẽ tính toán điểm số và đưa ra các gợi ý nghề nghiệp phù hợp với nhóm tính cách cao nhất của người dùng. Ứng dụng có giao diện hiện đại, kết quả bài kiểm tra chi tiết và các gợi ý nghề nghiệp cụ thể.

## Các Tính Năng

### 1. Tính Năng Chính
- Chức Năng Kiểm Tra: Bài kiểm tra có các câu hỏi được phân loại thành 6 nhóm tính cách:
    - Thực tế (Realistic)
    - Nghiên cứu (Investigative)
    - Nghệ thuật (Artistic)
    - Xã hội (Social)
    - Thuyết phục (Enterprising)
    - Quy chuẩn (Conventional)
- Điều Hướng: Ứng dụng cung cấp các nút điều hướng cho người dùng:
    - Quay lại câu hỏi trước
    - Tiến hành câu hỏi tiếp theo
    - Kết thúc bài kiểm tra và xem kết quả
- Thanh Tiến Trình: Hiển thị tỷ lệ phần trăm hoàn thành bài kiểm tra.

### 2. Kết Quả Bài Kiểm Tra
- Nhóm Tính Cách: Ứng dụng hiển thị nhóm tính cách có điểm số cao nhất và cung cấp mô tả chi tiết về nhóm đó.
- Gợi Ý Nghề Nghiệp: Dựa trên nhóm tính cách, người dùng nhận được các gợi ý nghề nghiệp phù hợp với sở thích và năng lực.
- Thống Kê: Biểu đồ cột hiển thị điểm số của từng nhóm tính cách với màu sắc mã hóa cho các nhóm nghề nghiệp.
- Phân Tích Nâng Cao: Cung cấp cái nhìn sâu sắc về điểm mạnh, điểm yếu của người dùng cùng các gợi ý nghề nghiệp chi tiết.

### 3. Nâng Cấp Giao Diện
- Màn Hình Chính: Giao diện hiện đại với nền động và hiệu ứng chuyển đổi mượt mà, các nút chức năng được thiết kế dưới dạng thẻ.
- Màn Hình Câu Hỏi: Câu hỏi được hiển thị trong các thẻ với bố cục rõ ràng và sử dụng font chữ Poppins.
- Thư Viện Nghề Nghiệp: Hiển thị danh sách 6 nhóm nghề nghiệp, mỗi nhóm có màu sắc đặc trưng cùng mô tả, yêu cầu kỹ năng, mức lương và xu hướng nghề nghiệp.

### 4. Tính Năng Dự Kiến
- Hỗ Trợ Đa Ngôn Ngữ: Ứng dụng sẽ hỗ trợ cả tiếng Anh và tiếng Việt, tự động chuyển đổi ngôn ngữ theo cài đặt của thiết bị.
- Chế Độ Tối (Dark Mode): Người dùng có thể chuyển đổi giữa chế độ sáng và tối.
- Tối Ưu Trải Nghiệm Người Dùng: Thêm nút quay lại trên tất cả các màn hình, hỗ trợ cuộn màn hình kết quả và thư viện nghề nghiệp để hiển thị thông tin dài.

### 5. Công Nghệ Sử Dụng
- Ngôn Ngữ Lập Trình: Kotlin
- Framework: Android SDK
- Thư Viện:
  - MPAndroidChart: Hiển thị biểu đồ thống kê.
  - View Binding: Quản lý giao diện hiệu quả.
  - JSON Parsing: Đọc dữ liệu câu hỏi và nghề nghiệp từ các tệp JSON.

## Chuẩn Bị Dữ Liệu

### Tổng Quan Về Dữ Liệu
Dữ liệu đóng vai trò cốt lõi trong việc xây dựng ứng dụng Holland Quiz. Dữ liệu định nghĩa câu hỏi, nhóm nghề nghiệp và kết quả phân tích. Việc chuẩn bị dữ liệu bao gồm các bước thu thập, xử lý, tổ chức và định dạng để đảm bảo tính chính xác và hiệu quả khi tích hợp vào ứng dụng.

### Cấu Trúc Dữ Liệu

#### 1. Nhóm Nghề Nghiệp
Dữ liệu được chia thành 6 nhóm nghề nghiệp theo lý thuyết của John Holland:
- Thực tế (Realistic)
- Nghiên cứu (Investigative)
- Nghệ thuật (Artistic)
- Xã hội (Social)
- Thuyết phục (Enterprising)
- Quy chuẩn (Conventional)

#### 2. Dữ Liệu Cần Thiết Cho Mỗi Nhóm Nghề Nghiệp
Mỗi nhóm nghề nghiệp cần chứa các thông tin sau:
- Tên Nhóm: (ví dụ: Thực tế, Nghệ thuật)
- Mô Tả Ngắn: Giải thích đặc điểm chung của nhóm.
- Danh Sách Nghề Nghiệp Liên Quan: Tối thiểu 5, tối đa 10 nghề.
- Yêu Cầu Kỹ Năng: Các kỹ năng cần thiết cho nhóm nghề.
- Xu Hướng Phát Triển: Cơ hội và triển vọng của các nghề trong nhóm.

#### 3. Thu Thập Dữ Liệu
- Xác Định Thông Tin Cần Thu Thập: Thu thập thông tin về các nhóm nghề nghiệp và các nghề tiêu biểu.
- Nghiên Cứu & Tổng Hợp: Sử dụng nguồn dữ liệu đáng tin cậy để tổng hợp thông tin.
- Kiểm Chứng & Chọn Lọc: Đối chiếu và chọn lọc dữ liệu từ nhiều nguồn để đảm bảo tính chính xác.

### Ví Dụ Dữ Liệu (Định Dạng JSON)
```json
{
  "career_groups": [
    {
      "name": "Thực tế",
      "description": "Các nghề mang tính thực tiễn, hành động",
      "careers": ["Kỹ sư", "Thợ điện", "Phi công", "Kỹ thuật viên", "Nông dân"],
      "required_skills": ["Giải quyết vấn đề", "Kỹ năng kỹ thuật", "Sức khỏe thể chất"],
      "career_trends": "Công nghiệp xây dựng và kỹ thuật có nhu cầu lớn về lao động có tay nghề."
    },
    {
      "name": "Nghiên cứu",
      "description": "Nghề nghiệp tập trung vào nghiên cứu và phân tích",
      "careers": ["Nhà khoa học", "Nhà nghiên cứu", "Chuyên viên phân tích dữ liệu", "Nhà vật lý", "Nhà toán học"],
      "required_skills": ["Suy nghĩ phân tích", "Nghiên cứu", "Giải quyết vấn đề"],
      "career_trends": "Tăng trưởng nhu cầu trong các ngành khoa học dữ liệu và nghiên cứu môi trường."
    }
  ]
}


