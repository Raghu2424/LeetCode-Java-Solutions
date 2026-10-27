class Solution {
    public String[] createGrid(int m, int n) {
        // Initialize the grid with all obstacles ('#')
        StringBuilder[] grid = new StringBuilder[m];

        for (int i = 0; i < m; ++i) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < n; ++j) {
                sb.append('#');
            }

            grid[i] = sb;
        }

        // Fill the first row with free cells ('.')
        for (int j = 0; j < n; ++j) {
            grid[0].setCharAt(j, '.');
        }

        // Fill the last column with free cells ('.')
        for (int i = 0; i < m; ++i) {
            grid[i].setCharAt(n - 1, '.');
        }

        // Convert StringBuilder array to String array
        String[] result = new String[m];

        for (int i = 0; i < m; ++i) {
            result[i] = grid[i].toString();
        }

        return result;
    }
}
