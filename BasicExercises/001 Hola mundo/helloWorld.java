/*1. Imprimir "Hola Mundo":
Escribe un programa que imprima "Hola Mundo" en la consola.*/
/*-------------------------------------------------------*/

public class Actividad1 {

	int[] array = new int[15];

	public int findMax(int[] array) {
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	public int findMin(int[] array) {
		int min = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}

	public void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Actividad1 actividad1 = new Actividad1();
		for (int i = 0; i < actividad1.array.length; i++) {
			actividad1.array[i] = (int) ((Math.random() * 50 + 1));
		}
		actividad1.printArray(actividad1.array);
		System.out.println("Max = " + actividad1.findMax(actividad1.array));
		System.out.println("Min = " + actividad1.findMin(actividad1.array));
	}
}


