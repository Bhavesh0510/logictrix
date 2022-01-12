import java.util.Scanner;

public class Check {
    public static void main(String[] args) {
        System.out.println("Hello\n");

        String s1 = "Boss",s2;
        int i1 = 7,i2;
        float f1 = 23.78f,f2;
        double d1 = 7.80,d2;

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter one String value: ");
        s2 = sc.next();

        System.out.print("Enter one Integer value: ");
        i2 = sc.nextInt();

        System.out.print("Enter one Float value: ");
        f2 = sc.nextFloat();

        System.out.print("Enter one Double value: ");
        d2 = sc.nextDouble();

            if (s1 == s2){
                System.out.println("String Matched");
            }
            else {
                System.out.println("String cannot matched using ==");
            }

        if(i1==i2){
            System.out.println("Integer value matched");
        }
        else{
            System.out.println("Integer value not matched");
        }

        if(d1==d2){
            System.out.println("Double value matched");
        }
        else{
            System.out.println("Double value not matched");
        }

        if(f1==f2){
            System.out.println("Float value matched");
        }
        else{
            System.out.println("Float value not matched");
        }

        if(s2.equals(s1)){
            System.out.println("String value matched using equals method");
        }
        else{
            System.out.println("String value not matched");
        }
    }
}
