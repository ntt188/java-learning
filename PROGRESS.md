# PROGRESS: Java learning (Claude đọc file này đầu mỗi phiên)

## Nguồn lộ trình
- Học **theo đúng thứ tự cột "Thứ tự"** trong database **✅ Nhiệm vụ (Tasks)** của Notion **"Thắng's Developer Learning OS"**.
  - Data source: `collection://d27ec295-8bbe-479e-b0af-952c16040ea7` (tổng 242 task, bắt đầu 06/10/2026).
  - Mỗi task có: Bước (Learn/Practice/Build/Review/Apply), Hạn, Thời gian (phút), Ghi chú (thường ghi tiêu chí "Xong khi").
- Chỉ truy vấn Notion khi cần xem task kế tiếp ngoài danh sách bên dưới, hoặc khi đồng bộ trạng thái.
- Thư mục bài mới đặt tên theo số task: `taskNN-ten-ngan/` (ví dụ `task08-control-flow/`).

## Trạng thái hiện tại (cập nhật 2026-10-09)
- **Đang làm: Task #8**: Control flow & methods: 10 bài nhỏ (FizzBuzz, số nguyên tố, Fibonacci, bảng cửu chương, đổi cơ số…). Practice, 60 phút.
  - Chưa bắt đầu. Tạo branch + thư mục `task08-control-flow/`.
- Việc tồn đọng (không bắt buộc): phần "📝 Tổng kết Bài 2" trong `lesson02-fundamentals/Notes.md` còn trống; tick README cho Task #6.

## Task đã xong (Notion)
| # | Task | Bằng chứng trong repo |
|---|---|---|
| 1 | Cài JDK 21, IntelliJ, Git | |
| 2 | 15 lệnh terminal | |
| 3 | Git init/add/commit/log | commit đầu tiên |
| 4 | Git branch/merge/conflict | branch `feature/*`, merge conflict HelloWorld |
| 5 | GitHub profile + README | README.md |
| 6 | Tự kiểm tra Java Fundamentals (10 câu) | `lesson02-fundamentals/` (3.35/10 → kiểm tra lại 4/4; câu sai ghi ở trang topic Java Fundamentals trên Notion) |
| 7 | String & StringBuilder: 5 bài | `lesson01-strings/` (77/77 PASS; kiểm tra cuối 3.1/5 → ôn lại 6/6) |

## 5 task kế tiếp
| # | Task | Bước | Phút |
|---|---|---|---|
| 8 | Control flow & methods: 10 bài nhỏ (FizzBuzz, số nguyên tố, Fibonacci, bảng cửu chương, đổi cơ số…) | Practice | 60 |
| 9 | Dùng Debugger: breakpoint, step into/over, watch, evaluate trên bài vừa làm | Practice | 30 |
| 10 | P01 Todo CLI: viết yêu cầu & thiết kế class (Task, TaskService, TaskRepository) trên giấy | Build | 60 |
| 11 | Weekly review tuần 1: điền trang Tuần, cập nhật topic, kế hoạch tuần 2 | Review | 30 |
| 12 | Học class là gì; phân biệt class và object | Learn | 30 |

(Tiếp theo #13–#32: OOP tuần 2: Book, constructor/this, encapsulation, static/final, inheritance, polymorphism, abstract/interface, **#25 equals & hashCode**, enum/record, Maven + JUnit, P01.)

## Điểm yếu cần ôn (lồng vào bài mới)
1. **Biến reference chứa ĐỊA CHỈ** + quy tắc "vế trái của `=`" (tên biến = đổi địa chỉ; có `.`/`[ ]` = sửa object). Mới nắm (4/4), cần ôn ở Task #14 (sơ đồ reference) và OOP.
2. `Object.equals()` mặc định = `==`; StringBuilder không override `equals()` (sai 3 lần). Ôn kỹ ở Task #25.
3. `null` khác `""`; quy tắc 2 bước "biến trước dấu chấm".
4. `split`: không cắt được thì trả về `[""]`.
5. Pass-by-value: gán tham số trong hàm thì bên ngoài không thấy.
6. Kiến thức nền còn hổng: 8 kiểu primitive (kích thước, mặc định), `final` khác immutable, Integer cache.

## Thói quen cần nhắc
- Quên xóa `// TODO` (4 lần). Quên ghi mức tự tin ✅🤔❌ khi làm quiz.
- Hay nhờ "do it / note it" khi khó. Khi đó Claude làm hộ nhưng giải thích kỹ và ra 1 bài luyện ngắn để kiểm tra lại.
- Học tốt nhất qua: sơ đồ stack/heap, ví dụ "tờ giấy địa chỉ / ngôi nhà", lần theo từng bước, thí nghiệm sửa code rồi xem test FAIL.
