package Task3;

import java.util.LinkedHashMap;
import java.util.Map;

public class City {
    private String cityName;
    private final Map<Integer, Integer> neighbourValueMap;

    public City(){
        neighbourValueMap = new LinkedHashMap<>();
    }

    public String getCityName() {
        return cityName;
    }

    public Map<Integer, Integer> getNeighbourValueMap() {
        return neighbourValueMap;
    }

    public void setName(String name) {
        this.cityName = name;
    }

    public void addNewNeighbourValue(Integer neighbour, Integer value){
        neighbourValueMap.put(neighbour, value);
    }

    @Override
    public String toString() {
        return "City: " + cityName +  ", neighbourValueMap=" + neighbourValueMap;
    }
}
