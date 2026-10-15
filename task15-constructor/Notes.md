# Task #15: Constructor, constructor overloading & this

## 🔁 Khởi động (VẼ sơ đồ / kẻ bảng trước, rồi mới ghi ✅🤔❌)
- K1:
STACK (biến)            HEAP (object)
k ──────────────┐
                ├──►  #1 Book { "K", "?", 500 }
b ──────────────┐
                ├──►  #2 Book { "Mới", 999 }
K 500 ✅
- K2:
STACK (biến)            HEAP (object)
s[2] ──────────────┐
                   ├──►  #1 Book { "P", 1 }
s[1] ──────────────┘
                   ├──►  #2 Book { "Q", 2 }
s[0] ──────────────┐
                   ├──►  #3 Book { "Q", 2 }
Mảng S có 2 object khác nhau. cả đoạn code tạo 4 object Book 🤔

## 🔮 Đoán output (ghi ✅🤔❌)
- Đoán 1: null ✅
- Đoán 2: Lỗi vì không truyền tham số 🤔
- Đoán 3: 0 🤔

## ✏️ Bài 1–3: kết quả chạy `ConstructorDemo`
9/9 PASS
### Bài 2: Cách A và Cách B (dán cả 2, nói cách nào tốt hơn và vì sao)
- Cách A:
Book(String title, int pages) {
    this.title = title;
    this.author = "Không rõ";
    this.pages = Math.max(0, pages);
    this.currentPage = 0;
}
- Cách B:
Book(String title, int pages) {
    this(title, "Không rõ", pages);
}

Khác nhau là cách B viết nhanh
A phải sửa 2 chỗ, B sửa 1 chỗ
## 🧪 Kiểm tra hiểu bài (ghi ✅🤔❌)
1. 0 ✅
2. ✅
    Được
    Book (String title, String name)
3. ✅
STACK (biến)            HEAP (object)
a ──────────────┐
                ├──►  #1 Book { "Z", "x", 100 }
b ──────────────┘
                ├──►  #2 Book { "A", "x", 100 }
c ──────────────┘
Z A true false
4. cả 4 đáp án đều lỗi biên dịch ✅
5. ✅
Bước    Đang ở đâu    Làm gì                        In ra
1       Lamp()       This(40) nhảy qua lamp(40)
2       Lamp(40)     this.w = 40*2                  2
3       Lamp()       quay về Lamp()                 1
4       main         println(x.w)                   80   

## 📝 Tổng kết (Claude viết hộ theo yêu cầu, 2026-10-15; ý 1 dựa trên câu của học viên)
1. **`this.x = x` khác `x = x`:** trong constructor, tên `x` đứng một mình là **tham số** (che mất field cùng tên). `this.x` mới là **field của object đang được tạo**. Viết `x = x` là gán tham số cho chính nó → field giữ giá trị mặc định (`0`/`null`).
   ```java
   class Box { int size; Box(int size) { size = size * 2; } }      // new Box(5).size == 0
   class Box2 { int size; Box2(int size) { this.size = size * 2; } } // new Box2(5).size == 10
   ```
2. **Java chọn constructor theo số lượng + kiểu + thứ tự tham số** (không theo tên tham số, không tự đổi `"5"` thành `5`). Không có cái nào khớp → **lỗi biên dịch**. Viết bất kỳ constructor nào thì Java **không tự tạo** `Book()` nữa.
   ```java
   new Book("A", 5);            // (String, int) ✅
   new Book(new Book("A", 1));  // (Book) ✅ — object bên trong tạo trước
   new Book("A", "5");          // ❌ không có (String, String)
   new Book(5, "A");            // ❌ sai thứ tự
   new Book();                  // ❌ không có constructor rỗng
   ```
3. **`this(...)` gọi constructor khác của cùng class** để luật kiểm tra (vd `pages` âm → 0) chỉ nằm **1 chỗ**; đổi luật chỉ sửa 1 nơi. Phải là **dòng đầu tiên**; chạy xong constructor được gọi rồi mới quay lại chạy tiếp.
   ```java
   Book(String title, int pages) { this(title, "Không rõ", pages); }
   // Lamp(): this(40) → Lamp(int) in "2 " → quay lại in "1 " → w = 80
   ```
4. **Copy constructor `new Book(a)`** tạo object **mới** (`== a` là false), khác `Book b = a` (cùng object).

## ✏️ Bài luyện ngắn để kiểm tra lại
- không chạy được vì new Cat(2, "Tom") sai vị trí và new Cat("Tom", "2") tham số thứ 2 phải là int chứ ko phải String