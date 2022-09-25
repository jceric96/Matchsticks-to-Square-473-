
public class Matchsticks {
    public boolean makesquare(int[] matchsticks) {

        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }
        int targetLen = 0;
        for (int stick : matchsticks) {
            targetLen += stick;
        }
        if (targetLen % 4 != 0)
            return false;
        targetLen = targetLen / 4;
        int[] result = new int[4];
        return makesquare(matchsticks, 0, targetLen, result);
    }

    private boolean makesquare(int[] matchsticks, int index, int targetLen, int[] result) {
        if (index == matchsticks.length) {
            if (result[0] == targetLen && result[1] == targetLen && result[2] == targetLen && result[3] == targetLen) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < result.length; i++) {
            if (matchsticks[index] + result[i] > targetLen)
                continue;

            // Optimization(32-38):if checked the same length before,
            // dont check it again, directly jump next position
            int j = i;
            while (--j >= 0) {
                if (result[i] == result[j])
                    break;
            }
            if (j != -1)
                continue;

            result[i] += matchsticks[index];
            if (makesquare(matchsticks, index + 1, targetLen, result))
                return true;
            result[i] -= matchsticks[index];
        }
        return false;
    }

    public static void main(String[] args) {
        Matchsticks sticks = new Matchsticks();
        int[] matchsticks = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 102 };
        System.out.println(sticks.makesquare(matchsticks));
    }
}
