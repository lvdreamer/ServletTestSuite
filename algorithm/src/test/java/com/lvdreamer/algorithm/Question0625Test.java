package com.lvdreamer.algorithm;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Question0625Test {
    /**
     * 有一天，小Q发现了n个被上锁的宝箱和m串钥匙。第i个宝箱上写着一个整数a，第串钥匙上写着一个整数b。 小Q已经通过一些古籍得知了这些宝箱内有很多的珍贵的宝物。所以他想尽可能多地打开这些宝箱。
     * 当且仅当第i个宝箱上的数字ai;与第j串钥匙上的数字bj;之和为奇数的时候，这个宝箱才能被这串钥匙打开。每个宝箱只能被打开一次,且每一串钥匙也只能被使用一次。
     * 现在小Q想知道他最多能开多少个宝箱,请你帮他计算出这个结果。
     * 输入描述:
     * 第一行两个整数，n和m,表示宝箱的数量和钥匙的数量
     * 第二行n个整数，a1,a2.-an, 表示每个宝箱上的数字
     * 第三行m个整数，b2,2…b. 表m示每个钥匙上的数字每两个数字之间用一个空格分隔
     * 满足1<=n,m<=10^ 5,1<=ai<=10^ 9,1<=bi<=10^ 9
     * 输出描述:
     * 一个整数，表示最多能打开的宝箱的数量
     */
    @Test
    public void solution() {
        System.out.println(solution(new int[]{1, 2, 3}, new int[]{4, 5, 6}));
    }

    private Integer solution(int[] arrA, int[] arrB) {
        int oddNumerOfA = 0;
        int evenNumberOfA = 0;
        int oddNumberOfB = 0;
        int evenNumberOfB = 0;
        int sizeOfA = arrA.length;
        int sizeOfB = arrB.length;
        for (int i = 0; i < sizeOfA; i++) {
            if ((arrA[i] & 1) == 1) {
                oddNumerOfA++;

            } else {
                evenNumberOfA++;
            }
        }
        for (int i = 0; i < sizeOfB; i++) {
            if ((arrB[0] & 1) == 1) {
                oddNumberOfB++;
            } else {
                evenNumberOfB++;
            }
        }
        return (oddNumerOfA < evenNumberOfB ? oddNumerOfA : evenNumberOfB) + (evenNumberOfA < oddNumberOfB ? evenNumberOfA : oddNumberOfB);
    }

    /**
     * 鹅厂的下午茶时间！很多人都去公司楼下的星巴克买咖啡，由于买咖啡的人很多，所以就排起了长长的队伍。
     * <p>
     * 队伍当中有 n 个顾客，从 1 到 n 标号，一开始，每个顾客 i 在队伍当中的位置是 i。
     * 每个顾客有两个属性 ai和 bi 。每个顾客的不满意度等于站在他前面的人与 ai的乘积，加上站在他后面的人与 bi 的乘积。
     * 正式来说，假设顾客位于位置 j，那么它的不满意度等于 ai(j-1)+bi(n-j) 作为咖啡店的经理，本着顾客至上的原则，你需要重新安排每个顾客的位置，使得所有的顾客的不满意度的总和最小。
     * 思路:以ai - bi的值作为排序，差值越大说明放在前面的性价比就越高
     */
    @Test
    public void solution2() {
        List<Customer> orginalOrder = Arrays.asList(new Customer(1, 2), new Customer(2, 1));
        List<Customer> bestOrder = orginalOrder.stream().sorted((o1, o2) ->
                (o2.getA() - o2.getB()) - (o1.getA() - o1.getB())
        ).collect(Collectors.toList());
        int custNum = bestOrder.size();
        long score = 0;
        long orginalScore = 0;
        for (int i = 1; i <= custNum; i++) {
            score += bestOrder.get(i - 1).getA() * (i - 1) + bestOrder.get(i - 1).getB() * (custNum - i);
        }
        for (int i = 1; i <= custNum; i++) {
            orginalScore += orginalOrder.get(i - 1).getA() * (i - 1) + orginalOrder.get(i - 1).getB() * (custNum - i);
        }
        System.out.println(orginalScore);
        System.out.println(bestOrder);
        System.out.println(score);

    }

    class Customer {
        private Integer a;
        private Integer b;

        Customer(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }

        public Integer getA() {
            return a;
        }

        public void setA(Integer a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }


    @Test
    public void mySqrtTest() {
        System.out.println(mySqrt(8));
    }

    public int mySqrt(int x) {
        if (x <= 1) {
            return 1;
        }
        int low = 1;
        int hight = x;
        while (low <= hight) {
            int middle = low + (hight - low) / 2;
            int sqr = x / middle;
            if (sqr == middle) {
                return middle;
            }
            if (sqr < middle) {
                hight = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return hight;

    }

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (o1, o2) -> o1[1] - o2[1]);
        int arrows = 1;
        int startPos = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= startPos) {
                continue;
            }
            startPos = points[i][1];
            arrows++;
        }
        return arrows;
    }

    @Test
    public void maxProfitTest() {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public int maxProfit(int[] prices) {

        int max = 0;
        int pLen = prices.length;
        if (pLen == 0) return 0;
        for (int i = 1; i < pLen; i++) {
            if (prices[i] > prices[i - 1]) {
                max = max + prices[i] - prices[i - 1];
            }
        }
        return max;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int cnt = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) continue;
            int pre = i == 0 ? 0 : flowerbed[i - 1];
            int next = i == flowerbed.length - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 & next == 0) {
                cnt++;
                flowerbed[i] = 1;
            }
        }
        return cnt >= n;
    }

    public List<Integer> partitionLabels(String S) {
        int[] lastChatIndex = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastChatIndex[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partCnt = new ArrayList<>();
        int startPos = 0;
        while (startPos < S.length()) {
            int endPos = startPos;
            for (int i = startPos; i < S.length() && i <= endPos; i++) {
                int lastPos = lastChatIndex[S.charAt(i) - 'a'];
                if (lastPos == i) continue;
                if (lastPos > endPos) {
                    endPos = lastPos;
                }
            }
            partCnt.add(endPos - startPos + 1);
            startPos = endPos + 1;

        }
        return partCnt;
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        List<int[]> newQueueList = new LinkedList<>();
        for (int[] p : people) {
            newQueueList.add(p[1], p);
        }
        int n = people.length;
        return newQueueList.toArray(new int[n][2]);

    }

    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) return new int[]{i + 1, j + 1};
            if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return null;
    }

    public String reverseVowels(String s) {
        HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'o', 'e', 'i', 'u', 'A', 'O', 'E', 'I', 'U'));
        if (s.length() == 0) return s;
        int i = 0, j = s.length() - 1;
        char[] reverseS = new char[s.length()];
        while (i <= j) {
            char lowChar = s.charAt(i);
            char hightChar = s.charAt(j);
            if (vowels.contains(lowChar) && vowels.contains(hightChar)) {
                reverseS[i] = hightChar;
                reverseS[j] = lowChar;
                i++;
                j--;
                continue;
            }
            if (!vowels.contains(lowChar)) {
                reverseS[i] = lowChar;
                i++;
            }
            if (!vowels.contains(hightChar)) {
                reverseS[j] = hightChar;
                j--;
            }
        }
        return new String(reverseS);
    }

    public boolean judgeSquareSum(int c) {
        int low = 0;
        int hight = (int) Math.sqrt(c);
        while (low <= hight) {
            int sum = low * low + hight * hight;
            if (sum == c) return true;
            if (sum < c) {
                low++;
            } else {
                hight--;
            }
        }
        return false;
    }

    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return validPalindrome(s, left, right - 1) || validPalindrome(s, left + 1, right);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean validPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int mIndex = m - 1;
        int nIndex = n - 1;
        int allIndex = m + n - 1;
        while (mIndex >= 0 || nIndex >= 0) {
            if (mIndex < 0) nums1[allIndex] = nums2[nIndex--];
            else if (nIndex < 0) nums1[allIndex] = nums1[mIndex--];
            else if (nums2[nIndex] > nums1[mIndex]) nums1[allIndex] = nums2[nIndex--];
            else nums1[allIndex] = nums1[mIndex--];
            allIndex--;
        }

    }

    public boolean hasCycle(ListNode head) {
        ListNode slowHeader = head;
        ListNode fastHeader = head.next;
        while (slowHeader != fastHeader) {
            if (fastHeader == null || fastHeader.next == null) {
                return false;
            }
            slowHeader = slowHeader.next;
            fastHeader = fastHeader.next.next;
        }
        return true;
    }

    public int findKthLargest(int[] nums, int k) {
        if (null == nums || nums.length == 0) return -1;
        partition(nums, 0, nums.length - 1);
        return nums[nums.length - k];

    }

    private void partition(int[] nums, int low, int high) {
        if (low > high) return;
        int key = nums[low];
        int i = low;
        int j = high;
        int swapTmp = 0;
        while (i < j) {
            while (nums[j] >= key && i < j) {
                j--;
            }
            while (nums[i] <= key && i < j) {
                i++;
            }
            if (i < j) {
                swapTmp = nums[i];
                nums[i] = nums[j];
                nums[j] = swapTmp;

            }
        }
        nums[low] = nums[i];
        nums[i] = key;
        partition(nums, low, i - 1);
        partition(nums, i + 1, high);
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> coutMap = new HashMap<>();
        for (int num : nums) {
            coutMap.put(num, coutMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> coutMap.get(a) - coutMap.get(b));
        for (int n : coutMap.keySet()) {
            priorityQueue.add(n);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            result.add(priorityQueue.poll());
        }
        Collections.reverse(result);
        return result;
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) max = Math.max(max, dfs(grid, i, j));

            }
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) return 0;
        if (grid[i][j] == 0) return 0;
        grid[i][j] = 0;
        return 1 + dfs(grid, i + 1, j) + dfs(grid, i - 1, j) + dfs(grid, i, j + 1) + dfs(grid, i, j - 1);
    }

    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                childDfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    public void childDfs(int[][] m, int[] visited, int i) {
        for (int j = 0; j < m.length; j++) {
            if (m[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                childDfs(m, visited, j);
            }
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> treePath = new ArrayList<>();
        if (null == root) return treePath;
        treeDfs(root, "", treePath);
        return treePath;
    }

    private void treeDfs(TreeNode root, String prefix, List<String> treePath) {
        if (null == root) return;
        if (root.left == null && root.right == null) {
            treePath.add(prefix + root.val);
            return;
        }
        prefix += (root.val + "->");
        treeDfs(root.left, prefix, treePath);
        treeDfs(root.right, prefix, treePath);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (null != digits && digits.length() != 0) {
            combination("", digits, 0, result);
        }
        return result;

    }

    private void combination(String prefix, String digits, int offset, List<String> result) {
        if (offset == digits.length()) {
            result.add(prefix);
            return;
        }
        String curr = KEYS[digits.charAt(offset) - '0'];
        for (char c : curr.toCharArray()) {
            combination(prefix + c, digits, offset + 1, result);
        }

    }

    public int[] dailyTemperatures(int[] T) {
        if (null == T || T.length == 0) {
            return T;
        }
        int[] rest = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int idx = stack.pop();
                rest[idx] = i - idx;
            }
            stack.push(i);
        }
        return rest;
    }

    public boolean isBalanced(TreeNode root) {

        Set<Boolean> flagSet = new HashSet<>();
        depth(root, flagSet);
        return flagSet.isEmpty();

    }

    public int depth(TreeNode root, Set<Boolean> flag) {
        if (null == root) return 0;
        int l = depth(root.left, flag);
        int r = depth(root.right, flag);
        if (Math.abs(r - l) > 1) {
            flag.add(false);
        }
        return 1 + Math.max(l, r);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (null == nums || nums.length == 0) {
            return null;
        }
        return buildBst(nums, 0, nums.length - 1);
    }

    public TreeNode buildBst(int[] nums, int left, int right) {
        if (left > right) return null;
        int middle = left + (right - left) / 2;
        TreeNode treeNode = new TreeNode(nums[middle]);
        treeNode.left = buildBst(nums, left, middle - 1);
        treeNode.right = buildBst(nums, middle + 1, right);
        return treeNode;
    }

    private int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        pathOfBinaryTree(root);
        return max;
    }

    public int pathOfBinaryTree(TreeNode rootNode) {
        if (null == rootNode) return 0;
        int left = pathOfBinaryTree(rootNode.left);
        int right = pathOfBinaryTree(rootNode.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

    /**
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;
            int sumval = l1Val + l2Val + carry;
            carry = sumval / 10;
            ListNode thisNode = new ListNode(sumval % 10);
            cursor.next = thisNode;
            cursor = thisNode;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {

                l2 = l2.next;
            }
        }
        return root.next;
    }

    @Test
    public void testReverse() {
        System.out.println(reverse(1534236469));
    }

    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        return (int) result == result ? (int) result : 0;
    }

    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int index = 0;
        while (index < n && chars[index] == ' ') {
            index++;
        }
        if (index == n) {
            return 0;
        }
        boolean sign = false;
        if (chars[index] == '-') {
            sign = true;
            index++;
        } else if (chars[index] == '+') {
            index++;
        } else if (!Character.isDigit(chars[index]))

        {
            return 0;
        }

        int result = 0;
        while (index < n && Character.isDigit(chars[index])) {
            int digit = chars[index] - '0';
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + digit;
            index++;
        }
        return sign ? -result : result;
    }

    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefixStr = strs[0];
        for (String str : strs) {
            while (!str.startsWith(prefixStr)) {
                if (prefixStr.length() == 1) {
                    return "";
                }
                prefixStr = prefixStr.substring(0, prefixStr.length() - 1);
            }
        }
        return prefixStr;
    }

    public boolean isValid(String s) {
        char[] charArr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : charArr) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char cStack = stack.pop();
                if (c == ')' && cStack != '(' ||
                        c == ']' && cStack != '[' ||
                        c == '}' && cStack != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int visit[] = new int[nums.length];
        permute(nums, result, new ArrayList<Integer>(), visit);
        return result;
    }

    public void permute(int[] nums, List<List<Integer>> result, ArrayList<Integer> tmpArray, int visit[]) {
        if (tmpArray.size() == nums.length) {
            result.add(new ArrayList<>(tmpArray));
        }
        for (int i = 0; i < nums.length; i++) {
            if (visit[i] == 1) continue;
            visit[i] = 1;
            tmpArray.add(nums[i]);
            permute(nums, result, tmpArray, visit);
            visit[i] = 0;
            tmpArray.remove(tmpArray.size() - 1);
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0)
            return ans;
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }
    public String reverseWords(String s) {
        char[] cs = s.toCharArray();
        //先反转整个字符串
        reverse(cs, 0, cs.length - 1);
        int i = 0, j = 0;
        while(j < s.length()) {
            while(j < s.length() && cs[j] != ' ') {
                j++;
            }
            reverse(cs,i, j - 1);
            //此时cs[j]为空格，下一个不为空格
            i = j + 1;
            j++;
        }
        return new String(cs);

    }

    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            ++start;
            --end;
        }
    }
}