import java.util.ArrayList;

public class Db {
    private ArrayList<Figure> geometricFigures;

    Db(){
        this.geometricFigures = new ArrayList<Figure>();
    }


    public void addFigure(Figure figureToAdd) {
        geometricFigures.add(figureToAdd);
    }


    public void deleteFigure(String idOfFigureToDel){
        for(int i = 0; i < geometricFigures.size(); i++){
            if(geometricFigures.get(i).getId() == idOfFigureToDel){
                geometricFigures.remove(i);
                return;
            }
        }
        System.out.println("There is no figure with such ID");
    }


    public void deleteFigure(Figure figureToDel){
        String idOfFigureToDel = figureToDel.getId();
        for(int i = 0; i < geometricFigures.size(); i++){
            if(geometricFigures.get(i).getId() == idOfFigureToDel){
                geometricFigures.remove(i);
            }
        }
    }


    public Figure getFigureById(String requiredFigureID){
        for(int i = 0; i < geometricFigures.size(); i++){
            if(geometricFigures.get(i).getId() == requiredFigureID){
                return geometricFigures.get(i);
            }
        }
        return null;
    }


    public ArrayList<Figure> getAllFigures(){
        return this.geometricFigures;
    }


    public String getInfoAboutFigureByID(String requiredFigureID){
        for(int i = 0; i < geometricFigures.size(); i++){
            if(geometricFigures.get(i).getId() == requiredFigureID){
                return geometricFigures.get(i).toString();
            }
        }
        return "There is no figure with such ID";
    }


    public ArrayList<String> getAllFiguresInfo() { //где возвращается ArrayList<String> в котором записанна информация от ту стринг по всем фигурам
        ArrayList<String> allInfo = new ArrayList<String>();
        for(int i = 0; i < geometricFigures.size(); i++)
            allInfo.add(geometricFigures.get(i).toString());

        return allInfo;
    }
}









