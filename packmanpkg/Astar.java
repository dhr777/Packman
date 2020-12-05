package packmanpkg;

import java.util.PriorityQueue;

public class Astar {
	int x, y;
Astar(int x, int y) {
		this.x = x;
		this.y = y;
	}
Node node;
	Node enemy_node;
	Node boss_node;
	PriorityQueue<Node> open;
	PriorityQueue<Node> close;
	Node startnode;
	void astar() {
		open = new PriorityQueue<Node>();
		close = new PriorityQueue<Node>();

		startnode = new Node();
		startnode.G = 0;
		startnode.H = getH(x, y);
		startnode.x = this.x;
		startnode.y = this.y;
		startnode.F = startnode.G + startnode.H;
		startnode.parent = null;
		open.add(startnode);
		while (true) {

			if (open.isEmpty()) {
				System.out.println("Empty");
				System.exit(0);
			}

			node = open.poll();
			close.add(node);

			int true_false = addNode(node, open, close);
			if (true_false == 1) {
				ismove(open.peek());
				break;
			}
		}
	}
void ismove(Node node) {
	if (node.parent.parent == null) {
			enemy_node = node;
			boss_node = node;
			return;
		} else
			while (node.parent.parent.parent != null) {
				node = node.parent;
			}
		enemy_node = node.parent;
		boss_node = node;
	}
	Node getNode() {
		return enemy_node;
	}
	Node getBosstNode() {
		return boss_node;
	}
	int addNode(Node node, PriorityQueue<Node> open, PriorityQueue<Node> close) {
		Node node2;
		int x = node.x;
		int y = node.y, a;

		if (!Manager.isWall(Packman.arr[node.x][node.y + 1])) {
			node2 = new Node();
			node2 = input_node2(node2, x, y + 1);
			a = addNode_Exception(open, close, node2);
		
			if (a == 1)
				return 1;
		}
		if (!Manager.isWall(Packman.arr[node.x + 1][node.y])) {
			node2 = new Node();
			node2 = input_node2(node2, x + 1, y);
			a = addNode_Exception(open, close, node2);
			if (a == 1)
				return 1;
		}
		if (!Manager.isWall(Packman.arr[node.x][node.y - 1])) {

			node2 = new Node();
			node2 = input_node2(node2, x, y - 1);

			a = addNode_Exception(open, close, node2);
			if (a == 1)
				return 1;
		}
		if (!Manager.isWall(Packman.arr[node.x - 1][node.y])) {
			node2 = new Node();
			node2 = input_node2(node2, x - 1, y);

			a = addNode_Exception(open, close, node2);
			if (a == 1)
				return 1;
		}
		return 0;
	}
	Node input_node2(Node node2, int x, int y) {
		node2.y = y;
		node2.x = x;
		node2.parent = node;
		node2.H = getH(x, y);
		node2.G = (node.G + 1);
		node2.F = node2.H + node2.G;
		
	
		return node2;
	}
int addNode_Exception(PriorityQueue<Node> open, PriorityQueue<Node> close, Node node2) {
		if (node2.x == Packman.x && node2.y == Packman.y) {
			open.add(node2);
			return 1;
		}
		Node node3 = null;
		for (Node a : close) {
			if (a.x == node2.x && a.y == node2.y) {
				node3 = a;
				break;
			}

		}
	if (node3 != null)
			return 0;
		for (Node a : open)
			if (a.x == node2.x && a.y == node2.y) {
				if (node2.G < a.G)
					a = node2;
				break;
			}
		if (node3 == null)
			open.add(node2);
		return 0;
	}
	static int getH(int x, int y) {
		return Math.abs(Packman.x - x) + Math.abs(Packman.y - y);
	}
}
