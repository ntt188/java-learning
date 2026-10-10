# Task #10: P01 Todo CLI

## 🔮 Đoán xem: 
- Nên kiểm tra ở TaskService. vì ở đây sẽ kiểm tra rồi mới lưu giá trị ✅

## Bước 1:
- Người dùng có thể thêm các task mới của mình: add task học Java
- Người dùng có thể xem lại chi tiết task của mình:
    - Title: Học Java. Description: học bài 10. Status: chưa hoàn thành
- Người dùng có thể sữa thông tin task của mình như: Title, Description, Status
- Nguời dùng có thể xoá những task của mình.
- Người dùng có thể xem lại danh sách task của mình:
    - Title: Học Java.
    - Title: Đi siêu thị mua đồ.
    - Title: Dọn nhà.
- Người dùng không thể thêm một task có title là rỗng: để trông "" và chỉ có khoảng trắng "      "
- Người dùng không thể xoá task mà không tồn tại trong listtask

## Bước 2:
┌────────────────────────────────────────────┐
│                   Task                     │
├────────────────────────────────────────────┤
│ - id: int                                  │
│ - title: String                            │
│ - description: String                      │
│ - completed: boolean                       │
├────────────────────────────────────────────┤
│ + Task(id: int, title: String,             │
│        description: String)                │
│ + getId(): int                             │
│ + getTitle(): String                       │
│ + setTitle(title: String): void            │
│ + getDescription(): String                 │
│ + setDescription(description: String): void│
│ + isCompleted(): boolean                   │
│ + setCompleted(completed: boolean): void   │
└────────────────────────────────────────────┘


┌────────────────────────────────────────────┐
│              TaskRepository                │
├────────────────────────────────────────────┤
│ - tasks: List<Task>                        │
├────────────────────────────────────────────┤
│ + add(task: Task): void                    │
│ + findAll(): List<Task>                    │
│ + findById(id: int): Task                  │
│ + delete(id: int): boolean                 │
└────────────────────────────────────────────┘


┌──────────────────────────────────────────────┐
│                TaskService                   │
├──────────────────────────────────────────────┤
│ - repository: TaskRepository                 │
│ - nextId: int = 1                            │
├──────────────────────────────────────────────┤
│ + addTask(title, description): Task          │
│ + getAllTasks(): List<Task>                  │
│ + getTaskById(id: int): Task                 │
│ + updateTask(id, title, description): boolean│
│ + completeTask(id: int): boolean             │
│ + deleteTask(id: int): boolean               │
│ - validateTitle(title: String): boolean      │
│ - validateTask(task: Task): boolean          │
└──────────────────────────────────────────────┘

## Bước 3:
- Nhánh TÌM THẤY (`done 2`, task 2 có tồn tại):
Main
 │ completeTask(2)
 ▼
TaskService
 │ findById(2)
 ▼
TaskRepository ──return Task #2──► TaskService
                                     │ task.setCompleted(true)
                                     │ return true
                                     ▼
                                   Main: in "Đã hoàn thành task 2"

- Nhánh KHÔNG TÌM THẤY:
Main
 │
 │ completeTask(2)
 ▼
TaskService
 │
 │ findById(2)
 ▼
TaskRepository
 │
 │ return null
 ▼
TaskService
 │
 │ Phát hiện task == null
 │ return false
 ▼
Main
 │
 │ Nhận false
 │ In thông báo lỗi
 ▼
Error: Task with ID 2 not found.

## Bước 4:
-               STACK                         HEAP

   Repository.tasks ────────────────► ArrayList
                                         │
                                         │ [0]
                                         ▼
                                    ┌─────────────┐
                                    │   Task #1   │
                                    ├─────────────┤
                                    │ done = true │ ◄── Đã thay đổi!
                                    └─────────────┘
                                         ▲
                                         │
   TaskService.task ─────────────────────┘

- Kết luận: `TaskRepository.update(task)` KHÔNG cần.
  `findById` trả về ĐỊA CHỈ của Task nằm trong list (không phải bản sao).
  Service gọi `task.setCompleted(true)` là sửa thẳng vào object đó,
  nên list thấy thay đổi ngay. (Chỉ cần `update` khi lưu ra file/database.)

## ✏️ Bài luyện ngắn (bạn tự làm, kiểm tra lại ý 1 và 2)
1 2 Học OOP

## 🧪 Kiểm tra hiểu bài (bắt buộc ghi ✅🤔❌ cho từng câu)
### Câu 1:
- Không được vì như vậy mỗi lần để taọ một biến nextID và đều giá trị là 1 ✅
### Câu 2:
Bước	Class	Làm gì	Trả về
1	Main	gọi completeTask(5)	
2	TaskService gọi findById(5)
3 TaskRepository return về null
4 TaskService phát hiện task bằng null return false
5 Main nhận trả về false in ra lỗi
### Câu 3:
- Học Java
### Câu 4: ✅
- dòng này chạy ra lỗi.
- null
### Câu 5: 🤔
lỗi
dòng 2 là sửa title
dòng 3 là sửa cả đối tượng
### Câu 6: ✅
-  đặt ở TaskService
- có gọi thêm findAll

## Kiểm tra lại nhanh (trả lời 1 dòng, kèm ✅🤔❌):
- trả về null
- khác ở chỗ đây là in ra của t chứ ko lấy trong service

## 📝 Tổng kết 
1. 
  - task khai báo thông tin đối tượng
  - TaskRepository ô chứa các đối tượng
  - TaskService xử lý các tác vụ
2. Không cần `update()` vì `findById` trả về ĐỊA CHỈ của Task trong list, không phải bản sao.
   Service gọi `task.setCompleted(true)` là sửa thẳng vào ngôi nhà đó, nên list thấy ngay.
   Quy tắc: có dấu `.` → sửa object; tên biến ở vế trái `=` → chỉ đổi tờ giấy địa chỉ.
3. Chỗ mình vướng hôm nay:
   - Condition debugger viết `i=500` (gán) thay vì `i == 500` (so sánh).
   - Thiết kế quên chỗ tạo id → cần constructor `Task(id, ...)` + `nextId` trong Service.
   - Câu 5: nghĩ `t = null` phá luôn object → sai, nó chỉ xóa tờ giấy của `t`.
   - Gọi method trên biến `null` → KHÔNG trả về null mà ném `NullPointerException`.
   - Hay quên ghi ✅🤔❌.
   (Mục 2–3 do Claude viết hộ: đọc lại và tự viết lại bằng lời của mình.)