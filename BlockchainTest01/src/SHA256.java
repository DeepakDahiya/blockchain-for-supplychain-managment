import java.lang.Object;
import java.math.BigInteger;

class SHA256
{


    //final static char[] hexCode = "0123456789ABCDEF".toCharArray();
    public static void main(String args[])
    {


        /*Converting ascii values of data stringg to binary numbers
          representing each character of string with 8 bits
        */


        String s = "abc";
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }

        System.out.println(binary);

        int i ;
        for(i = 0 ; i < binary.length() ; i++)
        {
            if(binary.charAt(i) == ' ')
            {
                binary.delete(i, i+1);
            }
        }

        String temp = binary.toString();

        int index = 0;
        String bin = temp;
        String[] hexString = new String[bin.length() / 4];
        for ( i = 0; i < bin.length() / 4; i++) {
            hexString[i] = "";
            for (int j = index; j < index + 4; j++) {
                hexString[i] += bin.charAt(j);
            }
            index += 4;
        }


        for(i = 0 ; i < bin.length()/4 ; i++)
        {
            System.out.print(hexString[i] + " ") ;
        }

        System.out.println();



        String[] result = DataConverter.binaryToHex(hexString);

        for(i = 0 ; i < bin.length()/4 ; i++)
        {
            //System.out.print(result[i] + " ") ;
        }
        System.out.println();

        String lol = "";
        for(i = 0 ; i < result.length ; i++)
        {
            lol = lol + result[i] ;
        }

        //System.out.println(lol);


        SHA256Digest sha256Digest = SHA256Digest.getInstance();
        //String s = temp ;
        byte[] data = DataConverter.parseHexBinary(lol);
        for(i = 0 ; i < data.length ; i++)
        {

        }
        //byte[] hash = sha256Digest.digest(data);
        int l = data.length;
        System.out.println(l);
        for( i = 0 ; i < data.length ; i++)
        {
            byte a  =  (byte)(data[i] / 16);
            byte b = (byte) (data[i] % 16) ;
            data[i] = (byte)((byte)(a*10) + (byte)b) ;
            //System.out.print(data[i] + " ");
        }
        byte[] hash = sha256Digest.digest(data);
        System.out.println();
        System.out.println(DataConverter.printHexBinary(hash));
        int b = 6;
        int c = 21 ;
        BigInteger a = new BigInteger("616263");

        //System.out.println(a);
    }


}