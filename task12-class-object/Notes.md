# Task #12: Class là gì; phân biệt class và object

## 🔁 Khởi động (ghi ✅🤔❌)
- a (1, "Học Java", "") ✅
- b (2, "Đi chợ", "") ✅
- a (2, "Đi chợ", "") ✅
- b là null ✅
- Đi Chợ ✅

## 🔮 Đoán output (ghi ✅🤔❌ từng câu)
- Đoán 1: null 0 ✅
- Đoán 2: false ✅
- Đoán 3: Miu ✅

## ✏️ Bài 1: class hay object?
| Thứ | Class hay object? | Vì sao |
|---|---|---|
| `Student` | Class | mô tả về học sinh |
| `new Student()` | object | tạo mới 1 thông tin về 1 học sinh |
| `s1` | biến reference | vì nó không mô tả gì cả |
| Bản thiết kế ngôi nhà | class | vì chỉ là bản thiết kế |
| Ngôi nhà số 12 Lê Lợi | object | chỉ ra ngôi nhà đó là ngôi nhà nào |

## ✏️ Bài 2: kết quả chạy `ClassObjectDemo`
(dán output vào đây)
Alice, 20
Bob, 22
Tôi là Alice, 20 tuổi
Tôi là Bob, 22 tuổi
### Edge case: 
- Tôi là null, 0 tuổi
## 🧪 Kiểm tra hiểu bài
1. 2 ✅
2. lỗi vì s chưa được khởi tạo dối tượng ✅
3. vì class chỉ là khung, việc tạo nên 1 đối tượng sẽ lưu nó ở một vùng nhớ khác nhau ✅
4. An ✅
5. 2 ✅
## 📝 Tổng kết (bằng lời của mình)
- **Class** là bản thiết kế (khuôn): khai báo object sẽ có field gì (`name`, `age`) và làm được gì (`introduce()`). Chỉ có 1 class `Student`.
- **Object** là thứ thật do `new` tạo ra trên heap. Mỗi lần `new` là thêm 1 object, có vùng nhớ và field riêng (Alice và Bob khác nhau dù chung class).
- **Biến reference** (`s1`, `a`, `b`) KHÔNG phải object: nó là tờ giấy ghi địa chỉ object. `b = a` chỉ chép địa chỉ → vẫn 1 object; đếm object = đếm số lần `new`.
- Field chưa gán có giá trị mặc định: `String` → `null`, `int` → `0` (nên ra "Tôi là null, 0 tuổi").
- Biến đang `null` mà gọi `.method()` → `NullPointerException`. Hôm nay làm đúng câu `null` 3/3 lần liên tiếp nhờ kẻ bảng từng dòng.
- Chỗ mình còn sai: lúc đầu ghi `s1` là object.

> 📝 Tổng kết do Claude viết hộ (học viên nhờ): đọc lại và viết lại 1–2 câu bằng lời của mình.

