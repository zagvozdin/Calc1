
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.Double.NaN;

/**
 * Created by Admin on 12.05.2016.
 */
public class Calc1 {

    // Stack from Java
    public static Stack st = new Stack();


    public static void main(String[] args) {

        Scanner scn = null;

        try {
            if (args.length > 0) {
                scn = new Scanner(new File(args[0]));
            } else {
                scn = new Scanner(System.in);
                System.out.println("Ввод с клаиватуры:");
            }
        } catch (Exception e) {
            System.out.println("Проблема с файлом " + args[0]);
            System.exit(1);
        }

        // scanned line
        String cmdIn;
        // splitted string
        String[] cmdParts;
        // defines
        Map<String, Double> defs = new HashMap();

        // result of Math action
        double tmp;

        // lets's go
        while (true) {
            try {
                cmdIn = scn.nextLine();
            } catch (Exception e) {
                break;
            }
            // empty string, exit
            if (cmdIn.equals("")) break;
//            System.out.println(">>> " + cmdIn);

            cmdParts = cmdIn.split(" ");
            switch (cmdParts[0]) {
                case ("#"):
                    break;
                case ("DEFINE"):
                    if (defs.containsKey(cmdParts[1])) {
                        defs.replace(cmdParts[1], Double.parseDouble(cmdParts[2]));
                    } else {
                        defs.put(cmdParts[1], Double.parseDouble(cmdParts[2]));
                        // System.out.println("put " + cmdParts[1]);
                    }
                    break;
                case ("PUSH"):
                    try {
                        Double.parseDouble(cmdParts[1]);
                        zPush(cmdParts[1]);
                    } catch (Exception e) {
                        if (defs.get(cmdParts[1]) == null) {
                            // System.out.println(cmdParts[1] + " define not found");
                        } else {
                            // System.out.println("define found");
                            zPush(defs.get(cmdParts[1]));
                        }
                    }
                    break;
                case ("POP"):
                    zPop();
                    break;
                case ("PRINT"):
                    try {
                        tmp = zPop();
                        System.out.println(tmp);
                        zPush("" + tmp);
                    } catch (Exception e) {
                    }
                    break;
                case ("+"):
                    try {
                        tmp = zPop() + zPop();
                        zPush(tmp);
                    } catch (Exception e) {
                        System.out.println(cmdParts[0] + " err");
                    }
                    break;
                case ("-"):
                    try {
                        tmp = zPop() - zPop();
                        zPush(tmp);
                    } catch (Exception e) {
                        System.out.println(cmdParts[0] + " err");
                    }
                    break;
                case ("*"):
                    try {
                        tmp = zPop() * zPop();
                        zPush(tmp);
                    } catch (Exception e) {
                        System.out.println(cmdParts[0] + " err");
                    }
                    break;
                case ("/"):
                    try {
                        tmp = zPop() / zPop();
                        zPush(tmp);
                    } catch (Exception e) {
                        System.out.println(cmdParts[0] + " err");
                    }
                    break;
                case ("SQRT"):
                    try {
                        tmp = Math.sqrt(zPop());
                        zPush(tmp);
                    } catch (Exception e) {
                        System.out.println(cmdParts[0] + " err");
                    }
                    break;
                case ("LIST"):
                    System.out.println("LIST command");
                    Object[] arr = (Object [])st.toArray();
                    for (Object o : arr
                            ) {
                        System.out.println(o.toString());
                    }
                    break;
                default:
                    System.out.println("Нераспознанная команда");
            }
        }
        System.out.println("Окончание вычислений");
    }

    private static double zPop() {
        try {
            return Double.parseDouble(st.pop().toString());
        } catch (Exception e) {
            return NaN;
        }
    }

    private static void zPush(double val) {
        st.push(val);
    }

    private static void zPush(String val) {
        st.push(val);
    }

}
