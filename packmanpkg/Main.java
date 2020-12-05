package packmanpkg;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {


		Packman p = new Packman();

		Packman.print();
		while (true) {
			
		p.movement();
			Packman.print();

		}
	}
}
