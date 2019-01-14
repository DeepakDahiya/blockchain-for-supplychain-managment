public class SHA256Util {
    private static final SHA256Util sha256DUtil = new SHA256Util();

    private int numberOfBlocks  ;
    //private int blockCounter = 0 ;
    private String paddedMessage ;


    /**
     * mod 2^32 is achieved by & 0xFFFFFFFF
     */
    public static SHA256Util getInstance() {
        return sha256DUtil;
    }

    /**
     * Private constructor to avoid initialization outside this class.
     */
    private SHA256Util() {
    }

    /**
     * Initial H values. These are the first 32
     * bits of the fractional parts of the square
     * roots of the first eight primes.
     */
    private static final int[] H = {
            0x6a09e667,
            0xbb67ae85,
            0x3c6ef372,
            0xa54ff53a,
            0x510e527f,
            0x9b05688c,
            0x1f83d9ab,
            0x5be0cd19
    };

    /**
     * Initial K values. These are the first 32
     * bits of the fractional parts of the cube root
     * of the first 64 primes.
     */
    private static final int[] K = {
            0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5,
            0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3,
            0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc,
            0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7,
            0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13,
            0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3,
            0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5,
            0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208,
            0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
    };
    //private static  long modulo = 4294967296L ;

    /**
     * Private reused array for representing a block of 64 bytes.
     */
    //private  final byte[] block = new byte[64];
    private int[] block = new int[64] ;

    /**
     * Private reused array for representing 64 32 bit words.
     */
    private  final int[] words = new int[64];

    public void startHashing(String data)
    {
        messagePadding(data);
        //blockFormation(0);
        for(int i = 0 ; i < numberOfBlocks ; i++)
        {
            blockFormation(i);
            int tempHS[] = new int[8] ;
            for(int a = 0 ; a < 8 ; a++)
            {
                tempHS[a] = H[a];
            }

            for(int j = 0 ; j < 64 ; j++)
            {

                for(int k = 0 ; k < 8 ; k++)
                {
                    System.out.print( Integer.toHexString(tempHS[k]) + " ");
                }

                System.out.println();
                int sigma0 = Integer.rotateRight(tempHS[0], 2) ^
                        Integer.rotateRight(tempHS[0], 13) ^
                        Integer.rotateRight(tempHS[0], 22);

                int sigma1 = Integer.rotateRight(tempHS[4], 6) ^
                        Integer.rotateRight(tempHS[4], 11) ^
                        Integer.rotateRight(tempHS[4], 25);

                int maj = (tempHS[0] & tempHS[1]) ^ (tempHS[0] & tempHS[2]) ^ (tempHS[1] & tempHS[2]);
                int ch = (tempHS[4] & tempHS[5]) ^ (~tempHS[4] & tempHS[6]);

                int  T1 = (((((((tempHS[7] + sigma1)& 0xFFFFFFFF ) + ch)& 0xFFFFFFFF)+K[i])& 0xFFFFFFFF)+block[i])& 0xFFFFFFFF ;
                int T2 = ((sigma0 - maj)& 0xFFFFFFFF + maj & 0xFFFFFFFF)& 0xFFFFFFFF;

                tempHS[7] = tempHS[6];
                tempHS[6] = tempHS[5];
                tempHS[5] = tempHS[4];
                tempHS[4] = (tempHS[3] + T1) & 0xFFFFFFFF ;
                tempHS[3] = tempHS[2];
                tempHS[2] = tempHS[1];
                tempHS[1] = tempHS[0];
                tempHS[0] = (T1 + T2 ) & 0xFFFFFFFF ;



            }
            String hexstring = "" ;
            for(int j = 0 ; j < 8 ; j++)
            {
                H[j] = (H[j] +  tempHS[j]) & 0xFFFFFFFF;
                hexstring = hexstring + Integer.toHexString(H[j]);
            }

            System.out.println(hexstring);



        }

        //return paddedMessage ;

    }

    private void blockFormation(int counter)
    {
        int start = counter * 512 ;
        for(int i = 0 ; i < 16 ; i++)
        {
            block[i] = Integer.decode("0x" + paddedMessage.substring(start , start + 8) );
            //block[i] = Integer.parseInt(paddedMessage.substring(start , start + 8));
            start = start + 8 ;
        }

        String s;

        for(int i = 16 ; i < 64 ; i++)
        {

            int roh0 = (Integer.rotateRight(block[i-15] , 7) ^ Integer.rotateRight(block[i-15] , 18)
                    ^ (block[i-15] >>> 3) ) & 0xFFFFFFFF;
            int roh1 = (Integer.rotateRight(block[i-15] , 17) ^ Integer.rotateRight(block[i-15] , 19)
                    ^ (block[i-15] >>> 10) ) & 0xFFFFFFFF ;
            int temp1 = (block[i-7] + roh0) & 0xFFFFFFFF ;
            int temp2 = (block[i-16] + roh1) & 0xFFFFFFFF  ;

            block[i] = (temp1 + temp2) & 0xFFFFFFFF  ;

        }
        //System.out.println(block[1]);
    }

    private void messagePadding(String data)
    {
        int len = data.length() ;
        numberOfBlocks = (len*4 + 1) / 448 + 1 ;
        int numberOfZeros = k(len*4);

        data = data + "8" ;
        numberOfZeros = numberOfZeros - 3 ;
        numberOfZeros = numberOfZeros / 4 ;
        for(int i = 0 ; i < numberOfZeros ; i++)
        {
            data = data + "0" ;
        }

        String lengthHex = Integer.toHexString(len*4);
        int lenHexString = lengthHex.length();
        int temp = 16 - lenHexString ;

        for(int i = 0 ; i < temp ; i++)
        {
            data = data + "0" ;
        }

        data = data + lengthHex ;

        paddedMessage = data ;
        System.out.println(paddedMessage);

    }

    private int k (int l)
    {
        for(int i = 0 ; i < 512 ; i++)
        {
            if((l+1+i) % 512 == 448)
            {
                return i ;
            }
        }

        return 0 ;
    }

}
