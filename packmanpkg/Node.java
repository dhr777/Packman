package packmanpkg;

class Node implements Comparable<Node> {
	int F;
	int G, H;
	int x;
	int y;
	Node parent;

	public int compareTo(Node a) {
		if (this.F > a.F)
			return 1;
		else
			return -1;
	}
}