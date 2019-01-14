import org.omg.CORBA.INTERNAL;

public class SHA256V2 {
    public static void main(String args[])
    {
        final char hex[] = {'A' , 'B' , 'C' , 'D' , 'E' , 'F'};
        String s = "abc";
        String hexString = "";
        //Conversion of given string to hexadecimal string where each character is represented by two positions
        for(int i = 0 ; i < s.length() ; i++)
        {
            int a = s.charAt(i) / 16 ;
            int b = s.charAt(i) % 16 ;
            if(b < 10)
            {
                hexString = hexString + (a*10 + b);
            }
            else {
                hexString = hexString + a  ;
                hexString = hexString + hex[b-10];
            }

        }


        int a = Integer.decode("0x"+"A");
        int c = 0 ;
        int b = 0 ^ 1 ;

       // System.out.println("b = " + b + "    a  = " + a  );

        SHA256Util sha256Util = SHA256Util.getInstance();
        //String result =
        sha256Util.startHashing(hexString);
        int result = Integer.parseInt("21");
        //System.out.println(hexString);
    }
}
