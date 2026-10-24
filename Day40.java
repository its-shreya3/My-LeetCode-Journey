class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arriveA = dayOfYear(arriveAlice);
        int leaveA = dayOfYear(leaveAlice);
        int arriveB = dayOfYear(arriveBob);
        int leaveB = dayOfYear(leaveBob);

        int overlapStart = Math.max(arriveA, arriveB);
        int overlapEnd = Math.min(leaveA, leaveB);

        if (overlapStart <= overlapEnd) {
            return overlapEnd - overlapStart + 1;
        }
        
        return 0;
    }

    private int dayOfYear(String date) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3));
        
        int dayCount = 0;
        for (int i = 0; i < month - 1; i++) {
            dayCount += daysInMonth[i];
        }
        dayCount += day;
        
        return dayCount;
    }
}
