

import java.util.List;

public class ResultCounter extends Colleague
{
	int cnt;
	
	public ResultCounter(Mediator m) {
		super(m);
	}

	public void count(List<Integer> values) {
		this.cnt = (int) values.stream().count();
		completed("count");
	}
	
	public int getCnt() {
		return cnt;
	}
}
