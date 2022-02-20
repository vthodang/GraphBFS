/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2_graph;

/**
 * Graph class by edge matrix
 * @author VNHS
 */
public class Graph {
    private Integer[][] e;
    
    public Graph(Integer[][] e) {
        this.e = e;
    }
    
    // explore all connected vertex of V
    public String BFS(char V) {
        int Vindex = index(V);
        int n = e.length;
        String explored = "";
        boolean[] beExplored = new boolean[n]; // flag or is explored or to be explored (enqueued)
        Queue toExplore = new Queue(n);
        toExplore.enqueue(Vindex);
        beExplored[Vindex] = true;
        while (!toExplore.isEmpty()) {
            int v = toExplore.dequeue(); // taken from queue
            explored += "," + (char) ('A' + v); // add the vertex to explore string           
            // add connected to v vertex to queue
            for (int j = 0; j < n; j++) {
                if (e[v][j] == 1 && !beExplored[j]) {
                    toExplore.enqueue(j);
                    beExplored[j] = true;
                }
            }
        }
        return explored.substring(1);
    }
    
    // isConnected - if BFS of a virtex all the v
    public boolean isConnected() {
        return BFS('A').length() == 2* e.length - 1;
    }
    
    // vertice degree
    public int degree(char V) {
        int d = 0;
        int i = index(V);
        for (int j = 0; j < e.length; j++) {
            if (e[i][j] == 1) d++;
        }
        return d;
    }
    
    // auxiliary methods
    private int index(char V) {
        return V - 'A';
    }
    
    // auxiliary cyclic queue class for BFS
    private class Queue {
        private int size;
        private int[] data;
        private int head =0, tail =-1;
        
        // constructor - initiate the queue with define size
        public Queue(int size) {
            this.size = size;
            this.data = new int[size];
        }
        
        //isEmpty if tail == -1
        public boolean isEmpty() {
            return tail == -1;
        }
        // isFull if tail - head = size-1 or -1 and not empty
        public boolean isFull() {
            return !isEmpty() && (tail - head == size -1 || tail - head == -1);
        }
        
        // enqueue to tail
        public void enqueue(int x) {
            if (!isFull()) {
                if (tail == size -1) {
                    tail = 0;
                    data[tail] = x;
                } else {
                    data[++tail] = x;
                }
            } else {
                throw new IllegalStateException("Queue is full");
            }
        }
        
        // pop at head
        public int dequeue() {
            if (isEmpty()) {
                throw new NullPointerException("Queue is empty");
            }
            int x = data[head];
            if (head == tail) { // reset queue empty
                tail = -1;
                head = 0;
            } else {
                head = (head +1) % size;
            }
            return x;
        }
    }
}
