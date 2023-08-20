
import java.util.Optional;

public class Visualiser
{
	Optional<Integer> optSum = Optional.ofNullable(null);
	Optional<Integer> optCnt = Optional.ofNullable(null);

	public void setCount(int cnt)
	{
		this.optCnt = Optional.of(cnt);
	}

	public void setSum(int sum)
	{
		this.optSum = Optional.of(sum);
	}

	public void show()
	{
		System.out.println("Sum = " + (optSum.isPresent() ? optSum.get() : "not available"));
		System.out.println("Cnt = " + (optCnt.isPresent() ? optCnt.get() : "not available"));
	}
}
