# Bài 2: Tự kiểm tra Java Fundamentals

## 🧪 Kết quả lần đầu (không nhìn tài liệu): 3.35 / 10

| Câu | Điểm | Tự tin | Ghi chú |
|---|---|---|---|
| Q1. 8 kiểu primitive | 0.25 | không chắc | nhầm `varchar` (SQL), `string` (class); thiếu `byte`, `float` |
| Q2. Primitive và reference | 0.6 | không chắc | ghi reference "chứa đối tượng" ❌ |
| Q3. `==` và `equals` | 0.67 | **chắc chắn** ⚠️ | `Object.equals()` mặc định: ghi "false" ❌ |
| Q4. String pool | **1** ✅ | chắc chắn | đoán đúng cả `intern()` |
| Q5. Immutable | 0.33 | | output đúng, chưa nêu được định nghĩa và lý do |
| Q6. String / Builder / Buffer | 0 | | chưa học |
| Q7. Pass-by-value | 0 | **chắc chắn** ⚠️ | ghi `100 \| new`, đúng là `1 \| hello world` |
| Q8. Integer cache | 0.5 | **chắc chắn** ⚠️ | `p == q` (128) ghi true, đúng là `false` |
| Q9. `equals` và `hashCode` | 0 | | chưa học |
| Q10. `final` và immutable | 0 | | ghi "compile được", sai; hiểu ngược `final` |

⚠️ **3 chỗ chắc chắn nhưng sai** (Q3, Q7, Q8), cùng một gốc: chưa coi biến reference là ĐỊA CHỈ.

---

## 🔑 Ý quan trọng nhất: biến reference chứa ĐỊA CHỈ, không chứa object
```
int n = 1;                    StringBuilder sb = new StringBuilder("hello");
STACK: [ n | 1 ]              STACK: [ sb | #A1 ] ──► HEAP: #A1: "hello"
       chứa thẳng giá trị              chỉ chứa địa chỉ       object thật nằm ở đây
```
- Biến reference = **tờ giấy ghi địa chỉ nhà**, không phải ngôi nhà.
- Ý này giải thích được Q2, Q3, Q7, Q8, Q10.

---

## Lời giải từng câu

### Q1. 8 kiểu primitive
| Kiểu | Byte | Mặc định | | Kiểu | Byte | Mặc định |
|---|---|---|---|---|---|---|
| `byte` | 1 | `0` | | `float` | 4 | `0.0f` |
| `short` | 2 | `0` | | `double` | 8 | `0.0` |
| `int` | 4 | `0` | | `char` | 2 | `'\u0000'` |
| `long` | 8 | `0L` | | `boolean` | JVM tự quyết | `false` |
- Mẹo nhớ: số nguyên byte, short, int, long có kích thước **1, 2, 4, 8**. Số thực: float, double. Thêm char và boolean.
- `varchar` là kiểu của SQL. `String` là class (reference), không phải primitive.

### Q2. Primitive và reference
| | Primitive | Reference |
|---|---|---|
| Chứa gì | chính giá trị | **địa chỉ** của object |
| Nằm ở đâu | stack (biến cục bộ) | biến ở stack, **object ở heap** |
| Mặc định (field) | `0`, `0.0`, `false`, `'\u0000'` (không có `""`) | `null` |
| Có thể `null`? | không | có |

### Q3. `==` và `equals()`
- `==` với object: so sánh **địa chỉ**.
- `equals()`: so sánh **nội dung**, nhưng CHỈ KHI class đã override (String, Integer, HashMap có; StringBuilder không).
- `Object.equals()` mặc định: `return this == other;`, tức là so sánh địa chỉ, giống `==`. Không phải "luôn false".
- ⚠️ Lỗi 2 của Bài 1, **lần thứ 3**.

### Q4. String pool ✅
`(1) true  (2) false  (3) true  (4) true`
- `intern()` trả về object TRONG String pool có cùng nội dung, nên chính là object mà `a` trỏ tới.

### Q5. Immutable
- **Định nghĩa:** tạo ra rồi thì nội dung không bao giờ thay đổi. Mọi method "sửa" đều trả về object MỚI.
- **Lý do Java thiết kế như vậy:**
  1. String pool an toàn: nhiều biến dùng chung 1 object, nếu sửa được thì ảnh hưởng tất cả.
  2. Bảo mật: tên file, URL, mật khẩu truyền vào hàm không bị sửa lén.
  3. An toàn đa luồng: không ai sửa được, nên nhiều luồng cùng đọc không xung đột.
  4. Làm key HashMap an toàn: `hashCode` không đổi, tính 1 lần rồi lưu lại.
- Output `java`: `toUpperCase()` tạo String mới nhưng không gán lại.

### Q6. String / StringBuilder / StringBuffer
| | Mutable? | Thread-safe? | Dùng khi |
|---|---|---|---|
| `String` | ❌ | ✅ (nhờ immutable) | mặc định, giá trị không đổi |
| `StringBuilder` | ✅ | ❌ | nối chuỗi trong vòng lặp, 1 luồng (phổ biến nhất) |
| `StringBuffer` | ✅ | ✅ (`synchronized`, chậm hơn) | hiếm: nhiều luồng cùng sửa 1 chuỗi |

### Q7. Pass-by-value → `1 | hello world`
Java luôn **copy giá trị của biến** khi truyền vào hàm. Với reference, giá trị đó là **địa chỉ**.
- `change(n)`: hàm nhận copy của số 1, sửa bản copy, nên `n` vẫn là 1.
- `append(sb)`: hàm nhận copy của địa chỉ, đi tới đúng object đó và **sửa đồ trong nhà**, nên bên ngoài thấy `"hello world"`.
- `reassign(sb)`: hàm nhận copy của địa chỉ, rồi **ghi đè tờ giấy copy** bằng địa chỉ mới. Tờ giấy gốc bên ngoài không đổi.
- 👉 **Sửa đồ trong nhà** (gọi method trên object) thì bên ngoài thấy. **Đổi địa chỉ** (gán `=`) trong hàm thì bên ngoài không thấy.

### Q8. Integer cache và unboxing
- `Integer` là class (reference), nên `==` so sánh **địa chỉ**.
- Java có sẵn pool các object `Integer` từ **-128 đến 127**.
  - `x == y` (127): cùng object trong pool, nên `true`.
  - `p == q` (128): ngoài pool, mỗi lần là object mới, nên `false`.
- `int k = z;` (z = null): Java tự gọi `z.intValue()` (**unboxing**). Biến trước dấu chấm là null, nên 💥 `NullPointerException`.
- 👉 So sánh `Integer`, `Long`...: **luôn dùng `equals()`**.

### Q9. `equals()` và `hashCode()`
- **Quy ước:** 2 object `equals()` nhau thì BẮT BUỘC cùng `hashCode()`.
- HashMap là tủ nhiều ngăn: `hashCode()` chọn ngăn, `equals()` tìm đúng món trong ngăn.
- Chỉ override `equals()` thì 2 object "bằng nhau" rơi vào 2 ngăn khác nhau:
  - `HashSet` chứa bản trùng.
  - `map.get(key)` trả về `null` dù key "bằng" key đã cất.

### Q10. `final` và immutable → KHÔNG compile được
- Lỗi ở `list = new ArrayList<>();`: `cannot assign a value to final variable list`.
- `list.add("a")` vẫn chạy được.

| | `final` | immutable |
|---|---|---|
| Áp dụng cho | **biến** (tờ giấy địa chỉ) | **object** (ngôi nhà) |
| Ý nghĩa | không được ghi địa chỉ khác | không được sửa đồ trong nhà |
| Ví dụ | `final StringBuilder sb` vẫn `append` được | `String` không có method nào sửa nội dung |

---

## ⚠️ Lỗi đã mắc
1. **Nghĩ biến reference "chứa đối tượng".** Thật ra nó chứa ĐỊA CHỈ. Đây là gốc của Q2, Q3, Q7, Q8, Q10.
2. **`Object.equals()` mặc định = `==`** (so sánh địa chỉ), không phải "luôn false". Lặp lại lỗi 2 của Bài 1 (lần 3).
3. **Truyền object vào hàm rồi gán `=` trong hàm, tưởng bên ngoài cũng đổi.** Chỉ gọi method sửa object mới thấy được từ bên ngoài.
4. **Dùng `==` cho `Integer`.** Chỉ "may mắn" đúng trong khoảng -128..127.
5. **Hiểu ngược `final`.** `final` khóa BIẾN (không gán lại), không khóa OBJECT.
6. **Nhầm kiến thức SQL với Java** (`varchar`) và coi `String` là primitive.

---

## ✅ Kiểm chứng bằng `Verify.java` (tự điền sau khi chạy)
| Câu | Kết quả chạy thật | Khớp lời giải? |
|---|---|---|
| Q4 | | |
| Q5 | | |
| Q7 | | |
| Q8 | | |
| Q10 (lesson02-fundamentals/Verify.java:80: error: cannot assign a value to final variable list
        list = new ArrayList<>(); // Uncommenting this line will cause a compile-time error: "cannot assign a value to final variable list"
        ^
1 error
error: compilation failed) | | |

## 📝 Tổng kết Bài 2 (tự viết bằng lời của mình)
1. Ý quan trọng nhất mình rút ra:
   -
2. Câu nào mình vẫn chưa hiểu sau khi chạy code:
   -
