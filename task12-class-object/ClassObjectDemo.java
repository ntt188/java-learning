// Task #12: class và object
// Bài 2: viết class Student bên dưới, rồi tạo object trong main.

public class ClassObjectDemo {

    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Alice";
        s1.age = 20;

        Student s2 = new Student();
        s2.name = "Bob";
        s2.age = 22;

        System.out.println(s1.name + ", " + s1.age);
        System.out.println(s2.name + ", " + s2.age);

        System.out.println(s1.introduce());
        System.out.println(s2.introduce());
    }
}

class Student {
    String name;
    int age;

    String introduce() {
        return "Tôi là " + name + ", " + age + " tuổi";
    }
}