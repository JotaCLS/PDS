package lab03.Ex01;

public interface JGaloInterface {
	public abstract char getActualPlayer();
	public abstract boolean setJogada(int lin, int col);
	public abstract boolean isFinished();
	public abstract char checkResult();
}