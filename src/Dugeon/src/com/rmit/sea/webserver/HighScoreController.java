/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.webserver;

import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.webserver.model.Score;
import com.rmit.sea.webserver.model.ScoreData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Steve
 */
public class HighScoreController {

    private ScoreData scoreData;
    private static Score latestScore;

    public HighScoreController() {
    }

    public String getScoreTable() {
        Set<Score> list = readHighScore().getHighScoredata();
        List<Score> sortedList = new ArrayList<Score>(list);
        Collections.sort(sortedList, new Comparator<Score>() {

            @Override
            public int compare(Score o1, Score o2) {
                if (o1.getGold() > o2.getGold()) {
                    return -1;
                } else if (o1.getGold() < o2.getGold()) {
                    return 1;
                } else {
                    if (o1.getLevel() > o2.getLevel()) {
                        return -1;
                    } else if (o1.getLevel() < o2.getLevel()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }

            }
        });
        System.out.println("Webserver " + list.size());
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>HighScore</h1>");
        sb.append("<table border='1'>");
        sb.append("<tr bgcolor='red'><th align='center' width='20%'>Player</th><th align='center' width='20%'>Status</th><th align='center' width='20%'>Gold</th><th align='center' width='20%'>Level</th><th align='center' width='50%'>Last Login</th></tr>");
        for (Score s : sortedList) {
//            if (latestScore != null && s.getScore() == latestScore.getScore()
//                    && s.getUserName().equals(latestScore.getUserName())
//                    && s.getDate().equals(latestScore.getDate())) {
//                sb.append("<tr bgcolor='blue'>");
//            } else {
//                sb.append("<tr>");
//            }
            sb.append("<tr>");
            sb.append("<td align='center' >");
            if (s.getTitle() == null || s.getTitle().isEmpty()) {
                sb.append(s.getUserName());

            } else {
                sb.append(s.getUserName()+" "+s.getTitle());
            }
            sb.append("</td>");
            sb.append("<td align='center' >");
            sb.append(s.isAlive()?"Alive":"Dead");
            sb.append("</td>");
            sb.append("<td align='center' >");
            sb.append(s.getGold());
            sb.append("</td>");
            sb.append("<td align='center' >");
            sb.append(s.getLevel());
            sb.append("</td>");
            sb.append("<td align='center' >");
            sb.append(s.getDate());
            sb.append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        System.out.println(sb.toString());
        return sb.toString();
    }

    public void addHighScore(Score score) {
        latestScore = score;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            System.out.println("Reading from file");
            scoreData = readHighScore();
            if (scoreData == null) {
                scoreData = new ScoreData(new HashSet<Score>());
            }
            System.out.println("Before add " + scoreData.size());
            scoreData.add(score);
            fos = new FileOutputStream(Constant.HIGH_SCORE_FILE);

            oos = new ObjectOutputStream(fos);
            oos.writeObject(scoreData);
            oos.flush();
            System.out.println("After add " + scoreData.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    private ScoreData readHighScore() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if (new File(Constant.HIGH_SCORE_FILE).length() == 0) {
            return new ScoreData(new HashSet<Score>());
        }
        try {
            fis = new FileInputStream(Constant.HIGH_SCORE_FILE);

            ois = new ObjectInputStream(fis);

            Object o = ois.readObject();

            if (o != null) {
                return (ScoreData) o;
            } else {
                return new ScoreData(new HashSet<Score>());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("2");
            ex.printStackTrace();
        } catch (IOException e) {
            System.out.println("3" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                System.out.println("close");
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("dasdada");
        return null;
    }
}
