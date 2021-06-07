import DateHelpers.DateParser;
import org.junit.Test;


//Тесты 1-20: dd/mm/yyyy + hh:mm:ss или mm:ss
//Тесты 21-36: /mm/yyyy + hh:mm:ss или mm:ss
//Тесты 37-51: /mm/ + hh:mm:ss или mm:ss
//Тесты 52-65: yyyy + hh:mm:ss или mm:ss


//Тесты для проверки работы преобразования строки в целосную дату
public class CreateDateTests {
    private final DateParser dateParser = new DateParser();

    @Test
    public void createDateTest1(){
        String dateStr = "11/10/2002";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest2(){
        String dateStr = "-11/12/2012";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest3(){
        String dateStr = "35/12/2010";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest4(){
        String dateStr = "11/-5/2010";
        dateParser.parseDate(dateStr);
    }


    @Test(expected = RuntimeException.class)
    public void createDateTest5(){
        String dateStr = "11/13/2010";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest6(){
        String dateStr = "11/10/-2010";
        dateParser.parseDate(dateStr);
    }

    @Test
    public void createDateTest7(){
        String dateStr = "11/10/2002 11:10:25";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest8(){
        String dateStr = "11/10/2002 -11:10:25";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest9(){
        String dateStr = "11/10/2002 50:10:25";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest10(){
        String dateStr = "11/10/2002 11:-10:25";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest11(){
        String dateStr = "11/10/2002 11:70:25";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest12(){
        String dateStr = "11/10/2002 11:10:-25";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest13(){
        String dateStr = "11/10/2002 11:10:125";
        dateParser.parseDate(dateStr);
    }

    @Test
    public void createDateTest14(){
        String dateStr = "11/12/2002 10:25";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest15(){
        String dateStr = "11/12/2002 -10:25";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest16(){
        String dateStr = "11/12/2002 100:25";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest17(){
        String dateStr = "11/12/2002 10:-25";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest18(){
        String dateStr = "11/12/2002 10:250";
        dateParser.parseDate(dateStr);
    }

    //29-е февраля високосного года
    @Test
    public void createDateTest19(){
        String dateStr = "29/02/2020";
        dateParser.parseDate(dateStr);
    }

    //29-е февраля не високосного года
    @Test(expected = RuntimeException.class)
    public void createDateTest20(){
        String dateStr = "29/02/2021";
        dateParser.parseDate(dateStr);
    }



    @Test
    public void createDateTest21(){
        String dateStr = "/02/2021";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest22(){
        String dateStr = "/-2/2021";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest23(){
        String dateStr = "/20/2021";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest24(){
        String dateStr = "/2/-2021";
        dateParser.parseDate(dateStr);
    }

    @Test
    public void createDateTest25(){
        String dateStr = "/02/2021 11:12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest26(){
        String dateStr = "/02/2021 -11:12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest27(){
        String dateStr = "/02/2021 30:12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest28(){
        String dateStr = "/02/2021 11:-12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest29(){
        String dateStr = "/02/2021 11:70:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest30(){
        String dateStr = "/02/2021 11:70:-13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createDateTest31(){
        String dateStr = "/02/2021 11:70:65";
        dateParser.parseDate(dateStr);
    }

    @Test
    public void createTest32(){
        String dateStr = "/02/2021 11:12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest33(){
        String dateStr = "/02/2021 -11:12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest34(){
        String dateStr = "/02/2021 70:12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest35(){
        String dateStr = "/02/2021 11:-12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest36(){
        String dateStr = "/02/2021 11:65";
        dateParser.parseDate(dateStr);
    }



    @Test
    public void createTest37(){
        String dateStr = "/3/";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest38(){
        String dateStr = "/-3/";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest39(){
        String dateStr = "/13/";
        dateParser.parseDate(dateStr);
    }

    @Test
    public void createTest40(){
        String dateStr = "/3/ 11:12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest41(){
        String dateStr = "/3/ -11:12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest42(){
        String dateStr = "/3/ 27:12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest43(){
        String dateStr = "/3/ 11:-12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest44(){
        String dateStr = "/3/ 11:70:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest45(){
        String dateStr = "/3/ 11:12:-13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest46(){
        String dateStr = "/3/ 11:12:70";
        dateParser.parseDate(dateStr);
    }

    @Test
    public void createTest47(){
        String dateStr = "/3/ 11:12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest48(){
        String dateStr = "/3/ -11:12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest49(){
        String dateStr = "/3/ 70:12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest50(){
        String dateStr = "/3/ 11:-12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest51(){
        String dateStr = "/3/ 11:70";
        dateParser.parseDate(dateStr);
    }



    @Test
    public void createTest52(){
        String dateStr = "1998";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest53(){
        String dateStr = "-2015";
        dateParser.parseDate(dateStr);
    }

    @Test
    public void createTest54(){
        String dateStr = "1998 11:12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest55(){
        String dateStr = "1998 -11:12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest56(){
        String dateStr = "1998 27:12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest57(){
        String dateStr = "1998 11:-12:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest58(){
        String dateStr = "1998 11:65:13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest59(){
        String dateStr = "1998 11:12:-13";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest60(){
        String dateStr = "1998 11:12:70";
        dateParser.parseDate(dateStr);
    }

    @Test
    public void createTest61(){
        String dateStr = "1998 11:12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest62(){
        String dateStr = "1998 -11:12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest63(){
        String dateStr = "1998 67:12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest64(){
        String dateStr = "1998 11:-12";
        dateParser.parseDate(dateStr);
    }

    @Test(expected = RuntimeException.class)
    public void createTest65(){
        String dateStr = "1998 11:71";
        dateParser.parseDate(dateStr);
    }


}
