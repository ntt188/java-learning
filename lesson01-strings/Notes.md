Dự đoán:
- khái niệm niệm 1: 
    (1) java
    (2) JAVA
- khái niệm 2:
    2 nhanh hơn nhưng ko biết nhanh hơn bao nhiêu
- Khái niệm 3:
    (1) true
    (2) false
    (3) true
    (4) true
- khái niệm 4:
    có

---

## Giải thích (sau khi chạy code)

### Khái niệm 2: `+` và StringBuilder trong vòng lặp
- String là immutable, nên mỗi lần `result += i` Java phải tạo chuỗi MỚI và copy toàn bộ chuỗi cũ sang.
- Tổng số ký tự phải copy là 0 + 1 + ... + (n-1) ≈ n²/2, nên độ phức tạp là O(n²).
- StringBuilder giữ một mảng char có dư chỗ trống (capacity). `append()` chỉ ghi tiếp vào cuối. Khi đầy thì mảng nới rộng khoảng gấp 2 lần. Độ phức tạp O(n).
- Nối vài chuỗi trong MỘT câu lệnh bằng `+` thì vẫn ổn. Chỉ khi nối trong VÒNG LẶP mới cần StringBuilder.

### Khái niệm 3: `==` và `equals()`
- Biến String chứa ĐỊA CHỈ trỏ tới object, không chứa chính chuỗi.
- Literal `"hi"` nằm trong String Pool và được dùng lại: `a == b` ra true vì cùng một object.
- `new String("hi")` luôn tạo object mới ngoài pool: `a == c` ra false.
- `==` so sánh địa chỉ. `equals()` so sánh nội dung (vì String đã override `equals()`).
- `char` là primitive, biến chứa thẳng giá trị, nên `==` so sánh giá trị.
- Quy tắc: luôn dùng `equals()` để so sánh String.

### Khái niệm 4: `reverse()`
- StringBuilder là mutable: `reverse()` sửa trực tiếp object gốc và trả về chính nó (`return this`).
- `sb == r` ra true vì chỉ có MỘT object. Nhờ trả về `this` nên viết được method chaining: `sb.append(..).append(..).reverse()`.
- So sánh: `String.toUpperCase()` trả về object MỚI, không gán lại thì kết quả bị mất.

---

## Quiz kiểm tra hiểu bài (lần 1): 1.5 / 3
| Câu | Mình trả lời | Đáp án | Điểm |
|---|---|---|---|
| `s="a"; s+="b"; s+="c";` tạo bao nhiêu object? | 3 | **5** ("a","b","c" trong pool + "ab","abc") | 0.5 |
| `y = x + "va"`: `y == "Java"` / `y.equals("Java")` | false / true | false / true | 1 |
| `new StringBuilder("hi").equals(new StringBuilder("hi"))` | true | **false** | 0 |

## Quiz bổ sung (lần 2): 2 / 2 ✅
| Câu | Mình trả lời (tự tin) | Đáp án | Điểm |
|---|---|---|---|
| `p="hi"; q="hi"; r=new String("hi");` bao nhiêu object? | 2 (100%) | 2 (1 trong pool dùng chung + 1 do `new`) | 1 |
| `b = a; b.append("!");` in `a` / `a.equals(b)` | hi! (80%) / true (70%) | `hi!` / `true` (a và b cùng một object, nên `Object.equals` = `==` ra true) | 1 |

## ⚠️ Lỗi đã mắc (cần nhớ)
1. **Quên đếm literal.** Mỗi chuỗi viết trong `"..."` cũng là một object (nằm trong pool), kể cả khi không gán cho biến.
2. **Tưởng StringBuilder.equals() so sánh nội dung.** StringBuilder KHÔNG override `equals()`, nên nó dùng `Object.equals()`, tức là `==` (so sánh địa chỉ).
   - So sánh nội dung đúng cách: `a.toString().equals(b.toString())` hoặc `a.compareTo(b) == 0` (Java 11+).
3. `equals()` chỉ so sánh nội dung khi class đó override nó (String có, StringBuilder không).

## 🎁 Mở rộng nên thử
- `final String x = "Ja"; String y = x + "va";` thì `y == "Java"` ra **true**, vì compiler tính sẵn hằng số lúc compile và dùng object trong pool.


---

# Bài tập 1.1: Đảo chuỗi

## Trả lời 3 câu hỏi
1. **Vì sao Cách A phải đổi sang `char[]`?**
   Vì String không thể thay đổi giá trị bên trong nên phải tìm một loại có thể sửa được, đó là char[]. Nên đáp án là: vì phải có thứ để có thể sửa đổi được nên mới chọn char[].
   - Bổ sung: String không có method kiểu `setCharAt()`. `s.charAt(0) = 'H'` sẽ báo lỗi compile. `toCharArray()` tạo ra một bản COPY, nên Cách A còn tốn thêm bộ nhớ chứ không tiết kiệm.
2. **Vòng lặp chạy bao nhiêu lần?** n/2 lần (làm tròn xuống). Ví dụ `"hello"` chỉ đổi chỗ 2 lần, chữ `l` ở giữa đứng yên.
3. **Code thật dùng cách nào?** Mình chọn cách B vì đỡ phải viết dài dòng, và có thể đọc được emoji.

4. **`split(" ")` với nhiều khoảng trắng liên tiếp.** `"  I  love".split(" ")` trả về `["", "", "I", "", "love"]`, tức là sinh ra các chuỗi rỗng `""`.
   - Cách sửa: `s.trim().split("\\s+")`. `trim()` bỏ khoảng trắng ở 2 đầu, còn `\\s+` nghĩa là "một hoặc nhiều khoảng trắng". Sẽ gặp lại ở Bài 1.4: Đếm từ.
5. **Viết thiếu test case.** Test PASS hết không có nghĩa là code không có bug. Luôn thêm các case: nhiều khoảng trắng, chuỗi chỉ có khoảng trắng, emoji.
6. **Viết test cho `null` nhưng quên xử lý `null` trong hàm.** Kết quả là `NullPointerException` làm dừng cả chương trình. Mỗi khi viết test `null`, phải thêm guard clause ở đầu hàm.
7. **Mảng đếm `int[128]` chỉ đủ cho ASCII.** Ký tự như `é` (233) làm vượt chỉ số. Dùng `int[Character.MAX_VALUE + 1]` hoặc dùng `HashMap`.
8. **Quên xóa `// TODO`** sau khi viết xong hàm (mắc 2 lần).

## Lý do chọn trả về `null`
Lý do chọn null là để đỡ phải báo lỗi, chỉ cần giấu lỗi đi.
- Đánh đổi cần nhớ: "giấu lỗi" có thể làm người gọi gặp `NullPointerException` ở chỗ khác, khó tìm nguyên nhân. Nếu muốn báo lỗi ngay tại chỗ thì dùng `throw new IllegalArgumentException(...)`.

## Emoji 😀
- `reverseA("a😀b")` cho kết quả emoji bị vỡ ❌, `reverseB("a😀b")` cho `b😀a` ✅.
- Lý do: 😀 không vừa 1 `char` (16 bit), nên Java lưu nó bằng 2 `char` (surrogate pair). `"😀".length()` bằng 2.
- Cách A đổi chỗ từng `char`, nên 2 nửa của emoji bị đảo ngược và emoji bị vỡ. `StringBuilder.reverse()` nhận ra cặp surrogate và giữ nguyên thứ tự của chúng.

## Review code
- Kết quả cuối: **21 / 21 PASS** (reverseA ×6, reverseB ×6, reverseWords ×9).
- Đã sửa:
  - `reverseWords`: `s.split(" ")` → `s.trim().split("\\s+")`
  - `reverseB`: đổi sang guard clause (kiểm tra `null` và `return` ngay đầu hàm)
  - Sửa comment sai "Cách C" thành "Mở rộng: đảo thứ tự các từ"
- Bài học: **test xanh chỉ chứng minh code đúng với những case đã viết.** 6 test ban đầu của `reverseWords` đều PASS nhưng code vẫn có bug, vì chưa có test nào chứa nhiều khoảng trắng.


---

# Bài tập 1.2: Đếm ký tự

## Dự đoán: HashMap so sánh
- `m1 == m2`: **false** (2 object khác nhau, khác địa chỉ).
- `m1.equals(m2)`: **true**. Khác với StringBuilder, các class Map CÓ override `equals()`: hai Map bằng nhau nếu có cùng các cặp key → value, **không quan tâm thứ tự**.

## Trả lời 3 câu hỏi
1. **Cách nào chạy được với mọi ký tự?**
   Theo tôi nghĩ thì cách B sẽ đọc được hết.
   - Bổ sung: B đúng với `é` và chữ tiếng Việt (mỗi chữ = 1 `char`). Nhưng emoji 😀 = 2 `char` (surrogate pair), nên B đếm thành 2 key, vẫn SAI với emoji.
   - Cách A với mảng `int[128]` bị crash với `"café"` (`'é'` = 233 > 127 → `ArrayIndexOutOfBoundsException`). Đã sửa bằng mảng `Character.MAX_VALUE + 1` = 65536 ô, đủ cho mọi `char`.
2. **HashMap và LinkedHashMap khác gì?**
   - `HashMap` in ra `{e=1, h=1, l=2, o=1}`: thứ tự **không đảm bảo** (sắp theo mã băm, không theo thứ tự thêm vào).
   - `LinkedHashMap` in ra `{h=1, e=1, l=2, o=1}`: **giữ đúng thứ tự thêm vào**.
3. **Vì sao `check()` so sánh được 2 Map dù thứ tự in khác nhau?**
   `check()` dùng `Objects.equals()`, mà Map đã override `equals()` để so sánh **nội dung** (các cặp key → value), không so sánh thứ tự hay địa chỉ.

## Review code
- Kết quả: **14 / 14 PASS** cho Bài 1.2 (mỗi cách 7 test, gồm `null` và `"café"`).
- Đã sửa:
  - Thêm guard clause `if (s == null) return null;` cho cả 2 hàm. Trước đó test `null` làm **crash cả chương trình** (`NullPointerException`), các test phía sau không chạy được.
  - Cách A: mảng `int[128]` → `int[Character.MAX_VALUE + 1]` để không crash với ký tự ngoài ASCII.
  - Xóa các dòng `// TODO`, sửa thụt lề, dùng `Objects.equals` ngắn gọn (đã có `import java.util.*`).
