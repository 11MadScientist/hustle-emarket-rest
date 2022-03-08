package org.emarket.hustle.emarkethustle;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Store;

public class Algorithms
{
//	quick sort
	public void sortStoreByStockSold(List<Store> stores, int low, int high)
	{

		if(low < high - 1)
		{
			double pivot = stores.get(low).getOverallRating();
			int l = low;
			int h = high;

			while (l < h)
			{
				do
				{
					l++;
				}
				while (l < high && stores.get(l).getOverallRating() > pivot);

				do
				{
					h--;
				}
				while (h > low && stores.get(h).getOverallRating() <= pivot);

				if(l < h)
				{
					swap(stores, l, h);
				}

			}
			swap(stores, low, h);
			sortStoreByStockSold(stores, low, h);
			sortStoreByStockSold(stores, h + 1, high);
		}

	}

	public <E> void swap(List<E> obj, int l, int h)
	{

		E temp = obj.get(l);
		obj.set(l, obj.get(h));
		obj.set(h, temp);

	}
}
