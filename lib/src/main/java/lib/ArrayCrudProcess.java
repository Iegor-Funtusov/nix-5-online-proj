package lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


@Deprecated
public class ArrayCrudProcess<E extends BaseEntity> implements CrudProcess<E> {
    private int currentLength;
    private Object []objects;

    ArrayCrudProcess(){
        this.currentLength = 2;
        this.objects = new Object[currentLength];
    }


    public void create(E e){
        memoryRealloc();
        e.setId(generateID(UUID.randomUUID().toString()));
        for(int i = 0; i < currentLength; i ++){
           if(objects[i] == null){
               objects[i] = e;
               return;
           }
        }
    }


    public void update(E e){
        if(StringUtils.isNotBlank(e.getId())){
            E current = getObjByID(e.getId());
            if (current == null){
                throw new RuntimeException("Entity is not exist");
            }
            try {
                BeanUtils.copyProperties(current, e);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            throw new RuntimeException("Entity is not exist");
        }
    }


    public void delete(String id){
        if(StringUtils.isNotBlank(id)){
            E current = getObjByID(id);
            if (current == null){
                throw new RuntimeException("Entity is not exist");
            }
            for(int i = 0; i < currentLength; i++){
                if(objects[i] != null){
                    if(((E)objects[i]).getId().equals(id)){
                        objects[i] = null;
                        shiftElements();
                        memoryRealloc();
                        return;
                    }
                }
            }
        } else{
            throw new RuntimeException("Entity is not exist");
        }
    }



    public Collection<E> read(){
        List<E> elements = new ArrayList<>();
        for(int i = 0; i < currentLength; i++){
            if(objects[i] != null){
                elements.add((E)objects[i]);
            }
        }
        return elements;
    }


    public E read(String id){
        if(StringUtils.isNotBlank(id)){
            E current = getObjByID(id);
            if (current == null){
                throw new RuntimeException("Entity is not exist");
            }
            return current;
        }
        throw new RuntimeException("Entity is not exist");
    }


    private E getObjByID(String id){
        for(int i = 0; i < currentLength; i++){
            if(((E)objects[i]).getId().equals(id)){
                return (E)objects[i];
            }
        }
        return null;
    }


    private String generateID(String id){
        for(int i = 0; i < currentLength; i++){
            if(objects[i] != null){
                if(((E)objects[i]).getId().equals(id)){
                    return generateID(UUID.randomUUID().toString());
                }
            }
        }
        return id;
    }


    //Перевыделение памяти для расширеня массива
    //Если кол-во значащих элементов на один меньше текущего размера, увеличиваю массив в два раза
    private void memoryRealloc(){
        shiftElements();        //Чтобы дополнительно обезопасить себя, вызываю укомплектовку текущего массива
        int quantityOfElements = 0;

        //Определяю кол-во значимых элементов в данном массиве
        for(Object obj : objects){
            if(obj == null)
                continue;
            quantityOfElements++;
        }

        if(quantityOfElements == currentLength-1) {
            currentLength *= 2;
        }

        Object[] newObjects = new Object[currentLength];

        for(int i = 0 ; i < currentLength; i++){
            if(i < objects.length){
                newObjects[i] = objects[i];         //Перезапись элементов
            }
        }
        objects = newObjects.clone();


    }


    //Метод сдвига элементов, чтобы посреди массива не было null
    //Т.к. в методе удаления удаляемый элемент заменяется на null
    //и посреди массива может возникнуть "пустой" элемент
    private void shiftElements(){
        Object []newObjects = new Object[currentLength];
        int j = 0;

        for(int i = 0; i < currentLength; i++){
            if(objects[i] != null){
                newObjects[j] = objects[i];
                j++;
            }
        }
        objects = newObjects.clone();
    }
}