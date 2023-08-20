package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SkipList {

    private final int MAX_LEVEL = 20;

    private final Node head;

    private int level;

    private final Random random;

    private static class Node {
        Node[] right;
        Integer value;

        public Node(int value, int level) {
            this.value = value;
            this.right = new Node[level+1];
        }
    }

    public SkipList() {
        head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
        random = new Random();
    }

    private int calLevel() {
        int newLevel = 0;
        double PROMOTION_PROBABILITY = 0.5;
        while(newLevel < MAX_LEVEL && random.nextDouble() > PROMOTION_PROBABILITY) {
            newLevel++;
        }
        return newLevel;
    }

    public void add(int value) {
        int newLevel = calLevel();
        Node newNode = new Node(value, newLevel);

        if(newLevel > level) {
            level = newLevel;
        }

        Node curNode = head;
        for(int i = level; i >= 0; i--) {
            while(curNode.right[i] != null && curNode.right[i].value < value) {
                curNode = curNode.right[i];
            }

            if(i <= newLevel) {
                newNode.right[i] = curNode.right[i];
                curNode.right[i] = newNode;
            }
        }
    }

    public void remove(int value) {
        Node curNode = head;
        for(int i = level; i >= 0; i--) {
            while(curNode.right[i] != null && curNode.right[i].value < value) {
                curNode = curNode.right[i];
            }

            if(curNode.right[i] != null && curNode.right[i].value == value) {
                curNode.right[i] = curNode.right[i].right[i];
            }
        }
    }

    public boolean exist(int value) {
        Node leftNode = head;
        for(int i = level; i >= 0; i--) {
            while(leftNode.right[i] != null && leftNode.right[i].value < value) {
                leftNode = leftNode.right[i];
            }

            if(leftNode.right[i] != null && leftNode.right[i].value == value) {
                return true;
            }
        }

        return false;
    }

    public void printStack() {
        for(int i = level; i >= 0; i--) {
            Node node = head.right[i];
            System.out.print("level-" + i + ": ");
            while(node != null) {
                System.out.printf("%d -> ", node.value);
                node = node.right[i];
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= 30; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        SkipList skipList = new SkipList();
        for(int val : list) {
            skipList.add(val);
        }
        skipList.printStack();;

        skipList.remove(11);
        skipList.remove(22);
        skipList.remove(1);
        skipList.remove(30);
        skipList.remove(10);
        skipList.printStack();

        System.out.println(skipList.exist(1));
        System.out.println(skipList.exist(30));
        System.out.println(skipList.exist(12));
        System.out.println(skipList.exist(18));
    }
}

//https://www.jianshu.com/p/9d8296562806
//https://zhuanlan.zhihu.com/p/101143158
//https://zhuanlan.zhihu.com/p/68516038