public class test {
    public static void main(String args[])
    {
        byte[] arr = new byte[3];
        byte a = 61 ;
        for(int i = 0 ; i < 3 ; i++)
        {
            arr[i] = (byte)(a + (byte)i) ;
        }

        sha256Test1 sha256 = sha256Test1.getInstance();
        byte[] result = sha256.hash(arr);

        String deepak = "";

        for(int i = 0 ; i < result.length ; i++)
        {
            deepak = deepak + Integer.toHexString((int)(result[i]));
            //System.out.print(result[i] + " ");
        }

        System.out.println(deepak);
    }
}
