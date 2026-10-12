# PROGRESS: Java learning (Claude đọc file này đầu mỗi phiên)

## Nguồn lộ trình
- Học **theo đúng thứ tự cột "Thứ tự"** trong database **✅ Nhiệm vụ (Tasks)** của Notion **"Thắng's Developer Learning OS"**.
  - Data source: `collection://d27ec295-8bbe-479e-b0af-952c16040ea7` (tổng 242 task, bắt đầu 06/10/2026).
  - Mỗi task có: Bước (Learn/Practice/Build/Review/Apply), Hạn, Thời gian (phút), Ghi chú (thường ghi tiêu chí "Xong khi").
- Chỉ truy vấn Notion khi cần xem task kế tiếp ngoài danh sách bên dưới, hoặc khi đồng bộ trạng thái.
- Thư mục bài mới đặt tên theo số task: `taskNN-ten-ngan/` (ví dụ `task08-control-flow/`).

## Trạng thái hiện tại (cập nhật 2026-10-10 16:25)
- **Task #10 XONG** (branch `task10-todo-design`, `Notes.md`: yêu cầu, sơ đồ 3 class, luồng `done 2` cả 2 nhánh, sơ đồ reference). Notion: CHƯA đổi trạng thái (chờ học viên đồng ý).
- **Bước tiếp theo: Task #11**: Weekly review tuần 1: điền trang Tuần trên Notion, cập nhật trạng thái topic, kế hoạch tuần 2. Review, 30 phút.
  - Mở đầu bằng 1 câu ôn (bắt buộc ✅🤔❌): `Task t = repo.findById(1); t = null; t.getTitle();` → chuyện gì xảy ra? (đáp án: `NullPointerException`, KHÔNG phải "trả về null").
- Việc tồn đọng (không bắt buộc): 📝 Tổng kết Bài 2 (`lesson02-fundamentals`) còn trống; tick README Task #6; viết lại 📝 Task #8, #9 và mục 2–3 📝 Task #10 bằng lời của mình (Claude viết hộ); sửa lỗi gõ "sữa"/"Nguời" trong Task #10 Notes.md.

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
| 8 | Control flow & methods: 10 bài | `task08-control-flow/` (65/65 PASS; đoán output yếu: Q 2/5, L ~0.75/3, C ~1.75/3; 🧪 + 📝 do Claude làm hộ) |
| 9 | Dùng Debugger | `task09-debugger/` (11/11 PASS; 🧪 ~1/5 + bổ sung 1/3; bài NaN 1/5; 📝 do Claude viết hộ) |
| 10 | P01 Todo CLI: thiết kế class | `task10-todo-design/` (🧪 3/3 + thêm 2/3; khởi động 1.5/2; Claude sửa hộ Bước 2–4 và viết hộ 📝 mục 2–3) |

## 5 task kế tiếp
| # | Task | Bước | Phút |
|---|---|---|---|
| 11 | Weekly review tuần 1: điền trang Tuần, cập nhật topic, kế hoạch tuần 2 | Review | 30 |
| 12 | Học class là gì; phân biệt class và object | Learn | 30 |
| 13 | Tạo class đơn giản Book với fields & methods | Practice | 30 |
| 14 | Tạo nhiều object từ một class; vẽ sơ đồ reference trỏ tới object | Practice | 20 |
| 15 | Hiểu constructor, constructor overloading & this | Learn | 30 |

(Tiếp theo #16–#32: OOP tuần 2: constructor/this, encapsulation, static/final, inheritance, polymorphism, abstract/interface, **#25 equals & hashCode**, enum/record, Maven + JUnit, P01.)

## Điểm yếu cần ôn (lồng vào bài mới)
1. **Biến reference chứa ĐỊA CHỈ** + quy tắc "vế trái của `=`" (tên biến = đổi địa chỉ; có `.`/`[ ]` = sửa object). Mới nắm (4/4), cần ôn ở Task #14 (sơ đồ reference) và OOP. Task #10: vẽ đúng sơ đồ 2 biến trỏ 1 Task, đúng câu `t = new Task(...)`, nhưng SAI khi có `null`: nghĩ `t = null` "sửa cả đối tượng" (🤔). Bài luyện cuối: `b = a; a = null; b.getTitle()` → đoán LỖI (sai, đúng là in "Học Java"); vẫn tưởng `= null` phá object. **Task #14 phải ôn lại đúng dạng này.**
2. `Object.equals()` mặc định = `==`; StringBuilder không override `equals()` (sai 3 lần). Ôn kỹ ở Task #25.
3. `null` khác `""`; quy tắc 2 bước "biến trước dấu chấm". Task #10: biết `null.getTitle()` lỗi nhưng sau đó lại ghi "trả về null" → phải nói được tên `NullPointerException`.
4. `split`: không cắt được thì trả về `[""]`.
5. Pass-by-value: gán tham số trong hàm thì bên ngoài không thấy.
6. Kiến thức nền còn hổng: 8 kiểu primitive (kích thước, mặc định), `final` khác immutable, Integer cache.
7. **Số học & overflow** (Task #8): `int / int` chia nguyên, `%` là số dư (dấu theo số bị chia); có `double` thì in `9.0`; tràn số quay vòng **không ném exception**; kiểu phép tính do **toán hạng** quyết định (`long r = big * 2` vẫn tràn → `big * 2L`). Sai cả Q1, Q2, L1, L2, C1, C2. Task #9: bắt được overflow `i * i` bằng conditional breakpoint nhưng lúc đầu giải thích sai ("long không đủ"); chưa biết chia `double` cho 0 ra `Infinity`/`NaN` (không exception), `NaN != NaN`; bỏ trống câu `(long)(a * b)`.
8. `continue`/`break`: đoán vòng lặp theo cảm giác, không lần từng bước (sai Q3, L3; C3 đúng).
9. **Debugger** (Task #9): dòng có breakpoint CHƯA chạy (sai 1 lần, sửa được ở câu A); Condition dùng `==` để dừng đúng 1 lần (viết `i > 500` ✅ mà sai); panel Frames = các hàm đang làm dở (chưa hiểu 2 lần, đã giải thích bằng sơ đồ). Ôn lại khi gặp debug ở các task sau. Task #10 khởi động: lại viết `i=500` (gán) thay vì `i == 500`; `i > 500` biết dừng ở 501 nhưng không biết dừng nhiều lần.

## Thói quen cần nhắc
- Quên xóa `// TODO` (4 lần; Task #8 đã nhớ xóa ✅). Quên ghi mức tự tin ✅🤔❌ (Task #8: quên 2 lần, ghi "chắc chắn" thay cho ✅).
- **Tự tin sai** (Task #8: 6 câu ✅ mà sai) → bắt buộc kẻ bảng `i | điều kiện | làm gì` trước khi ghi ✅. Task #9: quên ✅🤔❌ 2 lần liền, sau khi nhắc thì có ghi; vẫn 1 câu ✅ mà sai (`i > 500`).
- Hay nhờ "do it / note it" khi khó (Task #8: nhờ làm hộ 3 lần: câu hỏi thêm, 🧪, 📝 + sumTo). Khi đó Claude làm hộ nhưng giải thích kỹ và ra 1 bài luyện ngắn để kiểm tra lại. Task #9: "note it" 2 lần (phần ghi chép B.2/edge case/Bug 4 và 🧪 + 📝).
- Hay trả lời "chưa hiểu câu hỏi" khi câu hỏi trừu tượng → hỏi bằng ví dụ code cụ thể 3 dòng thì làm được (câu A đúng).
- Học tốt nhất qua: sơ đồ stack/heap, ví dụ "tờ giấy địa chỉ / ngôi nhà", lần theo từng bước, thí nghiệm sửa code rồi xem test FAIL.
- Task #10: vẫn quên ✅🤔❌ (≈5 lần trong 1 buổi). Khi mức tự tin có ghi thì khá chuẩn (câu sai duy nhất ghi 🤔). Nhờ "sửa hộ" Bước 2–4 và "viết tiếp" 📝 → đã làm hộ + ra bài luyện (bài luyện làm đúng `1 2 Học OOP`).
- Thiết kế: hay quên "dữ liệu này sinh ra ở đâu" (id) và quên đối chiếu thiết kế với yêu cầu Bước 1.
