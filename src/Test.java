import com.sun.org.apache.xpath.internal.objects.XNull;

        import java.io.InputStream;
        import java.util.Scanner;

public class Test {
    static Scanner sc = new Scanner(System.in);
    /*
    创建购物车（carts数组），将商品加入购物车
    */
    static Product carts[] = new Product[3]; //创建购物车（用数组存储）
    public static void main(String[] args) throws ClassNotFoundException {

        boolean bool = true;
        while (bool) {
            System.out.println("请输入用户名：");
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
                    bool = false;

                    while (true) {
                        System.out.println("购买商品请按1");
                        System.out.println("查看购物车2");
                        System.out.println("结账请按3");
                        System.out.println("退出请按4");

                        int choose = sc.nextInt();
                        if (choose == 1){
                            shopping(inProduct);

                        } else if (choose == 2) {
                            System.out.println("当前购物车商品如下：");
                            for (Product pro : carts) {
                                if (pro != null) {
                                    System.out.print(pro.getpId());
                                    System.out.print("\t" + pro.getpName());
                                    System.out.print("\t" + pro.getPrice());
                                    System.out.println("\t" + pro.getpDesc());
                                }
                            }
                        }else if (choose == 4){
                            break;
                        }

                    }
                    break;

                } else {
                    System.out.println("登入失败");
                }
            }
        }
    }
    public static void shopping(InputStream in) throws ClassNotFoundException {
        ReadProductExcel readProductExcel = new ReadProductExcel();
        Product products[] = readProductExcel.getAllProduct(in);
        for (Product product : products) {
            System.out.print(product.getpId());
            System.out.print("\t" + product.getpName());
            System.out.print("\t" + product.getPrice());
            System.out.println("\t" + product.getpDesc());
        }

        System.out.println("请输入商品ID，把该商品加入购物车");
        String pId = sc.next();
        int count = 0;

        ReadProductExcel readProductExcel1 = new ReadProductExcel();
        in = null;
        in = Class.forName("Test").getResourceAsStream("/product.xlsx");
        Product product = readProductExcel.getProductById(pId, in);
        if (product != null) {
            carts[count++] = product;  //将搜索到的商品存入购物车中
        }
    }
}
