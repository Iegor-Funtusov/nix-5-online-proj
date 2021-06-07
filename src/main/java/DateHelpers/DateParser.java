package DateHelpers;

//Парсер из строки в полноценную дату
public class DateParser {
    private final int DIMENSION = 6;
    private int[] result;

    public DateParser() {
        result = new int[DIMENSION];
    }

//     1 - day
//     2 - month
//     3 - year
//     4 - hour
//     5 - minute
//     6 - second

    public int[] parseDate(String date) {
        result = new int[DIMENSION];

        //Отделяю дату
        String[] onlyDate = date.split("[\\s/]+");
        //Проверяю, если прилетело помимо даты ещё и время, для корректного парса даты отделяю ненужное пока время
        if(onlyDate[onlyDate.length-1].split(":").length > 1){
            onlyDate[onlyDate.length-1] = "";
        }

        //Отделяю время
        String[] onlyTime = date.split(":");
        //Т.к. при разбитии по ':' в первой части останутся "лишние" для времени остатки от даты, их тоже нужно убрать
        String[] tmpTime = onlyTime[0].split(" ");
        onlyTime[0] = tmpTime[tmpTime.length-1];


        if(onlyTime.length == 1){
            if(!validationDate(onlyDate)){
                throw new RuntimeException("Incorrect data entered");
            }
            parseOnlyDate(onlyDate);
        }
        else {
            if(!validationDate(onlyDate)){
                throw new RuntimeException("Incorrect data entered");
            }
            if(!validationTime(onlyTime)){
                throw new RuntimeException("Incorrect time entered");
            }
            parseOnlyDate(onlyDate);
            parseOnlyTime(onlyTime);
        }

        return result;
    }


    private void parseOnlyDate(String[] pieces) {
        switch (pieces.length){
            case 1 ->{
                //Если прилетел просто год
                result[0] = DateConstants.DEFAULT_DAY;
                result[1] = DateConstants.DEFAULT_MONTH;
                result[2] = Integer.parseInt(pieces[0]);
            }
            case 2 ->{
                //Случай /m/ без времени
                if(pieces[0].equals("")){
                    result[0] = DateConstants.DEFAULT_DAY;
                    result[1] = Integer.parseInt(pieces[1]);
                    result[2] = DateConstants.DEFAULT_YEAR;
                }
                //Если прилетел просто год с датой
                else{
                    result[0] = DateConstants.DEFAULT_DAY;
                    result[1] = DateConstants.DEFAULT_MONTH;
                    result[2] = Integer.parseInt(pieces[0]);
                }

            }
            default -> {
                //3 - если прилетела просто полная дата, 4-если дата со временем
                if (pieces.length == 3 || pieces.length == 4) {
                    if (pieces[0].equals("")) {      //Случай /m/y
                        result[0] = DateConstants.DEFAULT_DAY;
                    } else {                         //Общий случай d/m/y
                        result[0] = Integer.parseInt(pieces[0]);
                    }
                    if (pieces[2].equals("")) {           //Случай если прилетает /m/ t:t:t
                        result[2] = DateConstants.DEFAULT_YEAR;
                    } else {
                        result[2] = Integer.parseInt(pieces[2]);
                    }
                    result[1] = Integer.parseInt(pieces[1]);
                }
            }
        }
    }


    private void parseOnlyTime(String[] pieces) {
        switch (pieces.length){
            case 3 -> {
                result[3] = Integer.parseInt(pieces[0]);
                result[4] = Integer.parseInt(pieces[1]);
                result[5] = Integer.parseInt(pieces[2]);
            }
            case 2 -> {
                result[3] = DateConstants.DEFAULT_TIME;
                result[4] = Integer.parseInt(pieces[0]);
                result[5] = Integer.parseInt(pieces[1]);
            }
            default -> throw new RuntimeException("Incorrect time entered");
        }
    }


    private boolean validationDate(String[] pieces){
        //Проверка дня, месяца и года
        if(pieces.length == 3 || pieces.length == 4){
            return validateFullEnteredDate(pieces);
        }
        //Если прилетело 2 из 3 параметров даты
        else if(pieces.length == 2){
            return validateTwoParamEnteredDate(pieces);
        }
        //Если прилетел только один параметр
        else{
            if(pieces[0].equals("")){
                return false;
            }
            else {
                int year = Integer.parseInt(pieces[0]);
                if(year < 0){
                    return false;
                }
            }
        }
        return true;
    }


    //Проверка всех параметров
    private boolean validateFullEnteredDate(String []pieces){
        int day, month, year;
        boolean leapYear = false;

        //Проверка года
        if(!pieces[2].equals("")){
            year = Integer.parseInt(pieces[2]);
            if(year < 0){
                return false;
            }

            //Проверка на високосный год
            if (year % 4 == 0) {
                leapYear = true;
            }
        }

        //Проверка месяца
        if(!pieces[1].equals("")){
            month = Integer.parseInt(pieces[1]);
            if(month < 0 || month > 12)
                return false;
        }
        else {
            month = DateConstants.DEFAULT_MONTH;
        }

        //Проверка дня
        if (!pieces[0].equals("")) {
            day = Integer.parseInt(pieces[0]);
            if(day < 0){
                return false;
            }

            int daysInMonth = DateConstants.getMonthDay().get(month);
            //Если февраль вискосного года
            if(month == 2 && leapYear){
                if(day > (daysInMonth+1)){
                    return false;
                }
            }
            //Если просто любой другой месяц
            else{
                if(day > daysInMonth){
                    return false;
                }
            }
        }
        return true;
    }


    private boolean validateTwoParamEnteredDate(String []pieces){
        int day, month, year;
        //Если задан только год, и время
        if(!pieces[0].equals("")){
            year = Integer.parseInt(pieces[0]);
            if(year < 0){
                return false;
            }
        }

        //Если год не задан, проверю только месяц и день
        else {
            //Проверка месяца
            if(!pieces[1].equals("")){
                month = Integer.parseInt(pieces[1]);
                if(month < 0 || month > 12)
                    return false;
            }
            else {
                month = DateConstants.DEFAULT_MONTH;
            }

            //Проверка дня
            if (!pieces[0].equals("")) {
                day = Integer.parseInt(pieces[0]);
                if(day < 0){
                    return false;
                }

                int daysInMonth = DateConstants.getMonthDay().get(month);
                if(day > daysInMonth){
                    return false;
                }
            }
        }
        return true;
    }


    private boolean validationTime(String[] pieces){
        int seconds, minutes, hours;

        if(pieces.length == 1){
            return false;
        }
        //Если заданно полное время с часами, минутами и секундами
        if(pieces.length == 3){
            //Проверка часов
            if(!validateHours(pieces[0])){
                return false;
            }
            //Проверка минут
            if(!validateMinutes(pieces[1])){
                return false;
            }
            //Проверка секунд
            return validateSeconds(pieces[2]);
        }
        else {
            //Проверка минут
            if(!validateMinutes(pieces[0])){
                return false;
            }
            //Проверка секунд
            return validateSeconds(pieces[1]);
        }
    }


    private boolean validateSeconds(String secondsStr){
        if(!secondsStr.equals("")){
            int seconds = Integer.parseInt(secondsStr);
            return seconds >= 0 && seconds <= 60;
        }
        return true;
    }


    private boolean validateMinutes(String minutesStr){
        if(!minutesStr.equals("")){
            int minutes = Integer.parseInt(minutesStr);
            return minutes >= 0 && minutes <= 60;
        }
        return true;
    }


    private boolean validateHours(String hoursStr){
        if(!hoursStr.equals("")){
            int hours = Integer.parseInt(hoursStr);
            return hours >= 0 && hours <= 24;
        }
        return true;
    }
}