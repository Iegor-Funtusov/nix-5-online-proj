import java.util.ArrayList;

public class Db {
    private ArrayList<Figure> geometricFigures;

    Db(){
        this.geometricFigures = new ArrayList<Figure>();
    }


    public void addFigure(Figure figureToAdd) {
        geometricFigures.add(figureToAdd);
    }


    public void deleteFigure(int idOfFigureToDel){
        for(int i = 0; i < geometricFigures.size(); i++){
            if(geometricFigures.get(i).getId() == idOfFigureToDel){
                geometricFigures.remove(i);
                System.out.println("Delete was successful");
                return;
            }
        }
        System.out.println("There is no figure with such ID");
    }


    public Figure getFigure(int requiredFigureID){
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


    public String getInfoAboutFigureByID(int requiredFigureID){
        for(int i = 0; i < geometricFigures.size(); i++){
            if(geometricFigures.get(i).getId() == requiredFigureID){
                return geometricFigures.get(i).toString();
            }
        }
        return "There is no figure with such ID";
    }
}