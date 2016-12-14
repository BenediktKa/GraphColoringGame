import Jama.Matrix;
import Jama.EigenvalueDecomposition;

public class Eigenvalues {
   public static void main(String[] args) {
      int N = 5;


      //Add the symmetric adj. matrix
      Matrix A = new Matrix(N, N);

      for (int i=0; i<M; i++) {
        for(int j=0;j<=1;j++) {
          double value = Math.random();
          N.set(i,j,value);
          N.set(j,i,value);

        }
      }
  //Eigen value decomposition
  EigenvalueDecomposition eigen= N.eig();

  double [] realPart=eigen.getRealEigenvalues();
  for int(i=0; i<realPart.length; i++) {
    Matrix d= eigen.getD();
    System.out.println("Diagonal matrix of eigenvalue is: ");
    d.print(N, 4);
  }
  double array[]= eigen.getD();

public static int getMaxValue(double[] array) {
    int maxValue = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] > maxValue) {
            maxValue = array[i];
        }
return maxValue;
    }
  }
    public static int getMinValue(int[] array) {
    int minValue = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] < minValue) {
            minValue = array[i];
        }
    }
    return minValue;

  }
}
}
