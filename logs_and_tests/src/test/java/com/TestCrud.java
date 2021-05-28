package com;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCrud {

    private static AllService allService = new AllService();

    @BeforeAll
    public static void setUp(){
        for (int i = 0; i < 9; i++) {
            Footballer footballer =new Footballer("testFoot"+i,20+i);
            allService.createFoot(footballer);
        }
        for (int i = 0; i < 4; i++) {
            Team team = new Team(5);
            team.setT_name("testTeam"+i);
            allService.createTeam(team);
        }
        Assertions.assertTrue(allService.findAllFoot().length!=0 && allService.findAllTeam().length!=0);
    }



    @Test
    @Order(1)
    public void findAllFoot() {
        int cnt = 0;
        Footballer[] footballers = allService.findAllFoot();
        for (Footballer footballer1 : footballers) {
            if(footballer1!=null){
                cnt++;
            }
        }
        Assertions.assertTrue(cnt==9);
    }

    @Test
    @Order(2)
    public void findAllTeam() {
        int cnt = 0;
        Team[] teams = allService.findAllTeam();
        for (Team t1 : teams) {
            if(t1!=null){
                cnt++;
            }
        }
        Assertions.assertTrue(cnt==4);
    }

    @Test
    @Order(3)
    public void findFootByID() {
        boolean isPrez = false;
        Footballer[] footballers = allService.findAllFoot();
        String id = footballers[1].getFoot_id();
        if(allService.findFootById(id)!=null){
            isPrez = true;
        }
        Assertions.assertTrue(isPrez);
    }

    @Test
    @Order(4)
    public void findTeamByID() {
        boolean isPrez = false;
        Team[] teams = allService.findAllTeam();
        String id = teams[1].getT_id();
        if(allService.findTeamById(id)!=null){
            isPrez = true;
        }
        Assertions.assertTrue(isPrez);
    }

    @Test
    @Order(5)
    public void deleteFootByID() {
        Footballer[] footballers = allService.findAllFoot();
        String id = footballers[1].getFoot_id();
        allService.deleteFoot(id);
        footballers = allService.findAllFoot();
        Assertions.assertTrue(footballers[1]==null);
    }


    @Test
    @Order(6)
    public void deleteTeamByID() {
        Team[] teams = allService.findAllTeam();
        String id = teams[1].getT_id();
        allService.deleteTeam(id);
        teams = allService.findAllTeam();
        Assertions.assertTrue(teams[1]==null);
    }


    @Test
    @Order(7)
    public void createFoot() {
        Footballer footballer = new Footballer("test",23);
        allService.createFoot(footballer);
        int cnt = 0;
        Footballer[] footballers = allService.findAllFoot();
        for (Footballer footballer1 : footballers) {
            if(footballer1!=null){
                cnt++;
            }
        }
        Assertions.assertTrue(cnt>0);
    }
    @Test
    @Order(8)
    public void createTeam() {
        Team team = new Team(5);
        team.setT_name("Lao");
        allService.createTeam(team);
        int cn = 0;
        Team[] teams = allService.findAllTeam();
        for (Team te : teams) {
            if(te!=null){
                cn++;
            }
        }
        Assertions.assertTrue(cn>0);
    }
    @Test
    @Order(9)
    public void updateFoot(){
        Footballer[] footballers = allService.findAllFoot();
        Footballer f1 = footballers[1];
        int a = f1.getFoot_age();
        f1.setFoot_age(1);
        String at = f1.getFoot_id();
        allService.updateFoot(f1);
        Footballer fo = allService.findFootById(at);
        int b = fo.getFoot_age();
        Assertions.assertTrue(a!=b);
    }

    @Test
    @Order(10)
    public void updateTeam(){
        Team[]teams = allService.findAllTeam();
        Team t1 = teams[1];
        String a = t1.getT_name();
        t1.setT_name("testTTTT");
        String at = t1.getT_id();
        allService.updateTeam(t1);
        t1 = allService.findTeamById(at);
        String b = t1.getT_name();
        Assertions.assertFalse(a.equals(b));
    }

    @Test
    @Order(11)
    public void footToTeam(){
        Footballer[] footballers = allService.findAllFoot();
        Footballer f1 = footballers[1];
        Team[]teams = allService.findAllTeam();
        Team t1 = teams[1];
        allService.pushFootToTeam(f1,t1);
        teams = allService.findAllTeam();
        t1 = teams[1];
        Footballer[]testf = t1.getT_foots();
        Assertions.assertFalse(testf[0]==null);
    }

    @Test
    @Order(12)
    public void deleteFootFromTeam(){
        Footballer[] footballers = allService.findAllFoot();
        Footballer f1 = footballers[1];
        String f1ID = f1.getFoot_id();

        allService.deleteFootFromTeam(f1ID);

        Team[]teams = allService.findAllTeam();
        Team t1 = teams[1];
        Footballer[]tests = t1.getT_foots();
        Assertions.assertTrue(tests[0]==null);
    }









}
