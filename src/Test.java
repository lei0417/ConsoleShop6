import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        System.out.println("请输入用户名：");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        System.out.println("您输入的用户名是：" + username);

        System.out.println("请输入密码：");
        String password = sc.next();
        System.out.println("您输入的密码是：" + password);

        File file = new File("C:\\Users\\雷斌\\IdeaProjects\\ConsoleShop\\src\\users.xlsx");
        ReadExcel readExcel = new ReadExcel(); //创建对象
        readExcel.readExcel(file);
        User users[] = readExcel.readExcel(file);
        for (int i = 0;i<users.length;i++){
            if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())){
                System.out.println("登入成功");
                break;
            }else {
                System.out.println("登入失败");
            }
        }
    }
}
