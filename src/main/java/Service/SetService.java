package Service;
import Vaidation.Validation;
import SetPackage.*;

public class SetService {
    private MathSet set;
//    private static SetService instance;

//    private SetService(){
//        set = new MathSet();
//    }
//
//    public static SetService getInstance(){
//        if(instance == null){
//            instance =  new SetService();
//        }
//        return instance;
//    }

    public SetService() {
       set = new MathSet();
    }



    //isCurrent - флаг отвечающий за выбор, создание нового текцщего мн-ва с которым работаем, или просто
    //создание како-го то дополнительного мн-ва
    public MathSet createSet(boolean isCurrent, Number []numbers){
        Validation.isEmptyArray(numbers);
        if(isCurrent){
            set.clear();
            set = new MathSet(numbers);
            return set;
        }
        return new MathSet(numbers);
    }


    public MathSet createSet(boolean isCurrent, Number[] ... numbers){
        Validation.isEmptyArray(numbers);
        if(isCurrent){
            set.clear();
            set = new MathSet(numbers);
            return set;
        }
        return new MathSet(numbers);
    }


    public MathSet createSet(boolean isCurrent, MathSet numbers){
        Validation.isEmptySet(numbers);
        if(isCurrent){
            set.clear();
            set = new MathSet(numbers);
            return set;
        }
        return new MathSet(numbers);
    }


    public MathSet createSet(boolean isCurrent, MathSet ... numbers){
        Validation.isEmptySet(numbers);
        if(isCurrent){
            set.clear();
            set = new MathSet(numbers);
            return set;
        }
        return new MathSet(numbers);
    }


    public void addElement(Number info){
        Validation.isEmptyElement(info);
        set.add(info);
    }

    public void addElement(Number ... info){
        Validation.isEmptyElement(info);
        set.add(info);
    }

    public void join(MathSet mathSet){
        Validation.isEmptySet(mathSet);
        set.join(mathSet);
    }

    public void join(MathSet ... mathSets){
        Validation.isEmptySet(mathSets);
        set.join(mathSets);
    }


    public Number get(int index){
        Validation.validateIndex(set, index);
        return set.get(index);
    }


    public Number[] toArray(){
        Validation.isEmptySet(set);
        return set.toArray();
    }

    public Number[] toArray(int firstIndex, int lastIndex){
        Validation.validateIndex(set, firstIndex, lastIndex);
        return set.toArray(firstIndex, lastIndex);
    }


    public void clear(){
        Validation.isEmptySet(set);
        set.clear();
    }

    public void clear(Number[] numbers){
        Validation.isEmptySet(set);
        Validation.isEmptyArray(numbers);
        set.clear(numbers);
    }

    public MathSet squash(int firstIndex, int lastIndex){
        Validation.isEmptySet(set);
        Validation.validateIndex(set, firstIndex, lastIndex);
        return set.squash(firstIndex, lastIndex);
    }


    public Number getMin(){
        Validation.isEmptySet(set);
        return set.getMin();
    }


    public Number getMax(){
        Validation.isEmptySet(set);
        return set.getMax();
    }



    public void sortAsc(){
        Validation.isEmptySet(set);
        set.sortAsc();
    }


    public void sortAsc(int firstIndex, int lastIndex){
        Validation.isEmptySet(set);
        Validation.validateIndex(set, firstIndex, lastIndex);
        set.sortAsc(firstIndex, lastIndex);
    }

    public void sortAsc(Number value){
        Validation.isEmptySet(set);
        Validation.isEmptyElement(value);
        Validation.isContainElement(set, value);
        set.sortAsc(value);
    }


    public void sortDesc(){
        Validation.isEmptySet(set);
        set.sortDesc();
    }

    public void sortDesc(int firstIndex, int lastIndex){
        Validation.isEmptySet(set);
        Validation.validateIndex(set, firstIndex, lastIndex);
        set.sortDesc(firstIndex, lastIndex);
    }

    public void sortDesc(Number value){
        Validation.isEmptySet(set);
        Validation.isEmptyElement(value);
        Validation.isContainElement(set, value);
        set.sortDesc(value);
    }

    public Number getAverage() {
        Validation.isEmptySet(set);
        return set.getAverage();
    }

    public Number getMedian() {
        Validation.isEmptySet(set);
        return set.getMedian();
    }

    public int getSize(){
        Validation.isEmptySet(set);
        return set.size();
    }
}