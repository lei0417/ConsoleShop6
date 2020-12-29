import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo = true;
        while (bo) {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("请输入密码：");
            String password = sc.next();

            //File file = new File("C:\\Users\\雷斌\\IdeaProjects\\ConsoleShop\\src\\users.xlsx");
            InputStream inUser = Class.forName("Test").getResourceAsStream("/users.xlsx");
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel(); //创建对象
            User users[] = readExcel.readExcel(inUser);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    //显示商品信息

                    System.out.println("登入成功");
                    System.out.println("商品信息如下：");
                    ReadProductExcel readProductExcel = new ReadProductExcel();
                    Product products[] = readProductExcel.readExcel(inProduct);
                    for (Product product : products) {
                        System.out.print(product.getpId());
                        System.out.print("\t" + product.getpName());
                        System.out.print("\t" + product.getPrice());
                        System.out.println("\t" + product.getpDesc());
                    }
                    //创建购物车（productes数组），将商品加入购物车
                    int count = 0;
                    Product productes[] = new Product[3];
                    System.out.println("请输入商品ID，把该商品加入购物车");
                    String pId = sc.next();
                    ReadProductExcel readProductExcel1 = new ReadProductExcel();
                    inProduct = null;
                    inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
                    Product product = readProductExcel.getProductById(pId,inProduct);
                    if(product != null){
                        productes[count++] = product;
                        System.out.println("商品加入购物车");
                    }

                    bo = false;
                    break;
                } else {
                    System.out.println("登入失败");
                }
            }
        }
    }
}
