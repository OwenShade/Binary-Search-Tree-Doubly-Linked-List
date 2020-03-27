import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class driver {

	static String path = "C:\\Users\\owens\\OneDrive\\Documents\\second year\\ADS2\\AE2\\int20k.txt";

	public static Integer[] OpenFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader intReader = new BufferedReader(fr);

		int lines = 20000;
		Integer[] data = new Integer[20000];
		int i;

		for (i=0; i<lines; i++){
			String temp = intReader.readLine();
			data[i] = Integer.parseInt(temp);
		}

		intReader.close();
		return data;
	}

	public static void main(String[] args) throws IOException {
		DoublyLinkedList S_dll = new DoublyLinkedList();
		BinarySearchTree S_bst = new BinarySearchTree();

		int[] random = new int[100];
		for(int i = 0; i<100; i++){
			int randomInt = ThreadLocalRandom.current().nextInt(49999);
			random[i] = randomInt;
		}

		Integer[] values = OpenFile();
		for(Integer i : values){
			S_dll.add(i);
			S_bst.Add(i);
		}
		long sumtime = 0;
		long sumtime1 = 0;
		for(int i : random){
			long time1 = System.nanoTime();
			S_bst.IsElement(S_bst.root, i);
			long time2 = System.nanoTime();
			long timetaken = time2 - time1;
			sumtime = sumtime + timetaken;
		}


		for(int i : random){
			long time1 = System.nanoTime();
			S_dll.IsElement(i);
			long time2 = System.nanoTime();
			long timetaken = time2 - time1;
			sumtime1 = sumtime1 + timetaken;
		}


		long finaltime = sumtime / 100;
		long finaltime1 = sumtime1 / 100;

		System.out.println("Binary Tree Average Time: " + finaltime);
		System.out.println("Linked List Average Time: " + finaltime1);
		System.out.println("Binary Tree Number of Nodes: " + S_bst.SetSize(S_bst.root));
		System.out.println("Linked List Number of Nodes: " + S_dll.SetSize());
		System.out.println("Binary Tree Height: " + S_bst.Height(S_bst.root));
	}
}
