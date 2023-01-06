package pl.kostrzynski;

import java.util.LinkedList;

public class SubsetSum {

    public int fptas(final double epsilon, final int[] values, final int limit) {

        final int valuesSize = values.length;
        final double delta = epsilon / valuesSize;

        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(0);

        for (int i = 1; i < valuesSize; i++) {
            final LinkedList<Integer> helperList = merge(list, values[i]);
            list = trim(helperList, delta);
            list.removeIf(value -> value > limit);
        }

        return list.getLast();
    }

    private LinkedList<Integer> trim(LinkedList<Integer> list, final double delta) {

        final LinkedList<Integer> helperList = new LinkedList<>();
        int value = list.removeFirst();
        helperList.addLast(value);

        for (final var y : list) {
            if (value < (1 - delta) * y) {
                helperList.addLast(y);
                value = y;
            }
        }

        return helperList;
    }

    private LinkedList<Integer> merge(LinkedList<Integer> list, final int x) {

        final LinkedList<Integer> helperList = new LinkedList<>();
        list.forEach(e -> helperList.addLast(e + x));

        final LinkedList<Integer> mergedList = new LinkedList<>();

        final var listIterator = list.listIterator();
        final var helperListIterator = helperList.listIterator();
        while (listIterator.hasNext() && helperListIterator.hasNext()) {
            final int listValue = listIterator.next();
            final int helperListValue = helperListIterator.next();

            if (listValue == helperListValue) {
                mergedList.addLast(listValue);
                continue;
            }
            if (listValue < helperListValue) {
                helperListIterator.previous();
                mergedList.addLast(listValue);
            } else {
                listIterator.previous();
                mergedList.addLast(helperListValue);
            }
        }
        while (listIterator.hasNext()) {
            mergedList.addLast(listIterator.next());
        }
        while (helperListIterator.hasNext()) {
            mergedList.addLast(helperListIterator.next());
        }

        return mergedList;
    }

}

