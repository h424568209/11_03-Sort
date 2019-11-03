import org.omg.CORBA.INTERNAL;

import java.util.Map;

/**
 * BucketSort -- 桶排序
 * 桶代表的是一个区间范围，每个桶的区间长度哟版都是一样的，比如说给定数组[1,5,7,10]，这里如果分为10个桶，则每个桶的长度都是1，等同于每个桶都代表一个数，如果分一个桶，则这个桶的范围就是1-10
 * 基本来说，桶的范围和个数是由数组中的最大值，最小值以及数组中的元素个数来决定，这样可以保证使用最少的桶覆盖所有的可能性
 * 这个题目要求我们求数组排好序后，相邻数的最大差值，首先遍历数组得到最大和最小的值，若数组排好序后数组中的元素都是等间隔的
 * 在数组长度、最大值、最小值确定的情况下，在这种等间隔情况下，求得的相邻数的最大差值是最小的
 * 因此，如果我们按这个等量分配的长度来定义桶的长度的话，我们不需要考虑桶内元素的差值
 * 我们要做的就是记录桶中所有元素的最大值和最小值，然后拿这两个值去和梁林的桶的最大值最小值最差
 *这样可以保证时间复杂度是O（n）的
 */
public class BucketSort {
    private class Bucket{
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
    }
    public  int maximumGap(int []nums){
        if(nums.length <2){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        //找到数组中最大和最小的元素
        for(int i :nums){
            min = Math.min(min,i);
            max = Math.max(max,i);
        }
        //n个数的情况下，形成的两两相邻区间是n-1个
        //因此 桶长度 = 区间总长度/区间总个数
        int bucketSize = Math.max(1,(max - min)/(nums.length-1));
        //桶个数 = 桶区间/桶长度+1（开闭区间的问题，最后一个元素是开区间，所以需要加一个桶）
        Bucket[] bucket = new Bucket[(max - min)/bucketSize+1];
        for(int i = 0 ; i <nums.length; i++){
            int lc  = (nums[i]- min)/bucketSize;
            if(bucket[lc] == null){
                bucket[lc] = new Bucket();
            }
            bucket[lc].min = Math.min(bucket[lc].min,nums[i]);
            bucket[lc].max = Math.max(bucket[lc].max,nums[i]);
        }
        int prbeiousMax = Integer.MAX_VALUE;
        int maxGap = Integer.MIN_VALUE;
        for(int i = 0 ; i < bucket.length ; i++){
            if(bucket[i]!=null && prbeiousMax!=Integer.MAX_VALUE){
                maxGap = Math.max(maxGap,bucket[i].min - prbeiousMax);
            }
            if(bucket[i]!=null){
                prbeiousMax = bucket[i].max;
                maxGap = Math.max(maxGap,bucket[i].max - bucket[i].min );
            }
        }
        return maxGap;
    }

    public static void main(String[] args) {
        int arr[] = {2,3,1,5,6,86,23,1,2,0};
        BucketSort b = new BucketSort();
        System.out.println(b.maximumGap(arr));
    }
}
