import java.util.Scanner;
import java.util.Random;

class GuessTheNumber{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		int value = 0;
		int randomValue= random.nextInt(10);
		do{
			String line = scanner.nextLine();
			value = Integer.parseInt(line);
			if (value > randomValue){
				System.out.println("Число меньше. Не угадал!");
			}else if (value < randomValue){
				System.out.println("Число больше. Не угадал!");
			}
		}while(value != randomValue);
		System.out.println("Угадал! Ваше значение " + value + " верно.");
	}
}