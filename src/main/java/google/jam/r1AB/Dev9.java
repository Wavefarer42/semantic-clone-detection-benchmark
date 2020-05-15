package google.jam.r1AB;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dev9 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {

        int n = _ingredientsCount;
        int p = _packagesCount;
        List<Integer> norms = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            norms.add(_ingredientQuantities[j]);
        }
        List<List<Integer>> allpackages = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            List<Integer> currentPack = new ArrayList<>();
            for (int k = 0; k < p; k++) {
                currentPack.add(_packages[j][k]);
            }
            Collections.sort(currentPack);
            allpackages.add(currentPack);
        }
        return process(norms, allpackages);
    }

    private static int process(List<Integer> norms, List<List<Integer>> allpackages) {
        List<List<Rating>> allratings = new ArrayList<>();
        for (int i = 0; i < norms.size(); i++) {
            List<Integer> a = allpackages.get(i);
            List<Rating> ratings = new ArrayList<>();
            int norm = norms.get(i);
            for (int k = 0; k < a.size(); k++) {
                boolean addNew = true;
                int normmultiplier = 1;
                int upper;
                int lower = (int) (norm * normmultiplier * 0.9);
                while (a.get(k) >= lower) {
                    upper = (int) (norm * normmultiplier * 1.1);
                    lower = (int) (norm * normmultiplier * 0.9);
                    if (a.get(k) >= lower && a.get(k) <= upper) {
                        if (addNew) {
                            ratings.add(new Rating(normmultiplier, normmultiplier));
                            addNew = false;
                        } else {
                            ratings.get(ratings.size() - 1).max = normmultiplier;
                        }
                    }
                    normmultiplier++;
                }
            }
            allratings.add(ratings);
        }
        List<Rating> ratingList = allratings.get(0);
        int count = 0;
        for (int j = 0; j < ratingList.size(); j++) {
            List<Point> pointsToRemove = new ArrayList<>();
            for (int k = ratingList.get(j).min; k <= ratingList.get(j).max; k++) {
                for (int c = 1; c < allratings.size(); c++) {
                    for (int i = 0; i < allratings.get(c).size() && pointsToRemove.size() < c; i++) {
                        if (allratings.get(c).get(i).max >= k && allratings.get(c).get(i).min <= k) {
                            pointsToRemove.add(new Point(c, i));
                        }
                    }
                }

            }
            if (pointsToRemove.size() == allratings.size() - 1) {
                count++;
                for (int i = 0; i < pointsToRemove.size(); i++) {
                    Point point = pointsToRemove.get(i);
                    allratings.get(point.x).remove(point.y);
                }
            }
        }
        return count;
    }


    static class Rating {
        int min;
        int max;

        public Rating(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}