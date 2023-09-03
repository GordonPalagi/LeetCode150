import java.util.*;

public class WorkingWithArrays {

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
        
    }
}
