package chess;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SingleplayerChessTable extends ChessTable{
	private Executor pool = Executors.newFixedThreadPool(2); // 2个线程容量的线程池
	private RobotThread robotThread = new RobotThread(this, chessimpl); // 机器人线程
	private HumanThread humanThread = new HumanThread(this, chessimpl); // 人类线程

	public SingleplayerChessTable(Room room) {
		super(room);
	    chessimpl.ResetGame();
	    pool.execute(humanThread); // 开启人类线程
	    pool.execute(robotThread); // 开启机器人线程
	}
	
	public class MouseHandler extends MouseAdapter {
	    public void mousePressed(MouseEvent event) {
	
	      synchronized (chessTable) {
	        int x = event.getX();
	        int y = event.getY();
	
	        if (x > 30 && x < 535 && y > 30 && y < 535) {
	          humanX = (x - 21) / 34;
	          humanY = (y - 21) / 34;
	            if (paintItem(humanX, humanY)) {
	              room.backGame=true;
	              Moves++;
	              System.out.println("黑棋在这" + humanX + "," + humanY);
	              System.out.println("is here!");
	              mark[humanX][humanY] = 1;
	              lock = true;
	              repaint();
	              audioPlayer.run();
	              if(Moves==225)
	                room.drawGame();
	              else if(chessimpl.compare(humanX,humanY,2)){
	                room.win();
	              }else
	              chessTable.notifyAll();
	            } else {
	              audioStopPlayer.run();
	            }

	        } else {
	          System.out.println("请将棋子放进棋盘内");
	        }
	        System.out.println("x:" + x + "y:" + y);
	      }
	    }
	  }
	
	boolean paintItem(int i, int j) {
	    if (i < 15 && j < 15) {
	        if (!chessimpl.add(i, j, 2)) {
	          return false;// 棋子不能下在这个位置
	        }
	        return true;
	    } else {
	      System.out.println("棋子没添加成功");
	    }
	    return false;
	  }
	
	public void unpaintItem() {
	    chessimpl.delete(2);
	    repaint();
	  }
	
	  public synchronized void robotChess() {
	    System.out.println("机器线程开启");
	    synchronized (chessTable) {
	      while (true) {
	        if (!lock) {
	        	tryWaitForLock();
	        } else {
	          tryThreadSleep();
	          updateGameSituation();
	        }
	      }
	    }
	  }

	  private void tryWaitForLock() {
		  try {
	        wait();
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	  }

	  private void tryThreadSleep() {
		  try {
	        Thread.sleep(700);
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	  }
	  
	  private void updateGameSituation() {
		  int[] XY = chessimpl.ComTurn(humanX, humanY);
	      mark[XY[0]][XY[1]] = 1;
	      repaint();
	      Moves++;
	      lock = false;
	      audioPlayer.run();
	      checkIfGameEnds(XY);
	  }
	  
	  private void checkIfGameEnds(int[] XY) {
		  if(Moves==225)
	          room.drawGame();
	        else if(chessimpl.compare(XY[0],XY[1],1))
	          room.defeat();
	        else
	          chessTable.notifyAll();
	  }
	
}
