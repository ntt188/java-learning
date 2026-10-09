# Task #9: Dùng Debugger (Practice, 30 phút)

**Xong khi:** `DebugPractice.java` ra **11/11 PASS**, bảng "Bằng chứng" có đủ 4 dòng (tìm lỗi bằng debugger, không phải đọc code), làm xong Phần B.

## ⏪ Kiểm tra đầu buổi (Task #8 do Claude làm hộ nên tự trả lời lại, kèm ✅🤔❌)
1. Trong `sumTo`, dòng nào là base case, dòng nào là bước thu nhỏ? Bỏ dòng `if (n < 0)` thì `sumTo(-3)` ra sao?
2. `int big = 2_000_000_000; long result = big * 2;` vì sao `result` âm? Sửa thế nào?

| Câu | Trả lời của tôi | Tự tin |
|---|---|---|
| 1 | n = 0 thì return về 0 là base case, bước thu nhỏ là return n + sumTo(n-1), bỏ dòng đó sẽ chạy vô hạn làm treo máy  | ✅ |
| 2 | vì nó lấy int * int nên vượt quá ngưỡng của int, thêm L sau số 2 big*2L | ✅ |

## 🎯 Mục tiêu
- Đặt **breakpoint** (điểm dừng: chương trình tạm dừng tại dòng đó để mình xem biến).
- Điều khiển từng bước: **Step Over**, **Step Into**, **Step Out**, **Resume**.
- Theo dõi biến bằng **Variables / Watches**, tính thử biểu thức bằng **Evaluate Expression**.
- Dùng **Conditional breakpoint** (chỉ dừng khi điều kiện đúng) cho vòng lặp dài.
- Xem **Frames** (danh sách stack frame) để hiểu đệ quy và pass-by-value.

## 📖 Học nhanh (IntelliJ, macOS; phím F cần giữ `fn` nếu Mac chưa bật phím F chuẩn)

| Thao tác | Phím | Khi nào dùng |
|---|---|---|
| Bật/tắt breakpoint | click lề trái số dòng, hoặc `⌘F8` | Muốn dừng ở dòng đó |
| Chạy Debug | `⌃D` (hoặc nút 🐞) | Thay cho Run |
| **Step Over** | `F8` | Chạy hết dòng hiện tại, KHÔNG chui vào method được gọi |
| **Step Into** | `F7` | Chui vào bên trong method được gọi ở dòng này |
| **Step Out** | `⇧F8` | Chạy nốt method hiện tại, quay về chỗ gọi |
| **Resume** | `⌥⌘R` | Chạy tiếp đến breakpoint kế tiếp |
| **Evaluate Expression** | `⌥F8` | Tính thử biểu thức bất kỳ với giá trị biến lúc đang dừng |
| Run to Cursor | `⌥F9` | Chạy tới dòng con trỏ đang đứng |

Lưu ý: dòng có breakpoint **chưa chạy** khi chương trình dừng ở đó.

### 🔮 Đoán (ghi ✅🤔❌ trước khi thử)
Đặt breakpoint ở dòng `return n + sumTo(n - 1);` trong `sumTo`, chạy Debug `sumTo(4)`, bấm Resume liên tục.
- Q1: Chương trình dừng ở dòng đó bao nhiêu lần?
- Q2: Lúc dừng lần cuối, panel **Frames** có bao nhiêu dòng `sumTo`?

| Câu | Đoán | Tự tin | Thực tế |
|---|---|---|---|
| Q1 | 3 | ✅ | 4 |
| Q2 | 4 | ✅ | 5 |

## ✏️ Phần A: Bắt lỗi trong `DebugPractice.java` (hiện 4/11 PASS)
Mỗi method có 1 lỗi. **Tìm bằng debugger trước**, ghi bằng chứng, rồi mới sửa.

| Bug | Breakpoint đặt ở dòng | Thấy biến/biểu thức = giá trị | Nguyên nhân | Cách sửa |
|---|---|---|---|---|
| 1 `average` | return sum / nums.length; vs static void check(String name, Object actual, Object expected)  | thấy actual đang là 1 | biểu thức đang là số nguyên | chuyển sum về kiểu số thực |
| 2 `countVowels` | đặt ở biến i và c | i là 1 và c là p | đang đếm từ vị trí 1 mà không phải 0 | int i = 0 | 
| 3 `isPalindromeNumber` | return reversed == n; | giá trị n luôn bằng 0 | vì chưa lưu giá trị ban đầu lại mà lấy chính nó để chia 10 nên sẽ bằng 0 | tạo một biến để chưa n |
| 4 `sumOfSquares` | `total += i * i;` (conditional: `i * i < 0`) | `i = 46341`, `i * i = -2147479015` | `int * int` tính bằng `int`, 46341² = 2.147.488.281 > 2.147.483.647 → overflow ra số âm, rồi mới cộng vào `total` | `long i` (hoặc `(long) i * i`) |

Gợi ý công cụ (không phải đáp án):
- Bug 1: dừng ở dòng `return`, dùng **Evaluate** tính `sum / nums.length` và `sum / (double) nums.length`.
- Bug 2: **Watch** biến `i` và `c`; vòng đầu tiên `c` là ký tự nào?
- Bug 3: dừng ở dòng `return`, nhìn **cả hai** biến trong phép so sánh.
- Bug 4: **Conditional breakpoint** trên dòng `total += ...` với điều kiện `i * i < 0`. Dừng lúc `i` = bao nhiêu? Vì sao bình phương lại âm?

## ✏️ Phần B: Debug lại `task08-control-flow/ControlFlowPractice.java`
1. **Step Into đệ quy:** breakpoint trong `sumTo`, gọi `sumTo(4)`. Bấm `F7` dần và quan sát panel **Frames** dài ra, rồi `⇧F8` để thấy từng frame được "gỡ" ra và giá trị trả về.
2. **Pass-by-value:** trong `main`, thêm tạm `int x = -45; reverseNumber(x);`. Đặt breakpoint trong `reverseNumber`, click frame `main` trong **Frames** để so sánh `x` (main) với `n` (reverseNumber) sau dòng `n = Math.abs(n)`.

### Ghi chép Phần B + edge case (Claude ghi hộ theo yêu cầu "note it")

**B.2 Pass-by-value:** sau dòng `n = Math.abs(n)`: `n` (frame `reverseNumber`) = `45`, `x` (frame `main`) = `-45`. Mỗi frame có biến riêng; khi gọi hàm, Java **chép giá trị** `-45` sang `n`, nên sửa `n` không đụng tới `x`.

**Edge case `average(new int[]{})`:** thực tế in ra `NaN` (Not a Number), không có exception.
- `sum` là `double` → `0.0 / 0` theo chuẩn số thực ra `NaN` (còn `1.0 / 0` ra `Infinity`). Chia số thực cho 0 **không báo lỗi**.
- Code gốc dùng `int sum` → `0 / 0` là chia **số nguyên** cho 0 → ném `ArithmeticException: / by zero`.
- Cả hai đều chưa tốt: mảng rỗng nên được chặn ở đầu hàm (ví dụ `if (nums.length == 0) throw new IllegalArgumentException(...)`).

**Bug 4 (vì sao `long total` không cứu được):** Java tính vế phải `i * i` **trước**, bằng kiểu của toán hạng (`int`). Lúc đó đã tràn số; giá trị âm sai mới được chuyển sang `long` để cộng vào `total`. Giống câu C2: phải đổi kiểu **trước khi nhân**.

## 🧪 Kiểm tra hiểu bài

**Bài luyện NaN/Infinity: 1/5** (quên ✅🤔❌, bỏ trống 2 câu)
| Biểu thức | Tôi | Đúng |
|---|---|---|
| `1.0 / 0` | NaN | `Infinity` ❌ |
| `-1.0 / 0` | NaN | `-Infinity` ❌ |
| `0.0/0 == 0.0/0` | false | `false` ✅ (NaN không bằng chính nó → dùng `Double.isNaN`) |
| `(long) 100000 * 100000` | (trống) | `10000000000` |
| `(long) (100000 * 100000)` | (trống) | `1410065408` (tràn số nhưng ra số DƯƠNG → khó phát hiện) |

**5 câu khái niệm: ~1/5** (không ghi ✅🤔❌ câu nào)
1. ½: F7 chui vào hàm, dừng ở dòng đầu trong hàm; F8 chạy trọn dòng (kể cả lời gọi hàm), dừng ở **dòng kế tiếp của hàm hiện tại**.
2. 0 (chưa hiểu): breakpoint ở `if (isPrime(i))`, Condition `i == 97`.
3. 0: dòng có breakpoint **chưa chạy** → `total` hiển thị giá trị **trước** khi cộng.
4. 0 (chưa hiểu): Frames = các hàm **đang làm dở** (đã gọi, chưa return), trên cùng là chỗ đang dừng. Sâu nhất của `sumTo(4)`: `sumTo n=0,1,2,3,4` + `main` = 6 dòng. Step Out → frame `n=0` biến mất, quay về `n=1`.
5. ½: "count thiếu 1" là **triệu chứng** (test đã báo); "i = 1, c = p" là **trạng thái bên trong** chỉ ra nguyên nhân.

**3 câu bổ sung: 1/3** (lần này CÓ ghi mức tự tin 👍)
| Câu | Tôi | Đúng |
|---|---|---|
| A: `a` lúc dừng ở dòng 2, sau F8 | 5, 10 ✅ | ✅ đúng |
| B: Condition dừng khi i là 500 | `i > 500` ✅ | ❌ tự tin nhưng sai → `i == 500` (`>` dừng ở MỌI vòng 501..999) |
| C: Frames khi đang ở trong `reverseNumber` | chưa hiểu ❌ | 2 dòng: `reverseNumber` (trên, đang chạy), `main` (dưới, đang chờ) |

## 📝 Tổng kết (Claude viết hộ theo yêu cầu "note it"; nên tự viết lại bằng lời của mình)
- **Debug = nhìn trạng thái thật**, không đoán: đặt breakpoint, xem biến, ghi bằng chứng (giá trị biến tại thời điểm cụ thể), rồi mới sửa. Kết quả test chỉ là triệu chứng.
- **Dòng có breakpoint CHƯA chạy** khi chương trình dừng ở đó.
- **F8 Step Over** chạy trọn dòng; **F7 Step Into** chui vào hàm được gọi; **⇧F8 Step Out** chạy nốt hàm hiện tại rồi quay về chỗ gọi; **⌥F8 Evaluate** tính thử biểu thức.
- **Conditional breakpoint** dừng **mỗi khi** điều kiện đúng → muốn dừng đúng 1 lần thì dùng `==` (`i == 500`), không dùng `>`.
- **Frames** = danh sách hàm đang làm dở; mỗi frame có biến riêng → thấy tận mắt pass-by-value (`x = -45` ở `main`, `n = 45` ở `reverseNumber`) và đệ quy chồng frame.
- **Số học:** `int * int` tràn số trước khi gán vào `long`; chia `int` cho 0 → `ArithmeticException`; chia `double` cho 0 → `Infinity`/`-Infinity`/`NaN`, **không báo lỗi**; `NaN != NaN`.
