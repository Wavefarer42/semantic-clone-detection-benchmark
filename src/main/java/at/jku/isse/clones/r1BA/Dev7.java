package at.jku.isse.clones.r1BA;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author a1exS
 */
public class Dev7 {
    public static float run(int _destination, int _positionCount, int[] _initialPosition, int[] _speed) {

        BigDecimal D = new BigDecimal(_destination);
        long[][] horses = new long[_positionCount][2];
        for (int j = 0; j < horses.length; j++) {
            horses[j][0] = _initialPosition[j];
            horses[j][1] = _speed[j];
        }

        BigDecimal mosttime = new BigDecimal(0).setScale(100);

        for (int j = 0; j < horses.length; j++) {
            BigDecimal temp = (new BigDecimal(D.longValue() - horses[j][0]).divide(new BigDecimal(horses[j][1]), 100, RoundingMode.HALF_UP));
            // BigDecimal temp = new BigDecimal((D.longValue()-horses[j][0])/horses[j][1]);
            if (mosttime.compareTo(temp) == -1)
                mosttime = temp;
        }

        BigDecimal res = D.divide(mosttime, 100, RoundingMode.HALF_UP);

        return res.floatValue();
    }
}
