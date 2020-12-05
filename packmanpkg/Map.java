package packmanpkg;

import java.io.FileReader;
import java.io.IOException;

public class Map {
	static final int WIDTH = 80; // ���� ����
	static final int HEIGHT = 20; // ���� ����
	char arr[][] = new char[HEIGHT][WIDTH];

	char[][] ReaderMap() throws IOException { // ���� �ҷ���.
		@SuppressWarnings("resource")
		FileReader fr = new FileReader("C:\\1231.txt");
		int n;
		int count = 0;
		char[] temp = new char[1000000];

		while ((n = fr.read()) != -1) {
			if ((char) n != '\n' && (char) n != '\r')
				temp[count++] = (char) n;
		}
		count = 0;
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				arr[i][j] = temp[count++];
			}
		}
		return arr;
	}
}
