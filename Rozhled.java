public class Rozhled{
    public static void main(String []args){
        
                     // X  Y
        int[] pozice = {0, 0};
                    //    X   Y
        int[] rozhled = {-6, -6};

        int[][] mesto = {{5, 0}, {0, 5}};


        rozhled[0] = pozice[0] + rozhled[0];
        rozhled[1] = pozice[1] + rozhled[1];
        
        for(int i = -5; i < 6; i++){
            rozhled[1] = -6;
            rozhled[0] = rozhled[0] + 1;
            for(int o = 0; o < 11; o++){
                rozhled[1] = rozhled[1] + 1;
            }
        }
    }
}