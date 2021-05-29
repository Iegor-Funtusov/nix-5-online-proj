package DatePackage;

public class Date {
    private int year;
    private byte month;
    private byte day;
    private int time;       //Всё время хранитсятся в секундах. Переполнения инта не будет, т.к. в сутках максимум 86400 секунд


    public void setValues(int []result){
        day = (byte)result[0];
        month = (byte)result[1];
        year = result[2];
        time = result[3] * 3600 + result[4] * 60 + result[5];
    }

    @Override
    public String toString() {
        byte hours = (byte) (time / 3600);
        byte minutes = (byte) ((time / 60) % 60);
        byte seconds = (byte) (time % 60);

        return year + "/" +
                month + "/" +
                day + " " +
                hours + ":" +
                minutes + ":" +
                seconds;
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
}
