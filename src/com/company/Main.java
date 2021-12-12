package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 2000;
    public static int bossDamage = 100;
    public static String bossWeakness = "";

    public static int[] heroesHealth = {300, 300, 300, 175, 240, 950,300, 300};
    public static int[] heroesDamage = {15, 20, 25, 20, 25, 5,10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "lucky", "thor", "golem","Berserk", "Heal"};
    public static int round_number = 1;

    public static void main(String[] args) {
        printStatistics(); // До начала игры вывод статистики
        while (!isGameFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length - 1); //0,1,2
        bossWeakness = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossWeakness);
    }

    public static void round() {
        round_number++;
        changeBossDefence();
        heal();
        bossHits();
        heroesHit();
        printStatistics();
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossWeakness) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void heal() {
        Random rand = new Random(); //instance of random class
        int upperbound = 2;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);
        int a = int_random;
        for (int i = 0; i < heroesHealth.length; i++) {

            if (heroesHealth[i] < 100 && heroesHealth[a] > 0) {
                heroesHealth[a] = heroesHealth[a] + 5;
            } else {
                heroesHealth[a] = heroesHealth[a] + 0;
            }
        }
    }

    public static void bossHits() {
        Random random = new Random();
        random.nextBoolean();
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    if (random.nextBoolean() == true) {
                        heroesHealth[3] = heroesHealth[3] - 0;
                    } else {
                        if (random.nextBoolean() == true) {
                            heroesHealth[i] = heroesHealth[i] - 0;
                            System.out.println("Boss Stunned");
                        } else {
                            if (random.nextBoolean() == true && heroesHealth[5] > 370 ) {
                                int shield = bossDamage/5;
                                heroesHealth[i] = heroesHealth[i] - (bossDamage - shield);
                                heroesHealth[5] = heroesHealth[5] - bossDamage * 2;
                            } else {
                                if(random.nextBoolean() == true && heroesHealth[6] > 0){
                                    random = new Random();
                                    random.nextInt(20, bossDamage);
                                    heroesHealth[6] = heroesHealth[6] - (bossDamage - random.nextInt(20,bossDamage));
                                    heroesDamage[6] = heroesDamage[6] + random.nextInt(20,bossDamage);
                                }
                                else{
                                heroesHealth[i] = heroesHealth[i] - bossDamage;
                            }}
                        }

                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND _______________");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
        System.out.println("_______________");
    }
}