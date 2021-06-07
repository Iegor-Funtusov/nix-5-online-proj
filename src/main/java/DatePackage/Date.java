package DatePackage;
import DateHelpers.DateConstants;


public class Date implements Comparable{
    private int year;
    private byte month;
    private byte day;
    private int time;       //Всё время хранитсятся в секундах. Переполнения инта не будет, т.к. в сутках максимум 86400 секунд


    public void setValues(int []result){
        day = (byte)result[0];
        month = (byte)result[1];
        year = result[2];
        time = result[3] * DateConstants.SECONDS_IN_HOUR + result[4] * DateConstants.SECONDS_IN_MINUTE + result[5];
    }


    @Override
    public String toString() {
        byte hours = (byte) (time / DateConstants.SECONDS_IN_HOUR);
        byte minutes = (byte) ((time / DateConstants.SECONDS_IN_MINUTE) % DateConstants.SECONDS_IN_MINUTE);
        byte seconds = (byte) (time % DateConstants.SECONDS_IN_MINUTE);

        return year + "/" +
                month + "/" +
                day + " " +
                hours + ":" +
                minutes + ":" +
                seconds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return year == date.year && month == date.month && day == date.day && time == date.time;
    }

    public int getYear() {
        return year;
    }

    public byte getMonth() {
        return month;
    }

    public byte getDay() {
        return day;
    }

    public int getTime() {
        return time;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public void setTime(int time) {
        this.time = time;
    }


    //Для корректной сортировки
    @Override
    public int compareTo(Object objectToCompare){
        Date dateToCompare = (Date) objectToCompare;
        if(this.year < dateToCompare.getYear()){
            return -1;
        }
        if(this.year > dateToCompare.getYear()){
            return 1;
        }
        return compareMonth(dateToCompare);
    }

    private int compareMonth(Date dateToCompare){
        if(this.month < dateToCompare.getMonth()){
            return -1;
        }
        if(this.month > dateToCompare.getMonth()){
            return 1;
        }
        return compareDay(dateToCompare);
    }

    private int compareDay(Date dateToCompare){
        if(this.day < dateToCompare.getDay()){
            return -1;
        }
        if(this.day > dateToCompare.getDay()){
            return 1;
        }
        return compareTime(dateToCompare);
    }

    private int compareTime(Date dateToCompare){
        return Integer.compare(this.time, dateToCompare.getTime());
    }
}
