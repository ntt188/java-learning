# Task #13: Class Book với fields & methods

## 🔁 Khởi động (ghi ✅🤔❌)
```java
Book a = new Book();
Book b = a;
Book c = new Book();
a.title = "Java";
b = null;
System.out.println(a.title);
```
1. Có mấy **object**? Mấy **biến reference**?
- có 2 object, 3 biến ✅
2. Dòng cuối in gì?
a | b | c
java | java | null
java | null | null
kết quả: java ✅

## 🔮 Đoán output (ghi ✅🤔❌, kẻ bảng nếu cần)
- Đoán 1: 2 1 ✅
- Đoán 2: 16 ✅
- Đoán 3: 16 ✅

## ✏️ Bài 1–3: kết quả `java BookTest`
12/12 PASS

### Cách B cho `read()` (viết ra 1 cách khác Cách A)
void readMath(int n) {
    if (n > 0) {
        currentPage = Math.min(currentPage + n, pages);
    }
}
Tràn số: n lớn làm currentPage + n quay vòng thành số âm, nên Math.min chọn luôn số âm đó.
## 🧪 Kiểm tra hiểu bài (ghi ✅🤔❌)
1. 17 17 ✅
2. 0 9 ✅
3. vì chúng ta cần lấy đúng giá trị bên trong của đối tượng cần sử dụng ✅
4. 50 25 true ✅
5. 33 33.3333f ✅
## 📝 Tổng kết (bằng lời của mình)
- field là các đặc điểm của object, method là các hoạt động của class để sử dụng các đặc điểm của object
- nên * trước rồi mới / vì nguyên chia nguyên sẽ gặp trường hợp 5/100 là 0. 
vd: 50 / 300 * 100 = 0, còn 50 * 100 / 300 = 16
- currentPage + n với n rất lớn sẽ quay vòng thành số âm mà không báo lỗi. Cách chặn là so sánh n với pages - currentPage trước khi cộng
