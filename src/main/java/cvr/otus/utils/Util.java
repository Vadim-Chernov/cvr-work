package cvr.otus.utils;


public class Util {
    public static int[] StringToIntArray(String str, String del) {
        String[] strArr = str.split(del);
        int[] nums = new int[strArr.length];
        int count = 0;
        for (String s : strArr)
            nums[count++] = Integer.parseInt(s);

        return nums;
    }

    private static boolean elementExists(int elem, int[] arr) {
        for (int e : arr)
            if (e == elem)
                return true;

        return false;
    }

    public static double examineArrays(int[] a1, int[] res) {
        double ret = 0;
        int len;
        if (a1.length > res.length)
            len = a1.length;
        else
            len = res.length;
        double delta = 5d / len;

        for (int elem : a1) {
            if (elementExists(elem, res))
                ret += delta;
        }
        return ret;
    }


}
