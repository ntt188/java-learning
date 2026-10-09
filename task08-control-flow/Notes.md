# Task #8: Control flow & methods (Practice, 60 phút)

**Xong khi:** `ControlFlowPractice.java` chạy ra **50/50 PASS**, bài 3 và bài 8 có đủ 2 cách, không còn `// TODO`.

## 🎯 Mục tiêu
- Chọn đúng vòng lặp: `for` (biết trước số lần), `while` (lặp đến khi điều kiện sai), `do-while` (chạy ít nhất 1 lần).
- Dùng `break` / `continue`, `if-else`, `switch` expression (Java 14+).
- Viết **method** (hàm): tham số, kiểu trả về, `return`; method gọi method; đệ quy (**recursion**: hàm tự gọi chính nó).
- Thành thạo `/` và `%` để "bóc" từng chữ số.

## 📖 Học nhanh

```java
// for: biết trước số lần
for (int i = 1; i <= 5; i++) { ... }

// while: lặp khi điều kiện còn đúng
while (n > 0) { int lastDigit = n % 10; n = n / 10; }

// switch expression (Java 14+): trả về giá trị, không cần break
String day = switch (d) {
    case 1, 7 -> "Cuối tuần";
    default -> "Ngày thường";
};

// Method: [static] kiểuTrảVề tên(tham số) { ... return giá trị; }
static int square(int x) { return x * x; }
```

Mẹo bóc chữ số: `n % 10` = chữ số cuối, `n / 10` = bỏ chữ số cuối (chia nguyên).

### 🔮 Đoán output (ghi đáp án + mức tự tin ✅🤔❌ TRƯỚC khi chạy)

```java
// Q1
System.out.println(7 / 2 + " " + 7 % 2 + " " + (-7) % 2);

// Q2 (ôn điểm yếu #6: kiểu primitive)
int x = Integer.MAX_VALUE;
x = x + 1;
System.out.println(x);

// Q3
for (int i = 1; i <= 6; i++) {
    if (i == 2) continue;
    if (i == 5) break;
    System.out.print(i + " ");
}

// Q4 (ôn điểm yếu #5: pass-by-value)
static void addOne(int n) { n = n + 1; }
// trong main:
int a = 5;
addOne(a);
System.out.println(a);

// Q5
int k = 10;
do { k++; } while (k < 5);
System.out.println(k);
```

| Câu | Đáp án của tôi | Tự tin | Kết quả thật |
|---|---|---|---|
| Q1 | 3 0.5 -0.5 | chắc chắn | `3 1 -1` ❌ |
| Q2 | 128 | chắc chắn | `-2147483648` ❌ |
| Q3 | 1 2 3 4 | chắc chắn | `1 3 4` ❌ |
| Q4 | 5 | chắc chắn | `5` ✅ |
| Q5 | 11 | chắc chắn | `11` ✅ |


**Vì sao sai (Claude giải thích):**
- **Q1:** `int / int` là **chia nguyên** (bỏ phần lẻ): `7 / 2 = 3`. Còn `%` là **phép lấy dư**, không phải phép chia: `7 % 2 = 1`. Với số âm, dấu của kết quả `%` theo **số bị chia**: `-7 % 2 = -1`. Muốn ra `3.5` thì phải có ít nhất 1 số `double`: `7 / 2.0`.
- **Q2:** `int` có 32 bit, phạm vi từ -2147483648 đến 2147483647. Cộng 1 vào giá trị lớn nhất sẽ bị **overflow** (tràn số): giá trị "quay vòng" xuống nhỏ nhất, **không báo lỗi**. (128 là giới hạn của `byte`, không phải `int`.)
- **Q3:** `continue` = **bỏ qua phần còn lại của vòng hiện tại** và nhảy sang vòng kế tiếp, nên số 2 không được in ra. `break` = **thoát hẳn** khỏi vòng lặp, nên số 5 và 6 không được in ra.

## ✏️ Luyện tập: `ControlFlowPractice.java`

| # | Method | Test mẫu | Bẫy cần để ý |
|---|---|---|---|
| 1 | `fizzBuzz(n)` | 15 → "FizzBuzz", 7 → "7" | Thứ tự kiểm tra điều kiện |
| 2 | `isPrime(n)` | 1 → false, 2 → true, 25 → false | n ≤ 1; chỉ cần thử đến √n |
| 3 | `fibonacci(n)` | 10 → 55, 50 → 12586269025 | Vì sao phải `long`? **2 cách: vòng lặp + đệ quy** |
| 4 | `multiplicationTable(n)` | "3 x 1 = 3\n..." | Không có `\n` ở cuối (gợi nhớ StringBuilder bài 7) |
| 5 | `toBase(n, base)` | 255,16 → "FF"; 0,2 → "0" | n = 0; thứ tự chữ số bị ngược |
| 6 | `sumDigits(n)` | -123 → 6 | Số âm |
| 7 | `reverseNumber(n)` | 1200 → 21, -45 → -54 | `%` với số âm cho kết quả âm (xem Q1) |
| 8 | `gcd(a, b)` | 12,18 → 6; 0,9 → 9 | **2 cách: while + đệ quy** |
| 9 | `factorial(n)` | 20 → 2432902008176640000 | 0! = 1; thử 21! xem chuyện gì xảy ra |
| 10 | `countPrimes(limit)` | 10 → 4, 100 → 25 | Phải gọi lại `isPrime` |

Câu hỏi thêm cho bài 3: gọi `fibonacciRecursive(45)` mất bao lâu so với bản vòng lặp? Vì sao?

### Trả lời câu hỏi thêm (Claude làm hộ)

**1. Vì sao `factorial(21)` ra số âm (`-4249290049419214848`)?**
`long` có 64 bit, lớn nhất khoảng 9,22 × 10^18. 20! ≈ 2,43 × 10^18 còn vừa, nhưng 21! ≈ 5,1 × 10^19 thì vượt giới hạn, nên bị **overflow** giống hệt Q2 (chỉ khác là xảy ra với `long`). Java không báo lỗi mà lặng lẽ cho ra số sai.
- `reverseNumber(1999999999)` cũng bị như vậy: số đảo ngược 9999999991 lớn hơn giới hạn của `int`.
- Cách phát hiện: dùng `Math.multiplyExact(a, b)`. Hàm này **ném exception** (báo lỗi) khi tràn số thay vì trả về số sai. Nếu cần số thật lớn thì dùng `BigInteger`.

**2. Vì sao `fibonacci2` (đệ quy) chậm?**
Mỗi lần gọi lại sinh ra 2 lời gọi mới, nên cùng một giá trị bị **tính đi tính lại** nhiều lần:

```
fib(5)
├── fib(4)
│   ├── fib(3)
│   │   ├── fib(2) ── fib(1), fib(0)
│   │   └── fib(1)
│   └── fib(2) ── fib(1), fib(0)
└── fib(3)
    ├── fib(2) ── fib(1), fib(0)
    └── fib(1)
```
Chỉ với `fib(5)`, `fib(2)` đã bị tính 3 lần và `fib(3)` bị tính 2 lần. Số lời gọi tăng theo **cấp số nhân** (khoảng 1,6^n): `fib(40)` cần khoảng 330 triệu lời gọi (0,56 giây), `fib(50)` cần khoảng 40 tỷ (hơn 1 phút).
Bản vòng lặp chỉ chạy n vòng, mỗi giá trị tính đúng 1 lần (độ phức tạp **O(n)**, tức thời gian tăng tuyến tính theo n).
Cách sửa mà vẫn giữ đệ quy: **memoization** (ghi nhớ kết quả đã tính vào mảng để lần sau dùng lại). Kỹ thuật này sẽ học sau.

### 🏋️ Bài luyện ngắn (tự làm để kiểm tra lại)
Đoán output, ghi mức tự tin ✅🤔❌, rồi mới chạy:
```java
System.out.println(10 / 4 + " " + 10 % 4 + " " + 10 / 4.0 + " " + (-10) % 4);
byte b = 127; b++; System.out.println(b);
for (int i = 0; i < 5; i++) { if (i % 2 == 0) continue; System.out.print(i + " "); }
```
| Câu | Đáp án của tôi | Tự tin | Kết quả thật |
|---|---|---|---|
| L1 | 2 2 2.0 -2 | ✅ | `2 2 2.5 -2` ❌ (3/4 đúng) |
| L2 | exception | ✅ | `-128` ❌ |
| L3 | 0 1 3 5 | ✅ | `1 3` ❌ |

## 🧪 Kiểm tra hiểu bài

**Kết quả đoán output (cả buổi):** Q1–Q5 đúng 2/5 · L1–L3 khoảng 0,75/3 · C1–C3 khoảng 1,75/3.
⚠️ Có **6 câu ghi "chắc chắn/✅" nhưng sai**, và 2 lần quên ghi mức tự tin → cần **kẻ bảng lần theo** trước khi ghi ✅.

**C2 (bẫy quan trọng):** `long result = big * 2;` ra `-294967296`. Java tính vế phải bằng kiểu của **toán hạng** (`int * int`), tràn số xong mới chuyển sang `long`. Sửa: `big * 2L` hoặc `(long) big * 2`.

**4 câu khái niệm (Claude trả lời hộ, học viên chưa tự trả lời):**
1. `while` khi **không biết trước số vòng** (lặp *cho đến khi* n == 0 / b == 0): `toBase`, `sumDigits`, `reverseNumber`, `gcdLoop`. `for` khi biết trước số vòng.
2. Đệ quy cần **base case** + **bước thu nhỏ tiến về base case**. Thiếu/không chạm base case → mỗi lời gọi thêm 1 stack frame → `StackOverflowError` (`fibonacciRecursive(-1)`).
3. Ước số đi theo cặp a × b = n; nếu a ≤ b thì a ≤ √n → có ước thì chắc chắn có ước ≤ √n.
4. `x` vẫn là `-45`: pass-by-value, hàm sửa **bản sao** `n`. Kết quả trả về phải hứng lại: `int r = reverseNumber(x);`.

**Bài luyện thêm `sumTo` (Claude viết hộ):** đã thêm vào `ControlFlowPractice.java` (base case `n == 0`, bước thu nhỏ `n + sumTo(n - 1)`, chặn `n < 0` bằng `IllegalArgumentException`).

## 📝 Tổng kết (Claude viết hộ theo yêu cầu; nên tự đọc lại và viết lại bằng lời của mình)
- **Chọn vòng lặp:** `for` = lặp N lần; `while` = lặp cho đến khi điều kiện sai; `do-while` = chạy ít nhất 1 lần (Q5).
- `continue` = bỏ phần còn lại của vòng **hiện tại**; `break` = **thoát hẳn** vòng lặp.
- **`/` và `%`:** `int / int` = chia nguyên; `%` = số dư, dấu theo số bị chia (`-7 % 2 = -1`). Có 1 số `double` thì kết quả là `double` (in ra `9.0`).
- **Bóc chữ số:** `n % 10` lấy chữ số cuối, `n / 10` bỏ chữ số cuối.
- **Overflow:** `int`/`long`/`byte` vượt giới hạn thì quay vòng, **không báo lỗi**. Kiểu phép tính do **toán hạng** quyết định, không do biến nhận. Dùng `long`, `2L`, `Math.multiplyExact` khi cần.
- **Method:** pass-by-value → sửa tham số không ảnh hưởng biến bên ngoài; muốn dùng kết quả thì `return` và hứng lại.
- **Đệ quy:** cần base case + bước thu nhỏ; đệ quy "rẽ đôi" như Fibonacci tính lặp lại rất nhiều → chậm theo cấp số nhân; vòng lặp O(n).
- **Kiểm tra số nguyên tố:** chỉ cần thử đến √n (viết `i <= n / i`).
