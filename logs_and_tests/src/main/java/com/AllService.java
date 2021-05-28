package com;

public class AllService {
    private AllDao allDao = new AllDao();

/////////////////////////////////////////////////////////////
    public void createFoot(Footballer footballer){
        allDao.createFoot(footballer);
    }

    public void updateFoot(Footballer footballer){
        allDao.updateFoot(footballer);
    }

    public void deleteFoot(String id){
        allDao.deleteFoot(id);
    }

    public Footballer[] findAllFoot(){
       return allDao.findAllFoot();
    }

    public Footballer findFootById(String id){
        return allDao.findFootById(id);
    }

    public String deleteFootFromTeam(String id){
       return(allDao.deleteFootFromTeam(id));
    }

    public void pushFootToTeam(Footballer f, Team team){
        allDao.pushFootToTeam(f,team);
    }


///////////////////////////////////////////////////////////////
    public void createTeam(Team team){
        allDao.createTeam(team);
    }

    public void updateTeam(Team team) {

        allDao.updateTeam(team);
    }

    public void deleteTeam(String id) {

        allDao.deleteTeam(id);
    }

    public Team[] findAllTeam(){
        return allDao.findAllTeam();
    }

    public Team findTeamById(String id){

        return allDao.findTeamById(id);
    }




}
