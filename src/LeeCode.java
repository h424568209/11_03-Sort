import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeeCode {
    /**
     *  使用比较器
     * @param nums 数组
     * @return 数组的最大数
     */
    public String largestNumber(int[] nums) {
        if(nums.length == 0){
            return "";
        }
        String []res = new String[nums.length];
        for(int i = 0 ; i < nums.length ;i++){
            res[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(String s :res){
            sb.append(s);
        }
        String r = sb.toString();
        if(r.charAt(0)=='0'){
            return "0";
        }
        return r;
    }
    /**
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     * 分为三个部分
     * 1 找到要插入的数组在二维数组的位置
     * 2 合并并且插入到二维数组中
     * 3 将原二维数组的后面的元素继续放在list中，最后将list转为二维数组
     * @param intervals 二维数组
     * @param newInterval 待插入的数组
     * @return 插入后的二维数组
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res= new ArrayList<>();
        int i = 0 ;
        while(i<intervals.length && intervals[i][1]<newInterval[0]){
            res.add(intervals[i]);
            i++;
        }
        //使用一个临时数组存放新的数组合并后的值
        int []tem = new int[]{newInterval[0],newInterval[1]};
        //和二维数组中每个元素的第一个元素比较
        while(i<intervals.length && newInterval[1]>=intervals[i][0]){
            tem[0] = Math.min(tem[0],intervals[i][0]);
            tem[1] = Math.max(tem[1],intervals[i][1]);
            i++;
        }
        res.add(tem);
        while(i<intervals.length){
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int [0][]);
    }
    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     * @param intervals 二维数组
     * @return 重叠后后的二维数组
     */
    public int[][] merge(int[][] intervals) {
        List<int []> res = new ArrayList<>();
        if(intervals ==null||intervals.length ==0 ){
            return res.toArray(new int[0][]);
        }
        //根据二维数组的第一个数字大小按每一行整体排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int i = 0;
        //二维数组的长度是行数  int lenY = a.length;
        //获得列数 int lenX = a[0].length;
        while(i<intervals.length){
            int left = intervals[i][0];
            int right = intervals[i][1];
            //比较当前的右边的元素和下一行的左边元素的大小 若大于 则合并
            while(i<intervals.length-1 && right>=intervals[i+1][0]){
                i++;
                right = Math.max(right,intervals[i][1]);
            }
            res.add(new int[]{left,right});
            i++;
        }
        return res.toArray(new int[0][]);
    }
    /**
     * 数组1的大小小于1000
     * 所以可以将arr1中的元素出现次数存放在一个数组m的对应下标中
     * 之后遍历arr2，zaim中查找每个元素的出现次数，并且根据出现次数将元素存放在新的数组中
     * 将剩余的元素存放在数组中
     * @param arr1 数组1
     * @param arr2 数组2 ，其中的元素各不相同且每个元素都出现在arr1中
     * @return 按照arr2元素顺序排序的arr1，其中剩下的元素按照升序排序
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int []res=  new int[arr1.length];
        int []m = new int[1001];
        //在m中对应下标存放arr1中每个元素的出现次数 比如元素3出现了2次 则 m[3] = 2
        for(int i = 0 ; i < arr1.length ; i++){
            m[arr1[i]]++;
    }
        int cnt = 0;
        //遍历arr2 ，当，优先在res中存放arr2中元素在m中出现的次数
        //若arr1[i] == arr2[i] 则在数组m中对应的是同样的位置
        for(int i = 0 ; i <arr2.length ; i++){
           while(m[arr2[i]]>0){
               res[cnt ++] = arr2[i];
               m[arr2[i]]--;
           }
        }
        //遍历数组m 查找剩下的在arr1出现但是不存在在arr2中的元素 放在res中
        for(int i = 0 ; i < 1000 ; i++){
            while(m[i]>0){
                res[cnt++] = i;
                m[i]--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
      int[]  arr1 = {2,3,1,3,2,4,6,7,9,2,19};
      int arr2[] = {2,1,4,3,9,6};
        System.out.println(Arrays.toString(LeeCode.relativeSortArray(arr1, arr2)));
        int [][] a= {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int []b = {2,4};
        int [][] as= {{1,2},{3,5},{6,9},{8,13},{12,16}};

        LeeCode l = new LeeCode();
        System.out.println(Arrays.deepToString(l.merge(as)));
        System.out.println(Arrays.deepToString(l.insert(a, b)));
        System.out.println(l.largestNumber(b));
    }
}
