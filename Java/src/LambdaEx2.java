package Java.src;

/**
 * myFunction2
 */
interface myFunction2 {

    void myMethod();

}

public class LambdaEx2 {

    public static void main(String[] args) {

        myFunction2 f = () -> {
        }; // MyFunction f = (MyFunction)(()->{});
        Object obj = (myFunction2) (() -> {
        });
        String str = ((Object) (myFunction2) (() -> {
        })).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);

    }

}
