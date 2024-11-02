import java.util.Scanner;

public class ChainReaction1 {
    public static int criticalValue(int i,int j,int r,int c)
    {
        if((i==0 && j==0)|| (i==0 && j==c-1) || (i==r-1 && j==0) || (i==r-1&&j==c-1))
        return 2;
        else if((j==0)||(i==0)||(i==r-1)||(j==c-1))
        return 3;
        else
        return 4;
    }
    public static void explode(String arr[][],int i,int j,char turn,int r,int c)
    {
       for(int k=0;k<r;k++)
       {
        for(int m=0;m<c;m++)
        {
            if((k==i && Math.abs(m-j)==1) || (m==j && Math.abs(i-k)==1))
            {
                if(arr[k][m].length()>1 && arr[k][m].charAt(1)==turn)
                {
                    arr[k][m] += turn;
                    int criticalmass = criticalValue(k, m,r,c);
                    if(arr[k][m].length()==criticalmass+1)
                    {
                        arr[k][m]=" ";
                        explode(arr, k, m, turn,r,c);
                         
                    }
                }
                else if(arr[k][m]==" ")
                {
                    arr[k][m] += turn;
                }
                else{
                    int len = arr[k][m].length();
                    arr[k][m]=" ";
                    for(int l=0;l<len;l++)
                    {
                        arr[k][m] += turn;
                    }
                    int criticalmass = criticalValue(k, m,r,c);
                    if(arr[k][m].length()==criticalmass+1)
                    {
                    
                        arr[k][m]=" ";
                        explode(arr, k, m, turn,r,c); 
                        
                       
                    }
                }

            }
            
        }
       }
      

    }
    public static boolean isgameover(String arr[][],char turn,int r,int c)
    {
        for(int k=0;k<r;k++)
        {
            for(int m=0;m<c;m++)
            {
                if(arr[k][m].charAt(0)!=turn)
                return false;
            }
        }

        return true;

    }
    public static void placement(String arr[][],int i,int j,char turn,int r,int c)
    {
        if(arr[i][j]==" " || arr[i][j].charAt(1)==turn)
        {

            arr[i][j] += turn;
            int criticalmass = criticalValue(i, j,r,c);
            if(criticalmass+1 == arr[i][j].length())
            {
                arr[i][j] = " ";
              explode(arr,i,j,turn,r,c);
            }
           if(isgameover(arr,turn,r,c))
           {
            System.out.println(turn+" is the winner");
           }
           else{
                if(turn=='R')
                {
                    turn = 'G';
                }
                else{
                    turn = 'R';
                }
                printmatrix(arr,r,c);
                System.out.println(turn+"'s turn:");
                input(arr, turn,r,c);
           }
        }
        

    }
    public static void printmatrix(String arr[][],int r,int c)
    {
        for(int k=0;k<r;k++)
        {
            for(int m=0;m<c;m++)
            {
                System.out.print(arr[k][m]+"\t|");
            }
            System.out.println("");
            System.out.println("_______________________________________________________________________________");
        }

    }
    public static void input(String arr[][],char turn,int r,int c)
    {
        System.out.println("Enter the position(i and j):");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int j = sc.nextInt();
        placement(arr,i,j,turn,r,c);
    }
    public static char choose()
    {
        System.out.println("Who is starting(R or G?)");
        Scanner sc = new Scanner(System.in);
        String turn = sc.next();
        return turn.charAt(0);
    }
    public static void main(String arg[])
    {
        int r,c;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Noof rows and columns");
        r = sc.nextInt();
        c= sc.nextInt();
        String arr[][] = new String[r][c];
        int criticalmassvalues[][] = new int[r][c];
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                arr[i][j]=" ";
            }
            System.out.println("");

        }
      char turn = choose();
      input(arr,turn,r,c);   
      
    }
}
