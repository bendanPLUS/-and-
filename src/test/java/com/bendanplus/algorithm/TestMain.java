package com.bendanplus.algorithm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class TestMain {


    public static void main(String[] args) throws IOException {
      class MyNode {

            int shengao;
            int tizhong;


          public void setShengao(int shengao) {
              this.shengao = shengao;
          }

          public void setTizhong(int tizhong) {
              this.tizhong = tizhong;
          }

          public int getShengao() {
              return shengao;
          }

          public int getTizhong() {
              return tizhong;
          }
      }
        Scanner scanner = new Scanner(System.in);
        ArrayList<MyNode> list = new ArrayList<>();

        while (scanner.hasNextInt()) { // 注意 while 处理多个 case
            String string = scanner.nextLine();
            if ("".equals(string)) break;
            String s1 = string.split(" ")[0];
            int m1 = Integer.parseInt(s1);
            String s2 = string.split(" ")[1];
            int m2 = Integer.parseInt(s2);
            MyNode node = new MyNode();
            node.setShengao(m1);
            node.setTizhong(m2);
            list.add(node);
        }
        list.sort((o1, o2) -> o1.shengao != o2.shengao ? o2.shengao - o1.shengao : o2.tizhong - o1.tizhong);
        for (int j = 0; j < 10; j++) {

            String s1 = String.valueOf(list.get(j).shengao);
            String s2 = String.valueOf(list.get(j).tizhong);
            System.out.println(s1 + " " + s2);
        }


//        //读取一整行
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String str = in.readLine();
//        //转化成StringBuilder
//        StringBuilder sb = new StringBuilder(str);
//        //如果是数组则切分
//        String[] sa = str.split(" ");
//        //string 转 int
//        int n = Integer.parseInt(sa[0]);


        //多行输入 求输入的和
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }

//         每次拿一个数 : Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt();
    }


}
