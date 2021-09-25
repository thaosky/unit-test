package com.example.demo.healthy;

public class ActivityCalculator {

    private static final int WORKOUT_DURATION_MIN = 45;

    public static String rateActivityLevel(int weeklyCardioMin, int weeklyWorkoutSessions) {

        if (weeklyCardioMin < 0 || weeklyWorkoutSessions < 0) {
            throw new RuntimeException("Input below 0");
        }

        // Tổng thời gian thể dục 1 tuần
        int totalMinutes = weeklyCardioMin + weeklyWorkoutSessions * WORKOUT_DURATION_MIN;

        // Trung bình số phút thể dục 1 ngày
        double avgDailyActivityMin = totalMinutes / 7;
        if (avgDailyActivityMin < 20) {
            return "bad";
        } else if (avgDailyActivityMin > 40) {
            return "good";
        } else {
            return "average";
        }
    }

}
