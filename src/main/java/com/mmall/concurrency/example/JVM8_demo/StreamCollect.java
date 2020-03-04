package com.mmall.concurrency.example.JVM8_demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class StreamCollect {

    public static void main(String[] args) {
        new StreamCollect().group();
    }


    @Test
    public void test(){
        new StreamCollect().group();
    }

    public void group() {
        List<Order> original = new ArrayList<>();
        Order order = new Order();
        order.setId("1");
        order.setCompany("111");
        original.add(order);
        order = new Order();
        order.setId("2");
        order.setCompany("222");
        original.add(order);

        order = new Order();
        order.setId("1");
        order.setCompany("333");
        original.add(order);

        order = new Order();
        order.setId("3");
        order.setCompany("222");
        original.add(order);

        List<Function<Order, String>> list = new ArrayList<>();
        list.add(Order::getCompany);
        list.add(Order::getId);

        final List<List<Order>> results = new ArrayList<>();
        for (Function<Order, String> function : list) {
            Map<String, List<Order>> map = original.stream().collect(Collectors.groupingBy(function));
            List<Order> temp = new ArrayList<>();
            for (Map.Entry<String, List<Order>> entry : map.entrySet()) {
                List<Order> value = entry.getValue();
                temp.add(value.get(0));
            }
            results.add(temp);
        }

        results.forEach(System.out::println);
    }

}

class Order {
    private String id;
    private String company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String conpany) {
        this.company = conpany;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}