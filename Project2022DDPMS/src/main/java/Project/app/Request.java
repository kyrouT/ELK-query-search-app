package Project.app;

public class Request {
	public String indexName;
	public String indexValue;
	public int logNumber;

	public Request()
	{

	}

	public Request(String indexName, String indexValue, int logNumber)
	{
		this.indexName = indexName;
		this.indexValue = indexValue;
		this.logNumber = logNumber;
	}	


}