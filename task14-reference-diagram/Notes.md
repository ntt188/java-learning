# Task #14: Nhiều object từ 1 class; sơ đồ reference

## 🔁 Khởi động (ghi ✅🤔❌, kẻ bảng trước)
- K1: Học Java 2 0 ✅
- K2: 0 ✅
## 🔮 Đoán output (ghi ✅🤔❌)
- Đoán 1: Đã đổi ✅
- Đoán 2: Đã đổi ✅
- Đoán 3: 3 🤔
✅
STACK                          HEAP
main:    x ─────────────────►  #1 { title="Đã đổi" }
replace: b ──(trước: #1)──X
           └────────────────►  #2 { title="Sách mới" }   ← mất khi replace kết thúc

## ✏️ Bài 1: Vẽ sơ đồ (Stack | Heap)
STACK (biến)            HEAP (object)
r ──────────────┐
                ├──►  #1 Book { title="R" }
p ──────────────┐
                ├──►  #2 Book { title="Q" }
q = null

- Kết quả: p.title = 'Q', r.title = 'R'

## ✏️ Bài 2–3: kết quả chạy `ReferenceDemo`
- Bài 2:
    FAIL đếm khác nhau: mong đợi 3, thực tế 0
    FAIL 1 object lặp 3 lần: mong đợi 1, thực tế 0
    8/10 PASS
- Bài 3:
    10/10 PASS
### Cách B cho `findLongest()`
static Book findLongest(Book[] shelf) {
    int maxPages = -1;
    for (Book b : shelf) {
        if (b != null && b.pages > maxPages) {
            maxPages = b.pages;
        }
    }
    for (Book b : shelf) {
        if (b != null && b.pages == maxPages) {
            return b;
        }
    }
    return null;
}
## 🧪 Kiểm tra hiểu bài (ghi ✅🤔❌)
1. 
STACK (biến)            HEAP (object)
s[0] = null
                   ├──►  #1 Book { title="T" }
s[1] ──────────────┘
- T 1
2. 0 true
STACK                               HEAP
main:  k ───────────────────────►  #1 { title="K", currentPage=0 }
reset: b ──(trước trỏ #1)──X           ▲ dòng b.currentPage = 0 sửa ở ĐÂY
       b = null  ← chỉ cắt mũi tên của b, k không bị ảnh hưởng
3. có 2 object **Book**, chỉ có 2 mũ tên trỏ vào object thứ 2
STACK (biến)            HEAP (object)
m, arr[0], arr[1] ──► #1
arr[2], n        ──► #2
4. 8 true ✅
5. 2 true A ✅
6. ✅
STACK (biến)            HEAP (object)
s[0] ───────────┐
                ├──►  #1 Book { title="X" }
s[1] ───────────┘
                |     #2 Book { title="Y" }
s[2] ───────────┘
                      #3 Book { title="Z" }
- 1 X
7. ✅
STACK (biến)            HEAP (object)
s ───────────┐
             ├──►  #1 { y, y }
t ───────────┘
- 1 true
## 📝 Tổng kết (bằng lời của mình)
- khác giữa = null hoặc = new so với . là 1 bên thay đổi giá trị biến 1 bên thay dổi giá trị object
- trong hàm gán lại tham số thì bên ngoài sẽ không thấy được
- muốn đếm object thì đếm số biến của object đang trỏ đến báo nhiêu object
