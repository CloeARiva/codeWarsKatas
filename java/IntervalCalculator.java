/*
 * kata kyu 4 
 */
import java.util.Vector;

public class IntervalCalculator {
	
	public static Integer sumIntervals(int[][] intervals)
	{
		//get the highest bound and the lowest bound
		if (intervals == null)
		{
			return 0;
		}
		
		int highestBound = 0;
		int lowestBound = Integer.MAX_VALUE;
		for (int i=0; i<intervals.length; i++)
		{
			int[] interval = intervals[i];
			int lowerBound = interval[0];
			if (lowerBound < lowestBound)
			{
				lowestBound = lowerBound;
			}
			
			int higherBound = interval[1];
			if (higherBound > highestBound)
			{
				highestBound = higherBound;
			}
		}
		
		//if we are dealing with negative numbers we need to transalte evrything up so it's positive
		int translation = 0;
		if (lowestBound < 0)
		{
			translation = - lowestBound;
		}
		highestBound = highestBound + translation;
		
		//make a vector of 0s to represent the total interval
		Vector<Integer> intervalRepresentationVect = new Vector<Integer>();
		for (int i=0; i<highestBound; i++)
		{
			intervalRepresentationVect.add(0);
		}
		
		//for each given interval change the 0s to 1s
		for (int i=0; i<intervals.length; i++)
		{
			int[] interval = intervals[i];
			int lowerBound = interval[0] + translation;
			int higherBound = interval[1] + translation;
			for (int j=lowerBound; j<higherBound; j++)
			{
				replace(intervalRepresentationVect, j, 1);
			}
		}
		
		//count the number of 1s
		int size = 0;
		for (int i=0; i<highestBound; i++)
		{
			if (intervalRepresentationVect.get(i) == 1)
			{
				size++;
			}
		}
		return size;
	}
	
	private static void replace(Vector<Integer> v, int indexToReplace, Integer value)
	{
		v.remove(indexToReplace);
		v.insertElementAt(value, indexToReplace);
	}

}
