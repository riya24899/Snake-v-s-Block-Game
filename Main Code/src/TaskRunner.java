import javafx.animation.AnimationTimer;

public class TaskRunner implements Runnable {
	/**
	 * This class is used to simultaneously run the two animation timers that set up the frame for the gameplay. 
	 */

	AnimationTimer at;
	
	public TaskRunner(AnimationTimer timer) {
		this.at=timer;
	}

	public AnimationTimer getAnimationTimer() {
		return at;
	}

	public void setAt(AnimationTimer at) {
		this.at = at;
	}
	
	public void run() {
		at.start();
	}
	
	
}