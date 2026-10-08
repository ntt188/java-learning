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


---

# Bài tập 1.3: Palindrome

## Trả lời 3 câu hỏi
1. **Cách nào tốn thêm bộ nhớ?**
   Theo tôi cả 2 cách đều tốn tương đương nhau vì đều tác động trên 1 đối tượng.
   - ❌ Sửa lại: **Cách B tốn thêm bộ nhớ O(n)**, Cách A chỉ tốn **O(1)**.
     - Cách A chỉ tạo thêm 2 số `int` (`left`, `right`). Chuỗi dài 10 hay 1 triệu ký tự thì vẫn chỉ 2 số đó.
     - Cách B tạo thêm 3 object: 1 `StringBuilder` + `filteredStr` + `reversedStr`, mỗi object chứa tới n ký tự.
     - "Tác động trên 1 đối tượng" chỉ đúng với chuỗi đầu vào. Cách B còn tạo ra object MỚI để làm việc.
2. **Vì sao Cách A không cần `toCharArray()`?**
   Vì chỉ cần tìm kiếm và so sánh, không cần thay đổi. ✅
   - Bổ sung: chỉ ĐỌC thì dùng `charAt(i)` là đủ. Bài 1.1 phải ĐỔI CHỖ ký tự (sửa) nên mới cần `char[]`.
3. **Nếu chỉ dùng `isLetter()` thay cho `isLetterOrDigit()` thì test `"0P"` còn đúng không?**
   - Đã thử: test `0P` bị **FAIL** (`got: true, expected: false`).
   - Lý do: `'0'` là chữ số, không phải chữ cái, nên bị BỎ QUA. Lúc đó cả `left` và `right` cùng chỉ vào `'P'`, vòng lặp dừng (`left < right` sai) và trả về `true`. Kết quả sai, vì `"0p"` đọc ngược là `"p0"`.
   - Bài học: chọn đúng method lọc. Chữ số cũng là ký tự hợp lệ cần so sánh.

## Lý do chọn trả về `false` cho `null`
- Hàm trả về `boolean` (primitive), nên KHÔNG THỂ `return null` (lỗi compile: `<null> cannot be converted to boolean`).
- Chọn `false`: coi `null` là "không phải palindrome", để chương trình không bị crash.

## Review code
- Kết quả: **55 / 55 PASS** cho toàn bộ file (Bài 1.1 + 1.2 + 1.3).
- Đã làm tốt: tránh được bẫy `reverse()` bằng cách lưu `filteredStr` TRƯỚC khi đảo; rút gọn thành `return filteredStr.equals(reversedStr);`; không còn `// TODO`.
- Đã sửa: test `null` mong đợi `false` (không phải `null`); xóa comment sai "hoặc return null".

## ⚠️ Lỗi đã mắc trong bài này
9. **Nhầm `null` với `""`.**
   - `""` là **hộp rỗng có thật**: `"".length()` = 0, `"".isEmpty()` = true.
   - `null` là **không có hộp**: gọi bất kỳ method nào cũng bị `NullPointerException`.
   - **Quy tắc 2 bước:**
     1. Biến đứng TRƯỚC dấu chấm là `null` → 💥 crash.
     2. Nếu không crash → method chạy bình thường. `x.equals(null)` luôn là `false`.
   - Mẹo an toàn: viết `"yes".equals(input)` thay vì `input.equals("yes")`.
10. **Viết comment sai** ("hoặc return null" trong hàm `boolean`). Comment sai còn nguy hiểm hơn không có comment.


---

# Bài tập 1.4: Đếm từ

## Dự đoán `split`: 0 / 4
| Câu | Mình đoán | Kết quả thật (đã chạy `splitDemo()` trong Concepts.java) |
|---|---|---|
| (1) `"".split("\\s+").length` | 0 | **1** → `[""]` |
| (2) `"   ".trim().split("\\s+").length` | 0 | **1** → `[""]` |
| (3) `"a b".split("\\s+").length` | 3 | **2** → `["a", "b"]` |
| (4) `" a b".split("\\s+").length` | 4 | **3** → `["", "a", "b"]` |

**Hình dung `split` = cầm kéo cắt sợi dây:**
- Mỗi chỗ khớp regex là 1 nhát cắt, kết quả là các đoạn dây còn lại. Số đoạn = số nhát cắt + 1.
- Dấu cách là CHỖ BỊ CẮT BỎ, không nằm trong kết quả. (Câu 3 mình đếm cả dấu cách nên sai.)
- Nhát cắt ở ngay đầu chuỗi sinh ra đoạn rỗng `""` ở đầu, nên phải `trim()` trước. (Câu 4)
- **Không cắt được gì thì trả về nguyên chuỗi gốc**, nên `""` cho ra `[""]`, tức 1 phần tử. `split` luôn trả về ít nhất 1 phần tử. (Câu 1, 2: bẫy của bài này)

## Trả lời 3 câu hỏi
1. **Vì sao `"".split("\\s+")` trả về 1 phần tử?**
   Mình trả lời: vì nó chỉ xoá 1 dấu " " khi phát hiện có "  " nên giá trị cuối cùng là " ". ❌
   - Sửa lại: không có chỗ nào để cắt, nên `split` trả về nguyên chuỗi gốc là `""`. Vì vậy mảng là `[""]`, có 1 phần tử. Muốn đếm đúng phải kiểm tra `trimmed.isEmpty()` thì trả về 0.
2. **Cách nào tốn thêm bộ nhớ?**
   Mình trả lời: cách A vì nó sẽ tạo ra nhiều đối tượng để cắt. 🟡 Đúng một nửa.
   - Cách A: tạo mảng `String[]` và các chuỗi con, nên tốn O(n).
   - Cách B bản đầu tiên cũng tốn O(n), vì `trim()` tạo String mới và `toCharArray()` tạo mảng copy.
   - Cách B bản đã sửa (dùng `charAt`, bỏ `trim`) chỉ tốn **O(1)**: chỉ có `inWord`, `count`, `i`.
3. **Vì sao Cách B không cần xử lý riêng chuỗi rỗng?**
   - Nếu **đếm lúc một từ BẮT ĐẦU** (gặp ký tự thường mà `inWord == false`), thì chuỗi `""` hoặc `"   "` không có chữ nào để bắt đầu, nên `count` giữ nguyên là 0.
   - Bản đầu tiên của mình đếm lúc từ KẾT THÚC (gặp khoảng trắng sau từ), nên phải thêm `trim()`, `isEmpty()` và `if (inWord) count++` sau vòng lặp. Đổi sang đếm lúc bắt đầu thì bỏ được cả 3.

## Lý do chọn trả về `-1` cho `null`
- `int` là primitive, nên không thể trả về `null`.
- Chọn `-1` vì số từ không bao giờ âm, nên `-1` rõ ràng nghĩa là "đầu vào không hợp lệ". Đây cũng là quy ước của Java: `"abc".indexOf('z')` trả về `-1`.

## Review code
- Kết quả: **69 / 69 PASS** cho toàn bộ file (Bài 1.1 → 1.4).
- Làm tốt: Cách A né đúng bẫy `[""]` bằng `if (trimmed.isEmpty()) return 0;`. Không còn `// TODO`.
- Đã sửa:
  - Viết lại Cách B: đếm lúc **bắt đầu** từ, dùng `charAt(i)` thay cho `toCharArray()`, bỏ `trim()`, `isEmpty()` và bước kiểm tra sau vòng lặp. Bộ nhớ giảm từ O(n) xuống O(1).
  - Không gán lại tham số (`s = s.trim()`). Nên dùng biến mới.
  - Sửa thụt lề comment (lần 2 bị lệch).
  - Thêm `splitDemo()` vào Concepts.java.

## ⚠️ Lỗi đã mắc trong bài này
11. **Hiểu sai `split`.** Dấu phân cách bị cắt bỏ (không nằm trong kết quả). Không cắt được gì thì trả về `[""]` (1 phần tử), không phải mảng rỗng.
12. **Thụt lề comment bị lệch** (lần 2, sau Bài 1.2).


---

# Bài tập 1.5: Nén chuỗi

## Ý tưởng
Đi qua chuỗi từ trái sang phải, luôn nhớ 2 thứ: **ký tự của nhóm đang đếm** (`currentChar`) và **nhóm đó dài bao nhiêu** (`currentCount`).
- Gặp ký tự GIỐNG thì `currentCount++`.
- Gặp ký tự KHÁC thì nhóm cũ đã kết thúc: ghi `currentChar + currentCount` vào `sb`, rồi bắt đầu nhóm mới.
- **Sau vòng lặp phải ghi nhóm cuối cùng.**

## Bảng lần theo với `"aabccc"`
| i | `s.charAt(i)` | Giống `currentChar`? | Việc làm | `currentChar` | `currentCount` | `compressed` |
|---|---|---|---|---|---|---|
| (trước vòng lặp) | `a` | | bắt đầu nhóm đầu tiên | `a` | 1 | `""` |
| 1 | `a` | ✅ | đếm tiếp | `a` | 2 | `""` |
| 2 | `b` | ❌ | ghi `a2`, bắt đầu nhóm `b` | `b` | 1 | `"a2"` |
| 3 | `c` | ❌ | ghi `b1`, bắt đầu nhóm `c` | `c` | 1 | `"a2b1"` |
| 4 | `c` | ✅ | đếm tiếp | `c` | 2 | `"a2b1"` |
| 5 | `c` | ✅ | đếm tiếp | `c` | 3 | `"a2b1"` |
| **hết vòng lặp** | | | **ghi `c3`** | | | **`"a2b1c3"`** |

👉 Nhóm `ccc` KHÔNG được ghi trong vòng lặp, vì nhóm cũ chỉ được ghi khi gặp ký tự KHÁC, mà sau `ccc` không còn ký tự nào. Nếu quên 2 dòng `append` sau vòng lặp, kết quả sẽ thiếu nhóm cuối: `"a2b1"`. Đây là **lỗi kinh điển** của bài này.

## Trả lời 3 câu hỏi
1. **Bảng lần theo:** xem bảng ở trên.
2. **Dùng `result += ...` thay cho StringBuilder có sai không?**
   Không sai (vẫn ra đúng kết quả), nhưng **chậm**: mỗi lần `+=` tạo một String mới và copy lại toàn bộ chuỗi cũ, nên độ phức tạp là O(n²). StringBuilder chỉ ghi tiếp vào cuối, nên là O(n) (khái niệm 2).
3. **Vì sao bài này trả về `null` được, còn 1.3, 1.4 thì không?**
   Bài này trả về `String`, là kiểu reference, nên biến có thể "không trỏ tới đâu" (`null`). Bài 1.3 trả về `boolean` và Bài 1.4 trả về `int`, đều là primitive, nên luôn phải có giá trị và không bao giờ là `null`.

## Đọc hiểu dòng cuối (toán tử 3 ngôi)
```java
return compressedStr.length() < length ? compressedStr : s;
```
Nghĩa là: `điều kiện ? giá trị nếu ĐÚNG : giá trị nếu SAI`. Viết dài ra thành:
```java
if (compressedStr.length() < length) {
    return compressedStr;   // bản nén ngắn hơn → dùng bản nén
} else {
    return s;               // bản nén dài hơn hoặc BẰNG → dùng bản gốc
}
```
Dùng `<` (không phải `<=`), nên `"aabb"` → `"a2b2"` cùng dài 4 → trả về bản gốc.

## Review code
- Kết quả: **8 / 8 PASS** (tổng file 77 / 77).
- Code chạy đúng ngay lần đầu, có xử lý `null`, `""` và nhóm cuối cùng.
- Đã sửa nhỏ: xóa `// TODO`; đổi tên biến `count` thành `length` (vì nó là độ dài chuỗi, không phải số đếm; tên cũ dễ nhầm với `currentCount`); thêm comment giải thích từng bước.

## ⚠️ Lỗi đã mắc trong bài này
13. **Đặt tên biến gây nhầm lẫn:** `count` chứa độ dài chuỗi, trong khi đã có `currentCount` để đếm nhóm. Tên biến phải nói đúng nó chứa gì.
14. **Quên xóa `// TODO`** (lần 3).

## 🧪 Thí nghiệm hiểu code: 3 / 3 ✅
1. **Bỏ 2 dòng `append` sau vòng lặp (ghi nhóm cuối):**
   - basic → `"a2b1c5"` (mất `a3`); twelve → `""` (mất `a12`); case → `"a2"` (mất `A3`); equal → `"a2"`; one → `""`.
   - Chuỗi chỉ có 1 nhóm (`twelve`, `one`) thì **mất trắng toàn bộ**, vì nhóm duy nhất cũng chính là nhóm cuối.
   - Test `abc` **vẫn PASS dù code sai**: `"a1b1"` dài hơn `"abc"` nên hàm trả về chuỗi gốc, vô tình ra đúng. Test PASS chưa chắc code đã đúng (xem lỗi 5).
2. **Đổi `<` thành `<=`:** test `equal` FAIL. `"a2b2"` và `"aabb"` cùng dài 4, nhưng `<=` chọn bản nén dù nó không ngắn hơn.
3. **Đổi `int i = 1` thành `int i = 0`:** `"aabcccccaaa"` → `"a3b1c5a3"`. Ký tự đầu tiên đã được đếm ở bước chuẩn bị (`currentCount = 1`), nên vòng lặp bắt đầu từ 0 sẽ đếm nó **2 lần**.
   - Đây là lỗi **off-by-one** (lệch đúng 1 đơn vị), rất hay gặp với vòng lặp và chỉ số mảng.

## ⚠️ Lỗi kinh điển cần nhớ từ bài này
15. **Quên ghi nhóm cuối cùng sau vòng lặp.** Khi code chỉ "ghi" lúc gặp sự thay đổi, phần tử/nhóm cuối không có sự thay đổi nào theo sau, nên phải ghi riêng sau vòng lặp.
16. **Off-by-one:** vòng lặp bắt đầu/kết thúc lệch 1 vị trí (ví dụ `i = 0` thay vì `i = 1`, hoặc `<=` thay vì `<`). Luôn tự hỏi: phần tử đầu và cuối đã được xử lý **đúng 1 lần** chưa?
