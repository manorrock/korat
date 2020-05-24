/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtils {

	public static <T> List<T> getGenericList(Class<T> clazz, int size) {

		return new ArrayList<T>(size);
	}

	public static <T> List<T> getGenericList(Class<T> clazz) {

		return new ArrayList<T>();
	}

	public static <T> List<T> getGenericSingletonList(T bean) {

		if (bean == null) {

			return null;
		}

		return Collections.singletonList(bean);
	}

	public static boolean isEmpty(Collection<?> collection) {

		if (collection == null || collection.isEmpty()) {

			return true;
		}

		return false;
	}

	public static boolean isEmpty(Map<?, ?> map) {

		if (map == null || map.isEmpty()) {

			return true;
		}

		return false;
	}

	public static <T> List<T> conjunction(Collection<T> c1, Collection<T> c2) {

		if (c1 == null || c2 == null) {

			return null;

		} else {

			List<T> result = new ArrayList<T>(c1.size());

			for (T o : c1) {

				if (c2.contains(o)) {

					result.add(o);
				}
			}
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getList(T... objects) {

		if (objects == null) {

			return null;
		}

		return new ArrayList<T>(Arrays.asList(objects));
	}

	public static void removeNullValues(List<?> list) {

		Iterator<?> iterator = list.iterator();

		while (iterator.hasNext()) {

			Object value = iterator.next();

			if (value == null) {

				iterator.remove();
			}
		}
	}

	/**
	 * @param list
	 * @return the size of the collection or 0 if the collection is null
	 */
	public static int getSize(Collection<?> collection) {

		if (collection == null) {

			return 0;
		}

		return collection.size();
	}

	public static int getSize(Collection<?>... collections) {

		int size = 0;

		if (collections != null) {

			for (Collection<?> collection : collections) {

				size += getSize(collection);
			}
		}

		return size;
	}

	/**
	 * Takes a list of lists as input and returns a single list containing the items of all input lists. If all input lists are empty or null then null is
	 * returned.
	 *
	 * @param <T>
	 * @param lists
	 * @return
	 */
	@SafeVarargs
	public static <T> ArrayList<T> combine(Collection<T>... collections) {

		int totalSize = 0;

		for (Collection<T> list : collections) {

			if (getSize(list) > 0) {

				totalSize += list.size();
			}
		}

		if (totalSize == 0) {

			return null;
		}

		ArrayList<T> combinedList = new ArrayList<T>(totalSize);

		for (Collection<T> list : collections) {

			if (list != null) {

				combinedList.addAll(list);
			}
		}

		return combinedList;
	}

	/**
	 * Takes a list of lists as input and returns a single set containing the unique items of all input lists. If all input lists are empty or null then null is
	 * returned.
	 *
	 * @param <T>
	 * @param lists
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> HashSet<T> combineAsSet(Collection<T>... collections) {

		int totalSize = 0;

		for (Collection<T> list : collections) {

			if (getSize(list) > 0) {

				totalSize += list.size();
			}
		}

		if (totalSize == 0) {

			return null;
		}

		HashSet<T> set = new HashSet<T>(totalSize);

		for (Collection<T> list : collections) {

			if (list != null) {

				set.addAll(list);
			}
		}

		return set;
	}

	public static <T> void addNewEntries(Collection<T> baseCollection, Collection<T> newEntries) {

		for (T object : newEntries) {

			if (!baseCollection.contains(object)) {

				baseCollection.add(object);
			}
		}
	}

	public static <T> List<T> instantiateIfNeeded(List<T> list) {

		if (list == null) {

			list = new ArrayList<T>();
		}

		return list;
	}

	public static <T> Set<T> instantiateIfNeeded(Set<T> set) {

		if (set == null) {

			set = new HashSet<T>();
		}

		return set;
	}

	public static <T> List<T> addAndInstantiateIfNeeded(List<T> list, T item) {

		if (item == null) {

			return list;
		}

		list = instantiateIfNeeded(list);

		list.add(item);

		return list;
	}

	public static <T> List<T> addAndInstantiateIfNeeded(List<T> list, List<T> items) {

		if (isEmpty(items)) {

			return list;
		}

		list = instantiateIfNeeded(list);

		list.addAll(items);

		return list;
	}

	public static <T> List<T> addNewEntriesAndInstantiateIfNeeded(List<T> list, List<T> items) {

		if (isEmpty(items)) {

			return list;
		}

		list = instantiateIfNeeded(list);

		addNewEntries(list, items);

		return list;
	}

	public static <T> Set<T> addAndInstantiateIfNeeded(Set<T> set, T item) {

		if (item == null) {

			return set;
		}

		set = instantiateIfNeeded(set);

		set.add(item);

		return set;
	}

	public static <T> List<T> removeDuplicates(List<T> list) {

		if (list == null) {
			return null;
		}

		if (list.size() <= 1) {
			return list;
		}

		return new ArrayList<T>(new LinkedHashSet<T>(list));
	}

	public static <T> void removeDuplicates(List<T> list, Comparator<T> comparator) {

		if (list == null) {
			return;
		}

		if (list.size() == 1) {
			return;
		}

		int index = list.size() - 1;

		while (index >= 0) {

			T currentObject = list.get(index);

			int innerIndex = 0;

			while (innerIndex < index) {

				T compareObject = list.get(innerIndex);

				if (comparator.compare(currentObject, compareObject) == 0) {

					list.remove(index);
					break;
				}

				innerIndex++;
			}

			index--;
		}
	}

	public static <T> void add(Collection<T> targetCollection, Collection<T> collectionToAdd) {

		if (collectionToAdd != null) {

			targetCollection.addAll(collectionToAdd);
		}
	}

	public static <T> boolean addItem(Collection<? super T> targetCollection, T item) {

		if (item != null) {

			return targetCollection.add(item);
		}

		return false;
	}

	public static <T> boolean equals(List<T> list1, List<T> list2) {

		if (list1 == null) {

			return list2 == null;
		}

		if (list2 == null) {

			return false;
		}

		return list1.equals(list2);
	}

	public static <T extends Comparable<T>> int addInOrder(final List<T> list, final T item) {

		final int insertAt;

		final int index = Collections.binarySearch(list, item);

		if (index < 0) {

			insertAt = -(index + 1);

		} else {

			insertAt = index + 1;
		}

		list.add(insertAt, item);
		return insertAt;
	}

	public static <T> int addInOrder(final List<T> list, final T item, Comparator<? super T> comparator) {

		final int insertAt;

		final int index = Collections.binarySearch(list, item, comparator);

		if (index < 0) {

			insertAt = -(index + 1);

		} else {

			insertAt = index + 1;
		}

		list.add(insertAt, item);

		return insertAt;
	}

	public static <T, X extends T> boolean contains(List<T> list, X object) {

		if (list == null || object == null) {

			return false;
		}

		return list.contains(object);
	}

	public static <T> List<T> getReverseList(List<T> list) {

		List<T> reverseList = new ArrayList<T>(list);

		Collections.reverse(reverseList);

		return reverseList;
	}

	public static <T> T getFirstValue(List<T> list) {

		if (!isEmpty(list)) {

			return list.get(0);
		}

		return null;
	}

	public static <T> List<List<T>> getDividedList(List<T> list, int parts) {

		if (parts < 1) {

			throw new RuntimeException("parts cannot be less than 1");
		}

		int partsPerList;

		if (parts > list.size()) {

			partsPerList = 1;

		} else {

			partsPerList = (list.size() + parts - 1) / parts;
		}

		List<List<T>> sublists = new ArrayList<List<T>>(parts);

		int index = 0;

		while (index < list.size()) {

			if (list.size() > index + partsPerList) {

				sublists.add(list.subList(index, index + partsPerList));

				index += partsPerList;

			} else {

				sublists.add(list.subList(index, list.size()));

				break;
			}
		}

		return sublists;
	}

	public static void removeLastValue(List<?> list) {

		list.remove(list.size() - 1);
	}

	public static <T> T getLastValue(List<T> list) {

		return list.get(list.size() - 1);
	}
}
