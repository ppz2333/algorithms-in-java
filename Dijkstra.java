package algorithm.common;

/**
 * @author ppz
 * @version 1.0
 * @date 2020/9/2 23:31
 * @description:
 */


public class Dijkstra {
    public static void main(String[] args) {

        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int INF = Integer.MAX_VALUE;
        int matrix[][] = {
                        /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        matrixUDG mu = new matrixUDG(vexs, matrix);

        mu.start(3);

    }
}

class matrixUDG {
    final int INF = Integer.MAX_VALUE;
    int numNodes;
    int[][] matrix;
    int[] prev ;
    int[] dist ;
    char[] vexs;
    //初始化图参数
    public  matrixUDG (char[] vexs, int[][] matrix) {
        this.vexs = vexs;
        this.matrix = matrix;
        numNodes = vexs.length;
        prev = new int[numNodes];
        dist = new int[numNodes];
    }

    // 核心代码！！！！
    // 包括三个数组 1.prev（包含当前节点的上一个父类节点） 2.dist（当前节点与原始节点的距离）
    //					   3.原始矩阵matrix[][],储存图           4.isVisited[]标记数组
    public void start(int vs) {

        //初始化类参数
        boolean[] isVisited = new boolean[numNodes];
        for (int i = 0; i < isVisited.length; i++) {
            dist[i] = matrix[vs][i];
            prev[i] = -1;
            if(dist[i] != INF) {
                prev[i] = vs;
            }
        }
        isVisited[vs] = true;

        //两次循环
        for (int i = 0; i < isVisited.length; i++) {
            int min = INF;
            int k = 0;
            //找到最近的节点
            for (int j = 0; j < isVisited.length; j++) {
                if(!isVisited[j] && dist[j] < min ) {
                    min = dist[j];
                    k = j;
                }
            }

            isVisited[k] = true;
            //更新最近路径和父节点
            for (int j = 0; j < isVisited.length; j++) {

                if(!isVisited[j] && matrix[k][j] != INF) {
                    if(dist[j] > matrix[k][j] + dist[k]) {
                        dist[j] =  matrix[k][j] + dist[k] ;
                        prev[j] = k;
                    }

                }
            }



        }
        //打印节点、路径、距离
        for (int i = 0; i < isVisited.length; i++) {
            System.out.print("节点" + i + "  ");
            int a = i;
            System.out.print("路径：");

            while (a != vs) {
                System.out.print(prev[a] + "  ");
                a = prev[a];
            }

            System.out.println("距离" + dist[i]);
        }

    }

}

