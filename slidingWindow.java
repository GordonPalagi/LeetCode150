import java.util.*;

class SlidingWindow {

    public static int lengthOfLongestSubstring(String s) {
        Map<Integer, Character> charFrequencyMap = new HashMap<>();
        int maxLength = 0;
        int anchor = 0;
        if (s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        if (s.length() == 2) {
            if (s.charAt(0) != s.charAt(1)) {
                return 2;
            } else return 1;
        }

        for (int i = 0; i < s.length(); i++) {
            if (charFrequencyMap.containsValue(s.charAt(i))) {
                charFrequencyMap.clear();
                i = anchor++;
            } else {
                charFrequencyMap.put(i, s.charAt(i));
                maxLength = Math.max(maxLength, charFrequencyMap.size());
            }
        }
        maxLength = Math.max(maxLength, charFrequencyMap.size());
        return maxLength;
    }
    public static int longestContiguousSubArray(int[] arr, int k) {
        int maxValue = Integer.MIN_VALUE;
        int currentRunningSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currentRunningSum += arr[i];
            if (i >= k - 1) {
                maxValue = Math.max(maxValue, currentRunningSum);
                currentRunningSum = arr[i - (k - 1)];
            }
        }
        return maxValue;
    }
    public static int smallestSubArray(int[] arr, int targetSum) {
        int minWindowSize = Integer.MAX_VALUE;
        int currentWindowSize = 0;
        int windowStart = 0;

        for (int i = 0; i < arr.length; i++) {
            currentWindowSize += arr[i];

            while (currentWindowSize >= targetSum) {
                minWindowSize = Math.min(minWindowSize, i - windowStart + 1);
                currentWindowSize -= arr[windowStart];
                windowStart++;
            }
        }


        return minWindowSize;
    }
    public static int longestStringKDistinct(String s, int k) {
        int maxLength = 0;
        int windowStart = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);

            while(charFrequencyMap.size() > k) {
                char leftChar = s.charAt(windowStart);
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                if (charFrequencyMap.get(leftChar) == 0) {
                    charFrequencyMap.remove(leftChar);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                if (n == 0) return true;
                n--;
            }
        }

        return n == 0;
    }
    public static boolean isValid(String s) {
        char[] vector = new char[s.length()];
        int position = 0;

        for(char chars : s.toCharArray()){
            switch(chars){
                case '(':
                    vector[position++] = ')';
                    break;
                case '{':
                    vector[position++] = '}';
                    break;
                case '[':
                    vector[position++] = ']';
                    break;
                default:
                    if(position == 0 || vector[--position] != chars){
                        return false;
                    }
                    break;
            }
        }
        return position == 0;
    }
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length <= 1) return nums;
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer num : map.keySet()) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 3;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
}