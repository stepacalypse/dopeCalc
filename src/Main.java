import java.util.Scanner;

public class Main {

        public static void main(String[] args) throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите выражение из арабских или римских чисел от 1 до 10: ");
            String input = scanner.nextLine();
            System.out.println(calc(input));
        }
        public static String calc(String input) throws Exception {
            int num1;
            int num2;
            String operator;
            String result;
            boolean isRoman;
            String[] operands = input.split("[+\\-*/]");

            // проверим, что вводимое выражение содержит ровно 2 операнда, иначе выведем ошибку
            if (operands.length != 2) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

            // передадим в нашу переменную обнаруженный математический оператор в веденном выражении
            operator = detectOperation(input);
            // проверим, что если пользователь ввел неправильный оператор, то выведем ошибку
            if (operator == null) throw new Exception("Неподдерживаемая математическая операция");


            //проверяем, если оба числа римские
            if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
                //тогда конвертируем оба числа в арабские для вычесления действия
                num1 = Roman.convertToArabic(operands[0]);
                num2 = Roman.convertToArabic(operands[1]);
                isRoman = true;
            }
            //если оба числа арабские
            else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
                //тогда преобразуем в int для дальнейшего вычесления
                num1 = Integer.parseInt(operands[0]);
                num2 = Integer.parseInt(operands[1]);
                isRoman = false;
            }
            //если же одно число римское, а другое - арабское, то выведем ошибку
            else {
                throw new Exception("Используются одновременно разные системы счисления");
            }

            //проверим, что вводимые числа от 1 до 10
            if (num1 > 10 || num2 > 10) {
                throw new Exception("Числа должны быть от 1 до 10");
            }

            // создадим переменную, в которую передадим метод, который и вычислит наше выражение
            int arabic = calc(num1, num2, operator);

            if (isRoman) {
                //если римское число получилось меньше либо равно нулю, генерируем ошибку
                if (arabic <= 0) {
                    throw new Exception("В римской системе нет отрицательных чисел");
                }
                //конвертируем результат операции из арабского в римское
                result = Roman.convertToRoman(arabic);
            } else {
                //Конвертируем арабское число в тип String
                result = String.valueOf(arabic);
            }
            //возвращаем результат
            return result;
        }

        // создаем метод, который определит, какой операнд в введенном выражении
        static String detectOperation(String input) {
            if (input.contains("+")) return "+";
            else if (input.contains("-")) return "-";
            else if (input.contains("*")) return "*";
            else if (input.contains("/")) return "/";
            else return null;
        }

        static int calc(int a, int b, String operator) {

            if (operator.equals("+")) return a + b;
            else if (operator.equals("-")) return a - b;
            else if (operator.equals("*")) return a * b;
            else return a / b;
        }

    }

    class Roman {
        static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};


        public static boolean isRoman(String value) {
            for (int i = 0; i < romanArray.length; i++) {
                if (value.equals(romanArray[i])) {
                    return true;
                }
            }
            return false;
        }

        public static int convertToArabic(String roman) {
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;
                }
            }
            return -1;
        }

        public static String convertToRoman(int arabic) {
            return romanArray[arabic];
        }

    }


