package com.example.lambda.mylambda.myinterface;

import com.example.lambda.mylambda.myinterface.bean.Cat;
import com.example.lambda.mylambda.myinterface.bean.Dish;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Test {
    public static String setName_static(String name) {
        return name.toLowerCase();
    }

    public String setName_test(String name) {
        return name.toLowerCase() + "外部对象";
    }

    static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, "肉"),
            new Dish("beef", false, 700, "肉"),
            new Dish("chicken", false, 400, "肉"),
            new Dish("french fries", true, 530, "其他"),
            new Dish("rice", true, 350, "其他"),
            new Dish("season fruit", true, 120, "其他"),
            new Dish("pizza", true, 550, "其他"),
            new Dish("prawns", false, 300, "鱼"),
            new Dish("salmon", false, 450, "鱼"));

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程的匿名函数");
            }
        });

        LockSupport.park();
        Runnable runnable = () -> System.out.println("线程的匿名函数");
        Thread thread1 = new Thread(() -> System.out.println("线程的匿名函数"));
        Thread thread2 = new Thread(runnable);


        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<String> callable = () -> UUID.randomUUID().toString();
        executorService.submit(callable);
        executorService.submit(() -> UUID.randomUUID().toString());

        List<Cat> cats = new ArrayList<>();
        cats.sort(new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return o1.getAge() - o2.getAge() * 2;
            }
        });
        cats.sort((o1, o2) -> o1.getAge() - o2.getAge() * 2);
        cats.add(new Cat(15, "15岁的猫", 15));
        cats.add(new Cat(15, "15岁的猫", 10));
        cats.add(new Cat(20, "20岁的猫", 20));
        cats.add(Cat.build(2, "2岁的吉吉", 2, Cat::new));
        cats.add(new Cat(10, "10岁的猫", 10));


        cats.sort(comparing(Cat::getAge));

        cats.sort(comparing(Cat::getAge).thenComparing(Cat::getWeight).reversed().thenComparing(Cat::getName));

        System.out.println(cats);
        cats.sort(comparing(Cat::getName));
        cats.sort(comparing(Cat::getAge).reversed());
        Test test = new Test();
        /* Cat cat=new Cat();*/
        Cat cat = Cat.build(Cat::new);
        Cat cat2 = Cat.build(2, "吉吉", Cat::new);
        System.out.println(cat2.toString());
        cat.otherName("otherName", Test::setName_static);
        System.out.println(cat.getName());
        cat.otherName2(String::toUpperCase);
        System.out.println(cat.getName());
        cat.otherName2(test::setName_test);
        System.out.println(cat.getName());

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);


        Predicate<Cat> catPredicate = a -> a.getAge() < 15;
        //negate()是非的意思，也就是>15
        Predicate<Cat> catPredicate1 = catPredicate
                .negate()
                .and(a -> a.getWeight() < 10)
                .or(a -> a.getName().equals("吉吉"));
        Function<String, String> function = s -> "琪琪的猫：" + s;
        Function<String, String> function2 = function.andThen(s -> "魔女宅急便的：" + s).andThen(s -> "宫崎骏创作的：" + s);
        List<Cat> cats2 = new ArrayList<>();
        cats2.add(Cat.build2(30, "吉吉", 15, catPredicate1, function2));
        cats2.add(Cat.build2(14, "吉吉2", 9, catPredicate1, function2));
        cats2.add(Cat.build2(16, "吉吉3", 8, catPredicate1, function2));
        cats2.add(Cat.build2(17, "吉吉4", 15, catPredicate1, function2));

        System.out.println(cats2);


        List<String> names = menu.stream()
                .filter(d -> d.getCalories() > 300)//过滤
                .sorted(comparing(Dish::getCalories))//按照Calories排序
                .skip(2)//跳过前2个元素
                .distinct()//去重
                .map(Dish::getName)//只提取name
                .limit(3) //只拿前三条
                .collect(toList());//把数据装到list中

        names.forEach(s -> System.out.println(s));

        long count = names.stream().count();

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(toList());

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());

        List<String> strs=Arrays.asList("Hello","World");

        List<String> strs2 = strs.stream().map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(strs2);

        Optional<Dish> any = menu.stream().filter(Dish::isVegetarian)
                .findAny();
        any.ifPresent(d-> System.out.println(d.getName()));

        Optional.ofNullable(strs2).filter(c->c.size()>0).orElse(new ArrayList<>());

        String teststr="Test";

        ofNullable(teststr).filter(s->s.length()>0).orElse("字符串为空");



        ofNullable(teststr)
                .filter(s -> s.length() > 0)
                .map(s->ofNullable(strs.get(0))
                        .orElse("字符串为空"))
                .orElse("字符串为空");

        Optional.ofNullable(strs2).orElseThrow(() -> new RuntimeException("集合为空"));

        List<Integer> ints=Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        OptionalInt calories1 = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        OptionalInt calories2 = menu.stream()
                .mapToInt(Dish::getCalories)
                .min();
        Integer reduce = ints.stream().reduce(0, (a, b) -> a + b);

        System.out.println(reduce);
        Optional<Integer> max = ints.stream().reduce(Integer::max);
        Optional<Integer> min = ints.stream().reduce(Integer::min);

        System.out.println(max.get());

        System.out.println(min.get());
        System.out.println("-------------");
        IntStream.range(0, 3).forEach(i-> System.out.println(i));
        System.out.println("--------------");
        IntStream.rangeClosed(0, 3).forEach(i-> System.out.println(i));

        System.out.println(menu.stream()
                .anyMatch(s->StringUtils.equals(s.getType(),"肉")));

        System.out.println(menu.stream()
                .allMatch(s->StringUtils.equals(s.getType(),"肉")));

        System.out.println(menu.stream()
                .noneMatch(s->StringUtils.equals(s.getType(),"肉")));

        System.out.println(menu.stream()
                .filter(s->StringUtils.equals(s.getType(),"肉")).findAny());

        System.out.println(menu.stream()
                .filter(s->StringUtils.equals(s.getType(),"肉")).findFirst());

        Stream<String> stream = Stream.of("java8", "python", "shell");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        Map<String, List<Dish>> collect = menu.stream().collect(groupingBy(Dish::getType));

        Set<Dish> collect1 = menu.stream().collect(Collectors.toSet());
        Double collect2 = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));

        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining("---->"));
    }

}
