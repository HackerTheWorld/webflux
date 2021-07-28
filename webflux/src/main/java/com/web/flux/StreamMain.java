package com.web.flux;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamMain {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("a1");
        list1.add("b1");
        list1.add("c1");
        list1.add("d1");
        list1.add("e1");
        list1.add("f1");
        list1.add("g1");
        //同步流 list1.stream()
        // forEach是并行处理的，forEachOrder是按顺序处理
//        list1.stream().skip(1).forEach(s -> System.out.println(s));
        //并行流
        list1.parallelStream().skip(1).forEach(s -> System.out.println(s));
        //any在并行流随机
        System.out.println("findAny ::"+list1.parallelStream().findAny().get());
//        anyMatch表示，判断的条件里，任意一个元素成功，返回true
//        allMatch表示，判断条件里的元素，所有的都是，返回true
//        noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true

        List<String> list2 = new ArrayList<>();
        list2.add("a,1");
        list2.add("b,2");
        list2.add("c,3");
        list2.add("d,4");
        list2.add("e,5");
        Stream<String> stream = list2.parallelStream()
                .flatMap(s -> Stream.of(s.split(",")))
                .onClose(() -> System.out.println("stream close"));
        //聚合操作多个合并成一个
//        String str = stream.reduce((v1,v2) -> "{"+v1+":"+v2+"}").get();
//        System.out.println("reduce || "+str);
        //forEach count 终结方法
        stream.collect(Collectors.toList())
                .forEach(s -> System.out.println("list ::"+s));
        stream.close();

        BinaryOperator<Tuple2<String,Integer>> bin = (v1,v2) -> {
            if(v1 == null && v2 != null){
                return v2;
            }
            if(v2 == null && v1 != null){
                return v1;
            }
            if(!isFilter(v1) && !isFilter(v2)){
                return v1.mapT2(vl -> v1.getT2()+v2.getT2());
            }else if(isFilter(v1)){
                return v2;
            }else if(isFilter(v2)){
                return v1;
            }
            return null;
        };

        Stream<Tuple2<String,Integer>> tuple2Stream = Stream.of(Tuples.of("a",1), Tuples.of("b",2),
                Tuples.of("c",3), Tuples.of("a",2),
                Tuples.of("b",4),Tuples.of("b",4)).sorted();
//        Map<String, List<Tuple2<String,Integer>>> in
//                = tuple2Stream.collect(Collectors.groupingBy(v1 -> v1.getT1(),(o1) -> {
//            HashMap<String,Integer> map = new HashMap<>();
//            return map;
//                })
//        );
//        System.out.println(in);
    }

    private static boolean isFilter(Tuple2<String,Integer> tuple2){
        return tuple2.getT1().equals("b") && tuple2.getT2().equals(4);
    }
}

