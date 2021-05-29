package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllService {
    private AllDao allDao = new AllDao();

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");


    public void createFoot(Footballer footballer){
        loggerInfo.info("Start create footballer" + footballer.getFoot_name());
        allDao.createFoot(footballer);
        loggerInfo.info("End create footballer");
    }

    public void updateFoot(Footballer footballer){
        loggerInfo.info("Start update footballer" + footballer.getFoot_name());
        allDao.updateFoot(footballer);
        loggerInfo.info("End update footballer");
    }

    public void deleteFoot(String id){
        loggerWarn.warn("Start removing of footballer by id: " + id);
        allDao.deleteFoot(id);
        loggerWarn.warn("End removing of footballer");
    }

    public Footballer[] findAllFoot(){
        loggerInfo.info("Find all footballer");
       return allDao.findAllFoot();
    }

    public Footballer findFootById(String id){
        loggerInfo.info("Find footballer by id: "+id);
        return allDao.findFootById(id);
    }

    public String deleteFootFromTeam(String id){
        loggerWarn.warn("Removing footballer by id " + id + " from his team");
       return(allDao.deleteFootFromTeam(id));

    }

    public void pushFootToTeam(Footballer f, Team team){
        loggerInfo.info("Start pushing footballer:  " + f.getFoot_name() + "from team:  " + team.getT_name() );
        allDao.pushFootToTeam(f,team);
        loggerInfo.info("End pushing footballer");
    }



    public void createTeam(Team team){
        loggerInfo.info("Start create team" + team.getT_name());
        allDao.createTeam(team);
        loggerInfo.info("End create team");
    }

    public void updateTeam(Team team) {
        loggerInfo.info("Start update team" + team.getT_name());
        allDao.updateTeam(team);
        loggerInfo.info("End update team");
    }

    public void deleteTeam(String id) {
        loggerWarn.warn("Start removing of team by id: " + id);
        allDao.deleteTeam(id);
        loggerWarn.warn("End removing of team");

    }

    public Team[] findAllTeam(){
        loggerInfo.info("Find all team");
        return allDao.findAllTeam();
    }

    public Team findTeamById(String id){
        loggerInfo.info("Find team by id: "+id);

        return allDao.findTeamById(id);
    }




}
