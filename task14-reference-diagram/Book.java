// Task #13: class Book có fields & methods (chưa dùng constructor, Task #15 mới học)
public class Book {
    // ===== Fields: dữ liệu RIÊNG của mỗi object =====
    String title;
    String author;
    int pages;        // tổng số trang
    int currentPage;  // đang đọc tới trang mấy (mặc định 0)

    // ===== Methods: việc object làm được =====

    // Đọc thêm n trang. currentPage KHÔNG được vượt quá pages.
    // n <= 0 thì không làm gì.
    // read(Integer.MAX_VALUE)
    void read(int n) {
        if (n > (pages-currentPage)) {
            currentPage = pages;
        } else if (n > 0) {
            currentPage += n;
        }
    }

    // Đã đọc hết sách chưa?
    boolean isFinished() {
        if (currentPage >= pages) {
            return true;
        }
        return false;
    }

    // Phần trăm đã đọc (số nguyên, làm tròn xuống). pages == 0 thì trả 0.
    // Cẩn thận: int / int là chia nguyên!
    int progressPercent() {
        if (pages > 0) {
            return (currentPage * 100) / pages;
        }
        return 0;
    }

    // Trả về chuỗi dạng: "Clean Code - Robert Martin (120/300)"
    String info() {
        if (title != null && author != null) {
            return title + " - " + author + " (" + currentPage + "/" + pages + ")";
        }
        return "";
    }
}
