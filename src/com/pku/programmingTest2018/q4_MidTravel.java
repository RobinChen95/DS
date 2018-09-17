package com.pku.programmingTest2018;

public class q4_MidTravel {
    private class Node{
        //定义结点
        private char data;
        private int type;
        private Node father;
        private int root=1;

        public Node(char da, Node fa , int ty){
            data = da;
            type = ty;
            father = fa;
        }

        public Node(char da, int root , int ty){
            data = da;
            type = ty;
            root = 0 ; //0代表根节点，1代表不是根节点
        }

        public char getName(){return data;}

        public Node getFather(){return father;}

        public int getType(){return type;}
    }

    private class Link{
        private Node father;
        private Node son;
        private int types;
        private Link next;
    }

    public static void main(String args[]){
    }

    public q4_MidTravel(){
        Node A = new Node('A',0,1);
        Node B = new Node('B',A,1);
        Node C = new Node('C',A,2);
        Node D = new Node('D',B,1);
        Node E = new Node('E',B,2);
        Node F = new Node('F',C,1);
        Node G = new Node('G',D,1);
        Node H = new Node('H',D,2);
    }

    public void midTravel(Node n){
        //中序遍历先遍历左子节点，再访问自己，最后遍历右字节点
    }
}
