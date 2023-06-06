/*
 * Ryan Cunningham
 * Towers of Hanoi
 * For Computer Science II
 * Whatcom Community College
 * Bellingham, Washington, USA
 * Taught by Jeremiah Ramsey
 * Invented by French mathematician Édouard Lucas in 1883
 * - I thought France had since paid *reparations* for its Middle East
 * colonialism! Mr. Ramsey, if you have any South or East Asian students,
 * apologies are now due.
 */

import java.util.*;
import java.io.*;

 public class RCELucas {
    public static Stack<Integer> towerone = new Stack<>();
    public static Stack<Integer> towertwo = new Stack<>();
    public static Stack<Integer> towerthree = new Stack<>();

    public static void main(String[] args) {
        System.out.println("Towers of Hanoi");
        for (int disk = 4; disk > 0; disk--) {
            towerone.push(disk);
        }
        System.out.println("Pushed 4 disks onto first tower");
        System.out.println("At beginning:");
        System.out.println(towerone.size() + " disks on first tower");
        System.out.println(towertwo.size() + " disks on second tower");
        System.out.println(towerthree.size() + " disks on third tower");
        moveTower(4, towerone, towertwo, towerthree);
        System.out.println("At end:");
        System.out.println(towerone.size() + " disks on first tower");
        System.out.println(towertwo.size() + " disks on second tower");
        System.out.println(towerthree.size() + " disks on third tower");
    }

    private static void moveTower(
        int topN,
        Stack<Integer> towerone,
        Stack<Integer> towertwo,
        Stack<Integer> towerthree
    ) {
        /* modified code from
         * <https://www.tutorialspoint.com/javaexamples/method_tower.htm>
         */
        if (topN == 1) {
            towerone.pop();
            towerthree.push(1);
        } else {
            moveTower(topN - 1, towerone, towerthree, towertwo);
            towerone.pop();
            towerthree.push(topN);
            moveTower(topN - 1, towertwo, towerone, towerthree);
        }
    }
 }