### Kinh nghiệm Unit Test
 - Cách tạo file test tương ứng vs class: di chuột vào tên class => alt + insert => create test
 - assertTrue, assertFalse, assertNull, assertArrayEquals
 - @DisplayName("Đặt tên tùy ý =))")
 - @Disabled: vô hiệu hóa 1 test, 1 bộ test
 - Test throw 1 ngoại lệ: assertThrows + Executable
 - assertAll khi muốn assert nhiều
 - Test nhiều bộ data mà ko cần tạo nhiều test case khác nhau: Dùng  @ParameterizedTest +  @CsvSource || @CsvFileSource || @ValueSource
 - Test lặp lại ( sử dụng để test trường hợp nhiều luồng thì phải): Thay vì @Test => @RepeatedTest(số_lần_muốn_test)
 - Test performance (muốn 1 method chạy trong 1 time nhất định): assertTimeout truyền vào 2 đối số là time và executable
 - Khi muốn bỏ qua test case khi thỏa mãn 1 ĐK nào đó (ví dụ ở môi trường PROD mới test, môi trường DEV ko test): assumeTrue(Điều_kiện_ko_test);
### Cấu trúc 1 file unit test
 Trong 1 class có nhiều phương thức => 1 phương thức có nhiều test case => Gom các test case này và chỉ ra chúng thuộc về phương thức nào = cách như sau:
  - B1: Tạo nested class bên trong class test, tên tùy ý, mà thường là tạo tên giống tên phương thức luôn
  - B2: Đánh dấu class vừa tạo bằng annotation @Nested
  - B3: Đưa các test case tương ứng vào class này
  => Chú ý khi tạo như vậy, mỗi class đều có thể sử dụng @BeforeEach @AfterEach nếu muốn.