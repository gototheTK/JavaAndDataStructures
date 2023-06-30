@FunctionalInterface
interface MyFunction {

    void myMetheod();

}

/**
 * LambdaEx2
 */
public class LambdaEx2 {

    public static void main(String[] args) {
        MyFunction f = () -> {
        }; // MyFunction f = (MyFunction) (()->{});
        Object obj = (MyFunction) (() -> {
        }); // Obejct타입으로 형변환이 생략됨
        String str = ((Object) (MyFunction) (() -> {
        })).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);
        System.out.println((MyFunction) (() -> {
        }));
        System.out.println(((Object) (MyFunction) (() -> {
        })).toString());// 에러
    }

}