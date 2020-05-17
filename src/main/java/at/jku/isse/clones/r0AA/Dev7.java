package at.jku.isse.clones.r0AA;


import java.util.*;

/**
 * @author 4castle
 */
public class Dev7 {
    public static int run(String _pattern, int _num) {
        String s = _pattern;
        int k = _num;

        BitSet bits = readPancakes(s);
        return getNumFlips(bits, s.length(), k);
    }

    private static BitSet readPancakes(String pancakeStr) {
        BitSet bits = new BitSet(pancakeStr.length());
        for (int i = 0; i < pancakeStr.length(); i++) {
            bits.set(i, pancakeStr.charAt(i) == '+');
        }
        return bits;
    }

    private static int getNumFlips(BitSet bits, int size, int k) {
        // Breadth-first search algorithm
        Set<Node> S = new HashSet<>();
        Queue<Node> Q = new ArrayDeque<>();

        Node root = new Node(bits, null);
        S.add(root);
        Q.add(root);

        final int min = 0;
        final int max = size - k;

        while (!Q.isEmpty()) {
            Node current = Q.remove();
            // if all true
            if (current.value.cardinality() == size) {
                return current.depth();
            }
            for (int i = min; i <= max; i++) {
                Node n = new Node(flip(current.value, i, k), current);
                if (S.add(n)) {
                    Q.add(n);
                }
            }
        }
        return -1;
    }

    private static BitSet flip(BitSet bits, int fromIndex, int amount) {
        BitSet bitsCopy = (BitSet) bits.clone();
        bitsCopy.flip(fromIndex, fromIndex + amount);
        return bitsCopy;
    }

    private static class Node {

        public Node parent;
        public BitSet value;

        public Node(BitSet value, Node parent) {
            this.parent = parent;
            this.value = value;
        }

        @Override
        public int hashCode() {
            return this.value.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            final Node other = (Node) obj;
            return Objects.equals(this.value, other.value);
        }

        public int depth() {
            int num = 0;
            Node n = this;
            while (n.parent != null) {
                n = n.parent;
                num++;
            }
            return num;
        }
    }
}
