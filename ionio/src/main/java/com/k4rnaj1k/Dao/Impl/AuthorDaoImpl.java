package com.k4rnaj1k.Dao.Impl;

import com.k4rnaj1k.Dao.AuthorDao;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.k4rnaj1k.entities.Author;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    private int id;

    public AuthorDaoImpl(){
        getId();
    }

    private void getId(){
        try(CSVReader reader = new CSVReader(new FileReader("authors.csv"))){
            List<String[]> csvData = reader.readAll();
            id = Integer.parseInt(csvData.get(csvData.size()-1)[CSVIndex.ID.ordinal()]);
            id++;
        }catch (Exception e){
            id = 1;
        }
    }

    @Override
    public void create(Author author) {
        getId();
        try (CSVWriter writer = new CSVWriter(new FileWriter("authors.csv", true))) {
            List<String[]> csvData = new ArrayList<>();
            String[] data = {String.valueOf(id), author.getName(), author.getSurname(), author.getBooklist(), "true"};
            csvData.add(data);
            writer.writeAll(csvData);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public ArrayList<Author> findAll() {
        ArrayList<Author> authors = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader("authors.csv"));){
            List<String[]> all = reader.readAll();
            for (int i = 1; i < all.size(); i++) {
                Author author = new Author();
                author.setName(all.get(i)[CSVIndex.NAME.ordinal()]);
                author.setSurname(all.get(i)[CSVIndex.SURNAME.ordinal()]);
                author.setBooklist(all.get(i)[CSVIndex.BOOKS.ordinal()]);
                author.setVisible(all.get(i)[CSVIndex.VISIBLE.ordinal()]);
                authors.add(author);
            }
            return authors;
        }catch (Exception e){
            System.out.println("Sorry, there was an exception during the process of getting all the authors.");
        }
        return null;
    }

    @Override
    public Author find(Author author) {
        Author res = null;
        try(CSVReader reader = new CSVReader(new FileReader("authors.csv"))){
            List<String[]> authors = reader.readAll();
            for (int i = 1; i < authors.size(); i++) {
                String[] authorrow = authors.get(i);
                if(author.getName().equals(authorrow[CSVIndex.NAME.ordinal()]) && author.getSurname().equals(authorrow[CSVIndex.SURNAME.ordinal()])){
                    res = new Author();
                    res.setBooklist(authorrow[CSVIndex.BOOKS.ordinal()]);
                    res.setId(Integer.parseInt(authorrow[CSVIndex.ID.ordinal()]));
                    res.setName(authorrow[CSVIndex.NAME.ordinal()]);
                    res.setSurname(authorrow[CSVIndex.SURNAME.ordinal()]);
                    res.setVisible(authorrow[CSVIndex.VISIBLE.ordinal()]);
                }
            }
        }catch (Exception e){
        }
        return res;
    }

    @Override
    public void update(Author current, Author updated) {
        try (
                CSVReader reader = new CSVReader(new FileReader("authors.csv"))
        ) {
            List<String[]> data = reader.readAll();
            data.set(current.getId(), new String[]{String.valueOf(current.getId()), updated.getName(), updated.getSurname(), current.getBooklist(), updated.getVisible()==null?current.getVisible(): updated.getVisible()});
            reader.close();
            CSVWriter writer = new CSVWriter(new FileWriter(new File("authors.csv"), false));
            writer.writeAll(data);
            writer.close();
        } catch (Exception e) {
        }
    }
}
