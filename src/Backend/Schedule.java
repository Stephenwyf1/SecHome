package Backend;

import java.util.Calendar;
import java.util.Date;

public class Schedule {
    private SecHomeSystem system = SecHomeSystem.getSingletonSystem();
    private String jobType;
    private String dateType;
    private int hour;
    private int min;

    Schedule(String jobType,String dateType,int hour,int min) {
        this.jobType = jobType;
        this.dateType = dateType;
        this.hour = hour;
        this.min = min;
    }

    Schedule() {}

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getDateType() {
        return dateType;
    }

    public int getHour() {
        return hour;
    }

    public String getJobType() {
        return jobType;
    }

    public int getMin() {
        return min;
    }

    public boolean canStart() {
        Calendar c = Calendar.getInstance();
        int nowHour = c.get(Calendar.HOUR_OF_DAY);
        int nowMin = c.get(Calendar.MINUTE);
//        System.out.println(isDay() && nowHour == this.hour && nowMin == this.getMin() && c.get(Calendar.SECOND) <= 5);
        return isDay() && nowHour == this.hour && nowMin == this.getMin() && c.get(Calendar.SECOND) <= 1;
    }

    public void startJob() {
        if (jobType.equals("On")) {
            system.turnOnSystem();
        } else {
            system.turnOffSystem();
        }
    }


    public boolean isDay() {
        int now = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        switch (dateType) {
            case "Weekdays":
                return now <= 6 && now >= 2;
            case "Weekends":
                return now == 1 || now == 7;
            default:
                return true;
        }
    }
}
