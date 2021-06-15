package SetPackage;

import java.math.BigDecimal;

public class MathSet{
    private Node top;

    public MathSet() {
        top = null;
    }

    public MathSet(Number []numbers){
        top = null;
        for(Number item: numbers){
            add(item);
        }
    }


    public MathSet(Number[] ... numbers){
        top = null;
        for(Number[] item : numbers){
            for(Number element : item){
                add(element);
            }
        }
    }


    public MathSet(MathSet numbers){
        top = null;
        Node setTop = numbers.getTop();
        while(setTop != null){
            add(setTop.info);
            setTop = setTop.next;
        }
    }


    public MathSet(MathSet ... numbers){
        top = null;
        for(MathSet item : numbers){
            Node setTop = item.getTop();
            while(setTop != null){
                add(top.info);
                top = top.next;
            }
        }
    }



    //Lifo список
    public void add(Number info){
        //Если элемент не уникален, то в множество он не добавляется
        if(!checkUniqueness(info)){
            return;
        }
        if(info == null){
            throw new RuntimeException("Number is not exists");
        }
        Node cur;
        cur = new Node();
        cur.info = info;
        cur.next = top;
        top = cur;
    }


    public void add(Number ... info){
        for (Number item : info) {
            add(item);
        }
    }


    public void join(MathSet set){
        //Если исходное мн-во пустое, то результатом объединения будет второе множество
        if(this.top == null){
            top = set.top;
            return;
        }

        Node setTop = set.getTop();
        while(setTop != null){
            add(setTop.info);
            setTop = setTop.next;
        }
    }


    public void join(MathSet ... sets){
        for(MathSet item : sets){
            Node setTop = item.getTop();
            while(setTop != null){
                add(top.info);
                top = top.next;
            }
        }
    }


    public void clear(){
        Node cur;
        while(top != null){
            cur = top;
            top = top.next;
            cur = null;
        }
    }

    public void clear(Number[] numbers){
        for(Number item : numbers){
            clear(item);
        }
    }

    public void clear(Number info){
        Node cur;
        Node prev;
        Node localTop = top;

        //Если нужно удалить первый элемент списка
        if(localTop.info.equals(info)){
            cur = localTop;
            localTop = localTop.next;
            cur = null;
            return;
        }
        //Если нужный элемент где-то в середине
        prev = localTop;
        cur = prev.next;
        while (cur != null){
            if(cur.info.equals(info)){
                prev.next = cur.next;
                cur = null;
                return;
            }
            else {
                prev = cur;
                cur = cur.next;
            }
        }
    }


    public MathSet squash(int firstIndex, int lastIndex){
        MathSet result = new MathSet();
        Node localTop = top;
        int i = 0;
        while (localTop != null){
            if(i >= firstIndex && i <= lastIndex){
                result.add(localTop.info);
            }
            localTop = localTop.next;
            i++;
        }
        return result;
    }

    public Node getTop(){
        return top;
    }


    public Number get(int index){
        int i = 0;
        Node localTop = top;
        while(localTop != null){
            if(i == index){
                return localTop.info;
            }
            localTop = localTop.next;
            i++;
        }
        return null;
    }


    public Number getMin(){
        Node localTop = top;
        Number minimum = localTop.info;
        while (localTop != null){
            if(compareTo(localTop.info, minimum) == -1){
                minimum = localTop.info;
            }
            localTop = localTop.next;
        }
        return minimum;
    }


    public Number getMax(){
        Node localTop = top;
        Number maximum = localTop.info;
        while (localTop != null){
            if(compareTo(localTop.info, maximum) == 1){
                maximum = localTop.info;
            }
            localTop = localTop.next;
        }
        return maximum;
    }




//    public void sortAsc()
//    { Node q, out, p , pr;
//        out = null;         // выходной список пуст
//        while (top != null){              // пока не пуст входной список
//
//            q = top;
//            top = top.next;      // исключить очередной
//
//            // поиск места включения
//            for( p=out,pr=null; p!=null && (compareTo(q.info, p.info) == 1); pr=p,p=p.next);
//
//            if (pr==null){             // включение перед первым
//             q.next=out; out=q;
//            }
//            else{                      // иначе после предыдущего
//             q.next=p; pr.next=q;
//            }
//        }
//        top = out;
//    }


    //Сортировка вставками1
    public void sortAsc() {
        Node new_top = null;

        while ( top != null ) {
            Node node = top;
            top = top.next;

            if ( new_top == null || (compareTo(node.info, new_top.info) == -1)) {
                node.next = new_top;
                new_top = node;
            }

            else {
               Node current = new_top;
                while ( current.next != null && !( compareTo(node.info, current.next.info) == -1)) {
                    current = current.next;
                }

                node.next = current.next;
                current.next = node;
            }
        }

        clear();
        top = new_top;
    }


    //Не до конца корректно сортирует
    public void sortAsc(int firstIndex, int lastIndex){
//        Number sortFrom = get(firstIndex);
//        Number sortTo = get(lastIndex);
//
//        //Ищу от какого элемента начать сортировку
//        while(compareTo(top.info, sortFrom) != 0){
//            top = top.next;
//        }
//
//        Node new_top = null;
//        while ( compareTo(top.info, sortTo) != 0 ) {
//            Node node = top;
//            top = top.next;
//
//            if ( new_top == null || (compareTo(node.info, new_top.info) == -1)) {
//                node.next = new_top;
//                new_top = node;
//            }
//
//            else {
//                Node current = new_top;
//                while ( current.next != null && !( compareTo(node.info, current.next.info) == -1)) {
//                    current = current.next;
//                }
//
//                node.next = current.next;
//                current.next = node;
//            }
//        }
//
//        top = new_top;
    }

    public void sortAsc(Number value){

    }


    public void sortDesc(){
        Node new_top = null;

        while ( top != null ) {
            Node node = top;
            top = top.next;

            if ( new_top == null || (compareTo(node.info, new_top.info) == 1)) {
                node.next = new_top;
                new_top = node;
            }

            else {
                Node current = new_top;
                while ( current.next != null && !( compareTo(node.info, current.next.info) == 1)) {
                    current = current.next;
                }

                node.next = current.next;
                current.next = node;
            }
        }

        top = new_top;
    }

    public void sortDesc(int firstIndex, int lastIndex){

    }

    public void sortDesc(Number value){

    }


    public Number getAverage(){
        double sum = 0;
        Node localTop = top;
        while(localTop != null){
            sum += localTop.info.doubleValue();
            localTop = localTop.next;
        }
        return sum / size();
    }


    public Number getMedian(){
        //Если нечётное кол-во, беру средний элемент
        if(size() % 2 != 0){
            return get(size() / 2);
        }
        //Если чётное кол-во, среднее арифм. двух средних элементов
        double first = get(size() / 2).doubleValue();
        double second = get((size() / 2) - 1).doubleValue();
        //Возвращаю их среднее арифметическое
        return (first + second) / 2;
    }



    private int compareTo(Number first, Number second) {
        BigDecimal b1 = BigDecimal.valueOf(first.doubleValue());
        BigDecimal b2 = BigDecimal.valueOf(second.doubleValue());
        return b1.compareTo(b2);
    }



    public int size(){
        int length = 0;
        Node localTop = top;
        while(localTop != null){
            length++;
            localTop = localTop.next;
        }
        return length;
    }


    public Number[] toArray(){
        Number []array = new Number[size()];
        Node localTop = top;
        int index = 0;
        while (localTop != null){
            array[index] = localTop.info;
            localTop = localTop.next;
            index++;
        }
        return array;
    }


    public Number[] toArray(int firstIndex, int lastIndex){
        Number []array = new Number[lastIndex - firstIndex];
        Node localTop = top;
        int index = 0;
        int i = 0;
        while (localTop != null){
            if(index >= firstIndex && index <= lastIndex){
                array[i] = localTop.info;
                i++;
                index++;
            }
            localTop = localTop.next;
            index++;
        }
        return array;
    }


    private boolean checkUniqueness(Number infoToCheck){
        Node localTop = top;
        while(localTop != null){
            if(localTop.info.equals(infoToCheck)){
                return false;
            }
            localTop = localTop.next;
        }
        return true;
    }
}
