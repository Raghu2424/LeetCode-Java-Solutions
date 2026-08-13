class Solution {
    static class Node {
        char leftChar;
        char rightChar;
        int prefix;
        int suffix;
        int max;

        Node() {}

        Node(char c) {
            leftChar = c;
            rightChar = c;
            prefix = 1;
            suffix = 1;
            max = 1;
        }
    }

    Node[] tree;
    char[] arr;

    private Node merge(Node left, Node right, int leftSize, int rightSize) {
        Node res = new Node();

        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        res.max = Math.max(left.max, right.max);

        if (left.rightChar == right.leftChar) {
            res.max = Math.max(res.max, left.suffix + right.prefix);
        }

        if (left.prefix == leftSize &&
            left.rightChar == right.leftChar) {
            res.prefix = leftSize + right.prefix;
        } else {
            res.prefix = left.prefix;
        }

        if (right.suffix == rightSize &&
            left.rightChar == right.leftChar) {
            res.suffix = rightSize + left.suffix;
        } else {
            res.suffix = right.suffix;
        }

        return res;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(arr[l]);
            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1],
            mid - l + 1,
            r - mid
        );
    }

    private void update(int node, int l, int r, int index, char value) {
        if (l == r) {
            arr[index] = value;
            tree[node] = new Node(value);
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1],
            mid - l + 1,
            r - mid
        );
    }

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {
        int n = s.length();
        int q = queryIndices.length;

        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] answer = new int[q];

        for (int i = 0; i < q; i++) {
            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            answer[i] = tree[1].max;
        }

        return answer;
    }
}
