package org.example.bst;

import sun.nio.cs.ext.MacHebrew;

public class AVLTree {

    private Node root;

    private static class Node {
        private int height;
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
            this.height = 0;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return this.data + "";
        }
    }

    public AVLTree() {
        root = null;
    }

    private int getHeight(Node x) {
        return x == null ? -1 : x.height;
    }

    private int getBalanceFactor(Node x) {
        return x == null ? 0 : getHeight(x.left) - getHeight(x.right);
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }

    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }

    private Node balance(Node x) {
        int balanceFactor = getBalanceFactor(x);
        if(balanceFactor > 1 && getBalanceFactor(x.left) >= 0) {
            x = rightRotate(x);
        } else if(balanceFactor > 1 && getBalanceFactor(x.left) < 0) {
            x.left = leftRotate(x.left);
            x = rightRotate(x);
        } else if(balanceFactor < -1 && getBalanceFactor(x.right) <= 0) {
            x = leftRotate(x);
        } else if(balanceFactor < -1 && getBalanceFactor(x.right) > 0) {
            x.right = rightRotate(x.right);
            x = leftRotate(x);
        }
        return x;
    }

    private Node insert(Node x, int data) {
        if(x == null) {
            return new Node(data);
        }

        if(data < x.data) {
            x.left = insert(x.left, data);
        } else if(data > x.data) {
            x.right = insert(x.right, data);
        }

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return balance(x);
    }

    public void delete(int data) {
        root = delete(root, data);
    }

    private Node delete(Node x, int data) {
        if(x == null) {
            return x;
        }

        if(data < x.data) {
            x.left = delete(x.left, data);
        } else if(data > x.data) {
            x.right = delete(x.right, data);
        } else {
            if(x.left == null || x.right == null) {
                x = x.left == null ? x.right : x.left;
            } else {
                Node successorNode = minNode(x.right);
                successorNode.right = delete(x.right, successorNode.data);
                successorNode.left = x.left;
                x = successorNode;
                /*
                另一种写法：
                Node successorNode = minNode(x.right);
                x.data = successorNode.data;
                x.right = delete(x.right, successorNode.data);
                 */
            }
        }

        if(x == null) {
            return x;
        }

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return balance(x);
    }

    private Node minNode(Node x) {
        while(x.left != null) {
            x = x.left;
        }
        return x;
    }

    public void inorderTraversal(Node x) {
        if(x == null) {
            return;
        }

        inorderTraversal(x.left);
        System.out.print(x + " ");
        inorderTraversal(x.right);
    }

    public void printTree() {
        System.out.println("printTree: ");
        inorderTraversal(root);
        System.out.println();
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.insert(40);
        avlTree.insert(50);
        avlTree.insert(25);
        avlTree.insert(41);
        avlTree.insert(14);
        avlTree.insert(54);
        avlTree.insert(28);
        avlTree.insert(49);

        avlTree.printTree();

        avlTree.delete(30);
        avlTree.delete(10);
        avlTree.delete(50);
        avlTree.delete(49);
        avlTree.printTree();
    }
}