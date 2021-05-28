package com;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class AllDao {
    private final int footMasSize = 10;
    private final int teamMasSize = 5;
    Footballer [] footballers = new Footballer[footMasSize];
    Team[] teams = new Team[teamMasSize];


    ///////////////////_____FOR_TEAM________/////////
//+
    public void createTeam(Team team){
        team.setT_id(generateIdTeam(UUID.randomUUID().toString()));
        for (int i = 0; i < teams.length; i++) {
            if (teams[i]==null){
                teams[i] = team;
                break;
            }
        }
    }
//+
    public void deleteTeam(String id){
        for (int i = 0; i < teams.length; i++) {
            if(teams[i]!=null) {
                if (teams[i].getT_id().equals(id)) {
                    System.out.println("Команда " + teams[i].getT_name() + " удалена");
                    teams[i] = null;
                    break;

                }
            }
        }
    }
//+
    public void updateTeam(Team team){
        Team te = getTeamById(team.getT_id());
        if (te == null) {
            throw new RuntimeException("Такой команды нет");
        }
        try {
            BeanUtils.copyProperties(te, team);
        }
        catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }

    }
//+
    public Team findTeamById(String id){
        if(getTeamById(id)==null){
            System.out.println("Такой команды нет");
        }

        return getTeamById(id);
    }
//+
    public Team[] findAllTeam(){

        return teams;
    }


    private String generateIdTeam(String id) {
        if(Arrays.stream(teams).filter(Objects::nonNull).anyMatch(i -> i.getT_id().equals(id))) {
            return generateIdTeam(UUID.randomUUID().toString());
        }
        return id;
    }
    private Team getTeamById(String id) {
        return Arrays.stream(teams)
                .filter(i -> i.getT_id().equals(id))
                .findAny()
                .orElse(null);
    }

    /////////////////////______FOR_Foot______///////////////////


//+
public String deleteFootFromTeam(String id) {
    String team_name = "";
    Footballer f = findFootById(id);
    for (Team team : teams) {
        if (team != null) {
            Footballer[] footballers = team.getT_foots();
            for (int i = 0; i < footballers.length; i++) {
                if(footballers[i]!=null){
                if (footballers[i].equals(f)) {
                    System.out.println("Игрок " + footballers[i].getFoot_name() + " удален из команды " + team.getT_name());
                    footballers[i] = null;
                    team.setT_foots(footballers);
                    updateTeam(team);
                    team_name=team.getT_name();
                    break;
                }
                }

            }

        }

    }
    return team_name;
}

//+
public void pushFootToTeam(Footballer f, Team team) {
        if(f!=null && team!=null){
            Footballer[] fu = team.getT_foots();
            if(fu[fu.length-1]==null){
                for (int i=0;i<fu.length;i++) {
                    if(fu[i]==null){
                        fu[i]=f;
                        team.setT_foots(fu);
                        updateTeam(team);
                        System.out.println("Игрок добавлен в эту команду");
                        break;
                    }
                }
            }
            else{
                System.out.println("Команда переполнена");
            }

        }
        else {
            System.out.println("Футболист или команда равны NULL");
        }

    }

//+
public void createFoot(Footballer footballer) {

    footballer.setFoot_id(generateFootId(UUID.randomUUID().toString()));
    if (footballers[footballers.length - 1] == null) {
        for (int i = 0; i < footballers.length; i++) {

            if (footballers[i] == null) {
                footballers[i] = footballer;
                break;
            }
        }
    } else {
        System.out.println("База данных переполнена");
    }


}

//+
public Footballer findFootById(String id){
        if(getFootById(id)==null){
            System.out.println("Такого футболиста нет");
        }
            return getFootById(id);
    }

//+
public Footballer[] findAllFoot(){
        return footballers;
    }

//+
public void deleteFoot(String id) {
    if (StringUtils.isNotBlank(id)) {
        for (int i = 0; i < footballers.length; i++) {
            if (footballers[i].getFoot_id().equals(id)) {
                deleteFootFromTeam(id);
                footballers[i] = null;

                break;
            }
        }
    } else {
        throw new RuntimeException("Такого футболиста нет");
    }
}

//+
public void updateFoot(Footballer footballer){
        Footballer footb = getFootById(footballer.getFoot_id());
        if (footb == null) {
            throw new RuntimeException("Такого футболиста нет");
        }

        try {
            BeanUtils.copyProperties(footb, footballer);
        }
        catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
    }


    private String generateFootId(String id) {
        if(Arrays.stream(footballers).filter(Objects::nonNull).anyMatch(i -> i.getFoot_id().equals(id))) {
            return generateFootId(UUID.randomUUID().toString());
        }
        return id;
    }
    private Footballer getFootById(String id) {
        return Arrays.stream(footballers)
                .filter(i -> i.getFoot_id().equals(id))
                .findAny()
                .orElse(null);
    }
}
