import java.util.Collections;
import java.util.Vector;

/*
 * kata kyu 4 
 */
public class Snail {

	public static int[] snail(int[][] array) {

		Vector<Integer> numbers = new Vector<Integer>();
		if (array.length == 1)
		{
			return array[0];
		}
		
		while (array.length > 1)
		{
			//top
			int[] firstLine = array[0];
			addToVector(firstLine, numbers);

			//right side
			Vector<Integer> rightNumbers = getSideNumbers(array, true);
			numbers.addAll(rightNumbers);

			//bottom
			int[] lastLine = array[array.length - 1];
			reverseArray(lastLine);
			addToVector(lastLine, numbers);

			//left side 
			if (array.length > 2)
			{
				Vector<Integer> leftNumbers = getSideNumbers(array, false);
				Collections.reverse(leftNumbers);
				numbers.addAll(leftNumbers);
			}

			//work out remaining array
			if (array.length > 1 && array[0].length>1)
			{
				array = getRemainder(array);
			}
		}
		
		//get the central number
		if (array.length > 0)
		{
			int[] centralLine = array[0];
			if (centralLine.length == 0)
			{
				return new int[0];
			}
			int center = centralLine[0];
			numbers.add(center);
		}
		
		int[] retval = new int[numbers.size()];
		for (int i=0; i<numbers.size(); i++)
		{
			retval[i] = numbers.get(i);
		}
		return retval;
	} 

	private static void addToVector(int[] innerArray, Vector<Integer> v)
	{
		for (int i=0; i<innerArray.length; i++)
		{
			int x = innerArray[i];
			v.add(x);
		}
	}
	
	private static void reverseArray(int[] array)
	{
		for (int i = 0; i < array.length / 2; i++)
		{
		    int temp = array[i];
		    array[i] = array[array.length - i - 1];
		    array[array.length - i - 1] = temp;
		}
	}

	private static Vector<Integer> getSideNumbers(int[][] array, boolean right)
	{
		Vector<Integer> retval = new Vector<Integer>();
		for (int i=1; i<array.length-1; i++)
		{
			int[] line = array[i];
			int n = -1;
			if (right)
			{
				n = line[line.length -1];
			}
			else
			{
				n = line[0];
			}
			retval.add(n);
		}
		return retval;
	}

	private static int[][] getRemainder(int[][] array)
	{
		int height = array.length -2;
		int length = array[0].length -2;
		int[][] remainder = new int[height][length];

		for (int i=1; i<array.length-1; i++)
		{
			int[] line = array[i];
			int[] remainderLine = new int[line.length -2];
			for (int j=1; j<line.length-1; j++)
			{
				int x = line[j];
				remainderLine[j-1] = x;
			}
			remainder[i-1] = remainderLine;
		}
		return remainder;
	}

}
